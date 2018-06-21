package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.TermInfoPojo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 49537 on 2018/6/20.
 */
public interface TermInfoMapper {

  List<Map<String,Object>> selectTermAll(TermInfoPojo termInfoPojo);

  List<Map<String,Object>> selectUdc();

  List<Map<String,Object>> selectTerm(TermInfoPojo termInfoPojo);

  List<Map<String,Object>> selectTermUdbc(@Param("entityId") String str1);

  List<Map<String,Object>> selectTermPhysical(@Param("physicalTable") String str1);
}