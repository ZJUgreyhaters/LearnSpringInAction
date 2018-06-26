package com.quantchi.termInfo.serviceImpl;

import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.termInfo.mapper.PhysicalFieldInfoMapper;
import com.quantchi.termInfo.mapper.PhysicalTableInfoMapper;
import com.quantchi.termInfo.mapper.TermInfoMapper;
import com.quantchi.termInfo.mapper.TermMainInfoMapper;
import com.quantchi.termInfo.pojo.*;
import com.quantchi.termInfo.service.TermInfoService;

import java.util.*;

import javafx.util.Pair;
import org.apache.commons.collections.map.HashedMap;
import org.apache.solr.client.solrj.io.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jdo.annotations.Transactional;

/**
 * Created by 49537 on 2018/6/20.
 */
@Service
public class TermInfoServiceImpl implements TermInfoService {

  @Autowired
  TermInfoMapper mapper;

  @Autowired
  PhysicalTableInfoMapper physicalTableInfoMapper;

  @Autowired
  PhysicalFieldInfoMapper physicalFieldInfoMapper;

  @Autowired
  TermMainInfoMapper termMainInfoMapper;

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

  private static final String ENTITY_TYPE = "1";

  private final Map<String,String> _TypeMap = new HashMap<String,String>(){
      {
          put("Area","度量");
          put("Text", "文本");
      }
    };

  @Override
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    try {
      List<Map<String, Object>> list = mapper.selectTermAll(termInfoPojo);
      List<Map<String, Object>> list1 = mapper.selectUdc();
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
        List<Map<String, Object>> udc = new ArrayList<>();
        for(Map<String,Object> map2:list1){
          if(map.get("entityId").equals(map2.get("entityId"))){
            udc.add(map2);
          }
        }
        physical.put("dataUDC",udc);
        map1.put("physicalField", physical);
        ResultList.add(map1);
      }
      if (termInfoPojo.getNums() != null) {
        ResultList = Paging.pagingPlug(ResultList, termInfoPojo.getNums(), 1);
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
          entitys.put(str, map.get(str));
        }
        for (String str : physicalField) {
          map1.put(str, map.get(str));
        }
        List<Map<String, Object>> list1 = mapper
            .selectTermUdbc(map.get("entityId").toString());
        map1.put("dataUDC", list1);
        entitys.put("physicalField", map1);
        for (String str : physicalFieldStatistics) {
          physicalFieldStatistic.put(str, map.get(str));
        }
        entitys.put("physicalFieldStatistics", physicalFieldStatistic);
        for (String str : physicalTable) {
          physicalTables.put(str, map.get(str));
        }
        List<Map<String, Object>> list2 = mapper
            .selectTermPhysical(map.get("physicalTable").toString());
        physicalTables.put("partitionInfo", list2);
        entitys.put("physicalTable", physicalTables);
        for (String str : externalInfo) {
          externalInfos.put(str, map.get(str));
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

  @Transactional
  @Override
  public String insertTerm(ArrayList<TermGenInfo> termGenInfos){
    try {
      for(TermGenInfo termGenInfo:termGenInfos) {
        if (termGenInfo.getTableInfo() == null
                || termGenInfo.getFieldInfoList() == null)
          throw new Exception("miss table or field or term info");


        //插入表信息
        physicalTableInfoMapper.insert(termGenInfo.getTableInfo());

        //插入字段信息
        physicalFieldInfoMapper.insertFields(termGenInfo.getFieldInfoList());

        //术语主信息插入
        List<TermMainInfo> _termlist = new ArrayList<TermMainInfo>();
        for (PhysicalFieldInfo field : termGenInfo.getFieldInfoList()) {
          TermMainInfo _term = new TermMainInfo();
          //from insert field entity_id
          _term.setEntityId("");
          _term.setEntityType(ENTITY_TYPE);
          _term.setEntityName(field.getPhysicalField());
          _term.setEntityDesc(field.getPhysicalFieldDesc());
          _term.setTechniqueRule("select " + field.getPhysicalField() + " from " + field.getPhysicalDb() + "." + field.getPhysicalTable());
          _term.setEntityStatus("正常");
          _term.setCreateTime(new Date());

          //getLogicTypeAndDisplayType(field.getDataType())
            String _type = getDisplayType(field.getDataType());
            String _logicType = _TypeMap.get(_type);
            _term.setDisplayType(_type);
            _term.setLogicType(_logicType);
            _termlist.add(_term);
        }

        if(_termlist.size() > 0)
            termMainInfoMapper.insertTerms(_termlist);



      }
      return JsonResult.successJson();
    }catch (Exception e){
      e.printStackTrace();
      return JsonResult.errorJson(e.getMessage());
    }

  }

  private String getDisplayType(String type){
      if(type.equals("double") || type.equals("float") || type.equals("int"))
          return "Area";
      if(type.equals("string") || type.indexOf("varchar")> -1 )
          return "Text";

      return "";
  }
}
