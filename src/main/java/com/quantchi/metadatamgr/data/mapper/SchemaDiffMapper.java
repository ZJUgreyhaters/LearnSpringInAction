package com.quantchi.metadatamgr.data.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author hetong.
 */
public interface SchemaDiffMapper {
  int insertDiffJob(Map<String, Object> map);
  int deleteJob(String jobId);
  int updateDiff(Map<String, Object> map);
  List<Map<String, Object>> getAllVersion(Map<String, Object> map);
  List<Map<String, Object>> getAllJob(Map<String, Object> map);
  List<Map<String, Object>> getVersionDiff(String datasourceId, String sourceVersion, String targetVersion);
  List<Map<String, String>> getTargetDatasource(String jobId);
  List<Map<String, String>> getSourceDatasource(String jobId);
}
