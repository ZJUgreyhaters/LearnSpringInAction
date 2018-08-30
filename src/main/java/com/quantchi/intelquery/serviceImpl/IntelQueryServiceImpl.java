package com.quantchi.intelquery.serviceImpl;

import com.quantchi.common.AppProperties;
import com.quantchi.common.HiveConnection;
import com.quantchi.common.Paging;
import com.quantchi.intelquery.QueryParser;
import com.quantchi.intelquery.StepResult;
import com.quantchi.intelquery.TokenizingResult;
import com.quantchi.intelquery.mapper.IntelQueryMapper;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.pojo.QuerySentence;
import com.quantchi.intelquery.query.BasicQuery;
import com.quantchi.intelquery.query.QueryNodes;
import com.quantchi.intelquery.query.QueryWithNodes;
import com.quantchi.intelquery.query.QueryWithTree;
import com.quantchi.intelquery.search.SearchEng;
import com.quantchi.intelquery.service.IntelQueryService;
import com.quantchi.intelquery.tokenize.search.Replacement;
import com.quantchi.intelquery.utils.SerializationUtils;
import com.quantchi.tianshu.common.JdbcPool;
import com.quantchi.transport.service.ExecSqlApiService;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/8/17.
 */
@Service
public class IntelQueryServiceImpl implements IntelQueryService {

  private static final String SEARCHTYPE = "solr";
  private static final String INTELQUERYVERSION = AppProperties.get("intelquery.version");;

  private static final Logger logger = LoggerFactory.getLogger(ExecSqlApiService.class);

  @Autowired
  @Qualifier("hiveJdbcPool")
  private JdbcPool jdbcPool;

  @Autowired
  private IntelQueryMapper intelQueryMapper;

  @Override
  public List<Map<String, Object>> getBusiCate() {
    return intelQueryMapper.getBusiCate();
  }

  @Override
  public List<Map<String, Object>> getRecommendQuery(String businessTypeId) {
    return intelQueryMapper.getRecommendQuery(businessTypeId);
  }

  public List<Object> getMetricsRet(String query) throws Exception {
    //TODO get metrics
    //if return [] , call intelquery
    SearchEng engObj = SearchEng.instanceOf(query, SEARCHTYPE);
    return engObj.getMetrics();
  }

  public void getIntelQuery() {

  }

  public List<Object> getQuickMacroQuery(String query) throws Exception {
    SearchEng engObj = SearchEng.instanceOf(query, SEARCHTYPE);
    return engObj.getQuickMacro();
  }

  public Map<String, Object> execsql(String sql, Map<String, Object> map) throws Exception {
    List<Map<String, Object>> resultList = HiveConnection.selectHive(sql, jdbcPool);
    Integer total = resultList.size();
    if (map.get("page_size") != null && map.get("page") != null) {
      resultList = Paging.pagingPlug(resultList, Integer.parseInt(map.get("page_size").toString()),
          Integer.parseInt(map.get("page").toString()));
    }
    Map<String, Object> resultMap = new HashMap();
    resultMap.put("resultList", resultList);
    resultMap.put("total", total);
    return resultMap;
  }

  public Map<String, Object> candidatesMapping(StepResult result) throws IOException {
    Map<String, Object> candidates = new HashMap<>();
    QueryWithNodes queryWithNodes = ((TokenizingResult) result).getQuery();
    QueryNodes nodes = queryWithNodes.getNodes();
    List<Map<String, Object>> queryNodes = new ArrayList<>();
    for (SemanticNode node : nodes) {
      Map<String, Object> nodeMap = new HashMap<>();
      String base64String = SerializationUtils.toSerializedString(node);
      nodeMap.put("node", node.getText());
      nodeMap.put("serializeNode", base64String);
      queryNodes.add(nodeMap);
    }
    List<Map<String, Object>> composeList = new ArrayList<>();
    List<Replacement> replacements = ((TokenizingResult) result).getReplacements();
    for (Replacement replacement : replacements) {
      Map<String, Object> composeMap = new HashMap<>();
      Integer begIndex = replacement.getBegIndex(); // 需要用户选择的起始node index
      Integer endIndex = replacement.getEndIndex(); // 需要用户选择的结束node index，左闭右开
      List<QueryNodes> compose = replacement.getAllCandidates(); // 所有可能的结果
      List<Map<String, Object>> composeNodeList = new ArrayList<>();
      for (QueryNodes queryNode : compose) {
        Map<String, Object> queryNodeMap = new HashMap<>();
        StringBuilder nodeBuilder = new StringBuilder();
        for (SemanticNode node : queryNode) {
          nodeBuilder.append(node.getText());
        }
        queryNodeMap.put("node", nodeBuilder);
        queryNodeMap.put("serializeNode", SerializationUtils.toSerializedString(queryNode));
        composeNodeList.add(queryNodeMap);
      }
      composeMap.put("begIndex", begIndex);
      composeMap.put("endIndex", endIndex);
      composeMap.put("compose", composeNodeList);
      composeList.add(composeMap);
    }
    candidates.put("queryNodes", queryNodes);
    candidates.put("composeList", composeList);
    return candidates;
  }

  public List<Map<String, Object>> stepsMapping(StepResult result)throws IOException{
    List<QueryWithTree> steps = result.getSteps();
    List<Map<String, Object>> stepsList = new ArrayList<>();
    for (QueryWithTree queryWithTree : steps) {
      Map<String, Object> stepsMap = new HashMap<>();
      stepsMap.put("node", queryWithTree.getTextForUser());
      stepsMap.put("serializeNode", SerializationUtils.toSerializedString(queryWithTree));
      stepsList.add(stepsMap);
    }
    return stepsList;
  }

  public String addQuerySentence(String username,
                                  String businessName,
                                  String query,
                                  boolean isParseable,
                                  String sql) throws Exception{

    QuerySentence qs = new QuerySentence();
    qs.setUsername(username);
    qs.setBusinessName(businessName);
    qs.setQuery(query);
    qs.setParseable(isParseable);
    qs.setQuerySql(sql);
    qs.setIntelqueryVer(INTELQUERYVERSION);

    SearchEng engObj = SearchEng.instanceOf(query, SEARCHTYPE);
    return engObj.addQuerySentence(qs);
  }

  public List<QuerySentence> getCorrelativeSentence(String query) throws Exception{
    if(!"".equals(query)){
      SearchEng engObj = SearchEng.instanceOf(query, SEARCHTYPE);
      List<QuerySentence> sentences =  engObj.getCorrelativeSentence();
      removeSameDomainSentence(sentences);
      return sentences;
    }else{
      return Collections.emptyList();
    }

  }

  private void removeSameDomainSentence(List<QuerySentence> sentences) throws Exception{
		int startIdx = 0;
		while(startIdx < sentences.size()){
      QuerySentence first = sentences.get(startIdx);
			for(int i=startIdx+1;i<sentences.size();i++){
				QuerySentence second = sentences.get(i);
				if(QueryParser.getInstance().hasSameDomainEntity(new BasicQuery(first.getQuery()), new BasicQuery(second.getQuery()))){
					//add times in the same sentences
					first.setCount(first.getCount()+second.getCount());
					sentences.remove(second);
					i--;
				}
			}
			startIdx++;
		}
	}

}
