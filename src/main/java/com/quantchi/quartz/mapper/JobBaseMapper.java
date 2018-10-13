package com.quantchi.quartz.mapper;

import java.util.List;
import java.util.Map;

public interface JobBaseMapper<T> {
	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	//int insertSelective(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

	//int updateByPrimaryKey(T record);

	List<Map<String,String>> selectBySelective(Map<String, Object> record);

	int deleteByKeys(List<String> jobIds);

	int selectCountJob(Map<String, Object> condition);
}
