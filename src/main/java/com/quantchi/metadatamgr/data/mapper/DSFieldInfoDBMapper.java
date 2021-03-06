package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSFieldInfoDB;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDBExample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DSFieldInfoDBMapper {

  int deleteByExample(DSFieldInfoDBExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(DSFieldInfoDB record);

  int insertSelective(DSFieldInfoDB record);

  List<DSFieldInfoDB> selectByExample(DSFieldInfoDBExample example);

  DSFieldInfoDB selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") DSFieldInfoDB record,
      @Param("example") DSFieldInfoDBExample example);

  int updateByExample(@Param("record") DSFieldInfoDB record,
      @Param("example") DSFieldInfoDBExample example);

  int updateByPrimaryKeySelective(DSFieldInfoDB record);

  int updateByPrimaryKey(DSFieldInfoDB record);

  int insertFields(List<Map<String, Object>> list);

  @MapKey("key")
  Map<String, Object> selectAll(List<Map<String, Object>> list);

  List<Map<String, Object>> selectFieldInfo(Map<String, Object> map);

  List<DSFieldInfoDB> selectFieldInfoBytableId(Map<String, Object> map);

  Map<String, Object> selectFieldInfoById(Map<String, String> map);
}