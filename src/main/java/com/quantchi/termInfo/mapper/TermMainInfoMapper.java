package com.quantchi.termInfo.mapper;


import com.quantchi.termInfo.pojo.TermMainInfo;
import com.quantchi.termInfo.pojo.TermMainInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermMainInfoMapper {
    int deleteByExample(TermMainInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TermMainInfo record);

    int insertTerms(List<TermMainInfo> list);

    int insertSelective(TermMainInfo record);

    List<TermMainInfo> selectByExampleWithBLOBs(TermMainInfoExample example);

    List<TermMainInfo> selectByExample(TermMainInfoExample example);

    TermMainInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TermMainInfo record, @Param("example") TermMainInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") TermMainInfo record, @Param("example") TermMainInfoExample example);

    int updateByExample(@Param("record") TermMainInfo record, @Param("example") TermMainInfoExample example);

    int updateByPrimaryKeySelective(TermMainInfo record);

    int updateByPrimaryKeyWithBLOBs(TermMainInfo record);

    int updateByPrimaryKey(TermMainInfo record);

    void update(TermMainInfo record);
}