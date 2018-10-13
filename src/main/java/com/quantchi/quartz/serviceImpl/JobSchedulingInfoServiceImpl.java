package com.quantchi.quartz.serviceImpl;

import com.quantchi.common.Util;
import com.quantchi.metadatamgr.data.entity.DSMetaInfoDB;
import com.quantchi.quartz.entity.*;
import com.quantchi.quartz.exception.BadCronExpressionException;
import com.quantchi.quartz.exception.JobAlreadyExistsException;
import com.quantchi.quartz.exception.JobSchedulerException;
import com.quantchi.quartz.mapper.MDExtractorJobMapper;
import com.quantchi.quartz.service.JobSchedulingInfoService;
import com.quantchi.quartz.util.JobConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobSchedulingInfoServiceImpl
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 15:39
 * @Version 1.0.0
 **/
@Service
public class JobSchedulingInfoServiceImpl extends JobBaseServiceImpl<MDExtractorJobMapper> implements JobSchedulingInfoService{

    private static final Logger logger = LoggerFactory.getLogger(JobSchedulingInfoServiceImpl.class);

    public Map<String,String> createJob(MDExtractorJob job){
        Map<String,String> ret = new HashMap<>();
        try {
            job.setJobClzName(JobConstant.ClzName);
            String quartzId = buildQuartzJob(job);
            job.setQuartzId(quartzId);
            if(mdJobBaseMapper.insert(job) > 0){
							ret.put("data",job.getJobName());

							DSMetaInfoDB record = new DSMetaInfoDB();
							record.setId(Integer.parseInt(job.getDatasourceId()));
							record.setMountNodeId(job.getParentTreeId());
							dsMetaInfoDBMapper.updateByPrimaryKeySelective(record);
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

    public Map<String, String> fireJobNow(List<MDExtractorJob> jobs) {
        return fireJobNowBase(jobs);
    }

    public Map<String,String> updateJob(MDExtractorJob job){
        Map<String,String> ret = new HashMap<>();
        //for(MDExtractorJob job : jobs){
            if(mdJobBaseMapper.updateByPrimaryKeySelective(job) > 0)
                ret.put(job.getId().toString(),"update success");
            else
                ret.put(job.getId().toString(),"update false");
        //}
        return ret;
    }

    public Map<String,String> changeJobSt(List<MDExtractorJob> jobs){
        Map<String,String> rets = new HashMap<>();
        for(MDExtractorJob job : jobs){
            job.setStatus(switchJobSt(job.getStatus()));
            //maybe should call standby func in quartz
            if(mdJobBaseMapper.updateByPrimaryKeySelective(job) > 0)
                rets.put(job.getId().toString(),"change success");
            else
                rets.put(job.getId().toString(),"change false");
        }
        return rets;
    }

    public List<Map<String,String>> searchJob(String datasourceId,String status,String keyword,String page,String page_size){
        Map<String,Object> searchCondition = new HashMap<>();
        searchCondition.put("datasourceId",Util.chkEmptyStringToNull(datasourceId));
        searchCondition.put("status",Util.chkEmptyStringToNull(status));
        searchCondition.put("keyword",Util.chkEmptyStringToNull(keyword));

        if(page!=null){
            int start = Integer.parseInt(page);
            int end = Integer.parseInt(page_size);
            searchCondition.put("page",(start-1)*end);
            searchCondition.put("page_size",end);
        }

        return  mdJobBaseMapper.selectBySelective(searchCondition);
    }

    public Map<String, String> deleteJobs(List<MDExtractorJob> jobs) {
        return deleteJobsBase(jobs);
    }

    public int getJobCount(String datasourceId,String status,String keyword){
        Map<String,Object> searchCondition = new HashMap<>();
        searchCondition.put("datasourceId", Util.chkEmptyStringToNull(datasourceId));
        searchCondition.put("status",Util.chkEmptyStringToNull(status));
        searchCondition.put("keyword",Util.chkEmptyStringToNull(keyword));
        return  mdJobBaseMapper.selectCountJob(searchCondition);
    }

    private String switchJobSt(String st){
        if(JobConstant.ACTIVE.equals(st))
            return JobConstant.UNACTIVE;
        else
            return JobConstant.ACTIVE;
    }

    @Override
    protected void setJobParam(MDExtractorBase job){
        job.getJobParam().put("datasourceId",job.getDatasourceId());
        job.getJobParam().put("type",JobConstant.JOB_HIVE_COLLECTOR_AUTO_TYPE);
    }

}
