package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSMetaInfoDB;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDBExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface DSMetaInfoDBMapper {

  int deleteByExample(@Param("example") DSMetaInfoDBExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(DSMetaInfoDB record);

  int insertSelective(DSMetaInfoDB record);

  //List<DSMetaInfoDB> selectByExample(@Param("example")DSMetaInfoDBExample example);

  List<DSMetaInfoDB> selectByExample(@Param("example") DSMetaInfoDBExample example,
      @Param("start") Integer start, @Param("pagesize") Integer pagesize);

  List<DSMetaInfoDB> selectAllByExample(@Param("example") DSMetaInfoDBExample example);

  DSMetaInfoDB selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") DSMetaInfoDB record,
      @Param("example") DSMetaInfoDBExample example);

  int updateByExample(@Param("record") DSMetaInfoDB record,
      @Param("example") DSMetaInfoDBExample example);

  int updateByPrimaryKeySelective(DSMetaInfoDB record);

  int updateByPrimaryKey(DSMetaInfoDB record);

 List<Map<String, Object>> loadSheet(@Param("dsName") String dsName);

  List<Map<String, Object>> selectFieldsByName(@Param("tableEnglishName") String tableEnglishName);
}