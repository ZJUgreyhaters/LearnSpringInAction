package com.quantchi.quartz.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;

import com.quantchi.quartz.core.SchedulerFactory;
import com.quantchi.quartz.core.SchedulerHelper;
import com.quantchi.quartz.entity.*;
import com.quantchi.quartz.exception.BadCronExpressionException;
import com.quantchi.quartz.service.JobSchedulingInfoService;
import com.quantchi.quartz.util.JobConstant;
import com.quantchi.quartz.util.JobHelper;
import com.quantchi.quartz.util.ResponseBuilder;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: JobController
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 14:05
 * @Version 1.0.0
 **/

@Controller
@RequestMapping("api")
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    private SchedulerHelper schedulerHelper = SchedulerFactory.getDefaultScheduler();

    @Autowired
    private JobSchedulingInfoService jobSchedulingInfoService;

    /**@api {POST} /api/execJob 立即执行任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/execJob
     * @apiName execJob
     * @apiGroup JobController
     * @apiParam {List} [job]
     * @apiParam {String} job.id 任务ID
     * @apiParam {String} job.jobName 任务ID
     * @apiParam {String} job.state 任务状态
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/
    @ResponseBody
    @RequestMapping(value = "/execJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String execJob(@RequestBody List<MDExtractorJob> jobs) {
        List<MDExtractorJob> jobIds =  jobs.stream().filter(i -> JobConstant.ACTIVE.equals(i.getStatus())).collect(Collectors.toList());
        Map<String,String> ret = jobSchedulingInfoService.fireJobNow(jobIds);
        return JsonResult.successJson(ret);
    }

    /**@api {post} /api/job/updateJob 更新任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/job/updateJob
     * @apiName updateJob
     * @apiGroup JobController
     * @apiParam {List} [job]
     * @apiParam {String} job.id 任务ID
     * @apiParam {String} job.jobName 任务名称
     * @apiParam {String} job.parentTreeId 悬挂节点id
     * @apiParam {String} job.datasourceId 数据源id
     * @apiParam {String} job.datasourceName 数据源名称
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/

    @ResponseBody
    @RequestMapping(value = "/job/updateJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String updateJob(@RequestBody MDExtractorJob jobs) {
        Map<String, String> ret = jobSchedulingInfoService.updateJob(jobs);

        return JsonResult.successJson(ret);
    }

    /**@api {post} /api/job/exchangeState 切换状态接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/job/exchangeState
     * @apiName exchangeState
     * @apiGroup JobController
     * @apiParam {Object[]} job
     * @apiParam {String} job.id 任务ID
     * @apiParam {String} job.status 任务状态
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/

    @ResponseBody
    @RequestMapping(value = "/job/exchangeState", method = {
    RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String changeJobST(@RequestBody List<MDExtractorJob> jobs) {

        Map<String,String> ret = jobSchedulingInfoService.changeJobSt(jobs);
        return JsonResult.successJson(ret);
    }

    /**@api {get} /api/job 获取任务信息列表
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/job
     * @apiName getJob
     * @apiGroup JobController
     * @apiParam {String} [keyword] 任务关键字
     * @apiParam {String} [status] 任务状态  0 未激活 1 激活
     * @apiParam {String} [datasourceId] 数据源
     * @apiParam {String} [page] 分页页码
     * @apiParam {String} [page_size] 分页大小
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 任务信息
     **/
    @ResponseBody
    @RequestMapping(value = "/job", method = {
            RequestMethod.GET})
    public String getJob(@RequestParam(value="datasourceId",required = false) String  datasourceId,
                         @RequestParam(value="status",required = false) String  status,
                         @RequestParam(value="keyword",required = false) String keyword,
                         @RequestParam(value="page",required = false) String page,
                         @RequestParam(value="page_size",required = false) String page_size) {

        List<Map<String,String>> jobs = jobSchedulingInfoService.searchJob(datasourceId,status,keyword,page,page_size);
        int total = jobSchedulingInfoService.getJobCount(datasourceId,status,keyword);
        return JsonResult.successJson(total, jobs);

    }

    /**@api {post} /api/job 添加任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/job
     * @apiName add_job
     * @apiGroup JobController
     * @apiParam {String} jobName 任务名称
     * @apiParam {String} parentTreeId 悬挂节点id
     * @apiParam {String} datasourceId 数据源id
     * @apiParam {int} frequency 频率：0为一次性，1为每天，2为每周，3位每月
     * @apiParam {int[]} whichDay 频率为周和月时，数组中保存一周中或一个月中哪几天触发
     * @apiParam {LocalTime} time 记录每天触发的具体时间
     * @apiParam {LocalDate} startDate 记录开始日期
     * @apiParam {LocalDate} endDate 记录结束日期
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/
    @ResponseBody
    @RequestMapping(value = "/job", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String addJob(@RequestBody MDExtractorJob job) throws Exception {

        Map<String,String> ret = jobSchedulingInfoService.createJob(job);
        if(ret.get("error") == null)
            return JsonResult.successJson(ret.get("data"));
        else
            return JsonResult.errorJson("create job error:"+ret.get("error"));


        /*RunTimeJobDetail runTimeJobDetail;
        try {
            runTimeJobDetail = schedulerHelper.scheduleJob(JobHelper.buildJobDetail(jobSchedulingInfo.getJobInfo()),
                    JobHelper.buildTrigger(jobSchedulingInfo.getScheduleInfo().getTriggerInfo(),
                            JobHelper.buildSchedulerBuilder(jobSchedulingInfo.getScheduleInfo())));
        } catch (BadCronExpressionException e) {
            throw new WebApplicationException(ResponseBuilder.badRequest(e.getLocalizedMessage()));
        }
        return JsonResult.successJson(runTimeJobDetail, ResultCode.SUCCESS, "Add job success.");*/
    }

    @RequestMapping(value = "/addTriggerToJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public RunTimeJobDetail addTriggersToJob(@RequestBody Map<String, Object> requestMap) {
        ScheduleInfo scheduleInfo = (ScheduleInfo) requestMap.get("scheduleInfo");
        String group = (String) requestMap.get("group");
        String name = (String) requestMap.get("name");
        RunTimeJobDetail runTimeJobDetail = null;
        try {
            runTimeJobDetail = jobExistsOrException(group, name);
        } catch (SchedulerException e) {
           logger.info("SchedulerException");
        }
        try {

            schedulerHelper.getQuartzScheduler().scheduleJob(JobHelper.buildTriggerForJob(scheduleInfo.getTriggerInfo(),
                    JobHelper.buildSchedulerBuilder(scheduleInfo), runTimeJobDetail.getJobDetail()));

        } catch (SchedulerException e) {
            logger.info("SchedulerException");
        } catch (BadCronExpressionException e) {
            logger.info("BadCronExpressionException");
        }
        return runTimeJobDetail;
    }


    @ResponseBody
    @RequestMapping(value = "/stopJob", method = {
            RequestMethod.PUT}, produces = "application/json;charset=UTF-8")
    public String stopJob(@RequestBody Map<String, Object> requestMap) throws Exception {
        String jobGroup = (String) requestMap.get("jobGroup");
        String jobName = (String) requestMap.get("jobName");
        String triggerGroup = (String) requestMap.get("triggerGroup");
        String triggerName = (String) requestMap.get("triggerName");

        RunTimeJobDetail jobDetail = schedulerHelper.getJobDetails(jobGroup, jobName);
        if(jobDetail == null) {
            throw new WebApplicationException(ResponseBuilder.resourceNotFound("Job", "{Group:" + jobGroup + ", name:" + jobName + "}"));
        }
        Trigger trigger = jobDetail.getTrigger(triggerGroup, triggerName);

        if(trigger != null) {
            schedulerHelper.getQuartzScheduler().unscheduleJob(trigger.getKey());
        }
        logger.info("Stop Job");
        return JsonResult.successJson(ResultCode.SUCCESS, "Stop job success.");
    }


    @ResponseBody
    @RequestMapping(value = "deleteAllJobs", method = {
            RequestMethod.DELETE}, produces = "application/json;charset=UTF-8")
    public String deleteAllJobs() {
        try {
            schedulerHelper.removeAllJobs();
        } catch (SchedulerException e) {
            logger.info("delete all jobs error");
        }
        logger.info("delete all jobs");
        return JsonResult.successJson(ResultCode.SUCCESS, "delete all jobs success.");
    }

    /**@api {post} /api/deleteJob 删除任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleteJob
     * @apiName deleteJob
     * @apiGroup JobController
     * @apiParam {Object[]} data
     * @apiParam {String} data.id 任务列表
     * @apiParam {String} data.jobName 任务列表
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/

    @ResponseBody
    @RequestMapping(value = "/deleteJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String deleteJob(@RequestBody List<MDExtractorJob> jobIds) {

        Map<String,String> ret = jobSchedulingInfoService.deleteJobs(jobIds);
        return JsonResult.successJson(ret);
       /* String group = (String) requestMap.get("group");
        String name = (String) requestMap.get("name");
        try {
            jobExistsOrException(group, name);
            schedulerHelper.removeJob(new JobKey(name, group));
        } catch (SchedulerException e) {
            logger.info("delete job error");
        }
        logger.info("delete job success.");
        return JsonResult.successJson(ResultCode.SUCCESS, "delete job success.");*/
    }

    private RunTimeJobDetail jobExistsOrException(String group, String name) throws SchedulerException {
        RunTimeJobDetail runTimeJobDetail = schedulerHelper.getJobDetails(group, name);
        if(runTimeJobDetail == null)
            throw new WebApplicationException(ResponseBuilder.resourceNotFound("Job", "{Group:" + group + ", name:" + name + "}"));
        return runTimeJobDetail;
    }
}
