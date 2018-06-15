package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSFieldRelDB;
import com.quantchi.metadatamgr.data.entity.DSFieldRelDBExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}