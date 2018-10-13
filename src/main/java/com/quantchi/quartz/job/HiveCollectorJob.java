package com.quantchi.quartz.job;


import com.quantchi.lineage.diff.Table;
import com.quantchi.metadatamgr.service.MetaDataDiffService;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
import com.quantchi.metadatamgr.service.MetaDataMgrTableApiService;
import com.quantchi.quartz.entity.JobInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class HiveCollectorJob extends CollectorJob{

	private static final Logger logger = LoggerFactory.getLogger(HiveCollectorJob.class);
	private MetaDataMgrTableApiService metaDataMgrTableApiService = null;
	private MetaDataMgrApiService metaDataMgrApiService = null;
	private MetaDataDiffService metaDataDiffService = null;

	public HiveCollectorJob(){
		super();
		metaDataMgrTableApiService = (MetaDataMgrTableApiService) webApplication.getBean("metaDataMgrTableApiService");
		metaDataMgrApiService = (MetaDataMgrApiService) webApplication.getBean("metaDataMgrApiService");
		metaDataDiffService = (MetaDataDiffService) webApplication.getBean("metaDataDiffService");
	}

	/*@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


		//1.get select tables info from ds_table_info
		//2.call extra func

		/// common function for all of Collectors
		//3.call diff
		//4.save diff
		//5.apply diff
		//6.throw diff result to message queue for notification

	}*/

	@Override
	public void runCollectorTask(JobInfo jobInfo) throws Exception {
		logger.info(jobInfo.getName() + " job call");

		//1.get select tables info from ds_table_info
		List<Map<String, Object>> tables = getExtractTables(jobInfo.getJobParams());
		if(tables == null || tables.size() == 0)
			logger.info(jobInfo.getName() + " no table need to extract ");
		else{
			logger.info("ready to extract table size is ["+tables.size()+"]");
			//2.call extra func
			String datasourceId = jobInfo.getJobParams().get("datasourceId").toString();
			Set<Table> extractTables =  metaDataMgrApiService.extractFieldsByTables(datasourceId,tables);

			logger.info("load latest table info in db....");
			//get latest field and table info from db
			Set<Table> latestTables = metaDataDiffService.getTableFromRow(datasourceId,metaDataMgrApiService.getLatestTableAndFieldsByDsId(jobInfo.getJobParams()));
			logger.info("table info in db size is:"+latestTables.size());

			//3.call diff
			logger.info("analyze diff...");
			Object ret = getdiffBetweenExtractAndLatest(latestTables,extractTables);



			//4.apply diff
			logger.info("apply diff...");
			if(applyChangeToLatest(ret) == 0){
				logger.info(jobInfo.getName() + " no diff");
			}else {
				//5.save diff
				logger.info("save diff...");
				if(saveDiffInDB(ret,extractTables) == 0){
					logger.info(jobInfo.getName() + " insert diff happen error");
				}
			}

		}


	}

	private List<Map<String, Object>> getExtractTables(Map<String,Object> jobParam){
		if(jobParam.get("datasourceId") == null)
			return null;

		return metaDataMgrTableApiService.searchTable(jobParam);
	}

}
