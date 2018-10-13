package com.quantchi.metadatamgr.data.mapper;

import java.util.List;
import java.util.Map;

public interface MDFieldInfoDBMapper {

  List<Map<String, Object>> searchField(Map<String, Object> map);

  void insertField (Map<String, Object> map);

  void updateField(Map<String, Object> map);

  void deleteField(Map<String, Object> map);

  List<Map<String, Object>> selectFieldInfo(Map<String, Object> map);

  List<String> selectEntityIdById(String fieldId);

  List<String> selectIdByEntityId(String fieldId);

  List<String> selectIdByName(String datasourceId, String dbName,
      String tableName, String fieldName);

  List<String> selectEntityIdByName(String datasourceId, String dbName,
      String tableName, String fieldName);

  List<Map<String, String>> getLatestFieldInfo(Map<String, Object> map);
}