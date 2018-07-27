package com.quantchi.lineage.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LineageMapper {

    List<Map<String, Object>> getlineageInfo(@Param("metricId") String metricId);
}
