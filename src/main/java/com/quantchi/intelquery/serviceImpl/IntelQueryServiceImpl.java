package com.quantchi.intelquery.serviceImpl;

import com.quantchi.common.HiveConnection;
import com.quantchi.common.Paging;
import com.quantchi.intelquery.mapper.IntelQueryMapper;
import com.quantchi.intelquery.search.SearchEng;
import com.quantchi.intelquery.service.IntelQueryService;
import com.quantchi.tianshu.common.JdbcPool;
import com.quantchi.transport.service.ExecSqlApiService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    Map<String,Object> resultMap = new HashMap();
    resultMap.put("resultList",resultList);
    resultMap.put("total",total);
    return resultMap;
}
}
