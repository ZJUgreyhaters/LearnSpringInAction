package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import java.util.List;
import java.util.Map;

import com.quantchi.termInfo.pojo.TermMainInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 49537 on 2018/7/27.
 */
public interface StandInfoMapper {

  List<Map<String, Object>> selectListCategory(Map<String, Object> mapCategory);

  List<Map<String, Object>> selectList(StandardMainInfo standardMainInfo);

  List<Map<String, Object>> selectThreeId(@Param("id") String id);
  //
  List<Map<String, Object>> selectMetric(TermMainInfo termMainInfo);

  List<Map<String, Object>> selectCodeDefinition(Map<String, Object> map);
  //
  List<Map<String, Object>> selectBusiness(Map<String, Object> map);

  List<Map<String, Object>> selectPhysicalProperty(Map<String, Object> map);

  List<Map<String, Object>> selectIdByDomainId(StandardMainInfo standardMainInfo);

  List<Map<String, Object>> selectMetricByEntityName(StandardMainInfo standardMainInfo);

  Integer getMaxNum(String prev);
}
