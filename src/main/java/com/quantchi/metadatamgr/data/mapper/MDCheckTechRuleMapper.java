package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.MDCheckTechRule;

public interface MDCheckTechRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByBusinessId(String businId);

    int insert(MDCheckTechRule record);

    int insertSelective(MDCheckTechRule record);

    MDCheckTechRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MDCheckTechRule record);

    int updateByPrimaryKeyWithBLOBs(MDCheckTechRule record);

    int updateByPrimaryKey(MDCheckTechRule record);
}