package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDBExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DSTableInfoDBMapper {
    int deleteByExample(DSTableInfoDBExample example);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("tableEnglishName") String tableEnglishName);

    int insert(DSTableInfoDB record);

    int insertSelective(DSTableInfoDB record);

    List<DSTableInfoDB> selectByExample(DSTableInfoDBExample example);

    DSTableInfoDB selectByPrimaryKey(@Param("id") Integer id, @Param("tableEnglishName") String tableEnglishName);

    int updateByExampleSelective(@Param("record") DSTableInfoDB record, @Param("example") DSTableInfoDBExample example);

    int updateByExample(@Param("record") DSTableInfoDB record, @Param("example") DSTableInfoDBExample example);

    int updateByPrimaryKeySelective(DSTableInfoDB record);

    int updateByPrimaryKey(DSTableInfoDB record);

    int insertTables(List<Map<String,String>> list);
}