package com.quantchi.quartz.controller;

import com.quantchi.common.AppProperties;
import com.quantchi.common.JsonResult;
import com.quantchi.quartz.entity.MDExtractorJobManual;
import com.quantchi.quartz.service.JobManualFileService;
import com.quantchi.quartz.util.FileSave;
import com.quantchi.quartz.util.JobConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobController
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/9/17 14:05
 * @Version 1.0.0
 **/

@Controller
@RequestMapping("api")
public class JobManualFileController {
    private static final Logger logger = LoggerFactory.getLogger(JobManualFileController.class);

    @Autowired
    private JobManualFileService jobManualFileService;


    /**@api {post} /api/getManualJob 获取任务信息列表
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getManualJob
     * @apiName getmanualJob
     * @apiGroup JobManualFileController
     * @apiParam {String} [keyword] 任务关键字
     * @apiParam {List} fileTpl  文件模板  table:数据表更新  field:数据字段更新 ["table","field" ..]
     * @apiParam {String} [page] 分页页码
     * @apiParam {String} [page_size] 分页大小
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 任务信息
     **/
    @ResponseBody
    @RequestMapping(value = "/getManualJob", method = {
            RequestMethod.POST})
    public String getJob(@RequestBody Map<String,Object> paramMap) {
        List<String>  fileTpl = (List<String>) paramMap.get("fileTpl");
        String keyword = null;
        String page = null;
        String page_size = null;
        if(paramMap.get("keyword") != null)
            keyword = paramMap.get("keyword").toString();
        if(paramMap.get("page") != null)
            page = paramMap.get("page").toString();
        if(paramMap.get("page_size") != null)
            page_size = paramMap.get("page_size").toString();

        List<Map<String,String>> jobs = jobManualFileService.searchJob(keyword,fileTpl,page,page_size);
        int total = jobManualFileService.getJobCount(keyword,fileTpl);
        return JsonResult.successJson(total, jobs);

    }


    /**@api {post} /api/manualJob 添加任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/manualJob
     * @apiName add_manual_job
     * @apiGroup JobManualFileController
     * @apiParam {String} jobName 任务名称
     * @apiParam {String} parentTreeId 悬挂节点id
     * @apiParam {String} fileTpl 文件模板
     * @apiParam {String} strategy 入库策略 1.保守全量 2.激进全量 3.增量
     * @apiParam {file} file 上传的文件
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/
    @ResponseBody
    @RequestMapping(value = "/manualJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String addManualJob(
            @RequestParam(value = "file", required = false) MultipartFile file,
            String  jobName,String  parentTreeId,String  fileTpl,String  strategy) throws Exception {

        try {
            MDExtractorJobManual job = new MDExtractorJobManual();
            job.setFileTpl(fileTpl);
            job.setJobName(jobName);
            job.setParentTreeId(parentTreeId);
            job.setStrategy(strategy);
            job.setFilepath(getfilePath(file));
            job.setType(JobConstant.JOB_COLLECTOR_MANUAL_TYPE);
            Map<String,String> ret = jobManualFileService.createJob(job);
            if(ret.get("error") == null)
                return JsonResult.successJson(ret.get("data"));
            else
                return JsonResult.errorJson("create job error:"+ret.get("error"));
        }catch (Exception e){
            logger.error("create manualJob error {}",e);
            return JsonResult.errorJson("create job error:"+e.getMessage());
        }


    }



    /**@api {post} /api/deleteManualJob 删除手动任务接口
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleteManualJob
     * @apiName deleteManualJob
     * @apiGroup JobManualFileController
     * @apiParam {Object[]} data
     * @apiParam {String} data.id 任务列表
     * @apiParam {String} data.jobName 任务列表
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     **/

    @ResponseBody
    @RequestMapping(value = "/deleteManualJob", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String deleteJob(@RequestBody List<MDExtractorJobManual> jobIds) {

        Map<String,String> ret = jobManualFileService.deleteJobs(jobIds);
        return JsonResult.successJson(ret);

    }

    private String getfilePath(MultipartFile file) throws Exception {
        //load configuration file first
        String type = AppProperties.getWithDefault("fileTpl.type","localpath");
        return FileSave.of(type,file).getFilePath();
    }

}
