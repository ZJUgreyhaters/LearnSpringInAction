package com.quantchi.termInfo.service;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import java.util.Map;

/**
 * Created by 49537 on 2018/7/27.
 */
public interface StandInfoService {

  String selectListCategory(StandardMainInfo standardMainInfo);

  String selectList(StandardMainInfo standardMainInfo);

  String selectMetric(StandardMainInfo standardMainInfo);

  String selectCodeDefinition(Map<String, Object> map);

  String selectBusiness(Map<String, Object> map);

  String selectPhysicalProperty(Map<String, Object> map);
}
