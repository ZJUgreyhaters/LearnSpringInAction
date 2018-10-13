package com.quantchi.quartz.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.quartz.core.SchedulerFactory;
import com.quantchi.quartz.core.SchedulerHelper;
import com.quantchi.quartz.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.WebApplicationException;
import java.util.Map;

/**
 * @ClassName: SchedulerController
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 19:42
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("api")
public class SchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @ResponseBody
    @RequestMapping(value = "/getAllSchedules", method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public Map<String, SchedulerHelper> getAllSchedule() {
        logger.info("get All Schedules.");
        return SchedulerFactory.schedulers();
    }

    @ResponseBody
    @RequestMapping(value = "/getScheduler", method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public SchedulerHelper getScheduler(@RequestBody Map<String, Object> requestMap) {
        String scheduleName = (String) requestMap.get("scheduleName");

        logger.info("get Scheduler.");
        return schedulerExistsOrException(scheduleName);
    }

    @ResponseBody
    @RequestMapping(value = "/getDefaultScheduler", method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public SchedulerHelper getDefaultScheduler(@RequestBody Map<String, Object> requestMap) {
        String scheduleName = (String) requestMap.get("scheduleName");

        logger.info("get Default Scheduler.");
        return schedulerExistsOrException(scheduleName);
    }


    @ResponseBody
    @RequestMapping(value = "/startSchedule", method = {
            RequestMethod.PUT}, produces = "application/json;charset=UTF-8")
    public String start(@RequestBody Map<String, Object> requestMap) throws Exception {
        String scheduleName = (String) requestMap.get("scheduleName");
        schedulerExistsOrException(scheduleName).start();
        logger.info("start schedule");
        return JsonResult.successJson(ResultCode.SUCCESS, "start schedule success.");
    }


    @ResponseBody
    @RequestMapping(value = "/standbySchedule", method = {
            RequestMethod.PUT}, produces = "application/json;charset=UTF-8")
    public String standby(@RequestBody Map<String, Object> requestMap) throws Exception {
        String scheduleName = (String) requestMap.get("scheduleName");
        schedulerExistsOrException(scheduleName).standby();
        logger.info("standby schedule");
        return JsonResult.successJson(ResultCode.SUCCESS, "standby schedule success.");
    }

    @ResponseBody
    @RequestMapping(value = "/shutdownSchedule", method = {
            RequestMethod.PUT}, produces = "application/json;charset=UTF-8")
    public String shutdown(@RequestBody Map<String, Object> requestMap) throws Exception {
        String scheduleName = (String) requestMap.get("scheduleName");
        schedulerExistsOrException(scheduleName).getQuartzScheduler().shutdown();
        logger.info("shutdown schedule");
        return JsonResult.successJson(ResultCode.SUCCESS, "shutdown schedule success.");
    }


    private SchedulerHelper schedulerExistsOrException(String schedulerName) {
        SchedulerHelper schedulerHelper = SchedulerFactory.getScheduler(schedulerName);
        if(schedulerHelper == null)
            throw new WebApplicationException(ResponseBuilder.resourceNotFound("Scheduler", schedulerName));
        return schedulerHelper;
    }

}
