package com.quantchi.termInfo.service;

import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/7/26.
 */
public interface TermFileService {

  void insertStandard(Map<String, Object> map);

  List<Map<String, Object>> selectStandard(Map<String, Object> map);

  void updateStandard(Map<String, Object> map);

  void updatePhysicalFieldChineseName(Map<String, Object> map);

  List<Map<String, Object>> selectPhysicalInfo(Map<String, Object> map);

  void insertPhysicalFile(Map<String, Object> map);

  void updatePhysicalFile(Map<String, Object> map);

  List<Map<String, Object>> selectPhysicalFile(Map<String, Object> map);

  Map<String, Object> selectDomainByName(Map<String, Object> map);

  Map<String, Object> selectStandardCategory(Map<String, Object> map);

  List<Map<String, Object>> selectStandardMain(Map<String, Object> map);

  void insertStandardMain(Map<String, Object> map);

  void updateStandardMain(Map<String, Object> map);

  List<Map<String, Object>> selectTargetMain(Map<String, Object> map);

  void insertTargetMain(Map<String, Object> map);

  void updateTargetMain(Map<String, Object> map);

  void insertOperation(Map<String, Object> map);

  Map<String, Object> selectDominById(Map<String, Object> map);
}
