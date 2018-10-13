package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.MDCheckBusiRule;

public interface MDCheckBusiRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MDCheckBusiRule record);

    int insertSelective(MDCheckBusiRule record);

    MDCheckBusiRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MDCheckBusiRule record);

    int updateByPrimaryKey(MDCheckBusiRule record);
}