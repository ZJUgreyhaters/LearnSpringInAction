package com.quantchi.metadatamgr.extract;

import com.quantchi.common.AppProperties;
import com.quantchi.quartz.job.CollectorJob;
import com.quantchi.quartz.mapper.MDExtractorJobManualMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManualUpdateExtractor extends ManualFileExtractor {

	private static final Logger logger = LoggerFactory.getLogger(ManualUpdateExtractor.class);

	private String type = null;

	private MDExtractorJobManualMapper mdExtractorJobManualMapper = null;
	protected WebApplicationContext webApplication = null;

	public ManualUpdateExtractor(String type){
		this.type = type;
		webApplication = ContextLoader.getCurrentWebApplicationContext();
		mdExtractorJobManualMapper = (MDExtractorJobManualMapper) webApplication.getBean("MDExtractorJobManualMapper");
	}

	@Override
	public boolean extract() {
		boolean ret = false;
		String sql = AppProperties.get("manual.mapping.sql."+type);
		String key = AppProperties.get("manual.mapping.sql."+type+".keyfield");
		if(sql != null){
			Map<String,String> sqlParam = new HashMap<>();
			sqlParam.put("sql",sql);
			List<List<String>> lines = getContent();
			List<String> header = lines.get(0);
			for(int i=1;i< lines.size();i++ ){
				List<String> item = lines.get(i);
				item.forEach((val)->sqlParam.put(header.get(item.indexOf(val)),val));
				if(mdExtractorJobManualMapper.executeUptSql(sqlParam) > 0)
					logger.info("update {} success",sqlParam.get(key));
			}
			ret = true;
		}

		return ret;
	}
}
