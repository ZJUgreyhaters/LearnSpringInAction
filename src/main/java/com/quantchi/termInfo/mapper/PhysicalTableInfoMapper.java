package com.quantchi.termInfo.mapper;


import com.quantchi.termInfo.pojo.PhysicalTableInfo;
import com.quantchi.termInfo.pojo.PhysicalTableInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhysicalTableInfoMapper {
    int deleteByExample(PhysicalTableInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalTableInfo record);

    int insertSelective(PhysicalTableInfo record);

    List<PhysicalTableInfo> selectByExampleWithBLOBs(PhysicalTableInfoExample example);

    List<PhysicalTableInfo> selectByExample(PhysicalTableInfoExample example);

    PhysicalTableInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhysicalTableInfo record, @Param("example") PhysicalTableInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") PhysicalTableInfo record, @Param("example") PhysicalTableInfoExample example);

    int updateByExample(@Param("record") PhysicalTableInfo record, @Param("example") PhysicalTableInfoExample example);

    int updateByPrimaryKeySelective(PhysicalTableInfo record);

    int updateByPrimaryKeyWithBLOBs(PhysicalTableInfo record);

    int updateByPrimaryKey(PhysicalTableInfo record);
}