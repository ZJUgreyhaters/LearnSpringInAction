package com.quantchi.quartz.job;

import com.quantchi.quartz.entity.JobInfo;
import com.quantchi.quartz.exception.JobSchedulerException;
import com.quantchi.quartz.util.JobHelper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class BaseJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(BaseJob.class);

	public BaseJob(){}

	@Override
	public void execute(JobExecutionContext jobExecutionContext)  {
			//set log id first
			setHisRecordId();
			runTasks(JobHelper.buildJobInfo(jobExecutionContext.getJobDetail()));
	}

	protected void runTasks(JobInfo jobInfo) {}

	private void setHisRecordId(){
		Thread.currentThread().setName(UUID.randomUUID().toString().replace("-", ""));
	}
}
