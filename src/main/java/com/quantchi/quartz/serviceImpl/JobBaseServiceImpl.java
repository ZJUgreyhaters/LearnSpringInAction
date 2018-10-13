package com.quantchi.quartz.serviceImpl;

import com.quantchi.metadatamgr.data.mapper.DSMetaInfoDBMapper;
import com.quantchi.quartz.core.SchedulerFactory;
import com.quantchi.quartz.core.SchedulerHelper;
import com.quantchi.quartz.entity.*;
import com.quantchi.quartz.exception.BadCronExpressionException;
import com.quantchi.quartz.exception.JobAlreadyExistsException;
import com.quantchi.quartz.exception.JobSchedulerException;
import com.quantchi.quartz.mapper.JobBaseMapper;
import com.quantchi.quartz.util.JobConstant;
import com.quantchi.quartz.util.JobHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobBaseServiceImpl<T extends JobBaseMapper>{

	private static final Logger logger = LoggerFactory.getLogger(JobBaseServiceImpl.class);

	@Autowired
	T mdJobBaseMapper;

	@Autowired
	DSMetaInfoDBMapper dsMetaInfoDBMapper;


	protected SchedulerHelper schedulerHelper = SchedulerFactory.getDefaultScheduler();

	public Map<String,String> deleteJobsBase(List<? extends MDExtractorBase> jobs){
		Map<String,String> ret = new HashMap<>();
		for(MDExtractorBase job:jobs){
			try{
				if(schedulerHelper.getJobDetails(job.getJobName()).getJobDetail()!= null)
					schedulerHelper.removeJob(job.getJobName());
				if(mdJobBaseMapper.deleteByPrimaryKey(job.getId())>0)
					ret.put(job.getJobName(),"delete success");
				else
					ret.put(job.getJobName(),"delete failed");
			}catch (JobSchedulerException e){
				ret.put(job.getJobName(),"delete failed");
				logger.error("SchedulerException error happend: {}",e);
			}
		}
		return ret;
	}

	protected JobInfo buildQuartzJobInfo(MDExtractorBase job){
		JobInfo jobInfo = new JobInfo();
		jobInfo.setName(job.getJobName());
		jobInfo.setGroup(JobConstant.DEFAULTGROUP);
		jobInfo.setJobClass(job.getJobClzName());
		jobInfo.setStoreDurably(true);
		setJobParam(job);
		jobInfo.setJobParams(job.getJobParam());
		return jobInfo;
	}

	protected TriggerInfo buildQuartzTriggerInfo(MDExtractorBase job){
		return null;

	}

	protected ScheduleInfo buildQuartzScheduleInfo(MDExtractorBase job){
		ScheduleInfo scheduleInfo = new ScheduleInfo();

		//手动设置执行
		TriggerInfo triggerInfo = buildQuartzTriggerInfo(job);
		scheduleInfo.setTriggerInfo(triggerInfo);

		//先不做任何解析调度表达式
		scheduleInfo.setSimpleSchedule(true);
		SimpleSchedulerInfo simpleSchedulerInfo = new SimpleSchedulerInfo();
		simpleSchedulerInfo.setRepeatForever(false);
		scheduleInfo.setSimpleScheduleInfo(simpleSchedulerInfo);

		return scheduleInfo;
	}

	protected String buildQuartzJob(MDExtractorBase job) throws ClassNotFoundException, BadCronExpressionException, JobAlreadyExistsException, JobSchedulerException {

		JobInfo jobInfo = buildQuartzJobInfo(job);
		ScheduleInfo scheduleInfo = buildQuartzScheduleInfo(job);
		RunTimeJobDetail runTimeJobDetail = null;
		if(scheduleInfo.getTriggerInfo() == null){
			runTimeJobDetail = schedulerHelper.addJob(JobHelper.buildJobDetail(jobInfo));
		}else{
			runTimeJobDetail = schedulerHelper.scheduleJob(JobHelper.buildJobDetail(jobInfo),
							JobHelper.buildTrigger(scheduleInfo.getTriggerInfo(), JobHelper.buildSchedulerBuilder(scheduleInfo)));
		}
		return runTimeJobDetail.getJobId();
	}

	protected Map<String,String> fireJobNowBase(List<? extends MDExtractorBase> jobs){
		Map<String,String> rets = new HashMap<>();
		for(MDExtractorBase job : jobs){
			String jobName = job.getJobName();
			try {
				if(schedulerHelper.getJobDetails(jobName).getJobDetail() != null){
					schedulerHelper.triggerJob(jobName);
					rets.put(jobName,"success");
				}else
					rets.put(jobName,"not found :"+jobName+" in quartz");

			} catch (JobSchedulerException e) {
				rets.put(jobName,e.getMessage());
				logger.error("SchedulerException error: {}",e);
			}

		}
		return rets;
	}

	protected void setJobParam(MDExtractorBase job){

	}
}
