package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDBExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DSTableInfoDBMapper {

  int deleteByExample(DSTableInfoDBExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(DSTableInfoDB record);

  int insertSelective(DSTableInfoDB record);

  List<DSTableInfoDB> selectByExample(DSTableInfoDBExample example);

  DSTableInfoDB selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") DSTableInfoDB record,
      @Param("example") DSTableInfoDBExample example);

  int updateByExample(@Param("record") DSTableInfoDB record,
      @Param("example") DSTableInfoDBExample example);

  int updateByPrimaryKeySelective(DSTableInfoDB record);

  int updateByPrimaryKey(DSTableInfoDB record);

  List<DSTableInfoDB> search(DSTableInfoDB tableInfo);

  void delete(DSTableInfoDB tableInfo);

  void update(DSTableInfoDB tableInfo);

  int insertTables(List<Map<String,String>> list);

  List<Map<String,Object>> selectTableInfo(Map<String,Object> mapRequest);

}