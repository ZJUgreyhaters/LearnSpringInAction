package com.quantchi.quartz.core;

import com.quantchi.quartz.entity.RunTimeJobDetail;
import com.quantchi.quartz.exception.JobAlreadyExistsException;
import com.quantchi.quartz.exception.JobSchedulerException;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName: SchedulerHelper
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 14:30
 * @Version 1.0.0
 **/
public class SchedulerHelper {
    private org.quartz.Scheduler quartzScheduler;

    private static String DEFAULTGROUP = "default";

    public SchedulerHelper(Scheduler quartzScheduler) {
        this.quartzScheduler = quartzScheduler;
    }

    public RunTimeJobDetail scheduleJob(JobDetail jobDetail, Trigger trigger) throws JobSchedulerException, JobAlreadyExistsException {
        try{
            if(quartzScheduler.checkExists(jobDetail.getKey()))
                throw  new JobAlreadyExistsException(jobDetail.getKey().getName()+" already exists");
            quartzScheduler.scheduleJob(jobDetail, trigger);
            return getJobDetails(jobDetail.getKey().getGroup(), jobDetail.getKey().getName());
        }catch (SchedulerException e){
            throw new JobSchedulerException("schedule job :"+jobDetail.getKey().getName()+" error:"+e.getMessage());
        }

    }

    public RunTimeJobDetail addJob(JobDetail jobDetail) throws JobSchedulerException {
        try {
            quartzScheduler.addJob(jobDetail, true);
            return getJobDetails(jobDetail.getKey().getGroup(), jobDetail.getKey().getName());
        }
        catch (SchedulerException e){
                throw new JobSchedulerException("addJob job :"+jobDetail.getKey().getName()+" error:"+e.getMessage());
            }
    }

    public void removeJob(String jobId) throws JobSchedulerException{
        try{
            quartzScheduler.deleteJob(new JobKey(jobId,DEFAULTGROUP));
        }catch (SchedulerException e){
            throw  new JobSchedulerException("get jobId ["+jobId+"] detail error:"+e.getMessage());
        }

    }

    public void removeJob(JobKey jobKey) throws SchedulerException{
        quartzScheduler.deleteJob(jobKey);
    }

    public void removeAllJobs() throws SchedulerException {
        for (String groupName : quartzScheduler.getJobGroupNames()) {
            for (JobKey jobKey : quartzScheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                quartzScheduler.deleteJob(jobKey);
            }
        }
    }

    public List<RunTimeJobDetail> listAllJobs() throws SchedulerException {
        List<RunTimeJobDetail> jobs = new ArrayList<RunTimeJobDetail>();
        for (String groupName : quartzScheduler.getJobGroupNames()) {
            for (JobKey jobKey : quartzScheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                jobs.add(getJobDetails(groupName, jobKey.getName()));
            }
        }
        return jobs;
    }

    public List<RunTimeJobDetail> searchJobs(String groupExp, String nameExp) throws SchedulerException {
        Pattern groupPattern = Pattern.compile(groupExp);
        Pattern namePattern = Pattern.compile(nameExp);

        List<RunTimeJobDetail> jobs = new ArrayList<RunTimeJobDetail>();
        for (String groupName : quartzScheduler.getJobGroupNames()) {
            if(groupPattern.matcher(groupName).matches()) {
                for (JobKey jobKey : quartzScheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    if(namePattern.matcher(jobKey.getName()).matches()) {
                        jobs.add(getJobDetails(groupName, jobKey.getName()));
                    }
                }
            }
        }
        return jobs;
    }

    /**
     * Search Trigger
     * @param group
     * @param name
     * @return
     * @throws SchedulerException
     */
    public Trigger getTrigger(String group, String name) throws SchedulerException {
        return quartzScheduler.getTrigger(new TriggerKey(name, group));
    }

    public RunTimeJobDetail getJobDetails(String name) throws JobSchedulerException {
        try{
            return getJobDetails(DEFAULTGROUP,name);
        }catch (SchedulerException e){
            throw  new JobSchedulerException("get job ["+name+"] detail error:"+e.getMessage());
        }

    }

    public RunTimeJobDetail getJobDetails(String group, String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        return new RunTimeJobDetail().
                setJobDetail(quartzScheduler.getJobDetail(jobKey)).
                setTriggers(quartzScheduler.getTriggersOfJob(jobKey));
    }

    public void pauseJob(JobKey jobKey) throws SchedulerException {
        quartzScheduler.pauseJob(jobKey);
    }

    public void resumeJob(JobKey jobKey) throws SchedulerException {
        quartzScheduler.resumeJob(jobKey);
    }

    public String buildTriggerName(String metricAlias, String duration, String interval) {
        return String.format("Trigger.%s.%s.%s",metricAlias,duration,interval);
    }

    public void triggerJob(String jobName) throws JobSchedulerException{
        try {
            quartzScheduler.triggerJob(JobKey.jobKey(jobName));
        } catch (SchedulerException e) {
            throw new JobSchedulerException("trigger job:"+jobName+" error:"+e.getMessage());
        }
    }

    public Scheduler getQuartzScheduler() {
        return quartzScheduler;
    }

    public void setQuartzScheduler(Scheduler quartzScheduler) {
        this.quartzScheduler = quartzScheduler;
    }

    public SchedulerHelper standby() throws SchedulerException {
        this.quartzScheduler.standby();
        return this;
    }

    public SchedulerHelper start() throws SchedulerException {
        this.quartzScheduler.start();
        return this;
    }
}
