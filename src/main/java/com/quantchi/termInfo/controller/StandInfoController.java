package com.quantchi.termInfo.controller;

import com.quantchi.termInfo.pojo.StandardMainInfo;
import com.quantchi.termInfo.service.StandInfoService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api")
public class StandInfoController {

  @Autowired
  private StandInfoService standInfoService;

  /**
   * @api {post} /api/standard 标准查询接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/standard
   * @apiName standard
   * @apiGroup StandInfoController
   * @apiParam {Integer} [page] 页数
   * @apiParam {Integer} [page_size] 每页数据数
   * @apiParam {String} [entityName] 标准名称关键字
   * @apiParam {String} [entityId] 标准编码
   * @apiParam {String} [entityCategory] 标准分类Id
   * @apiParam {String} [entityDomainId] 主题id
   * @apiParam {String} [entityType] 指标类型
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total  返回记录总数
   * @apiSuccess {List} [data] 返回数据 标准信息列表
   * @apiSuccess {String} [data.entityId] 标准id
   * @apiSuccess {String} [data.entityName] 标准名称
   * @apiSuccess {String} [data.entityDesc] 标准描述
   * @apiSuccess {String} [data.entityDomainId] 标准主题id
   * @apiSuccess {String} [data.entityCategory] 标准分类
   * @apiSuccess {String} [data.standardLevel] 标准层次
   * @apiSuccess {String} [data.business_definition] 业务定义
   * @apiSuccess {String} [data.according] 制定依据
   * @apiSuccess {String} [data.supervision] 监管标志
   * @apiSuccess {String} [data.udcRuleName] 编码规则
   * @apiSuccess {String} [data.entityType] 指标类型
   * @apiSuccess {String} [data.businessRule] 业务口径
   * @apiSuccess {String} [data.displayType] 常用维度
   * @apiSuccess {String} [data.frequency] 统计频率
   * @apiSuccess {String} [data.dataType] 数据类型
   * @apiSuccess {String} [data.dataUnit] 度量单位
   * @apiSuccess {String} [data.dataLength] 数据长度
   * @apiSuccess {String} [data.dataPrecision] 数据精度
   * @apiSuccess {String} [data.dataPrecision] 数据精度
   * @apiSuccess {String} [data.dataArea] 取值范围
   * @apiSuccess {String} [data.udcCode] 引用代码
   * @apiSuccess {String} [data.systemUsed] 落地系统
   * @apiSuccess {String} [data.techniqueRule] 技术口径
   * @apiSuccess {String} [data.systemFrom] 来源系统
   * @apiSuccess {String} [data.oralTechniqueRule] 源系统技术口径
   * @apiSuccess {String} [data.controlDept] 管理部门
   * @apiSuccess {String} [data.entityStatus] 发布状态
   * @apiSuccess {String} [data.effectiveTime] 生效日期
   * @apiSuccess {String} [data.offlineTime] 失效日期
   * @apiSuccess {String} [data.remark] 备注
   */
  //查询
  @ResponseBody
  @RequestMapping(value = "/standard", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String list(@RequestBody StandardMainInfo standardMainInfo) {
    return standInfoService.selectList(standardMainInfo);
  }

  /**
   * @api {post} /api/standardCategory 标准类目获得
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/standardCategory
   * @apiName standardCategory
   * @apiGroup StandInfoController
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total  返回记录总数
   * @apiSuccess {List} [data] 返回数据 标准信息列表
   * @apiSuccess {String} [data.id] 标准类目id
   * @apiSuccess {String} [data.name] 标准类目名称
   * @apiSuccess {String} [data.description]  标准类目描述
   * @apiSuccess {String} [data.level] 标准类目分类
   * @apiSuccess {String} [data.domain] 标准主题
   * @apiSuccess {List} [data.children] 标准类目的孩子节点
   * @apiSuccess {String} [data.children.name] 标准类目名称
   * @apiSuccess {String} [data.children.id] 标准类目id
   */
  @ResponseBody
  @RequestMapping(value = "/standardCategory", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String listCategory(StandardMainInfo standardMainInfo) {

    return standInfoService.selectListCategory(standardMainInfo);
  }

  /**
   * @api {post} /api/selectMetric 指标查询接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/selectMetric
   * @apiName selectMetric
   * @apiGroup StandInfoController
   * @apiParam {Integer} [page] 页数
   * @apiParam {Integer} [page_size] 每页数据数
   * @apiParam {String} [entityName] 标准名称关键字
   * @apiParam {String} [entityId] 标准编码
   * @apiParam {String} [entityCategory] 标准分类Id
   * @apiParam {String} [entityDomainId] 主题id
   * @apiParam {String} [standardLevel] 标准层次
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total  返回记录总数
   * @apiSuccess {List} [data] 返回数据 标准信息列表
   * @apiSuccess {String} [data.entityId] 标准id
   * @apiSuccess {String} [data.entityName] 标准英文名
   * @apiSuccess {String} [data.entityDesc] 标准中文名
   * @apiSuccess {String} [data.entityDomainId] 标准主题id
   * @apiSuccess {String} [data.entityCategory] 标准分类
   * @apiSuccess {String} [data.standardLevel] 标准层次
   * @apiSuccess {String} [data.business_definition] 业务定义
   * @apiSuccess {String} [data.according] 制定依据
   * @apiSuccess {String} [data.supervision] 监管标志
   * @apiSuccess {String} [data.udcRuleName] 编码规则
   * @apiSuccess {String} [data.entityType] 指标类型
   * @apiSuccess {String} [data.businessRule] 业务口径
   * @apiSuccess {String} [data.displayType] 常用维度
   * @apiSuccess {String} [data.frequency] 统计频率
   * @apiSuccess {String} [data.dataType] 数据类型
   * @apiSuccess {String} [data.dataUnit] 度量单位
   * @apiSuccess {String} [data.dataLength] 数据长度
   * @apiSuccess {String} [data.dataPrecision] 数据精度
   * @apiSuccess {String} [data.dataPrecision] 数据精度
   * @apiSuccess {String} [data.dataArea] 取值范围
   * @apiSuccess {String} [data.udcCode] 引用代码
   * @apiSuccess {String} [data.systemUsed] 落地系统
   * @apiSuccess {String} [data.techniqueRule] 技术口径
   * @apiSuccess {String} [data.systemFrom] 来源系统
   * @apiSuccess {String} [data.oralTechniqueRule] 源系统技术口径
   * @apiSuccess {String} [data.controlDept] 管理部门
   * @apiSuccess {String} [data.entityStatus] 发布状态
   * @apiSuccess {String} [data.effectiveTime] 生效日期
   * @apiSuccess {String} [data.offlineTime] 失效日期
   * @apiSuccess {String} [data.remark] 备注
   */
  @ResponseBody
  @RequestMapping(value = "/selectMetric", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String selectMetric(@RequestBody StandardMainInfo standardMainInfo) {

    return standInfoService.selectMetric(standardMainInfo);
  }

  /**
   * @api {post} /api/insertMetric 指标新增接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/insertMetric
   * @apiName insertMetric
   * @apiGroup StandInfoController
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiParam {String} [entityId] 指标id
   * @apiParam {String} [entityName] 指标名称
   * @apiParam {String} [entityDesc] 指标描述
   * @apiParam {String} [entityDomainId] 指标主题id
   * @apiParam {String} [entityCategory] 指标分类
   * @apiParam {String} [standardLevel] 指标层次
   * @apiParam {String} [business_definition] 业务定义
   * @apiParam {String} [according] 制定依据
   * @apiParam {String} [supervision] 监管标志
   * @apiParam {String} [udcRuleName] 编码规则
   * @apiParam {String} [entityType] 指标类型
   * @apiParam {String} [businessRule] 业务口径
   * @apiParam {String} [displayType] 常用维度
   * @apiParam {String} [frequency] 统计频率
   * @apiParam {String} [dataType] 数据类型
   * @apiParam {String} [dataUnit] 度量单位
   * @apiParam {String} [dataLength] 数据长度
   * @apiParam {String} [dataPrecision] 数据精度
   * @apiParam {String} [dataPrecision] 数据精度
   * @apiParam {String} [dataArea] 取值范围
   * @apiParam {String} [udcCode] 引用代码
   * @apiParam {String} [systemUsed] 落地系统
   * @apiParam {String} [techniqueRule] 技术口径
   * @apiParam {String} [systemFrom] 来源系统
   * @apiParam {String} [oralTechniqueRule] 源系统技术口径
   * @apiParam {String} [controlDept] 管理部门
   * @apiParam {String} [entityStatus] 发布状态
   * @apiParam {String} [effectiveTime] 生效日期
   * @apiParam {String} [offlineTime] 失效日期
   * @apiParam {String} [remark] 备注
   */
  @ResponseBody
  @RequestMapping(value = "/insertMetric", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String insertMetric(StandardMainInfo standardMainInfo) {

    return standInfoService.selectListCategory(standardMainInfo);
  }

  /**
   * @api {get} /api/metricCategory 指标业务分类接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metricCategory
   * @apiName metricCategory
   * @apiGroup StandInfoController
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {String} total  返回记录总数
   * @apiSuccess {List} [data] 返回数据 业务分类列表
   * @apiSuccess {String} [data.id] 业务分类id
   * @apiSuccess {String} [data.businessName] 业务线名称
   * @apiSuccess {String} [data.domainName] 业务主体名称
   * @apiSuccess {String} [data.tableName] 表名称
   */
  @ResponseBody
  @RequestMapping(value = "/metricCategory", method = {
          RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String getMetricCategory(StandardMainInfo standardMainInfo) {

    return standInfoService.selectListCategory(standardMainInfo);
  }
  //查询代码定义
  @ResponseBody
  @RequestMapping(value = "/codeDefinition", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String codeDefinition(@RequestBody Map<String,Object> map) {

    return standInfoService.selectCodeDefinition(map);
  }

}
