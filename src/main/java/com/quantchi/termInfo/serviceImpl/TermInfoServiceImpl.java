package com.quantchi.termInfo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDB;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDBExample;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDBExample;
import com.quantchi.metadatamgr.data.mapper.DSEntityInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import com.quantchi.termInfo.mapper.*;
import com.quantchi.termInfo.pojo.*;
import com.quantchi.termInfo.service.TermInfoService;

import java.util.*;

import javafx.util.Pair;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

  @Autowired
  TermLogicFieldDraftMapper termLogicFieldDraftMapper;

  @Autowired
  TermLogicFieldMapper termLogicFieldMapper;

  @Autowired
  TermLogicCatagoryMapper termLogicCatagoryMapper;

  @Autowired
  DSEntityInfoDBMapper dsEntityInfoDBMapper;

  @Autowired
  DSTableInfoDBMapper dsTableInfoDBMapper;

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
      List<TermMainInfo> _termlist = new ArrayList<TermMainInfo>();
      for(TermGenInfo termGenInfo:termGenInfos) {
        if (termGenInfo.getTableInfo() == null
                || termGenInfo.getFieldInfoList() == null)
          throw new Exception("miss table or field or term info");

        Map<String,String> _entityIdMap = new HashMap<>();

        for(PhysicalFieldInfo fieldInfo:termGenInfo.getFieldInfoList()){
            String _uuid = UUID.randomUUID().toString().replace("-","");
            fieldInfo.setEntityId(_uuid);
            _entityIdMap.put(fieldInfo.getPhysicalDb()+"."+fieldInfo.getPhysicalTable()+"."+fieldInfo.getPhysicalField(),_uuid);
        }

        //插入表信息
        physicalTableInfoMapper.insert(termGenInfo.getTableInfo());

        //插入字段信息
        physicalFieldInfoMapper.insertFields(termGenInfo.getFieldInfoList());

        //术语主信息插入

        for (PhysicalFieldInfo field : termGenInfo.getFieldInfoList()) {
          TermMainInfo _term = new TermMainInfo();
          //from insert field entity_id
          _term.setEntityId(_entityIdMap.get(field.getPhysicalDb()+"."+field.getPhysicalTable()+"."+field.getPhysicalField()));
          _term.setEntityType(ENTITY_TYPE);
          _term.setEntityName(field.getPhysicalField());
          _term.setEntityDesc(field.getPhysicalFieldDesc());
          _term.setTechniqueRule(getTechCriteria(field));
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
      return JsonResult.successJson(_termlist.size());
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

  private String getTechCriteria(PhysicalFieldInfo field){
    return "select " + field.getPhysicalField() + " from " + field.getPhysicalDb() + "." + field.getPhysicalTable();
  }

  private void insertFieldIntoOldDB(List<TermGenInfo> termGenInfos,Map<String,String> termLogicCategoryIdMap) throws Exception{
    try {
      for (TermGenInfo termInfo : termGenInfos) {
        PhysicalTableInfo _table = termInfo.getTableInfo();
        String _tableName = _table.getPhysicalTable();
        List<PhysicalFieldInfo> fieldInfos = termInfo.getFieldInfoList();

        if(!termLogicCategoryIdMap.containsKey(_tableName))
          continue;

        for (PhysicalFieldInfo field : fieldInfos) {
          TermLogicFieldDraft _logicField_draft = new TermLogicFieldDraft();
          _logicField_draft.setEnglishName(_tableName+"."+field.getPhysicalField());
          _logicField_draft.setChineseName(_tableName+"."+field.getPhysicalField());
          //_logicField_draft.setChineseName(field.getPhysicalFieldDesc()==null?"undefined":field.getPhysicalFieldDesc());
          //等插入表后返回
          _logicField_draft.setLogicCate(termLogicCategoryIdMap.get(_tableName));
          _logicField_draft.setTechCriteria(getTechCriteria(field));
          _logicField_draft.setStatus(2);
          _logicField_draft.setLogicOnlineId(0);
          _logicField_draft.setIsSum("false");
          _logicField_draft.setIsGroup("false");
          _logicField_draft.setIsUniq("false");
          try {
            termLogicFieldDraftMapper.insert(_logicField_draft);
          }catch (Exception e){
            if(e.getMessage().indexOf("Duplicate entry") == -1)
                throw e;
          }


          TermLogicField _logicField = new TermLogicField();
          _logicField.setEnglishName(_tableName+"."+field.getPhysicalField());
          _logicField.setChineseName(_tableName+"."+field.getPhysicalField());
          //_logicField.setChineseName(field.getPhysicalFieldDesc()==null?"undefined":field.getPhysicalFieldDesc());
          //_logicField.setEnglishName(field.getPhysicalField());
          _logicField.setLogicCate(termLogicCategoryIdMap.get(_tableName));
          _logicField.setTechCriteria(getTechCriteria(field));
          _logicField.setStatus(2);
          _logicField.setIsSum("false");
          _logicField.setIsGroup("false");
          _logicField.setIsUniq("false");

          if(_logicField_draft.getId() == null){
            TermLogicFieldDraftExample _ex = new TermLogicFieldDraftExample();
            _ex.createCriteria().andEnglishNameEqualTo(_tableName+"."+field.getPhysicalField());
            List<TermLogicFieldDraft> _res = termLogicFieldDraftMapper.selectByExample(_ex);
            TermLogicFieldDraft _logfield =  _res.get(0);
            _logicField.setLogicOnlineId(_logfield.getId());
          }else
            _logicField.setLogicOnlineId(_logicField_draft.getId());
          try {
            termLogicFieldMapper.insert(_logicField);
          }catch (Exception e){
            if(e.getMessage().indexOf("Duplicate entry") == -1)
              throw e;
          }

        }

      }
    } catch(Exception e){
         throw new Exception(e.getMessage());
    }

  }
  @Transactional
  @Override
  public String insertTermLogic(Map<String,Object> requestMap) {
    try{
      List<TermGenInfo> termGenInfos = (List)requestMap.get("termGenInfoList");
      String _termGenInfoStr = JSONObject.toJSONString(termGenInfos);
      termGenInfos  = JSONObject.parseArray(_termGenInfoStr,TermGenInfo.class);
      List<TermLogicCatagory> termLogicCatagories = (List<TermLogicCatagory>)requestMap.get("termLogicCatagoryEntityList");
      String _logicCataStr = JSONObject.toJSONString(termLogicCatagories);
      termLogicCatagories  = JSONObject.parseArray(_logicCataStr,TermLogicCatagory.class);
      Map<String,String> termLogicCategoryIdMap = new HashMap<>();
      for(TermLogicCatagory termLogicCatagory : termLogicCatagories){
        termLogicCatagoryMapper.insert(termLogicCatagory);

        DSEntityInfoDBExample dsEntityInfoDBExample = new DSEntityInfoDBExample();
        dsEntityInfoDBExample.createCriteria().andIdEqualTo(Integer.parseInt(termLogicCatagory.getDataSourceId()));
        List<DSEntityInfoDB> dsEntityInfoDBList = dsEntityInfoDBMapper.selectByExample(dsEntityInfoDBExample);

        DSTableInfoDBExample dsTableInfoDBExample = new DSTableInfoDBExample();
        dsTableInfoDBExample.createCriteria().andDatasourceIdEqualTo(termLogicCatagory.getDataSourceName());
        List<DSTableInfoDB> dsTableInfoDBList = dsTableInfoDBMapper.selectByExample(dsTableInfoDBExample);

        for(DSTableInfoDB dsTableInfoDB : dsTableInfoDBList){
          if(dsEntityInfoDBList.get(0).getMainTable().contains(dsTableInfoDB.getTableEnglishName()) || dsEntityInfoDBList.get(0).getNonMainTable().contains(dsTableInfoDB.getTableEnglishName())){
            TermLogicCatagory termLogicCatagory2 = new TermLogicCatagory();
            if(dsTableInfoDB.getTableChineseName() != null){
              termLogicCatagory2.setCategoryName(dsTableInfoDB.getTableChineseName());
            }else{
              termLogicCatagory2.setCategoryName(dsTableInfoDB.getTableEnglishName());
            }
            String tableName = dsTableInfoDB.getTableEnglishName().split("\\.")[1];
            termLogicCatagory2.setLogicTable(tableName);
            termLogicCatagory2.setPhysicalTable(tableName);
            termLogicCatagory2.setCreateTime(new Date());
            termLogicCatagory2.setParentId(termLogicCatagory.getId());
            termLogicCatagoryMapper.insert(termLogicCatagory2);
            termLogicCategoryIdMap.put(tableName,termLogicCatagory.getParentId()+"_"+termLogicCatagory.getId()+"_"+termLogicCatagory2.getId());
            //termLogicCategoryIdMap.put(tableName,termLogicCatagory.getParentId()+"_"+dsTableInfoDB.getId()+"_"+termLogicCatagory.getId());
          }

        }

      }
      insertFieldIntoOldDB(termGenInfos,termLogicCategoryIdMap);
      return JsonResult.successJson();
    }catch (Exception e){
      e.printStackTrace();
      return JsonResult.errorJson(e.getMessage());
    }
  }
}
