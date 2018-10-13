package com.quantchi.quartz.serviceImpl;

import com.quantchi.common.Util;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDB;
import com.quantchi.quartz.entity.MDExtractorBase;
import com.quantchi.quartz.entity.MDExtractorJob;
import com.quantchi.quartz.entity.MDExtractorJobManual;
import com.quantchi.quartz.entity.TriggerInfo;
import com.quantchi.quartz.exception.BadCronExpressionException;
import com.quantchi.quartz.exception.JobAlreadyExistsException;
import com.quantchi.quartz.exception.JobSchedulerException;
import com.quantchi.quartz.mapper.MDExtractorJobManualMapper;
import com.quantchi.quartz.mapper.MDExtractorJobMapper;
import com.quantchi.quartz.service.JobManualFileService;
import com.quantchi.quartz.service.JobSchedulingInfoService;
import com.quantchi.quartz.util.JobConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobManualFileServiceImpl extends JobBaseServiceImpl<MDExtractorJobManualMapper> implements JobManualFileService{

    private static final Logger logger = LoggerFactory.getLogger(JobManualFileServiceImpl.class);

    public List<Map<String,String>> searchJob(String keyword,List<String> fileTpl,String page,String page_size){
        Map<String,Object> searchCondition = new HashMap<>();
        //searchCondition.put("fileTpl",Util.chkEmptyStringToNull(fileTpl));
        searchCondition.put("fileTpl",fileTpl);
        searchCondition.put("keyword",Util.chkEmptyStringToNull(keyword));

        if(page!=null){
            int start = Integer.parseInt(page);
            int end = Integer.parseInt(page_size);
            searchCondition.put("page",(start-1)*end);
            searchCondition.put("page_size",end);
        }

        return  mdJobBaseMapper.selectBySelective(searchCondition);
    }

    public int getJobCount(String keyword,List<String> fileTpl){
        Map<String,Object> searchCondition = new HashMap<>();
        searchCondition.put("fileTpl", Util.chkEmptyStringToNull(fileTpl));
        searchCondition.put("keyword",Util.chkEmptyStringToNull(keyword));
        return  mdJobBaseMapper.selectCountJob(searchCondition);
    }

    @Override
    public Map<String, String> createJob(MDExtractorJobManual job) {
        Map<String,String> ret = new HashMap<>();
        try {
            job.setJobClzName("com.quantchi.quartz.job.ManualFileCollectorJob");
            String quartzId = buildQuartzJob(job);
            job.setQuartzId(quartzId);
            if(mdJobBaseMapper.insert(job) > 0){
                ret.put("data",job.getJobName());
            }

        }catch (JobSchedulerException e) {
            ret.put("error",e.getMessage());
            logger.error("SchedulerException error: {}",e);
        } catch (JobAlreadyExistsException e) {
            ret.put("error",e.getMessage());
            logger.error("JobAlreadyExistsException error: {}",e);
        } catch (ClassNotFoundException e) {
            ret.put("error",e.getMessage());
            logger.error("ClassNotFoundException error: {}",e);
        } catch (BadCronExpressionException e) {
            ret.put("error",e.getMessage());
            logger.error("BadCronExpressionException error: {}",e);
        }

        return ret;
    }

    @Override
    public Map<String, String> updateJob(MDExtractorJobManual job) {
        return null;
    }

    @Override
    public Map<String, String> deleteJobs(List<MDExtractorJobManual> jobs) {
        return deleteJobsBase(jobs);
    }

    @Override
    public Map<String, String> fireJobNow(List<MDExtractorJobManual> jobs) {
        return null;
    }

    @Override
    protected TriggerInfo buildQuartzTriggerInfo(MDExtractorBase job){
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.setName(job.getJobName());
        triggerInfo.setGroup(JobConstant.DEFAULTGROUP);
        triggerInfo.setStartNow(true);
        return  triggerInfo;
    }

    @Override
    protected void setJobParam(MDExtractorBase job){
        job.getJobParam().put("filepath",((MDExtractorJobManual)job).getFilepath());
        job.getJobParam().put("fileTpl",((MDExtractorJobManual)job).getFileTpl());
        job.getJobParam().put("type",JobConstant.JOB_COLLECTOR_MANUAL_TYPE);
    }
}
