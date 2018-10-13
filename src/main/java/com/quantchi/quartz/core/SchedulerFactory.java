package com.quantchi.quartz.core;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SchedulerFactory {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerFactory.class);
    private static Map<String, SchedulerHelper> schedulers = new HashMap<String, SchedulerHelper>();
    private static SchedulerHelper defaultScheduler;

    public static void buildSchedulers(String schedulerConfigsFolder) throws SchedulerException {
        File folder = new File(schedulerConfigsFolder);
        for(File scheduleConfigFile : folder.listFiles()) {
            StdSchedulerFactory sf = new StdSchedulerFactory();
            sf.initialize(scheduleConfigFile.getAbsolutePath());
            Scheduler scheduler = sf.getScheduler();
            scheduler.start();
            schedulers.put(scheduler.getSchedulerName(), new SchedulerHelper(scheduler));
            logger.info("name: " + scheduler.getSchedulerName());
            if(defaultScheduler == null) {
                defaultScheduler = schedulers.get(scheduler.getSchedulerName());
            }
        }
    }

    public static SchedulerHelper getDefaultScheduler() {
        try{
            if(defaultScheduler == null) {
                //buildSchedulers("D:\\CODE\\search\\src\\main\\resources\\quartzConfig");
                ClassLoader classLoader = SchedulerFactory.class.getClassLoader();
                URL url = classLoader.getResource("quartzConfig");
                buildSchedulers(url.getFile());
            }
        }catch (SchedulerException e) {
            logger.error("buildSchedulers(String schedulerConfigsFolder) failed!");
        }
        return defaultScheduler;
    }

    public static SchedulerHelper getScheduler(String schedulerName) {
        return schedulers.get(schedulerName);
    }

    public static Map<String, SchedulerHelper> schedulers() {
        return schedulers;
    }

    public static void setDefaultScheduler(SchedulerHelper scheduler) {
        SchedulerFactory.defaultScheduler = scheduler;
    }
}
