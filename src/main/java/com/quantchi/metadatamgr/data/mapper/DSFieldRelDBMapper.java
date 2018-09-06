package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSFieldRelDB;
import com.quantchi.metadatamgr.data.entity.DSFieldRelDBExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DSFieldRelDBMapper {

  int deleteByExample(DSFieldRelDBExample example);

  int deleteByPrimaryKey(Integer relationId);

  int insert(Map<String, Object> map);

  int insertSelective(DSFieldRelDB record);

  List<DSFieldRelDB> selectByExample(DSFieldRelDBExample example);

  DSFieldRelDB selectByPrimaryKey(Integer relationId);

  int updateByExampleSelective(@Param("record") DSFieldRelDB record,
      @Param("example") DSFieldRelDBExample example);

  int updateByExample(@Param("record") DSFieldRelDB record,
      @Param("example") DSFieldRelDBExample example);

  int updateByPrimaryKeySelective(DSFieldRelDB record);

  int updateByPrimaryKey(Map<String, Object> map);

  int insertReleations(@Param("tableName") String tableName, @Param("field") String field,
      @Param("foreignTable") String foreignTable, @Param("foreignFieldId") String foreignFieldId,
      @Param("isprimary") Integer isprimary);

  List<Map<String, String>> foreignkeys(Map<String, Object> map);

  List<Map<String, String>> selectReleation(@Param("ids") String id);
}