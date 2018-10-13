package com.quantchi.metadatamgr.data.mapper;

import java.util.List;
import java.util.Map;

public interface MDCheckRuleMapper {


  List<Map<String, Object>> selectBusin(Map<String, Object> map);

  List<Map<String, Object>> selectTech( Map<String, Object> map);

}
