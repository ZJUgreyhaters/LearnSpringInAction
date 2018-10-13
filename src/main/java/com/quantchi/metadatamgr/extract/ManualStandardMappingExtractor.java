package com.quantchi.metadatamgr.extract;

import com.quantchi.common.SQLQueryConfig;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ManualStandardMappingExtractor extends ManualFileExtractor {

	private static final Logger logger = LoggerFactory.getLogger(ManualStandardMappingExtractor.class);

	private String type = null;

	protected WebApplicationContext webApplication = null;
	private SQLQueryConfig sqlQueryConfig = null;
	private TermFileService termFileService = null;

	public ManualStandardMappingExtractor(String type){
		this.type = type;
		webApplication = ContextLoader.getCurrentWebApplicationContext();
		termFileService = (TermFileService) webApplication.getBean("termFileServiceImpl");
		sqlQueryConfig = (SQLQueryConfig) webApplication.getBean("SQLQueryConfig");
	}

	@Override
	public boolean extract() {
		boolean ret = false;
		try{
			String mapping = sqlQueryConfig.getSEL_DB_STANDARD_MAPPING();
			List<List<String>> content = getContent();
			List<String> titleList = getTitleList(mapping);
			for (int i = 1; i < content.size(); i++) {
				List<String> values = content.get(i);
				Map<String, Object> map1 = new HashMap<>();
				for (int j = 0; j < values.size(); j++) {
					map1.put(titleList.get(j), values.get(j));
				}
				List<Map<String, Object>> resultList = termFileService.selectStandard(map1);
				if (resultList == null || resultList.isEmpty()) {
					termFileService.insertStandard(map1);
				} else {
					termFileService.updateStandard(map1);
				}
			}
			ret = true;
		}catch (Exception e){
			e.printStackTrace();
			logger.info("error",e);
		}
		return ret;
	}
}
