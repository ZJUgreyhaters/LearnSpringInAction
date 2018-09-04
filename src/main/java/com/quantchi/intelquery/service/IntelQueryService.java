package com.quantchi.intelquery.service;

import com.quantchi.intelquery.StepResult;
import com.quantchi.intelquery.pojo.QuerySentence;
import com.quantchi.intelquery.sqlquery.ColumnRelation.TreeNode;
import com.quantchi.intelquery.sqlquery.SqlQuery;
import com.quantchi.intelquery.utils.ComplexTable;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/8/17.
 */
public interface IntelQueryService {

  List<Map<String, Object>> getBusiCate();

  List<Map<String, Object>> getRecommendQuery(String businessTypeId);

  List<Object> getQuickMacroQuery(String query) throws Exception;

  List<Object> getMetricsRet(String query) throws Exception;

  Map<String, Object> execsql(String sql, Map<String, Object> map) throws Exception;

  ResultSet execsqlWithResultSet(String sql, Map<String, Object> map) throws Exception;

  Map<String, Object> candidatesMapping(StepResult result) throws IOException;

  List<Map<String, Object>> stepsMapping(StepResult result) throws IOException;

  String addQuerySentence(String username,
      String businessName,
      String query,
      boolean isParseable,
      String sql) throws Exception;

  List<QuerySentence> getCorrelativeSentence(String query) throws Exception;

  List<Object> queryInstanceMapping(List<Object> quickMacroQuery);

  List<Map<String, Object>> columnRelationMapping(TreeNode columnRelation,
      Map<String, Object> tabulate);

  Map<String, Object> tabulateMapping(TreeNode columnRelation, Map<String, Object> tabulate);

  //Map<List<String>,Object> getComplexData(ResultSet rs,TreeNode columnRelation,int page,int pagesize) throws SQLException;
  Map<String,Object> getComplexData(ResultSet rs,TreeNode columnRelation,int page,int pagesize) throws SQLException;

  // 生成下载Excel文件用的数据
  ComplexTable getComplexTable(SqlQuery sqlQuery) throws Exception;

  String likenum(QuerySentence querySentence) throws Exception;
}

