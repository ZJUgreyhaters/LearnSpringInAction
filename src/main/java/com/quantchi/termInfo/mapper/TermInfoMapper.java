package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.TermInfoPojo;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/20.
 */
public interface TermInfoMapper {

  List<Map<String,Object>> selectTermAll(TermInfoPojo termInfoPojo);

  List<Map<String,Object>> selectUdc(TermInfoPojo termInfoPojo);

}
