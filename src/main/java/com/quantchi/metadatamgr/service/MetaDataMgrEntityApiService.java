package com.quantchi.metadatamgr.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDB;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDBExample;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDB;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSEntityInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSMetaInfoDBMapper;
import com.quantchi.termInfo.mapper.PhysicalTableInfoMapper;
import com.quantchi.termInfo.pojo.PhysicalTableInfo;
import com.quantchi.termInfo.pojo.PhysicalTableInfoExample;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaDataMgrEntityApiService {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrEntityApiService.class);

  @Autowired
  private DSEntityInfoDBMapper dsEntityInfoDBMapper;

  @Autowired
  private DSMetaInfoDBMapper dsMetaInfoDBMapper;

  @Autowired
  private PhysicalTableInfoMapper tableInfoMapper;

  public int createEntity(JSONObject json) {
    DSEntityInfoDB dsEntityInfoDB = new DSEntityInfoDB();
    dsEntityInfoDB.setEntityName(json.getString("entity_name"));
    dsEntityInfoDB.setBusiness(json.getString("business"));
    String dsName = json.getString("data_source_name");
    String dsId = json.getString("data_source_id");
    if (dsName != null && !dsName.equals("")) {
      DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
      dsMetaInfoDBExample.createCriteria().andDsNameEqualTo(dsName);
      List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper
          .selectAllByExample(dsMetaInfoDBExample);
      dsId = dsMetaInfoDBList.get(0).getId().toString();
    }
    dsEntityInfoDB.setDatasourceId(dsId);
    dsEntityInfoDB.setMainTable(json.getString("main_table_id"));
    dsEntityInfoDB.setEntityField(json.getString("main_entity_field_id"));
    dsEntityInfoDB.setMainEntityFieldName(json.getString("main_entity_field_name"));
    String nonMainTable = "";
    if (json.get("non_main_table_id") != null) {
      nonMainTable = String.join(",", (List) json.get("non_main_table_id"));
    }
    dsEntityInfoDB.setNonMainTable(nonMainTable);
    insertDomain(dsEntityInfoDB);
    return dsEntityInfoDBMapper.insert(dsEntityInfoDB);
  }

  private void insertDomain(DSEntityInfoDB info) {
    Map<String, Object> row = new HashMap<>();
    row.put("domainId", info.getEntityName());
    row.put("domainName", info.getEntityName());
    row.put("businessTypeId", info.getBusiness());
    row.put("businessTypeName", info.getBusiness());
    List<String> tables = new ArrayList<>();
    tables.add(info.getMainTable());
    tables.addAll(Arrays.asList(info.getNonMainTable().split(",")));
    for (int i = 0; i < tables.size(); i++) {
      String dbTable = tables.get(i);
      row.put("tableId", dbTable);
      String db = info.getMainTable().substring(0, dbTable.indexOf('.'));
      String table = info.getMainTable().substring(dbTable.indexOf('.') + 1);
      if (i == 0) {
        row.put("isMain", Boolean.TRUE);
        row.put("nameField", info.getMainEntityFieldName());
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

  public int modifyEntity(JSONObject json) {
    DSEntityInfoDB dsEntityInfoDB = new DSEntityInfoDB();
    dsEntityInfoDB.setId(Integer.parseInt(json.getString("entity_id")));
    dsEntityInfoDB.setEntityName(json.getString("entity_name"));
    dsEntityInfoDB.setBusiness(json.getString("business"));

    String dsName = json.getString("data_source_name");
    String dsId = json.getString("data_source_id");
    if (dsName != null && !dsName.equals("")) {
      DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
      dsMetaInfoDBExample.createCriteria().andDsNameEqualTo(dsName);
      List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper
          .selectAllByExample(dsMetaInfoDBExample);
      dsId = dsMetaInfoDBList.get(0).getId().toString();
    }
    dsEntityInfoDB.setDatasourceId(dsId);
    dsEntityInfoDB.setMainTable(json.getString("main_table_id"));
    dsEntityInfoDB.setEntityField(json.getString("main_entity_field_id"));
    String nonMainTable = String.join(",", (List) json.get("non_main_table_id"));
    dsEntityInfoDB.setNonMainTable(nonMainTable);
    dsEntityInfoDB.setMainEntityFieldName("main_entity_field_name");
    return dsEntityInfoDBMapper.updateByPrimaryKeySelective(dsEntityInfoDB);
  }

  public Map<String, Object> searchEntity(JSONObject json) {
    Map<String, Object> responseMap = new HashMap<>();
    DSEntityInfoDBExample dsEntityInfoDBExample = new DSEntityInfoDBExample();
    DSEntityInfoDBExample.Criteria _cr = dsEntityInfoDBExample.createCriteria();
    if (json.getString("business") != null && !json.getString("business").equals("")) {
      _cr.andBusinessEqualTo(json.getString("business"));
    }
    if (json.getString("keywords") != null && !json.getString("keywords").equals("")) {
      _cr.andEntityNameLike("%" + json.getString("keywords") + "%");
    }
    int page = Integer.parseInt(json.getString("page"));
    int page_size = Integer.parseInt(json.getString("page_size"));
    PageHelper.startPage(page, page_size);
    List<DSEntityInfoDB> list = dsEntityInfoDBMapper.selectByExample(dsEntityInfoDBExample);

    PageInfo<DSEntityInfoDB> pageInfo = new PageInfo<>(list);
    List<Map<String, Object>> responseList = new ArrayList<>();
    List<DSEntityInfoDB> dsList = pageInfo.getList();
    for (DSEntityInfoDB dsEntityInfoDB : dsList) {
      Map<String, Object> map = new HashMap<>();
      map.put("id", dsEntityInfoDB.getId());
      map.put("entity_name", dsEntityInfoDB.getEntityName());
      map.put("business", dsEntityInfoDB.getBusiness());

      String dsId = dsEntityInfoDB.getDatasourceId();
      if (dsId.matches("^[0-9]*$")) {
        DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
        dsMetaInfoDBExample.createCriteria().andIdEqualTo(Integer.parseInt(dsId));
        List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper
            .selectAllByExample(dsMetaInfoDBExample);
        map.put("data_source_name", dsMetaInfoDBList.get(0).getDsName());
      } else {
        map.put("data_source_name", dsId);
      }
      map.put("main_table_id", dsEntityInfoDB.getMainTable());
      map.put("main_entity_field_id", dsEntityInfoDB.getEntityField());
      map.put("non_main_table_id", dsEntityInfoDB.getNonMainTable());
      map.put("main_entity_field_name", dsEntityInfoDB.getMainEntityFieldName());
      responseList.add(map);
    }
    responseMap.put("data", responseList);
    responseMap.put("total", pageInfo.getTotal());
    return responseMap;
  }

  public int deleteEntity(String id) {
    return dsEntityInfoDBMapper.deleteByPrimaryKey(Integer.parseInt(id));
  }
}
