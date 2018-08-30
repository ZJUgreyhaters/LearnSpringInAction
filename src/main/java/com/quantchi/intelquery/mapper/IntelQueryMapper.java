package com.quantchi.intelquery.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/8/17.
 */
public interface IntelQueryMapper {

  List<Map<String, Object>> getBusiCate();

  List<Map<String, Object>> getRecommendQuery(String businessTypeId);

  Map<String, Object> selectCategory(Map<String,Object> map);
}
