package com.quantchi.quartz.mapper;


import com.quantchi.quartz.entity.MDExtractorJob;

import java.util.List;
import java.util.Map;

public interface MDExtractorJobManualMapper<MDExtractorJobManual> extends JobBaseMapper {
	int executeUptSql(Map<String,String> map);
}