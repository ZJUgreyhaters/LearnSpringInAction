package com.quantchi.quartz.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.AppProperties;
import com.quantchi.common.Util;
import com.quantchi.quartz.entity.JobInfo;
import com.quantchi.quartz.entity.MDExtractorJobHis;
import com.quantchi.quartz.mapper.MDExtractorJobHisMapper;
import com.quantchi.quartz.service.JobExtractHisService;
import com.quantchi.quartz.util.JobConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import com.quantchi.tianshu.common.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobExtractHisServiceImpl implements JobExtractHisService {

	private static final Logger logger = LoggerFactory.getLogger(JobExtractHisServiceImpl.class);
	private static final String influxdbUrl = AppProperties.get("influxdb.url");

	@Autowired
	MDExtractorJobHisMapper mdExtractorJobHisMapper;

	@Override
	public int updateJobHisStatus(MDExtractorJobHis jobHis) {
		return mdExtractorJobHisMapper.updateByPrimaryKeySelective(jobHis);
	}

	@Override
	public MDExtractorJobHis createJobHisRecord(JobInfo job){
		MDExtractorJobHis jobHis = new MDExtractorJobHis();
		jobHis.setJobname(job.getName());
		//可以通过其他途径拿job id
		jobHis.setExtractortype(job.getJobParams().get("type").toString());
		//jobHis.setExtractortype(JobConstant.JOB_HIVE_COLLECTOR_AUTO_TYPE);
		jobHis.setStarttime(Util.dateToString(new Date()));
		jobHis.setLogId(getHisLogId());
		jobHis.setStatus(JobConstant.JOB_COLLECTOR_DOING);
		if(mdExtractorJobHisMapper.insert(jobHis) > 0)
			return jobHis;
		else
			return null;

	}

	@Override
	public Entry<Integer,List<MDExtractorJobHis>> searchJobHis(String extractType, String status, String keyword, String page, String page_size){
		Map<String,Object> searchCondition = new HashMap<>();
		searchCondition.put("extractortype",Util.chkEmptyStringToNull(extractType));
		searchCondition.put("status",Util.chkEmptyStringToNull(status));
		searchCondition.put("keyword",Util.chkEmptyStringToNull(keyword));

		Map<String,Object> searchConditionWithPage = new HashMap<>();
		searchConditionWithPage.putAll(searchCondition);
		if(page!=null){
			int start = Integer.parseInt(page);
			int end = Integer.parseInt(page_size);
			searchConditionWithPage.put("page",(start-1)*end);
			searchConditionWithPage.put("page_size",end);
		}

		Entry<Integer,List<MDExtractorJobHis>> ret = new SimpleEntry<>(mdExtractorJobHisMapper.getJobHisCount(searchCondition)
											,mdExtractorJobHisMapper.selectBySelective(searchConditionWithPage));
		return ret;
	}

	@Override
	public int deleteJobHis(List<String> jobHisIds){
		//maybe need to delete detail log store
		return mdExtractorJobHisMapper.deleteByKeys(jobHisIds);
	}

	@Override
	public List<Object> getHisLogById(String logId) throws Exception{
		try{
			String sql = "SELECT message FROM "+JobConstant.INFLUXDBNAME+" WHERE log_id='"+logId+"'";
			String url = influxdbUrl + URLEncoder.encode(sql,"utf-8");
			String ret = HttpClient.getInstance().get(url);
			JSONObject logs = JSONObject.parseObject(ret);
				JSONObject detail = (JSONObject)logs.getJSONArray("results").get(0);
			if(detail.containsKey("series")){
				JSONObject series = (JSONObject) detail.getJSONArray("series").get(0);
				return series.getJSONArray("values");
			}else
				return null;

		}catch (UnsupportedEncodingException e) {
			logger.error("url encodeing log error,{}",e.getMessage());
			throw  new Exception("url encodeing error:"+e.getMessage());
		}
	}

	private String getHisLogId(){
		return Thread.currentThread().getName();
	}
}
