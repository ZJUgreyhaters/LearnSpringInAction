package com.quantchi.metadatamgr.controller;

import com.quantchi.common.Util;
import com.quantchi.metadatamgr.service.MetaDataMgrFieldApiService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api/metadata/field")
public class MetaDataMgrFieldApiController {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrFieldApiController.class);

  @Autowired
  private MetaDataMgrFieldApiService metaDataMgrFieldApiService;

  @RequestMapping(value = "/search", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> search(@RequestBody Map<String, Object> map) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      responseMap = metaDataMgrFieldApiService.search(map);
      responseMap.put("code", 200);
      responseMap.put("msg", "查询成功");
      return responseMap;
      //return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
    } catch (Exception e) {
      logger.error("list func err:", e.getMessage());
      responseMap.put("code", 500);
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }
  }

  @RequestMapping(value = "/searchField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String searchField(@RequestBody Map<String, Object> map) {
    return metaDataMgrFieldApiService.searchField(map);
  }

  /**@api {POST} /api/metadata/field/insertField 添加字段
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/field/insertField
   * @apiName insertField
   * @apiGroup MetaDataMgrFieldApiController
   * @apiParam {String} physical_field 字段英文名
   * @apiParam {String} physical_field_desc 字段中文名
   * @apiParam {String} physical_table_id 字段所属表的id
   * @apiParam {String} physical_db 字段所属表库名
   * @apiParam {String} data_type 字段类型
   * @apiParam {String} is_index 是否索引
   * @apiParam {String} field_number 字段序号
   * @apiParam {String} is_code_field 是否代码字段
   * @apiParam {String} reference_code 引用代码
   * @apiParam {String} datasource_id 数据源ID
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   **/
  @RequestMapping(value = "/insertField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String insertField(@RequestBody Map<String, Object> map) {
    return metaDataMgrFieldApiService.insertField(map);
  }

  /**@api {POST} /api/metadata/field/updateField 更新字段
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/field/insertField
   * @apiName updateField
   * @apiGroup MetaDataMgrFieldApiController
   * @apiParam {String} id 字段系统id
   * @apiParam {String} physical_field 字段英文名
   * @apiParam {String} physical_field_desc 字段中文名
   * @apiParam {String} physical_table_id 字段所属表的id
   * @apiParam {String} physical_db 字段所属表库名
   * @apiParam {String} data_type 字段类型
   * @apiParam {String} is_index 是否索引
   * @apiParam {String} field_number 字段序号
   * @apiParam {String} is_code_field 是否代码字段
   * @apiParam {String} reference_code 引用代码
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   **/
  @RequestMapping(value = "/updateField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String updateField(@RequestBody Map<String, Object> map) {
    return metaDataMgrFieldApiService.updateField(map);
  }

  /**@api {POST} /api/metadata/field/deleteField 删除字段
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/field/deleteField
   * @apiName deleteField
   * @apiGroup MetaDataMgrFieldApiController
   * @apiParam {String} id 字段系统id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   **/
  @RequestMapping(value = "/deleteField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String deleteField(@RequestBody Map<String, Object> map) {
    return metaDataMgrFieldApiService.deleteField(map);
  }

  @RequestMapping(value = "/edit", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> edit(@RequestBody String bodyString) {
    try {
      return Util.genRet(500, null, "", 0);
      //return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
    } catch (Exception e) {
      logger.error("list func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

}
