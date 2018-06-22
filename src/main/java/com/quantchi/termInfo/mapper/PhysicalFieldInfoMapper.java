package com.quantchi.termInfo.mapper;


import com.quantchi.termInfo.pojo.PhysicalFieldInfo;
import com.quantchi.termInfo.pojo.PhysicalFieldInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhysicalFieldInfoMapper {
    int deleteByExample(PhysicalFieldInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalFieldInfo record);

    int insertFields(List<PhysicalFieldInfo> list);

    int insertSelective(PhysicalFieldInfo record);

    List<PhysicalFieldInfo> selectByExampleWithBLOBs(PhysicalFieldInfoExample example);

    List<PhysicalFieldInfo> selectByExample(PhysicalFieldInfoExample example);

    PhysicalFieldInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhysicalFieldInfo record, @Param("example") PhysicalFieldInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") PhysicalFieldInfo record, @Param("example") PhysicalFieldInfoExample example);

    int updateByExample(@Param("record") PhysicalFieldInfo record, @Param("example") PhysicalFieldInfoExample example);

    int updateByPrimaryKeySelective(PhysicalFieldInfo record);

    int updateByPrimaryKeyWithBLOBs(PhysicalFieldInfo record);

    int updateByPrimaryKey(PhysicalFieldInfo record);
}