package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.MDSchemaDiffJob;

public interface MDSchemaDiffJobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MDSchemaDiffJob record);

    int insertSelective(MDSchemaDiffJob record);

    MDSchemaDiffJob selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MDSchemaDiffJob record);

    int updateByPrimaryKey(MDSchemaDiffJob record);
}