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
  * @apiVersion 1.0.0
  * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term
  * @apiName term
  * @apiGroup TermInfoController
  * @apiParam {Integer} [nums]
  * @apiParam {String} [entityDesc]
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
  * */
  //查询
  @ResponseBody
  @RequestMapping(value = "/term", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }

  /**
  * @api {get} /term/:Entityid 按id术语查询接口
  * @apiVersion 1.0.0
  * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term/
  * @apiName  selectTerm
  * @apiGroup TermInfoController
  * @apiParam {Integer} [Entityid] 术语id
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
  *
  *
  * */
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
   * @apiParam {Integer} [tableInfo.id] 表id
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
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} data  返回数据
   * @apiSuccess {String} msg  成功或者错误信息
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
