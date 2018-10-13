package com.quantchi.metadatamgr.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.metadatamgr.service.MetaDataDiffService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api", produces = "application/json;charset=UTF-8")
public class MetaDataDsDiffController {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataDsDiffController.class);

  @Autowired
  private MetaDataDiffService metaDataDiffService;

  /**
   * @api {post} /api/updateVerInfo 元数据版本更新
   * @apiDescription 某个版本定版
   * @apiVersion 1.0.0
   * @apiName updateVerInfo
   * @apiGroup MetaDataDsDiffController
   * @apiParam {String} version 版本编号
   * @apiParam {String} datasourceId 元数据id
   * @apiParam {String} tag 用户写的版本编号
   * @apiParam {Boolean} isBaseline 是否是基线
   * @apiParam {String} submitter 定版人
   * @apiParam {String} [description] 版本描述
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {int} [data] 返回数据
   */
  @RequestMapping(value = "/updateVerInfo", method = {RequestMethod.POST})
  public
  @ResponseBody
  String uptDsVersion(@RequestBody Map<String, Object> request) {
    try {
      Object o = metaDataDiffService.updateSchemaDiff(request);
      return JsonResult.successJson(o, ResultCode.SUCCESS, "update job success.");
    } catch (Exception e) {
      logger.error("getDiffJob func err: {}", e);
      return JsonResult.successJson(null, ResultCode.ERROR, "getDiffJob job failed.");
    }
  }

  /**
   * @api {get} /api/schemaDiff/getAllJob 获取查询元数据比对任务
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/schemaDiff/getAllJob
   * @apiName getAllJob
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {String} [keyword] 任务关键字
   * @apiParam {String} [status] 任务状态
   * @apiParam {String} [page] 分页页码
   * @apiParam {String} [pageSize] 分页大小
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {String} [data.id]
   * @apiSuccess {String} [data.jobName]
   * @apiSuccess {String} [data.source]
   * @apiSuccess {String} [data.target]
   * @apiSuccess {String} [data.status] 0是未完成，1是已完成
   */
  @RequestMapping(value = "/schemaDiff/getAllJob ", method = {RequestMethod.GET})
  public
  @ResponseBody
  String getDiffJob(@RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "status", required = false) String status,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
    try {
      Map<String, Object> o = metaDataDiffService.getAllJob(keyword, status, pageSize, page);
      return JsonResult.toJson(o, ResultCode.SUCCESS, "getDiffJob job success.");
    } catch (Exception e) {
      logger.error("getDiffJob func err: {}", e);
      return JsonResult.successJson(null, ResultCode.ERROR, "getDiffJob job failed.");
    }
  }

  /**
   * @api {post} /api/schemaDiff/deleteJob 删除元数据比对任务
   * @apiVersion 1.0.0
   * @apiName deleteJob
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {List} jobId
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {int} [data] >=1 成功
   **/
  @RequestMapping(value = "/schemaDiff/deleteJob", method = {RequestMethod.POST})
  public
  @ResponseBody
  String deleteJob(@RequestBody Map<String, List<String>> body) {
    try {
      Object o = metaDataDiffService.deleteJob(body.get("jobId"));
      return JsonResult.successJson(o, ResultCode.SUCCESS, "delete job success.");
    } catch (Exception e) {
      logger.error("getDiffJob func err: {}", e);
      return JsonResult.successJson(null, ResultCode.ERROR, "delete job failed.");
    }
  }

  /**
   * @api {get} /api/schemaDiffByJob/ 不同数据源比对详情
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/schemaDiffByJob
   * @apiName schemaDiff_detail
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {String} [jobId] 任务id
   * @apiParam {String} [page] 分页页码
   * @apiParam {String} [pageSize] 分页大小
   * @apiParam {String} [keyword] 筛选字段
   * @apiParam {String} [operation] 筛选operation (ADD, DEL, MOD)
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 和"同一个数据源比对详情API"相同
   */
  @RequestMapping(value = "/schemaDiffByJob", method = {RequestMethod.GET})
  public
  @ResponseBody
  String getDiffDetail(@RequestParam(value = "jobId") String jobId,
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(value = "operation", required = false) String operation,
      @RequestParam(value = "page") int page,
      @RequestParam(value = "pageSize") int pageSize) {
    try {
      Object o = metaDataDiffService.getDatasourceDiff(jobId, pageSize, page, keyword, operation);
      return JsonResult.successJson(o, ResultCode.SUCCESS, "getDiffJob job success.");
    } catch (Exception e) {
      logger.error("getDiffJob func err: {}", e);
      return JsonResult.successJson(null, ResultCode.ERROR, "getDiffJob job failed.");
    }
  }

  /**
   * @api {post} /api/schemaDiff 同一个数据源比对详情
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/schemaDiff
   * @apiName schemaDiff_version
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {String} datasourceId 数据源id
   * @apiParam {String} sourceVersion 源版本
   * @apiParam {String} targetVersion 目标版本
   * @apiParam {String} keyword 筛选字段
   * @apiParam {String} operation 筛选operation (ADD, DEL, MOD)
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 返回数据
   * @apiSuccess {int} [data.sourceTableAmount] source中表的总计数量
   * @apiSuccess {int} [data.targetTableAmount]
   * @apiSuccess {int} [data.sourceFieldAmount] source中字段的总计数量
   * @apiSuccess {int} [data.targetFieldAmount]
   * @apiSuccess {int} [data.tableAdditionsAmount] 添加表数
   * @apiSuccess {int} [data.tableDeletionsAmount]
   * @apiSuccess {int} [data.tableChangesAmount]
   * @apiSuccess {int} [data.fieldAdditionsAmount] 添加字段数
   * @apiSuccess {int} [data.fieldDeletionsAmount]
   * @apiSuccess {int} [data.fieldChangesAmount]
   * @apiSuccess {String} [data.datasourceId] datasourceId
   * @apiSuccess {List} [data.modifications] 具体修改的字段
   * @apiSuccess {String} [data.modifications.fieldName]
   * @apiSuccess {String} [data.modifications.operation] ADD or DEL or MOD (添加，删除，修改)
   * @apiSuccess {String} [data.modifications.desc]
   */
  @RequestMapping(value = "/schemaDiff", method = {RequestMethod.POST})
  public
  @ResponseBody
  String getDiffVersion(@RequestBody Map<String, Object> request) {
    try {
      Object o = metaDataDiffService.getVersionDiff((String) request.get("datasourceId"),
          (String) request.get("sourceVersion"), (String) request.get("targetVersion"),
          (String) request.get("keyword"), (String) request.get("operation"),
          (Integer) request.get("page"), (Integer) request.get("pageSize")
       );
      return JsonResult.successJson(o, ResultCode.SUCCESS, "getDiffJob job success.");
    } catch (IllegalArgumentException e) {
      return JsonResult.errorJson("400", e.getMessage());
    } catch (Exception e) {
      logger.error("getDiffJob func err: {}", e);
      return JsonResult.errorJson(ResultCode.ERROR, "getDiffJob job failed: " + e.getMessage());
    }
  }

  /**
   * @api {post} /api/schemaDiff/getAllVersion 获取所有的版本
   * @apiVersion 1.0.0
   * @apiName getAllSchemaVersion
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {String} page 分页页码
   * @apiParam {String} pageSize 分页大小
   * @apiParam {String} [tag] 用户定的版本号
   * @apiParam {String} [datasourceId]
   * @apiParam {Boolean} [baseline]
   * @apiParam {String} [submitter]
   * @apiParam {String} [dateMin]
   * @apiParam {String} [dateMax]
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {int} [data.additions] 增加
   * @apiSuccess {String} [data.baseline] 基线版本
   * @apiSuccess {Date} [data.date] 日期
   * @apiSuccess {String} [data.desc] 版本描述
   * @apiSuccess {String} [data.submitter] 定版人员
   * @apiSuccess {int} [data.changes] 修改
   * @apiSuccess {int} [data.deletions] 删除
   * @apiSuccess {String} [data.tag] 用户写的版本编号
   * @apiSuccess {String} [data.version] 版本号
   **/
  @ResponseBody
  @RequestMapping(value = "/schemaDiff/getAllVersion", method = {RequestMethod.POST})
  public String getAllVersion(@RequestBody Map<String, Object> map) {
    try {
      Map<String, Object> o = metaDataDiffService.getAllVersion(map);
      return JsonResult.toJson(o, ResultCode.SUCCESS, "getDiff success.");
    } catch (Exception e) {
      logger.error("getDiff err: {}", e);
      return JsonResult.errorJson(ResultCode.ERROR, "getDiffJob job failed.");
    }
  }

  /**
   * @api {get} /api/schemaDiff/insertDiffJob 插入对比Job
   * @apiVersion 1.0.0
   * @apiName insertDiffJob
   * @apiGroup MetaDataDsDiffController(版本比对)
   * @apiParam {String} [jobName]
   * @apiParam {String} [sourceId]
   * @apiParam {String} [targetId]
   * @apiSuccess {int} [data] 生成的ID
   **/
  @ResponseBody
  @RequestMapping(value = "/schemaDiff/insertDiffJob", method = {RequestMethod.GET})
  public String insertDiffJob(@RequestParam(value = "jobName") String jobName,
      @RequestParam(value = "sourceId") String sourceId,
      @RequestParam(value = "targetId") String targetId) {
    try {
      Object o = metaDataDiffService.insertDiffJob(jobName, sourceId, targetId);
      return JsonResult.successJson(o, ResultCode.SUCCESS, "getDiffJob job success.");
    } catch (Exception e) {
      logger.error("insert err: {}", e);
      return JsonResult.errorJson(ResultCode.ERROR, "insert failed.");
    }
  }
}
