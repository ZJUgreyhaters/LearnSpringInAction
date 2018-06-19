package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.KeyInfo;
import com.quantchi.metadatamgr.data.entity.DSFieldRelDB;
import com.quantchi.metadatamgr.data.entity.DSFieldRelDBExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface DSFieldRelDBMapper {
    int deleteByExample(DSFieldRelDBExample example);

    int deleteByPrimaryKey(Integer relationId);

    int insert(DSFieldRelDB record);

    int insertSelective(DSFieldRelDB record);

    List<DSFieldRelDB> selectByExample(DSFieldRelDBExample example);

    DSFieldRelDB selectByPrimaryKey(Integer relationId);

    int updateByExampleSelective(@Param("record") DSFieldRelDB record, @Param("example") DSFieldRelDBExample example);

    int updateByExample(@Param("record") DSFieldRelDB record, @Param("example") DSFieldRelDBExample example);

    int updateByPrimaryKeySelective(DSFieldRelDB record);

    int updateByPrimaryKey(DSFieldRelDB record);

    int insertReleations(@Param("tableName")String tableName,@Param("field")String field, @Param("foreignTable")String foreignTable, @Param("foreignFieldId")String foreignFieldId);
}