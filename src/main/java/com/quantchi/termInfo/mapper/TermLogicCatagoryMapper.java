package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.TermLogicCatagory;
import com.quantchi.termInfo.pojo.TermLogicCatagoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermLogicCatagoryMapper {
    int deleteByExample(TermLogicCatagoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TermLogicCatagory record);

    int insertSelective(TermLogicCatagory record);

    List<TermLogicCatagory> selectByExample(TermLogicCatagoryExample example);

    TermLogicCatagory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TermLogicCatagory record, @Param("example") TermLogicCatagoryExample example);

    int updateByExample(@Param("record") TermLogicCatagory record, @Param("example") TermLogicCatagoryExample example);

    int updateByPrimaryKeySelective(TermLogicCatagory record);

    int updateByPrimaryKey(TermLogicCatagory record);
}