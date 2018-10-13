package com.quantchi.quartz.controller;

import com.quantchi.quartz.core.SchedulerFactory;
import com.quantchi.quartz.core.SchedulerHelper;
import com.quantchi.quartz.util.ResponseBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
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
 * @ClassName: TriggerController
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/19 23:20
 * @Version 1.0.0
 **/

@Controller
@RequestMapping("api")
public class TriggerController {
    private static final Logger logger = LoggerFactory.getLogger(TriggerController.class);

    private SchedulerHelper schedulerHelper = SchedulerFactory.getDefaultScheduler();

    @ResponseBody
    @RequestMapping(value = "/getTrigger", method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public Trigger getTrigger(@RequestBody Map<String, Object> requestMap) {
        String group = (String) requestMap.get("group");
        String name = (String) requestMap.get("name");

        logger.info("get trigger.");
        return triggerExistsOrException(group, name);
    }

    @ResponseBody
    @RequestMapping(value = "/getJobsByTrigger", method = {
            RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public JobDetail getJobsByTrigger(@RequestBody Map<String, Object> requestMap) {
        String group = (String) requestMap.get("group");
        String name = (String) requestMap.get("name");

        Trigger trigger = triggerExistsOrException(group, name);
        logger.info("get all jobs identified by trigger.");
        try {
            return schedulerHelper.getQuartzScheduler().getJobDetail(trigger.getJobKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Trigger triggerExistsOrException(String group, String name) {
        Trigger trigger = null;
        try {
            trigger = schedulerHelper.getTrigger(group, name);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if(trigger == null)
            throw new WebApplicationException(ResponseBuilder.resourceNotFound("Trigger", group + "." + name));
        return trigger;
    }
//
//    public SchedulerHelper scheduler() {
//        return SchedulerFactory.getDefaultScheduler();
//    }
}
