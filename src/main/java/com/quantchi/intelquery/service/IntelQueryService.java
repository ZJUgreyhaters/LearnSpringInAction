package com.quantchi.intelquery.service;

import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/8/17.
 */
public interface IntelQueryService {

  List<Map<String, Object>> getBusiCate();

  List<Map<String, Object>> getRecommendQuery(String businessTypeId);
}
