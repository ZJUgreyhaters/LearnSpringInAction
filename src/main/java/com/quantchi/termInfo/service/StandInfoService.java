package com.quantchi.termInfo.service;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import com.quantchi.termInfo.pojo.TermMainInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/7/27.
 */
public interface StandInfoService {

  String selectListCategory(Map<String, Object> map);

  String selectList(StandardMainInfo standardMainInfo);

  String selectMetric(TermMainInfo termMainInfo);

  String selectCodeDefinition(Map<String, Object> map);

  String selectBusiness(Map<String, Object> map);

  String selectPhysicalProperty(Map<String, Object> map);

  List<Map<String, Object>> selectMetricByEntityName(StandardMainInfo standardMainInfo);

  String selectMapping(Map<String, Object> map);

  String insertMapping(Map<String, Object> map);

  String deleteMapping(Map<String, Object> map);

  String insertStandard(Map<String, Object> map);

  String deleteStandard(Map<String, Object> map);

  String insertStandardRelation(Map<String, Object> map);

  String deleteStandardRelation(Map<String, Object> map);

  String selectStandardRelation(Map<String, Object> map);

  String selectOperation(Map<String, Object> map);

  String getTermEntityId(String type);

  String deleteTarget(Map<String, Object> map);
}
