package com.quantchi.intelquery.serviceImpl;

import com.quantchi.intelquery.mapper.IntelQueryMapper;
import com.quantchi.intelquery.search.SearchEng;
import com.quantchi.intelquery.service.IntelQueryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/8/17.
 */
@Service
public class IntelQueryServiceImpl implements IntelQueryService {

  private static final String SEARCHTYPE = "solr";

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
    SearchEng engObj = SearchEng.instanceOf(query,SEARCHTYPE);
    return engObj.getMetrics();
  }

  public void getIntelQuery(){

  }

  public List<Object> getQuickMacroQuery(String query) throws Exception {
    SearchEng engObj = SearchEng.instanceOf(query,SEARCHTYPE);
    return engObj.getQuickMacro();
  }
}
