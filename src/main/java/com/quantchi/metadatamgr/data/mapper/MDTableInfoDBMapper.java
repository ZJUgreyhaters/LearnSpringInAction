package com.quantchi.metadatamgr.data.mapper;

import java.util.List;
import java.util.Map;

public interface MDTableInfoDBMapper {

  List<Map<String, Object>> searchTable(Map<String, Object> map);

  void deleteTable(Map<String, Object> map);

  void updateTable(Map<String, Object> map);

  void insertTable(Map<String, Object> map);

  Map<String, Object> selectFields(Map<String, Object> map);

  Map<String, Object> foreignNums(Map<String, Object> map);

  List<Map<String, Object>> selectTableInfo(Map<String, Object> map);
}