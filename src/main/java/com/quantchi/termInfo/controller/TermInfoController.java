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

  //查询
  @ResponseBody
  @RequestMapping(value = "/term", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }

  //查询
  @ResponseBody
  @RequestMapping(value = "/term/{EntityId}", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String selectTerm(@PathVariable String EntityId, TermInfoPojo termInfoPojo) {
    termInfoPojo.setEntityId(EntityId);
    return service.selectTerm(termInfoPojo);
  }

  /**
   * @api {post} /term/:id 术语添加接口
   * @apiName TermInfoController
   * @apiGroup TermInfoController
   * @apiParam {Object[]} termGenInfos
   * @apiParam {PhysicalFieldInfo[]} fieldInfoList
   * @apiParam {Integer} [fieldInfoList.id]
   * @apiParam {String} [fieldInfoList.entityId]
   * @apiParam {String} [fieldInfoList.physicalFieldHash]
   * @apiParam {String} [fieldInfoList.physicalField]
   * @apiParam {String} [fieldInfoList.physicalFieldDesc]
   * @apiParam {String} [fieldInfoList.physicalTable]
   * @apiParam {String} [fieldInfoList.physicalDb]
   * @apiParam {String} [fieldInfoList.dataType]
   * @apiParam {Integer} [fieldInfoList.dataLength]
   * @apiParam {Integer} [fieldInfoList.dataPrecision]
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
   * @apiParam {PhysicalTableInfo} tableInfo
   * @apiParam {Integer} [tableInfo.id]
   * @apiParam {String} [tableInfo.physicalTableHash]
   * @apiParam {String} [tableInfo.physicalTable]
   * @apiParam {String} [tableInfo.physicalDb]
   * @apiParam {String} [tableInfo.tableType]
   * @apiParam {String} [tableInfo.tableName]
   * @apiParam {String} [tableInfo.tableDesc]
   * @apiParam {Date} [tableInfo.lastModifiedTime]
   * @apiParam {String} [tableInfo.partitionFlag]
   * @apiParam {String} [tableInfo.primaryKey]
   * @apiParam {String} [tableInfo.foreignKey]
   * @apiSuccess {String} firstname Firstname of the User.
   * @apiSuccess {String} lastname  Lastname of the User.
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
