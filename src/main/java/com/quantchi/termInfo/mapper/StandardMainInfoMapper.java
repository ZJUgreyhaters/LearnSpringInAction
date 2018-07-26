package com.quantchi.termInfo.mapper;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import com.quantchi.termInfo.pojo.StandardMainInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardMainInfoMapper {
    int deleteByExample(StandardMainInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StandardMainInfo record);

    int insertSelective(StandardMainInfo record);

    List<StandardMainInfo> selectByExampleWithBLOBs(StandardMainInfoExample example);

    List<StandardMainInfo> selectByExample(StandardMainInfoExample example);

    StandardMainInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StandardMainInfo record, @Param("example") StandardMainInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") StandardMainInfo record, @Param("example") StandardMainInfoExample example);

    int updateByExample(@Param("record") StandardMainInfo record, @Param("example") StandardMainInfoExample example);

    int updateByPrimaryKeySelective(StandardMainInfo record);

    int updateByPrimaryKeyWithBLOBs(StandardMainInfo record);

    int updateByPrimaryKey(StandardMainInfo record);
}