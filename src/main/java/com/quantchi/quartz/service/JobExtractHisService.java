package com.quantchi.quartz.service;

import com.quantchi.quartz.entity.JobInfo;
import com.quantchi.quartz.entity.MDExtractorJobHis;
import java.util.List;
import java.util.Map.Entry;

public interface JobExtractHisService {

  MDExtractorJobHis createJobHisRecord(JobInfo job);

  int updateJobHisStatus(MDExtractorJobHis jobHis);

  Entry<Integer, List<MDExtractorJobHis>> searchJobHis(String extractType, String status,
      String keyword, String page, String page_size);

  int deleteJobHis(List<String> jobHisIds);

  List<Object> getHisLogById(String logId) throws Exception;
}
