package com.quantchi.metadatamgr.service;

import com.quantchi.common.AppProperties;
import com.quantchi.common.Paging;
import com.quantchi.common.Util;
import com.quantchi.metadatamgr.data.entity.MDCheckBusiRule;
import com.quantchi.metadatamgr.data.entity.MDCheckTechRule;
import com.quantchi.metadatamgr.data.entity.MetricPhysicalInfo;
import com.quantchi.metadatamgr.data.mapper.MDCheckBusiRuleMapper;
import com.quantchi.metadatamgr.data.mapper.MDCheckRuleMapper;
import com.quantchi.metadatamgr.data.mapper.MDCheckTechRuleMapper;
import com.quantchi.metadatamgr.util.MetaMgrConstant;
import com.quantchi.termInfo.mapper.StandInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetaDataMgrCheckApiService {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrCheckApiService.class);

  @Autowired
  private MDCheckRuleMapper mdCheckRuleMapper;

  @Autowired
  private MDCheckBusiRuleMapper mdCheckBusiRuleMapper;

  @Autowired
  private MDCheckTechRuleMapper mdCheckTechRuleMapper;

  @Autowired
  private StandInfoMapper standInfoMapper;


  public Map<String, Object> searchBusin(Map<String, Object> map) {
    Map<String, Object> responseMap = new HashMap<>();

    List<Map<String, Object>> list =  mdCheckRuleMapper.selectBusin(map);

    int total = list.size();
    if (map.get("page") != null && map.get("page_size") != null) {
      int page =  (Integer) map.get("page") ;
      int page_size =  (Integer)map.get("page_size") ;
      list = Paging.pagingPlug(list, page_size, page);
    }
    if (map.get("ruletypefilter") != null ) {
       String ruletypefilter =  (String) map.get("ruletypefilter");
       String ruletypes[] = ruletypefilter.split("_");
       String ruletypefirst = ruletypes[0];
       map.put("ruletypefirst",ruletypefirst);
       if(ruletypes.length>1){
        String ruletypesecond = ruletypes[1];
         map.put("ruletypesecond",ruletypesecond);
       }
    }
    for (Map<String ,Object> businRule :list){
      String techid =  businRule.get("techruleids").toString();
      String[]  techids = techid.split(",");
      businRule.put("techruleids",techids);
     }
    responseMap.put("data", list);
    responseMap.put("total", total);
    return responseMap;
  }

  public Object deleteBusiRules(List<MDCheckBusiRule> rules){
    rules.forEach(i->{
      mdCheckTechRuleMapper.deleteByBusinessId(i.getBusinId());
      mdCheckBusiRuleMapper.deleteByPrimaryKey(i.getId());});
    return true;
  }

  public Object addOrUptBusiRules(MDCheckBusiRule rule){
    Object ret = null;
    if(rule.getId() != null){
      ret = mdCheckBusiRuleMapper.insertSelective(rule);
    }else{
      rule.setBusinId(getRuleId());
      rule.setCreateDate(new Date());
      rule.getMetricPhysicalInfo().forEach(i->{
        String sql = getRuleSqlText(i);

        Map<String,Object> map = new HashMap<>();
        map.put("entityId",i.getEntityId());
        List<Map<String, Object>> list = standInfoMapper.selectPhysicalProperty(map);


        MDCheckTechRule techRule = new MDCheckTechRule();
        techRule.setTechRuleId(getRuleId());
        techRule.setBusinId(rule.getBusinId());
        techRule.setRuleName(rule.getRuleName());
        techRule.setRuleSqlText(sql);
        techRule.setCreator(rule.getCreator());
        techRule.setRuleFirstType(rule.getRuleFirstType());
        techRule.setRuleSecondType(rule.getRuleSecondType());
        techRule.setCreateDate(rule.getCreateDate());
        techRule.setCreator(rule.getCreator());

        if(list.size() > 0){
          techRule.setSourceData(list.get(0).get("physicalDb").toString());
          techRule.setSourceTable(list.get(0).get("physicalTable").toString());
          techRule.setSourdeCol(list.get(0).get("physicalField").toString());
        }

        mdCheckTechRuleMapper.insert(techRule);
      });
      ret = mdCheckBusiRuleMapper.insert(rule);
    }
    return ret;
  }

  public Map<String, Object> searchTech(Map<String, Object> map) {
    Map<String, Object> responseMap = new HashMap<>();

    List<Map<String, Object>> list =  mdCheckRuleMapper.selectTech(map);

    int total = list.size();
    if (map.get("page") != null && map.get("page_size") != null) {
      int page =  (Integer) map.get("page") ;
      int page_size =  (Integer)map.get("page_size") ;
      list = Paging.pagingPlug(list, page_size, page);
    }
    if (map.get("ruletypefilter") != null ) {
      String ruletypefilter =  (String) map.get("ruletypefilter");
      String ruletypes[] = ruletypefilter.split("_");
      String ruletypefirst = ruletypes[0];
      map.put("ruletypefirst",ruletypefirst);
      if(ruletypes.length>1){
        String ruletypesecond = ruletypes[1];
        map.put("ruletypesecond",ruletypesecond);
      }
    }
    responseMap.put("data", list);
    responseMap.put("total", total);
    return responseMap;
  }

  public Object deleteTechRules(List<MDCheckTechRule> rules){
    rules.forEach(i->mdCheckTechRuleMapper.deleteByPrimaryKey(i.getId()));
    return true;
  }

  public Object addOrUptTechRules(MDCheckTechRule rule){
    Object ret = null;
    if(rule.getId() != null){
      ret = mdCheckTechRuleMapper.insertSelective(rule);
    }else
      ret = mdCheckTechRuleMapper.insert(rule);
    return ret;
  }

  private String getRuleId(){
    return UUID.randomUUID().toString().replace("-", "");
  }

  private String getRuleSqlText(MetricPhysicalInfo info){
    String sql = AppProperties.get("rule.check.sql."+info.getCheckType());
    Map<String,Object> physicalInfo = new HashMap<>();
    physicalInfo.put("table", info.getPhysicalTable());
    physicalInfo.put("field", info.getPhysicalField());
    physicalInfo.put("db", info.getPhysicalDb());
    for(int i=0;i<info.getCheckParam().size();i++){
      physicalInfo.put("param"+i, info.getCheckParam().get(i));
    }
    return Util.processTemplate(sql,physicalInfo);

  }



}
