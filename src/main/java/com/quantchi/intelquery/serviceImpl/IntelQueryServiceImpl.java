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
import com.quantchi.intelquery.sqlquery.ColumnInfo;
import com.quantchi.intelquery.sqlquery.ColumnRelation.TreeNode;
import com.quantchi.intelquery.tokenize.search.Replacement;
import com.quantchi.intelquery.utils.ComplexTable;
import com.quantchi.intelquery.utils.ComplexTable.LeafHeader;
import com.quantchi.intelquery.utils.SerializationUtils;
import com.quantchi.termInfo.mapper.StandInfoMapper;
import com.quantchi.tianshu.common.JdbcPool;
import com.quantchi.transport.service.ExecSqlApiService;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
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
  private static final String INTELQUERYVERSION = AppProperties.get("intelquery.version");
  ;

  private static final Logger logger = LoggerFactory.getLogger(ExecSqlApiService.class);

  @Autowired
  @Qualifier("hiveJdbcPool")
  private JdbcPool jdbcPool;

  @Autowired
  private IntelQueryMapper intelQueryMapper;

  @Autowired
  private StandInfoMapper standInfoMapper;

  @Autowired
  private HttpSolrClient httpSolr;

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

  public ResultSet execsqlWithResultSet(String sql, Map<String, Object> map) throws Exception {
    return HiveConnection.selectHiveWithRs(sql, jdbcPool);
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

  public List<Map<String, Object>> stepsMapping(StepResult result) throws IOException {
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


  //public Map<List<String>,Object> getComplexData(ResultSet rs,TreeNode columnRelation,int page,int pagesize) throws SQLException{
  public Map<String,Object> getComplexData(ResultSet rs,TreeNode columnRelation,int page,int pagesize) throws SQLException{
    Map<String,Object> ret = new HashMap<>();
    Map<List<String>,Object> retData = new HashMap<>();
    List<Object> retHeader = new ArrayList<>();
    ComplexTable ct = new ComplexTable(rs,columnRelation);
    LeafHeader lh = ct.getPrimeHeader();
    retHeader.add(lh.getTitles());
    List<Map<String, Object>> normHeaders = ct.getNormHeaders();
		lh.getData().forEach((i) -> retData.put(i.getRowData(),null));
		for(Map<String, Object> normalColumn : normHeaders){
			for(String col:normalColumn.keySet()){
        int index = normalColumn.keySet().stream().collect(Collectors.toList()).indexOf(col);
        String colKey = col;
        Map<String,Object> colHeader = new HashMap<>();
        //setColHeaderAndData();
        setColHeaderAndData(colHeader,col,normalColumn);
        /*colHeader.put(col,((LeafHeader)normalColumn.get(col)).getTitles());
        retHeader.add(colHeader);*/
        for(ComplexTable.Block nb:((LeafHeader)normalColumn.get(col)).getData()){
          Map<String,Object> colMap = (Map<String,Object>)retData.get(((ComplexTable.NormBlock)nb).getBelongTo().getRowData());
          if(colMap == null)
            colMap = new HashMap<>();
          else
            colKey = col+"_"+index;
          colMap.put(colKey,((ComplexTable.NormBlock)nb).getRowData());
          retData.put(((ComplexTable.NormBlock)nb).getBelongTo().getRowData(),colMap);
        }
			}

		}
    ret.put("data",retData);
    ret.put("header",retHeader);
		return ret;
  }

  private void setColHeaderAndData(Map<String,Object> colHeader,String ColName,Object normalColumn){
    if(normalColumn instanceof HashMap){
      Map<String,Object> subColHeader = new HashMap<>();
      String subCol = ((HashMap) normalColumn).keySet().toArray()[0].toString();
      Object subNormalCol = ((HashMap) normalColumn).get(subCol);
      setColHeaderAndData(subColHeader,subCol,subNormalCol);
			colHeader.put(ColName,subColHeader);
    }else if(normalColumn instanceof LeafHeader){
      colHeader.put(ColName,((LeafHeader) normalColumn).getTitles());
    }
  }

  @Override
  public List<Object> queryInstanceMapping(List<Object> quickMacroQuery) {
    for (Object quickMacroQueryObj : quickMacroQuery) {
      Map<String, Object> objectMap = (Map<String, Object>) quickMacroQueryObj;
      if ("value".equals(objectMap.get("type"))) {
        Map<String, Object> dbFieldMap = new HashMap<>();
        String dbField = objectMap.get("db_field").toString();
        String[] splits = dbField.split("\\.");
        dbFieldMap.put("physical_db", splits[0]);
        dbFieldMap.put("physical_table", splits[1]);
        dbFieldMap.put("physical_field", splits[2]);
        Map<String, Object> categoryMap = intelQueryMapper.selectCategory(dbFieldMap);
        if (categoryMap != null) {
          List<Map<String, Object>> businessList = standInfoMapper.selectBusiness(categoryMap);
          StringBuilder str = new StringBuilder();
          str.append(businessList.get(0).get("businessTypeName")).append("<")
              .append(businessList.get(0).get("domainName")).append("<")
              .append(businessList.get(0).get("logicTableName"));
          objectMap.put("entityCategory", str);
        }
      }
    }
    return quickMacroQuery;
  }

  @Override
  public List<Map<String, Object>> columnRelationMapping(TreeNode columnRelation,
      Map<String, Object> tabulate) {

    columnRelationMappingExt(columnRelation, null,
        (List<Map<String, Object>>) tabulate.get("resultList"));

    List<TreeNode> children = columnRelation.getChildren();
    List<Map<String, Object>> listTop = new ArrayList<>();
    for (TreeNode treeNode : children) {
      ColumnInfo columnInfo = treeNode.getColumnInfo();
      if (treeNode.isRowToCol() && (treeNode.getChildren() == null
          || treeNode.getChildren().isEmpty())) {
        ColumnInfo parentInfo = columnRelation.getColumnInfo();
        List<String> mappings = mapping(columnInfo.getAlias(), tabulate);
        for (String mapping : mappings) {
          Map<String, Object> mapRowTocol = new HashMap<>();
          StringBuilder title = new StringBuilder();
          title.append(parentInfo.getAlias()).append(">>").append(mapping);
          mapRowTocol.put("title", mapping);
          mapRowTocol.put("dataIndex", false);
          mapRowTocol.put("name", title);
          mapRowTocol.put("keys", false);
          mapRowTocol.put("key", title);
          mapRowTocol.put("fixed", false);
          mapRowTocol.put("children", new ArrayList<>());
          listTop.add(mapRowTocol);
        }
      } else if (treeNode.isRowToCol() && (treeNode.getChildren() != null
          && !treeNode.getChildren().isEmpty())) {
        List<String> mappings = mapping(columnInfo.getAlias(), tabulate);
        List<TreeNode> childrenInfo = treeNode.getChildren();
        for (String mapping : mappings) {
          Map<String, Object> mapRowTocol = new HashMap<>();
          List<Map<String, Object>> childrenList = childrenInfoMapping(mapping, childrenInfo);
          mapRowTocol.put("title", mapping);
          mapRowTocol.put("dataIndex", false);
          mapRowTocol.put("name", mapping);
          mapRowTocol.put("keys", false);
          mapRowTocol.put("key", mapping);
          mapRowTocol.put("fixed", false);
          mapRowTocol.put("children", childrenList);
          listTop.add(mapRowTocol);
        }
      } else {
        Map<String, Object> mapTop = new HashMap<>();
        mapTop.put("title", columnInfo.getAlias());
        mapTop.put("dataIndex", columnInfo.isDomainField());
        mapTop.put("name", columnInfo.getAlias());
        mapTop.put("index", columnInfo.getIndex());
        mapTop.put("keys", columnInfo.isKey());
        mapTop.put("key", columnInfo.toString());
        mapTop.put("fixed", columnInfo.isPrime());
        mapTop.put("physicalField", columnInfo.toString());
        mapTop.put("rowToCol", treeNode.isRowToCol());
        if (treeNode.getChildren() != null && !treeNode.getChildren().isEmpty()) {
          List<Map<String, Object>> list = columnRelationMapping(treeNode, tabulate);
          mapTop.put("children", list);
        } else {
          mapTop.put("children", new ArrayList<>());
        }
        listTop.add(mapTop);
      }
    }
    return listTop;
  }


  private Map<Object, Object> columnRelationMappingExt(TreeNode columnRelation,
      List<TreeNode> primeTreeNodes, List<Map<String, Object>> datas) {

    Map<Object, Object> ret = new HashMap<>();
    //List<TreeNode> primeTreeNodes =
    if (primeTreeNodes == null) {
      primeTreeNodes = columnRelation.getChildren().stream()
          .filter(n -> n.getColumnInfo().isPrime()).collect(Collectors.toList());
    }
    List<TreeNode> children = columnRelation.getChildren();
    for (TreeNode treeNode : children) {
      if (primeTreeNodes.contains(treeNode)) {
        continue;
      }

      ColumnInfo columnInfo = treeNode.getColumnInfo();
      //普通列
      if (treeNode.getChildren().isEmpty()) {
        System.out.print(getNormalColumn(treeNode, primeTreeNodes, datas));
      }
      //普通列带有孩子
      else if (!treeNode.getChildren().isEmpty() && !treeNode.isRowToCol()) {
        System.out.print(getNormalColumnWithChildNode(treeNode, primeTreeNodes, datas));
      }
    }
    return ret;
  }

  private Map<Object, Object> getNormalColumn(TreeNode curTreeNode, List<TreeNode> primeTreeNodes,
      List<Map<String, Object>> datas) {

    Function<Map<String, Object>, Object> normalFunc = e -> {
      ArrayList<String> groupbyCol = new ArrayList<>();
      primeTreeNodes.forEach(i -> groupbyCol.add(e.get(i.getColumnInfo().toString()).toString()));
      return groupbyCol;
    };

    Map<Object, Object> ret = new HashMap<>();
    Map<Object, List<Map<String, Object>>> result = datas.stream()
        .collect(Collectors.groupingBy(normalFunc));
    result.forEach((k, v) -> ret.put(k, v.stream().collect(Collectors
        .toMap(e -> curTreeNode.getColumnInfo().toString(),
            e -> e.get(curTreeNode.getColumnInfo().toString())))));
    return ret;
  }

  private Map<Object, Object> getNormalColumnWithChildNode(TreeNode curTreeNode,
      List<TreeNode> primeTreeNodes, List<Map<String, Object>> datas) {

    Function<Map<String, Object>, Object> normalFunc = e -> {
      ArrayList<String> groupbyCol = new ArrayList<>();
      primeTreeNodes.forEach(i -> groupbyCol.add(e.get(i.getColumnInfo().toString()).toString()));
      return groupbyCol;
    };

    List<String> childColumns = new ArrayList<>();
    curTreeNode.getChildren().forEach(i -> childColumns.add(i.getColumnInfo().toString()));

    /*Function<Map<String,Object>,Object> filterFunc = e->{
      e.entrySet().
      ArrayList<String> groupbyCol = new ArrayList<>();
      primeTreeNodes.forEach(i -> groupbyCol.add(e.get(i.getColumnInfo().toString()).toString()));
      return groupbyCol;
    };*/

    Map<Object, Object> ret = new HashMap<>();
    Map<Object, List<Map<String, Object>>> result = datas.stream()
        .collect(Collectors.groupingBy(normalFunc));
    result.forEach((k, v) -> ret.put(k, v.stream().collect(Collectors
        .toMap(e -> curTreeNode.getColumnInfo().toString(),
            e -> e.get(curTreeNode.getColumnInfo().toString())))));
    return ret;

  }

  @Override
  public Map<String, Object> tabulateMapping(TreeNode columnRelation,
      Map<String, Object> tabulate) {
    List<TreeNode> children = columnRelation.getChildren();

    List<String> primeList = new ArrayList<>();
    Map<String, List<String>> map = getclassifyList(columnRelation);
    for (TreeNode treeNode : children) {
      if (treeNode.getColumnInfo().isPrime()) {
        primeList.add(treeNode.getColumnInfo().getAlias());
      }
    }

    List<Map<String, Object>> resultList = (List<Map<String, Object>>) tabulate.get("resultList");
    List<Object> listMap = new ArrayList<>();
    if (!primeList.isEmpty()) {
      for (Map<String, Object> tabulateMap : resultList) {
        Object alias = primeList.get(0);
        Object obj = tabulateMap.get(alias);
        if (!listMap.contains(obj)) {
          listMap.add(obj);
        }
      }
    }

    return null;
  }

  @Override
  public String likenum(QuerySentence querySentence) throws Exception {
    SearchEng engObj = SearchEng.instanceOf(querySentence.getQuery(), SEARCHTYPE);
    return engObj.addQuerySentence(querySentence);
  }

  List<String> mapping(String info, Map<String, Object> tabulate) {
    List<Map<String, Object>> resultList = (List<Map<String, Object>>) tabulate
        .get("resultList");
    List<String> infoList = new ArrayList<>();
    for (Map<String, Object> tabulateMap : resultList) {
      String fields = tabulateMap.get(info).toString();
      if (!infoList.contains(fields)) {
        infoList.add(fields);
      }
    }
    return infoList;
  }

  List<Map<String, Object>> childrenInfoMapping(String mapping, List<TreeNode> childrenInfo) {

    List<Map<String, Object>> list = new ArrayList<>();
    for (TreeNode treeNode : childrenInfo) {
      ColumnInfo columnInfo = treeNode.getColumnInfo();
      Map<String, Object> mapTop = new HashMap<>();
      StringBuilder topBuilder = new StringBuilder();
      topBuilder.append(columnInfo.getAlias()).append(">>").append(mapping);
      mapTop.put("title", columnInfo.getAlias());
      mapTop.put("dataIndex", columnInfo.isDomainField());
      mapTop.put("name", topBuilder);
      mapTop.put("index", columnInfo.getIndex());
      mapTop.put("keys", columnInfo.isKey());
      mapTop.put("key", columnInfo.toString());
      mapTop.put("fixed", columnInfo.isPrime());
      mapTop.put("physicalField", columnInfo.toString());
      mapTop.put("rowToCol", treeNode.isRowToCol());
      list.add(mapTop);
    }
    return list;
  }

  Map<String, List<String>> getclassifyList(TreeNode columnRelation) {
    Map<String, List<String>> map = new HashMap<>();
    List<String> domainList = new ArrayList<>();
    List<String> rowTocolList = new ArrayList<>();
    getclassify(domainList, rowTocolList, columnRelation);
    map.put("domainList", domainList);
    map.put("rowTocolList", rowTocolList);
    return map;
  }

  void getclassify(List<String> domainList, List<String> rowTocolList,
      TreeNode columnRelation) {
    List<TreeNode> children = columnRelation.getChildren();
    for (TreeNode treeNode : children) {
      ColumnInfo columnInfo = treeNode.getColumnInfo();
      if (columnInfo.isDomainField()) {
        domainList.add(columnInfo.getAlias());
      }
      if (treeNode.isRowToCol()) {
        rowTocolList.add(columnInfo.getAlias());
      }
      if (treeNode.getChildren() != null && !treeNode.getChildren().isEmpty()) {
        getclassify(domainList, rowTocolList, treeNode);
      }
    }
  }


  public String addQuerySentence(String username,
      String businessName,
      String query,
      boolean isParseable,
      String sql) throws Exception {

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

  public List<QuerySentence> getCorrelativeSentence(String query) throws Exception {
    SearchEng engObj = SearchEng.instanceOf(query, SEARCHTYPE);
    List<QuerySentence> sentences = engObj.getCorrelativeSentence();
    removeSameDomainSentence(sentences);
    return sentences;
  }

  private void removeSameDomainSentence(List<QuerySentence> sentences) throws Exception {
    int startIdx = 0;
    while (startIdx < sentences.size()) {
      QuerySentence first = sentences.get(startIdx);
      for (int i = startIdx + 1; i < sentences.size(); i++) {
        QuerySentence second = sentences.get(i);
        if (QueryParser.getInstance().hasSameDomainEntity(new BasicQuery(first.getQuery()),
            new BasicQuery(second.getQuery()))) {
          //add times in the same sentences
          first.setCount(first.getCount() + second.getCount());
          sentences.remove(second);
          i--;
        }
      }
      startIdx++;
    }
  }

}
