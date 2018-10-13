package com.quantchi.metadatamgr.extract;

import com.quantchi.common.SQLQueryConfig;
import com.quantchi.intelquery.search.SearchEng;
import com.quantchi.intelquery.search.SolrEng;
import com.quantchi.lineage.exception.SqlParserException;
import com.quantchi.lineage.metric.MetricLineage;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ManualTargetMappingExtractor extends ManualFileExtractor {

	private static final Logger logger = LoggerFactory.getLogger(ManualTargetMappingExtractor.class);

	private String type = null;

	protected WebApplicationContext webApplication = null;
	private SQLQueryConfig sqlQueryConfig = null;
	private TermFileService termFileService = null;

	private static final String SEARCHTYPE = "solr";
	public ManualTargetMappingExtractor(String type){
		this.type = type;
		webApplication = ContextLoader.getCurrentWebApplicationContext();
		termFileService = (TermFileService) webApplication.getBean("termFileServiceImpl");
		sqlQueryConfig = (SQLQueryConfig) webApplication.getBean("SQLQueryConfig");
	}

	@Override
	public boolean extract() {
		boolean ret = false;
		try{
			String mapping = sqlQueryConfig.getSEL_DB_TARGET_MAPPING();
			List<List<String>> content = getContent();
			List<String> titleList = getTitleList(mapping);
			for (int i = 1; i < content.size(); i++) {
				List<String> values = content.get(i);
				Map<String, Object> map1 = new HashMap<>();
				for (int j = 0; j < titleList.size(); j++) {
					map1.put(titleList.get(j), values.get(j));
				}
				List<Map<String, Object>> PhysicalInfo = termFileService.selectPhysicalInfo(map1);
				if (PhysicalInfo != null && !PhysicalInfo.isEmpty()) {
					map1.put("fieldId", PhysicalInfo.get(0).get("entity_id"));
				} else {
					map1.put("fieldId", null);
				}
				List<Map<String, Object>> PhysicalFile = termFileService.selectPhysicalFile(map1);
				if (PhysicalFile == null || PhysicalFile.isEmpty()) {
					termFileService.insertPhysicalFile(map1);
				}
				List<Map<String, Object>> list1 = termFileService.selectTargetMain(map1);
				if (list1 != null && list1.size() > 0) {
					//force update physical_field_desc
					Map<String, Object> updateMap = new HashMap<>(3);
					updateMap.put("fieldId", map1.get("fieldId"));
					updateMap.put("entityDesc", list1.get(0).get("entity_desc"));
					termFileService.updatePhysicalFieldChineseName(updateMap);
				}

				if (list1 != null && !list1.isEmpty() && list1.get(0).get("technique_rule") != null
						&& list1.get(0).get("technique_rule").toString().length() > 0) {

					try {
						MetricLineage.loadLineage(list1.get(0).get("entity_id").toString(),
								list1.get(0).get("technique_rule").toString());
					} catch (SqlParserException e) {
						logger.info("entity_id:" + list1.get(0).get("entity_id").toString());
						logger.info("sql:" + list1.get(0).get("technique_rule").toString());
						//e.printStackTrace();
					} catch (Exception e) {
						logger.info("entity_id:" + list1.get(0).get("entity_id").toString());
						logger.info("sql:" + list1.get(0).get("technique_rule").toString(), e);
						//e.printStackTrace();
					}
				}
			}

			//add reIndex
			SearchEng engObj = SearchEng.instanceOf("", SEARCHTYPE);
			((SolrEng) engObj).metricsImport();
			ret = true;
		}catch (Exception e){
			e.printStackTrace();
			logger.info("error",e);
		}
		return ret;
	}
}
