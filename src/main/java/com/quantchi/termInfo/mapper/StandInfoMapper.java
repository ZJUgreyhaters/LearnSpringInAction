package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 49537 on 2018/7/27.
 */
public interface StandInfoMapper {

  List<Map<String, Object>> selectListCategory(StandardMainInfo standardMainInfo);

  List<Map<String, Object>> selectList(StandardMainInfo standardMainInfo);

  List<Map<String, Object>> selectThreeId(@Param("id") String id);

}
