package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.TermLogicField;
import com.quantchi.termInfo.pojo.TermLogicFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermLogicFieldMapper {
    int deleteByExample(TermLogicFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TermLogicField record);

    int insertSelective(TermLogicField record);

    List<TermLogicField> selectByExampleWithBLOBs(TermLogicFieldExample example);

    List<TermLogicField> selectByExample(TermLogicFieldExample example);

    TermLogicField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TermLogicField record, @Param("example") TermLogicFieldExample example);

    int updateByExampleWithBLOBs(@Param("record") TermLogicField record, @Param("example") TermLogicFieldExample example);

    int updateByExample(@Param("record") TermLogicField record, @Param("example") TermLogicFieldExample example);

    int updateByPrimaryKeySelective(TermLogicField record);

    int updateByPrimaryKeyWithBLOBs(TermLogicField record);

    int updateByPrimaryKey(TermLogicField record);
}