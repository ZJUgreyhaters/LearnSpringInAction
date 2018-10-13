package com.quantchi.metadatamgr.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.metadatamgr.data.entity.MDCheckBusiRule;
import com.quantchi.metadatamgr.data.entity.MDCheckTechRule;
import com.quantchi.metadatamgr.service.MetaDataMgrCheckApiService;
import com.quantchi.quartz.entity.MDExtractorJobManual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "api/metadata/check")
public class MetaDataMgrCheckApiController {

  private static final Logger logger = LoggerFactory
      .getLogger(MetaDataMgrCheckApiController.class);

  @Autowired
  private MetaDataMgrCheckApiService metaDataMgrCheckApiService;

  //business part

  /**
   * @api {post} /api/metadata/check/searchBusin  获取业务规则
   * @apiVersion 1.0.0
   * @apiName searchBusin
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {Int} page 版本编号
   * @apiParam {Int} page_size 元数据id
   * @apiParam {String} businfilter 过滤条件 id 或者规则名字
   * @apiParam {String} checksystem 业务系统
   * @apiParam {String} checkstatus 状态 0 未审核 1 审核通过 2 打回
   * @apiParam {String} ruletypefilter 规则类过滤
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total 总条数
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {String} [data.creator]  创建者
   * @apiSuccess {String} [data.rulesecondtype]  规则小类
   * @apiSuccess {String} [data.rulefirsttype]   规则大类
   * @apiSuccess {String} [data.checksystem]   业务系统
   * @apiSuccess {String} [data.checkstatus]   审核状态
   * @apiSuccess {String} [data.createdate] 创建时间
   * @apiSuccess {String} [data.rulename] 规则名字
   * @apiSuccess {list} [data.techruleids] 技术规则id
   * @apiSuccess {String} [data.businid] 业务规则id
   * @apiParamExample {json} Request-example:
   * {"page":1,"page_size":100,"businfilter":"测试","checksystem":"UF20","checkstatus":"0","ruletypefilter":"1_2"}
   */
  @RequestMapping(value = "/searchBusin", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> searchBusin(@RequestBody Map<String, Object> map) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      responseMap = metaDataMgrCheckApiService.searchBusin(map);
      responseMap.put("code", "200");
      responseMap.put("msg", "成功");
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", "500");
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }
  }

  /**
   * @api {post} /api/metadata/check/deleteBusiRules  删除业务规则
   * @apiVersion 1.0.0
   * @apiName deleteBusin
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {List} rules 业务规则列表  [{"id":"xxx","businId":"xxx"}]
   * @apiParam {Integer} rules.id 系统id
   * @apiParam {String} rules.businId 业务规则id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   */
  @ResponseBody
  @RequestMapping(value = "/deleteBusiRules", method = {
          RequestMethod.POST})
  public String deleteBusiRules(@RequestBody List<MDCheckBusiRule> rules) {

    Object rets = metaDataMgrCheckApiService.deleteBusiRules(rules);
    return JsonResult.successJson(rets);

  }

  /**
   * @api {post} /api/metadata/check/busiRules  新增或更新业务规则
   * @apiVersion 1.0.0
   * @apiName busiRules
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {String} checkSystem 业务系统id
   * @apiParam {String} ruleName 业务规则名称
   * @apiParam {String} ruleFirstType 业务大类id
   * @apiParam {String} ruleSecondType 业务小类id
   * @apiParam {String} creator 业务创建者
   * @apiParam {List} metricPhysicalInfo 业务创建者
   * @apiParam {String} metricPhysicalInfo.entityId 指标id
   * @apiParam {String} metricPhysicalInfo.checkType 规则类型   area limit data notnull customize
   * @apiParam {List} metricPhysicalInfo.checkParam 规则参数   [ param0，param1 .. ]
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   */
  @ResponseBody
  @RequestMapping(value = "/busiRules", method = {
          RequestMethod.POST})
  public String addOrUptBusiRules(@RequestBody MDCheckBusiRule rule) {

    Object rets = metaDataMgrCheckApiService.addOrUptBusiRules(rule);
    return JsonResult.successJson(rets);

  }


  //technical part

  /**
   * @api {post} /api/metadata/check/searchTech  获取技术规则
   * @apiVersion 1.0.0
   * @apiName searchTech
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {Int} page 版本编号
   * @apiParam {Int} page_size 元数据id
   * @apiParam {String} techfilter 过滤条件 id 或者规则名字
   * @apiParam {String} checksystem 业务系统
   * @apiParam {String} checkstatus 状态 0 未审核 1 审核通过 2 打回
   * @apiParam {String} ruletypefilter 规则类过滤
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total 总条数
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {String} [data.sourcetable]  数据表
   * @apiSuccess {String} [data.techruleid]  技术规则id
   * @apiSuccess {String} [data.creator]   创建者
   * @apiSuccess {String} [data.sourcedata]   业务数据库
   * @apiSuccess {String} [data.sourdecol]   数据字段
   * @apiSuccess {String} [data.rulefirsttype] 规则大类
   * @apiSuccess {String} [data.checkstatus] 审核状态
   * @apiSuccess {list} [data.createdate] 创建时间
   * @apiSuccess {String} [data.rulesqltext] 检验sql语句
   * @apiSuccess {String} [data.rulename] 规则名称
   * @apiSuccess {String} [data.rulesecondtype] 规则小类
   * @apiSuccess {String} [data.businid] 业务规则id
   * @apiParamExample {json} Request-example:
   * {"page":1,"page_size":100,"techfilter":"测试","checksystem":"UF20","checkstatus":"0","ruletypefilter":"1_2"}
   */
  @RequestMapping(value = "/searchTech", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> searchTech(@RequestBody Map<String, Object> map) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      responseMap = metaDataMgrCheckApiService.searchTech(map);
      responseMap.put("code", "200");
      responseMap.put("msg", "成功");
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", "500");
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }
  }

  /**
   * @api {post} /api/metadata/check/deleteTechRules  删除技术规则
   * @apiVersion 1.0.0
   * @apiName deleteTech
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {List} rules 技术规则列表   [ 123,456]
   * @apiParam {Integer} rules.id 技术规则id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   */
  @ResponseBody
  @RequestMapping(value = "/deleteTechRules", method = {
          RequestMethod.POST})
  public String deleteTechRules(@RequestBody List<MDCheckTechRule> rules) {

    Object rets = metaDataMgrCheckApiService.deleteTechRules(rules);
    return JsonResult.successJson(rets);

  }

  /**
   * @api {post} /api/metadata/check/techRules  新增或更新技术规则
   * @apiVersion 1.0.0
   * @apiName techRules
   * @apiGroup MetaDataMgrCheckApiController
   * @apiParam {List} rules 业务规则列表 [{"id":"xxx"}]
   * @apiParam {Object} rule 业务规则
   * @apiParam {Integer} rule.id 业务规则id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   */
  @ResponseBody
  @RequestMapping(value = "/techRules", method = {
          RequestMethod.POST})
  public String addOrUptTechRules(@RequestBody MDCheckTechRule rule) {

    Object rets = metaDataMgrCheckApiService.addOrUptTechRules(rule);
    return JsonResult.successJson(rets);

  }

}
