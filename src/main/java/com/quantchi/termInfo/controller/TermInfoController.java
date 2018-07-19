package com.quantchi.termInfo.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.termInfo.pojo.TermGenInfo;
import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 49537 on 2018/6/20.
 */
@Controller
public class TermInfoController {


  @Autowired
  private TermInfoService service;

  /**
   * @api {get} /term 术语查询接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term
   * @apiName term
   * @apiGroup TermInfoController
   * @apiParam {Integer} [page] 页数
   * @apiParam {Integer} [page_size] 每页数据数
   * @apiParam {String} [entityCategory] 逻辑分类ID
   * @apiParam {String} [entityDesc] 实体描述
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {List} [entitys] 实体信息
   * @apiSuccess {String} [data.entitys.entityType] 实体类型
   * @apiSuccess {String} [data.entitys.entityId] 实体id
   * @apiSuccess {String} [data.entitys.entityHash] 实体hash码
   * @apiSuccess {String} [data.entitys.entityName] 实体名
   * @apiSuccess {String} [data.entitys.entityDesc] 实体描述
   * @apiSuccess {String} [data.entitys.entityAlias] 实体别名
   * @apiSuccess {String} [data.entitys.businessRule] 规范描述
   * @apiSuccess {String} [data.entitys.techniqueRule] sql语句
   * @apiSuccess {String} [data.entitys.entityStatus] 实体状态
   * @apiSuccess {String} [data.entitys.controlDept] 管理部门
   * @apiSuccess {String} [data.entitys.assistDept] 分配部门
   * @apiSuccess {String} [data.entitys.devPolicy] 个人信息分类
   * @apiSuccess {String} [data.entitys.regulatory]
   * @apiSuccess {String} [data.entitys.logicType]
   * @apiSuccess {String} [data.entitys.displayType] 数据显示类型
   * @apiSuccess {List} [data.entitys.physicalField] 物理字段
   * @apiSuccess {String} [data.entitys.physicalField.entityId] 实体id
   * @apiSuccess {String} [data.entitys.physicalField.physicalFieldHash] 物理字段hash码
   * @apiSuccess {String} [data.entitys.physicalField.physicalFieldId] 物理字段id
   * @apiSuccess {String} [data.entitys.physicalField.physicalFieldDesc] 物理字段描述
   * @apiSuccess {String} [data.entitys.physicalField.physicalTable] 物理表
   * @apiSuccess {String} [data.entitys.physicalField.physicalDB] 物理数据库
   * @apiSuccess {String} [data.entitys.physicalField.dataType] 数据类型
   * @apiSuccess {String} [data.entitys.physicalField.dataLength] 数据长度
   * @apiSuccess {String} [data.entitys.physicalField.dataPattern]
   * @apiSuccess {String} [data.entitys.physicalField.dataUnit]
   * @apiSuccess {String} [data.entitys.physicalField.fieldPartition]
   * @apiSuccess {List} [data.entitys.physicalField.dataUDC] 条件数据
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.dataUDCCode]
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.dataUDCDesc] 数据描述
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.dataUDCRule] 数据规范
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.dataUDCValue] 数据值
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.entityHash] 数据hash码
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.entityId] 数据id
   * @apiSuccess {String} [data.entitys.physicalField.dataUDC.entityType] 数据类型
   */
  //查询
  @ResponseBody
  @RequestMapping(value = "/term", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }

  /**
   * @api {get} /term/{EntityId} 按id术语查询接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term/
   * @apiName selectTerm
   * @apiGroup TermInfoController
   * @apiParam {String} [Entityid] 术语id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {String} [data.entityType] 实体类型
   * @apiSuccess {String} [data.entityId] 实体id
   * @apiSuccess {String} [data.entityHash] 实体hash码
   * @apiSuccess {String} [data.entityName] 实体名
   * @apiSuccess {String} [data.entityDesc] 实体描述
   * @apiSuccess {String} [data.entityAlias] 实体别名
   * @apiSuccess {String} [data.businessRule] 规范描述
   * @apiSuccess {String} [data.techniqueRule] sql语句
   * @apiSuccess {String} [data.entityStatus] 实体状态
   * @apiSuccess {String} [data.controlDept] 管理部门
   * @apiSuccess {String} [data.assistDept] 分配部门
   * @apiSuccess {String} [data.devPolicy] 个人信息分类
   * @apiSuccess {String} [data.regulatory]
   * @apiSuccess {String} [data.logicType]
   * @apiSuccess {String} [data.displayType] 数据显示类型
   * @apiSuccess {List} [data.physicalField] 物理字段
   * @apiSuccess {String} [data.physicalField.entityId] 实体id
   * @apiSuccess {String} [data.physicalField.physicalFieldHash] 物理字段hash码
   * @apiSuccess {String} [data.physicalField.physicalFieldId] 物理字段id
   * @apiSuccess {String} [data.physicalField.physicalFieldDesc] 物理字段描述
   * @apiSuccess {String} [data.physicalField.physicalTable] 物理表
   * @apiSuccess {String} [data.physicalField.physicalDB] 物理数据库
   * @apiSuccess {String} [data.physicalField.dataType] 数据类型
   * @apiSuccess {String} [data.physicalField.dataLength] 数据长度
   * @apiSuccess {String} [data.physicalField.dataPattern]
   * @apiSuccess {String} [data.physicalField.dataUnit]
   * @apiSuccess {String} [data.physicalField.fieldPartition]
   * @apiSuccess {List} [data.physicalField.dataUDC] 条件数据
   * @apiSuccess {String} [data.physicalField.dataUDC.dataUDCCode]
   * @apiSuccess {String} [data.physicalField.dataUDC.dataUDCDesc] 数据描述
   * @apiSuccess {String} [data.physicalField.dataUDC.dataUDCRule] 数据规范
   * @apiSuccess {String} [data.physicalField.dataUDC.dataUDCValue] 数据值
   * @apiSuccess {String} [data.physicalField.dataUDC.entityHash] 数据hash码
   * @apiSuccess {String} [data.physicalField.dataUDC.entityId] 数据id
   * @apiSuccess {String} [data.physicalField.dataUDC.entityType] 数据类型
   */
  //查询
  @ResponseBody
  @RequestMapping(value = "/term/{EntityId}", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String selectTerm(@PathVariable String EntityId, TermInfoPojo termInfoPojo) {
    termInfoPojo.setEntityId(EntityId);
    return service.selectTerm(termInfoPojo);
  }

  /**
   * @api {post} /term 术语添加接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term
   * @apiName TermInfoController
   * @apiGroup TermInfoController
   * @apiParam {Object[]} termGenInfos
   * @apiParam {PhysicalFieldInfo[]} fieldInfoList 字段信息列表
   * @apiParam {Integer} [fieldInfoList.id] 字段id
   * @apiParam {String} [fieldInfoList.entityId] 实体id
   * @apiParam {String} [fieldInfoList.physicalFieldHash] 物理字段hash码
   * @apiParam {String} [fieldInfoList.physicalField] 物理字段名
   * @apiParam {String} [fieldInfoList.physicalFieldDesc] 物理字段描述
   * @apiParam {String} [fieldInfoList.physicalTable] 对应物理表
   * @apiParam {String} [fieldInfoList.physicalDb] 对应物理数据库
   * @apiParam {String} [fieldInfoList.dataType] 字段数据类型
   * @apiParam {Integer} [fieldInfoList.dataLength] 字段数据类型长度
   * @apiParam {Integer} [fieldInfoList.dataPrecision] 字段数据精准度
   * @apiParam {String} [fieldInfoList.dataPattern]
   * @apiParam {String} [fieldInfoList.dataUnit]
   * @apiParam {String} [fieldInfoList.partitionFlag]
   * @apiParam {String} [fieldInfoList.udcRuleName]
   * @apiParam {String} [fieldInfoList.udcCode]
   * @apiParam {BigDecimal} [fieldInfoList.max]
   * @apiParam {BigDecimal} [fieldInfoList.min]
   * @apiParam {BigDecimal} [fieldInfoList.avg]
   * @apiParam {BigDecimal} [fieldInfoList.dataNull]
   * @apiParam {String} [fieldInfoList.distribution]
   * @apiParam {String} [fieldInfoList.enumeration]
   * @apiParam {PhysicalTableInfo} tableInfo 表信息
   * @apiParam {Integer} [tableInfo.id] 表id（）
   * @apiParam {String} [tableInfo.physicalTableHash] 物理表hash码
   * @apiParam {String} [tableInfo.physicalTable] 物理表名
   * @apiParam {String} [tableInfo.physicalDb] 表的物理数据库名
   * @apiParam {String} [tableInfo.tableType] 表类型
   * @apiParam {String} [tableInfo.tableName] 表名
   * @apiParam {String} [tableInfo.tableDesc] 表描述
   * @apiParam {Date} [tableInfo.lastModifiedTime] 表最后修改时间
   * @apiParam {String} [tableInfo.partitionFlag]
   * @apiParam {String} [tableInfo.primaryKey] 表的主键
   * @apiParam {String} [tableInfo.foreignKey] 表的外键
   * @apiParam {TermMainInfo} termMainInfo 术语主信息 如果有传入值，则是前端录入，如果没有则是后端录入
   * @apiParam {String} [termMainInfo.entityType] 实体类型
   * @apiParam {String} [termMainInfo.entityId] 实体ID
   * @apiParam {String} [termMainInfo.entityHash] 实体MD5校验值
   * @apiParam {String} [termMainInfo.entityName] 实体英文名称
   * @apiParam {String} [termMainInfo.entityDesc] 实体中文名称
   * @apiParam {String} [termMainInfo.entityStatus] 实体状态
   * @apiParam {Date} [termMainInfo.createTime] 生效时间
   * @apiParam {Date} [termMainInfo.offlineTime] 失效时间
   * @apiParam {Integer} [termMainInfo.creator] 创建人ID
   * @apiParam {String} [termMainInfo.controlDept] 主管部门
   * @apiParam {String} [termMainInfo.assistDept] 协办部门
   * @apiParam {String} [termMainInfo.businessDesc] 业务定义
   * @apiParam {String} [termMainInfo.entityCategory] 逻辑分类
   * @apiParam {String} [termMainInfo.regulatory] 是否监管
   * @apiParam {String} [termMainInfo.logicType] 术语值类型
   * @apiParam {String} [termMainInfo.displayType] 显示英文类型
   * @apiParam {String} [termMainInfo.entityAlias] 常用名称
   * @apiParam {String} [termMainInfo.businessRule] 业务口径
   * @apiParam {String} [termMainInfo.techniqueRule] 技术口径（SQL）
   * @apiParam {String} [termMainInfo.devPolicy] 制定依据
   * @apiParam {String} [termMainInfo.remark] 备注
   * @apiParam {String} [termMainInfo.frequency] 统计频率
   * @apiParamExample {json} Request-example: [{ "fieldInfoList": [ { "dataType": "string",
   * "physicalDb": "cust_mining", "physicalField": "init_date", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "median_fund_asset", "physicalTable": "agg_cust_balance_feature_test" }, {
   * "dataType": "double", "physicalDb": "cust_mining", "physicalField": "var_fund_asset",
   * "physicalTable": "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb":
   * "cust_mining", "physicalField": "avg_secu_market_value", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "max_secu_market_value", "physicalTable": "agg_cust_balance_feature_test" }, {
   * "dataType": "double", "physicalDb": "cust_mining", "physicalField": "min_secu_market_value",
   * "physicalTable": "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb":
   * "cust_mining", "physicalField": "median_secu_market_value", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "var_secu_market_value", "physicalTable": "agg_cust_balance_feature_test" }, {
   * "dataType": "double", "physicalDb": "cust_mining", "physicalField": "avg_opfund_market_value",
   * "physicalTable": "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb":
   * "cust_mining", "physicalField": "max_opfund_market_value", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "min_opfund_market_value", "physicalTable": "agg_cust_balance_feature_test" },
   * { "dataType": "double", "physicalDb": "cust_mining", "physicalField":
   * "median_opfund_market_value", "physicalTable": "agg_cust_balance_feature_test" }, { "dataType":
   * "double", "physicalDb": "cust_mining", "physicalField": "var_opfund_market_value",
   * "physicalTable": "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb":
   * "cust_mining", "physicalField": "avg_total_asset", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "max_total_asset", "physicalTable": "agg_cust_balance_feature_test" }, {
   * "dataType": "double", "physicalDb": "cust_mining", "physicalField": "min_total_asset",
   * "physicalTable": "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb":
   * "cust_mining", "physicalField": "median_total_asset", "physicalTable":
   * "agg_cust_balance_feature_test" }, { "dataType": "double", "physicalDb": "cust_mining",
   * "physicalField": "var_total_asset", "physicalTable": "agg_cust_balance_feature_test" } ],
   * "tableInfo": { "physicalDb": "cust_mining", "physicalTable": "agg_cust_balance_feature_test",
   * "tableType": "" }, "termMainInfo":{ "entityType": "1", "entityId": "PT000001",
   * "entityHash":"55e4e1b391831960d8b7e4b61fdfaf7c", "entityName": "gender", "entityDesc": "客户性别",
   * "entityAlias": "性别", "businessRule": "记录个人客户的性别状况，如“男”、“女”等。", "techniqueRule": "select gender
   * from dim_customer", "entityStatus": "正常", "createTime": "2017-01-02T00:00:00.000Z",
   * "offlineTime": "", "creator": "dmp_admin", "controlDept": "机构管理部", "assistDept": "信息管理中心",
   * "devPolicy": "1.GB/T2261.1-2003个人基本信息分类与代码第1部分:人的性别代码", "regulatory": "是", "logicType": "代码",
   * "displayType": "CheckBox", } }]
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} data  返回数据
   * @apiSuccess {String} msg  成功或者错误信息 * @apiSuccessExample {json} Success-Response: HTTP/1.1 200
   * OK { "code": "200", "data": 18, "msg": "success" }
   */
  //插入
  @ResponseBody
  @RequestMapping(value = "/term", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String selectTerm(@RequestBody ArrayList<TermGenInfo> termGenInfos) {
    try {
      return service.insertTerm(termGenInfos);
    } catch (Exception e) {
      return JsonResult.errorJson(e.getMessage());
    }
  }

  //termLogicCategory
  @ResponseBody
  @RequestMapping(value = "/insertTermLogic", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String insertTermLogic(@RequestBody Map<String, Object> requestMap) {
    try {
      return service.insertTermLogic(requestMap);
    } catch (Exception e) {
      return JsonResult.errorJson(e.getMessage());
    }
  }

}
