package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.TermLogicFieldDraft;
import com.quantchi.termInfo.pojo.TermLogicFieldDraftExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermLogicFieldDraftMapper {
    int deleteByExample(TermLogicFieldDraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TermLogicFieldDraft record);

    int insertSelective(TermLogicFieldDraft record);

    List<TermLogicFieldDraft> selectByExampleWithBLOBs(TermLogicFieldDraftExample example);

    List<TermLogicFieldDraft> selectByExample(TermLogicFieldDraftExample example);

    TermLogicFieldDraft selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TermLogicFieldDraft record, @Param("example") TermLogicFieldDraftExample example);

    int updateByExampleWithBLOBs(@Param("record") TermLogicFieldDraft record, @Param("example") TermLogicFieldDraftExample example);

    int updateByExample(@Param("record") TermLogicFieldDraft record, @Param("example") TermLogicFieldDraftExample example);

    int updateByPrimaryKeySelective(TermLogicFieldDraft record);

    int updateByPrimaryKeyWithBLOBs(TermLogicFieldDraft record);

    int updateByPrimaryKey(TermLogicFieldDraft record);
}