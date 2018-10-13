package com.quantchi.metadatamgr.extract;

import com.quantchi.common.SQLQueryConfig;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ManualStandardExtractor extends ManualFileExtractor {

	private static final Logger logger = LoggerFactory.getLogger(ManualStandardExtractor.class);

	private String type = null;

	protected WebApplicationContext webApplication = null;
	private SQLQueryConfig sqlQueryConfig = null;
	private TermFileService termFileService = null;

	public ManualStandardExtractor(String type){
		this.type = type;
		webApplication = ContextLoader.getCurrentWebApplicationContext();
		termFileService = (TermFileService) webApplication.getBean("termFileServiceImpl");
		sqlQueryConfig = (SQLQueryConfig) webApplication.getBean("SQLQueryConfig");
	}

	@Override
	public boolean extract() {
		boolean ret = false;
		try{
			String mapping = sqlQueryConfig.getSEL_DB_STANDARD_COMMON();
			List<List<String>> content = getContent();
			List<String> titleList = getTitleList(mapping);
			for (int i = 1; i < content.size(); i++) {
				List<String> values = content.get(i);
				Map<String, Object> map1 = new HashMap<>();
				for (int j = 0; j < values.size(); j++) {
					map1.put(titleList.get(j), values.get(j));
				}
				Map<String, Object> entityCategory = termFileService.selectStandardCategory(map1);
				if (entityCategory == null || entityCategory.isEmpty()) {
					map1.put("entityCategory", null);
				} else {
					map1.put("entityCategory", entityCategory.get("cid"));
				}
				List<Map<String, Object>> standardMain = termFileService.selectStandardMain(map1);
				if (Objects.equals(map1.get("dataPrecision"), "")) {
					map1.put("dataPrecision", null);
				}
				if (Objects.equals(map1.get("dataLength"), "")) {
					map1.put("dataLength", null);
				}
				if (standardMain == null || standardMain.isEmpty()) {
					termFileService.insertStandardMain(map1);
				} else {
					termFileService.updateStandardMain(map1);
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
