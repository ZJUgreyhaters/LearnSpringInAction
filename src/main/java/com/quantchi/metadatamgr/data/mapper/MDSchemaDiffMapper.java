package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.MDSchemaDiff;
import java.util.List;

public interface MDSchemaDiffMapper {
    int deleteByPrimaryKey(String version);

    int insert(MDSchemaDiff record);

    int insertSelective(MDSchemaDiff record);

    List<Integer> selectByDatasourceDate(String datasourceId, String version);

    int updateByPrimaryKeySelective(MDSchemaDiff record);

    int updateByPrimaryKeyWithBLOBs(MDSchemaDiff record);

    int updateByPrimaryKey(MDSchemaDiff record);
}