package com.quantchi.intelquery.service;

import com.quantchi.intelquery.StepResult;
import java.io.IOException;
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

  Map<String, Object> execsql(String sql,Map<String, Object> map) throws Exception;

  Map<String, Object> candidatesMapping(StepResult result) throws IOException;

  List<Map<String, Object>> stepsMapping(StepResult result)throws IOException;

  String addQuerySentence (String username,
                           String businessName,
                           String query,
                           boolean isParseable,
                           String sql) throws Exception;

  List<Object> getCorrelativeSentence(String query) throws Exception;
}
