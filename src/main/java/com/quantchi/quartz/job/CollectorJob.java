package com.quantchi.quartz.job;

import com.quantchi.common.Util;
import com.quantchi.intelquery.date.formatter.DateFormatter;
import com.quantchi.lineage.diff.Field;
import com.quantchi.lineage.diff.SchemaDiff;
import com.quantchi.lineage.diff.Table;
import com.quantchi.lineage.diff.TableDiff;
import com.quantchi.metadatamgr.data.entity.MDSchemaDiff;
import com.quantchi.metadatamgr.data.mapper.MDFieldInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.MDSchemaDiffMapper;
import com.quantchi.metadatamgr.data.mapper.MDTableInfoDBMapper;
import com.quantchi.quartz.entity.JobInfo;
import com.quantchi.quartz.entity.MDExtractorJobHis;
import com.quantchi.quartz.exception.JobNotExistsException;
import com.quantchi.quartz.service.JobExtractHisService;
import com.quantchi.quartz.util.JobConstant;
import com.quantchi.tianshu.common.SerializationUtils;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.*;

public class CollectorJob extends BaseJob  {

	private static final Logger logger = LoggerFactory.getLogger(CollectorJob.class);
	//private static final Logger logger = LoggerFactory.getLogger("influxdb");

	protected WebApplicationContext webApplication = null;
	private JobExtractHisService jobExtractHisService = null;
	private MDSchemaDiffMapper mdSchemaDiffMapper;
	private MDTableInfoDBMapper mdTableInfoDBMapper;
	private MDFieldInfoDBMapper mdFieldInfoDBMapper;
	private JobInfo jobInfo = null;
	private String datasourceId = "";

	public CollectorJob(){
		webApplication = ContextLoader.getCurrentWebApplicationContext();
		jobExtractHisService = (JobExtractHisService) webApplication.getBean("jobExtractHisServiceImpl");
		mdSchemaDiffMapper = (MDSchemaDiffMapper) webApplication.getBean("MDSchemaDiffMapper");
		mdTableInfoDBMapper = (MDTableInfoDBMapper) webApplication.getBean("MDTableInfoDBMapper");
		mdFieldInfoDBMapper = (MDFieldInfoDBMapper) webApplication.getBean("MDFieldInfoDBMapper");
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	//run common part
	//3.call diff
	//4.save diff
	//5.apply diff
	//6.throw diff result to message queue for notification

	protected void runCollectorTask(JobInfo jobInfo) throws Exception {}

	protected void runTasks(JobInfo jobInfo) {

		this.jobInfo = jobInfo;
		if(jobInfo.getJobParams().get("datasourceId") != null){
			this.datasourceId = jobInfo.getJobParams().get("datasourceId").toString();
		}
		MDExtractorJobHis jobHis = null;
		try{
			//modify job status to doing
			jobHis = jobExtractHisService.createJobHisRecord(jobInfo);
			if(jobHis == null)
				throw new JobNotExistsException("can not create job his");
			updateJobHisStartStatus(jobHis);
			logger.info("change  job hist to start status ");
			runCollectorTask(jobInfo);

			//print start job time
			updateJobHisFinishStatus(jobHis);
		}catch (JobNotExistsException e){
			logger.error("excute job {} error {}",jobInfo.getName(),e.getMessage());
		} catch (Exception e){
			updateJobHisFailedStatus(jobHis);
			logger.error("excute job {} error {}",jobInfo.getName(),e.getMessage());
		}


	}

	protected Object getdiffBetweenExtractAndLatest(Set<Table> prev, Set<Table> post){
		SchemaDiff diff = SchemaDiff.findDiff(prev,post);
		return diff;
	}

	protected int saveDiffInDB(Object ret,Set<Table> latest) throws IOException {
		String serial = SerializationUtils.toSerializedString((SchemaDiff) ret);
		int tableSize = latest.size();
		int fieldSize = getFieldSizeByTable(latest);
		String version = "V" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Integer count = mdSchemaDiffMapper.selectByDatasourceDate(datasourceId, version).get(0) + 1;

		MDSchemaDiff  diff = new MDSchemaDiff();
		diff.setDatasourceId(datasourceId);
		diff.setVersion(version + "-" + String.format("%03d", count));
		diff.setSubmitter(jobInfo.getName()+"_autoTask");
		diff.setDiff(serial);
		diff.setDate(new Date());
		diff.setFieldAmount(fieldSize);
		diff.setTableAmount(tableSize);
		diff.setAdditions((short)((SchemaDiff) ret).getAdditions().size());
		diff.setDeletions((short)((SchemaDiff) ret).getDeletions().size());
		diff.setChanges((short)((SchemaDiff) ret).getChanges().size());
		return mdSchemaDiffMapper.insertSelective(diff);

	}

	protected int applyChangeToLatest(Object diff){
		SchemaDiff changes = (SchemaDiff) diff;

		tablesAdd(changes.getAdditions());
		tablesDelete(changes.getDeletions());
		tableChanges(changes.getChanges());

		return changes.getAdditions().size()+changes.getDeletions().size()+changes.getChanges().size();
	}

	protected void tablesAdd(Set<Table> tblAdd){
		//Map<String,String> tableIdMap = new HashMap<>();
		tblAdd.forEach(i -> {
			Map<String,Object> table = new HashMap<>();
			String[] dbinfo = i.getTableName().split("\\.");
			if(dbinfo.length > 0)
			{
				table.put("physical_db",dbinfo[0]);
				table.put("physical_table",dbinfo[1]);
				table.put("datasourceId",this.datasourceId);
				mdTableInfoDBMapper.insertTable(table);
				//tableIdMap.put(i.getTableName(),table.get("id").toString());
			}

		});
		//return tableIdMap;
	}

	protected void tablesDelete(Set<Table> tblDel){
		tblDel.forEach(i -> {
			Map<String,Object> table = new HashMap<>();
			String[] dbinfo = i.getTableName().split("\\.");
			if(dbinfo.length > 0) {
				table.put("physical_db",dbinfo[0]);
				table.put("physical_table",dbinfo[1]);
				table.put("datasourceId",this.datasourceId);
				mdTableInfoDBMapper.deleteTable(table);
			}

		});
	}

	protected void tableChanges(List<TableDiff> diffs){
		diffs.forEach(i->{
			fieldsAdd(i.getName(),i.getAdditions());
			fieldsDelete(i.getName(),i.getDeletions());
			fieldsChanges(i.getName(),i.getChanges());
		});
	}

	protected void fieldsAdd(String databaseAndTable,Set<Field> flsAdd){
		flsAdd.forEach(i->{
			Entry<String,String> dbAndtbl = getDataBaseAndTable(databaseAndTable);
			if(dbAndtbl != null){
				Map<String, Object> field = getField(databaseAndTable,i);
				if(field != null)
					mdFieldInfoDBMapper.insertField(field);
			}
		});

	}
	protected void fieldsDelete(String databaseAndTable,Set<Field> flsDel){
		flsDel.forEach(i->{
			Entry<String,String> dbAndtbl = getDataBaseAndTable(databaseAndTable);
			if(dbAndtbl != null){
				Map<String, Object> field = getField(databaseAndTable,i);
				if(field != null)
					mdFieldInfoDBMapper.deleteField(field);
			}
		});

	}
	protected void fieldsChanges(String databaseAndTable,List<TableDiff.FieldDiff> fieldsDiff){
		fieldsDiff.forEach(i->{
			Field fieldBefore =  i.getBefore();
			Map<String, Object> field = getField(databaseAndTable,fieldBefore);
			//remove data type
			field.remove("data_type");
			List<Map<String, Object>> fields =  mdFieldInfoDBMapper.searchField(field);
			if(fields.size() > 0){
				Field fieldAfter =  i.getAfter();

				//only field type can update
				fields.get(0).put("data_type",fieldAfter.getDataType());
				mdFieldInfoDBMapper.updateField(field);
			}
		});
	}

	private Map<String, Object> getField(String databaseAndTable,Field fl){
		Map<String, Object> field = null;
		Entry<String,String> dbAndtbl = getDataBaseAndTable(databaseAndTable);
		if(dbAndtbl != null) {
			field = new HashMap<>();
			String tableId = getTableId(dbAndtbl);
			field.put("physical_field", fl.getFieldName());
			field.put("physical_table_id", tableId);
			field.put("physical_db", dbAndtbl.getKey());
			field.put("physical_table", dbAndtbl.getValue());
			field.put("data_type", fl.getDataType());
		}
		return field;
	}

	private Entry<String,String> getDataBaseAndTable(String name){
		String[] dbinfo = name.split("\\.");
		if(dbinfo.length > 0){
			Entry<String,String> pair = new SimpleEntry<>(dbinfo[0],dbinfo[1]);
			return pair;
		}else
			return null;
	}

	private String getTableId(Entry<String,String> dbAndtbl){
		Map<String,Object> table = new HashMap<>();
		String tableId = "";
		table.put("datasourceId",datasourceId);
		table.put("physical_db",dbAndtbl.getKey());
		table.put("physical_table",dbAndtbl.getValue());
		List<Map<String,Object>> tableInfo = mdTableInfoDBMapper.selectTableInfo(table);
		if(tableInfo.size() > 0)
			tableId = tableInfo.get(0).get("id").toString();
		return tableId;
	}

	private int getFieldSizeByTable(Set<Table> tables){
		int count = 0;
		for(Table t : tables){
			count+= t.getFields().size();
		}
		return count;
	}

	private int updateJobHisStartStatus(MDExtractorJobHis jobHis){
		jobHis.setStatus(JobConstant.JOB_COLLECTOR_DOING);
		return updateJobHisStatus(jobHis);
	}

	private int updateJobHisFinishStatus(MDExtractorJobHis jobHis){
		jobHis.setStatus(JobConstant.JOB_COLLECTOR_DONE);
		jobHis.setFinishtime(Util.dateToString(new Date()));
		return updateJobHisStatus(jobHis);
	}

	private int updateJobHisFailedStatus(MDExtractorJobHis jobHis){
		jobHis.setStatus(JobConstant.JOB_COLLECTOR_FAILED);
		jobHis.setFinishtime(Util.dateToString(new Date()));
		return updateJobHisStatus(jobHis);
	}

	private int updateJobHisStatus(MDExtractorJobHis jobHis){
		return jobExtractHisService.updateJobHisStatus(jobHis);
	}

}
