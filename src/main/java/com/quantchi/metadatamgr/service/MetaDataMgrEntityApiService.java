package com.quantchi.metadatamgr.service;

import com.quantchi.common.Paging;
import com.quantchi.metadatamgr.data.mapper.DSEntityInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import com.quantchi.termInfo.mapper.PhysicalTableInfoMapper;
import com.quantchi.termInfo.pojo.PhysicalTableInfo;
import com.quantchi.termInfo.pojo.PhysicalTableInfoExample;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataMgrEntityApiService {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrEntityApiService.class);

  @Autowired
  private DSEntityInfoDBMapper dsEntityInfoDBMapper;

  @Autowired
  private DSTableInfoDBMapper dsTableInfoDBMapper;

  @Autowired
  private PhysicalTableInfoMapper tableInfoMapper;

  public int createEntity(Map<String, String> map) {
    List<Map<String, Object>> entityNameList = dsEntityInfoDBMapper.getEntityName(map);
    if (entityNameList != null && !entityNameList.isEmpty()) {
      return -1;
    }
    insertDomain(map);
    return dsEntityInfoDBMapper.insert(map);
  }

  private void insertDomain(Map<String, String> map) {
    Map<String, Object> row = new HashMap<>();
    row.put("domainId", map.get("entity_name"));
    row.put("domainName", map.get("entity_name"));
    row.put("businessTypeId", map.get("business"));
    row.put("businessTypeName", map.get("business"));
    List<String> tables = new ArrayList<>();
    List<Map<String, Object>> list = dsTableInfoDBMapper.selectTableName(map);
    tables.add(list.get(0).get("name").toString());
    tables.addAll(Arrays.asList(map.get("non_main_table_name").split(",")));
    for (int i = 0; i < tables.size(); i++) {
      String dbTable = tables.get(i);
      row.put("tableId", dbTable);
      String db = dbTable.substring(0, dbTable.indexOf('.'));
      String table = dbTable.substring(dbTable.indexOf('.') + 1);
      if (i == 0) {
        row.put("isMain", Boolean.TRUE);
        row.put("nameField", map.get("main_entity_field_name_id"));
      } else {
        row.put("isMain", Boolean.FALSE);
        row.put("nameField", null);
      }
      PhysicalTableInfoExample example = new PhysicalTableInfoExample();
      example.createCriteria().andPhysicalDbEqualTo(db).andPhysicalTableEqualTo(table);
      List<PhysicalTableInfo> physicalTables = tableInfoMapper.selectByExample(example);
      if (physicalTables.isEmpty()) {
        logger.warn("Failed to get physical table: " + dbTable);
      } else {
        row.put("tableName", physicalTables.get(0).getTableName());
        dsEntityInfoDBMapper.insertDomain(row);
      }
    }
  }

  public int modifyEntity(Map<String, String> map) {
    List<Map<String, Object>> entityNameList = dsEntityInfoDBMapper.getEntityName(map);
    if (entityNameList != null && !entityNameList.isEmpty()) {
      return -1;
    }
    return dsEntityInfoDBMapper.updateByPrimaryKeySelective(map);
  }

  public Map<String, Object> searchEntity(Map<String, String> map) {
    Map<String, Object> responseMap = new HashMap<>();

    List<Map<String, Object>> list = dsEntityInfoDBMapper.selectEntityInfo(map);
    int total = list.size();
    if (map.get("page") != null && map.get("page_size") != null) {
      int page = Integer.parseInt(map.get("page"));
      int page_size = Integer.parseInt(map.get("page_size"));
      list = Paging.pagingPlug(list,page_size,page);
    }
    responseMap.put("data", list);
    responseMap.put("total", total);
    return responseMap;
  }

  public int deleteEntity(String id) {
    return dsEntityInfoDBMapper.deleteByPrimaryKey(Integer.parseInt(id));
  }
}
