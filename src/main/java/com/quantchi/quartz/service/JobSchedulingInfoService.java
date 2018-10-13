package com.quantchi.quartz.service;

import com.quantchi.quartz.entity.JobSchedulingInfo;
import com.quantchi.quartz.entity.MDExtractorBase;
import com.quantchi.quartz.entity.MDExtractorJob;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobSchedulingInfoService
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 15:35
 * @Version 1.0.0
 **/

public interface JobSchedulingInfoService extends JobBaseService<MDExtractorJob> {

    //Map<String,String> createJob(MDExtractorJob job);

    //Map<String,String> fireJobNow(List<MDExtractorJob> jobs);

    //Map<String,String> updateJob(MDExtractorJob job);

    Map<String,String> changeJobSt(List<MDExtractorJob> jobs);

    List<Map<String,String>> searchJob(String datasourceId,String status,String keyword,String page,String page_size);

    //Map<String,String> deleteJobs(List<MDExtractorJob> jobs);

    int getJobCount(String datasourceId,String status,String keyword);
}
