package com.quantchi.quartz.service;

import com.quantchi.quartz.entity.MDExtractorJob;
import com.quantchi.quartz.entity.MDExtractorJobManual;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobSchedulingInfoService
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 15:35
 * @Version 1.0.0
 **/

public interface JobManualFileService extends JobBaseService<MDExtractorJobManual> {

    List<Map<String,String>> searchJob(String keyword,List<String> fileTpl, String page, String page_size);

    int getJobCount(String keyword,List<String> fileTpl);
}
