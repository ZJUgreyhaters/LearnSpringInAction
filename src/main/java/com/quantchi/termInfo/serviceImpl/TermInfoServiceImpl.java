package com.quantchi.termInfo.serviceImpl;

import com.quantchi.common.JsonResult;
import com.quantchi.termInfo.mapper.TermInfoMapper;
import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/6/20.
 */
@Service
public class TermInfoServiceImpl implements TermInfoService {

  @Autowired
  TermInfoMapper mapper;

  private List<String> name = Arrays
      .asList("entityType", "entityId", "entityHash", "entityName", "entityDesc", "logicType",
          "displayType");
  private List<String> physicalField = Arrays
      .asList("entityId", "physicalFieldHash", "physicalFieldId", "physicalFieldDesc",
          "physicalTable", "physicalDB", "dataType", "dataLength", "dataPrecision", "dataPattern",
          "dataUnit", "fieldPartition");

  private List<String> entity = Arrays
      .asList("entityType", "entityId", "entityHash", "entityName", "entityDesc", "entityAlias",
          "businessRule", "techniqueRule", "entityStatus", "createTime", "offlineTime", "creator",
          "controlDept", "assistDept", "devPolicy", "regulatory", "logicType", "displayType");
  private List<String> physicalFieldStatistics = Arrays
      .asList("physicalFieldId", "entityId", "physicalTable", "physicalDB", "dataMax", "dataAvg",
          "dataMin", "dataDistribution", "dataEnumeration", "dataNull");
  private List<String> physicalTable = Arrays
      .asList("entityId", "physicalTableHash", "physicalFieldId", "physicalTable", "physicalDB",
          "tableType",
          "tableName", "tableDesc", "lastModifiedTime", "primaryKey", "foreignKey",
          "tablePartition");
  private List<String> externalInfo = Arrays
      .asList("levelName", "entityId", "themeName", "category1", "category2", "suitableType",
          "category3",
          "suitableCondition");

  @Override
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    try {
      List<Map<String, Object>> list = mapper.selectTermAll(termInfoPojo);
      List<Map<String, Object>> ResultList = new ArrayList<>();
      for (Map<String, Object> map : list) {
        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> physical = new LinkedHashMap<>();
        for (String str : name) {
          map1.put(str, map.get(str));
        }
        for (String str : physicalField) {
          physical.put(str, map.get(str));
        }
        List<Map<String, Object>> list1 = mapper.selectUdc(map.get("logicType").toString());
        physical.put("dataUDC", list1);
        map1.put("physicalField", physical);
        ResultList.add(map1);
      }
      Map<String, Object> result = new HashedMap();
      result.put("entitys", ResultList);
      return JsonResult.successJson(result);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("search tableInfo error");
    }
  }

  @Override
  public String selectTerm(TermInfoPojo termInfoPojo) {
    try {
      List<Map<String, Object>> list = mapper.selectTerm(termInfoPojo);
      List<Map<String, Object>> ResultList = new ArrayList<>();
      for (Map<String, Object> map : list) {
        Map<String, Object> entitys = new LinkedHashMap<>();
        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> physicalFieldStatistic = new LinkedHashMap<>();
        Map<String, Object> physicalTables = new LinkedHashMap<>();
        Map<String, Object> externalInfos = new LinkedHashMap<>();
        for (String str : entity) {
          entitys.put(str,map.get(str));
        }
        for (String str : physicalField) {
          map1.put(str,map.get(str));
        }
        List<Map<String, Object>> list1 = mapper
            .selectTermUdbc(map.get("logicType").toString(), map.get("entityId").toString());
        map1.put("dataUDC",list1);
        entitys.put("physicalField", map1);
        for (String str : physicalFieldStatistics) {
          physicalFieldStatistic.put(str,map.get(str));
        }
        entitys.put("physicalFieldStatistics", physicalFieldStatistic);
        for (String str : physicalTable) {
          physicalTables.put(str,map.get(str));
        }
        List<Map<String, Object>> list2 = mapper
            .selectTermPhysical(map.get("fieldPartition").toString(),
                map.get("physicalTable").toString());
        physicalTables.put("partitionInfo",list2);
        entitys.put("physicalTable", physicalTables);
        for (String str : externalInfo) {
          externalInfos.put(str,map.get(str));
        }
        entitys.put("externalInfo", externalInfos);
        ResultList.add(entitys);
      }
      return JsonResult.successJson(ResultList);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("search tableInfo error");
    }
  }


}
