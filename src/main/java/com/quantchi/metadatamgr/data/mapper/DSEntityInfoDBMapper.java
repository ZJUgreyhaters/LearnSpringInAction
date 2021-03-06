package com.quantchi.metadatamgr.data.mapper;

import com.quantchi.metadatamgr.data.entity.DSEntityInfoDB;
import com.quantchi.metadatamgr.data.entity.DSEntityInfoDBExample;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DSEntityInfoDBMapper {
    int deleteByExample(DSEntityInfoDBExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Map<String, String> map);

    int insertDomain(Map<String, Object> record);

    int insertSelective(DSEntityInfoDB record);

    List<DSEntityInfoDB> selectByExample(DSEntityInfoDBExample example);

    DSEntityInfoDB selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DSEntityInfoDB record, @Param("example") DSEntityInfoDBExample example);

    int updateByExample(@Param("record") DSEntityInfoDB record, @Param("example") DSEntityInfoDBExample example);

    int updateByPrimaryKeySelective(Map<String, String> map);

    int updateByPrimaryKey(DSEntityInfoDB record);

    List<Map<String,Object>> getEntityName(Map<String, String> map);

    List<Map<String,Object>> selectEntityInfo(Map<String, String> map);
}