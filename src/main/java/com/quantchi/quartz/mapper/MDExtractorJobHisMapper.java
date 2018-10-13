package com.quantchi.quartz.mapper;

import com.quantchi.quartz.entity.MDExtractorJobHis;

import java.util.List;
import java.util.Map;

public interface MDExtractorJobHisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MDExtractorJobHis record);

    int insertSelective(MDExtractorJobHis record);

    MDExtractorJobHis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MDExtractorJobHis record);

    int updateByPrimaryKey(MDExtractorJobHis record);

    List<MDExtractorJobHis> selectBySelective(Map<String, Object> record);

    int getJobHisCount(Map<String, Object> condition);

    int deleteByKeys(List<String> jobHisIds);
}