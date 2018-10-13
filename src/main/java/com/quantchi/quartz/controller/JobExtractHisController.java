package com.quantchi.quartz.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.quartz.entity.MDExtractorJobHis;
import com.quantchi.quartz.service.JobExtractHisService;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class JobExtractHisController {

	private static final Logger logger = LoggerFactory.getLogger(JobExtractHisController.class);

	@Autowired
	JobExtractHisService jobExtractHisService;

	/**
	 * @api {get} /api/extractorJob 获取查询元数据抽取任务列表
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/extractorJob/
	 * @apiName extractorJob
	 * @apiGroup MetaDataDsExtractController
	 * @apiParam {String} [keyword] 任务关键字
	 * @apiParam {String} [status] 任务状态
	 * @apiParam {String} [extractType] 采集方式
	 * @apiParam {String} [page] 分页页码
	 * @apiParam {String} [page_size] 分页大小
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@ResponseBody
	@RequestMapping(value = "/extractorJob", method = {RequestMethod.GET})
	public String getExtractorJob(@RequestParam (value = "keyword", required = false) String keyword,
												 @RequestParam (value = "status", required = false) String status,
												 @RequestParam (value = "extractType", required = false) String extractType,
												 @RequestParam (value = "page", required = false) String page,
												 @RequestParam (value = "page_size", required = false) String page_size) {
		try {
			Entry<Integer,List<MDExtractorJobHis>> jobs = jobExtractHisService.searchJobHis(extractType,status,keyword,page,page_size);
			return JsonResult.successJson(jobs.getKey(),jobs.getValue());
		} catch (Exception e) {
			logger.error("getDiffJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getDiffJob job failed.");
		}
	}

	/**
	 * @api {get} /api/extractLog 获取元数据抽取任务日志
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/extractLog/
	 * @apiName extractLog
	 * @apiGroup MetaDataDsExtractController
	 * @apiParam {String} logId 日志唯一标识
	 * @apiParam {String} [page] 分页页码
	 * @apiParam {String} [page_size] 分页大小
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 * @apiSuccess {String} log 返回日志内容
	 */
	@ResponseBody
	@RequestMapping(value = "/extractLog", method = {RequestMethod.GET})
	public String getExtractLog(@RequestParam(value = "logId", required = false) String logId,
											 @RequestParam (value = "page", required = false) String page,
											 @RequestParam (value = "page_size", required = false) String page_size) {
		try {
			if(logId == null)
					throw new Exception("miss logId param");
			Object ret = jobExtractHisService.getHisLogById(logId);
			return JsonResult.successJson(ret, ResultCode.SUCCESS, "getExtractorlog job success.");
		} catch (Exception e) {
			logger.error("getExtractorlog func err logId is {} ,and error: {}",logId,e);
			return JsonResult.errorJson("getExtractorlog job failed.error:"+e.getMessage());
		}
	}


	/**
	 * @api {post} /api/extractorJob/del 删除元数据抽取任务历史
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/extractorJob/del
	 * @apiName extractorJob_del
	 * @apiGroup MetaDataDsExtractController
	 * @apiParam {list} jobHisIdList
	 * @apiParam {String} jobHisIdList.jobHisId 抽取日志历史id
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = "/extractorJob/del", method = {RequestMethod.POST})
	public
	@ResponseBody
	String delExtractJob(@RequestBody List<String> jobHisIds) {
		try {
			return JsonResult.successJson(jobExtractHisService.deleteJobHis(jobHisIds));
		} catch (Exception e) {
			logger.error("getExtractorJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getExtractorJob job failed.");
		}
	}

	/**
	 * @api {post} /api/extractorJob 停止抽取任务
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/extractorJob/
	 * @apiName extractorJob_stop
	 * @apiGroup MetaDataDsExtractController
	 * @apiParam {list} jobIds 任务标识列表
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = "/extractorJob", method = {RequestMethod.POST})
	public
	@ResponseBody
	String stopExtractJob(@RequestBody List<String> jobIds) {
		try {
			return JsonResult.successJson(null, ResultCode.SUCCESS, "getExtractorJob job success.");
		} catch (Exception e) {
			logger.error("getExtractorJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getExtractorJob job failed.");
		}
	}

}
