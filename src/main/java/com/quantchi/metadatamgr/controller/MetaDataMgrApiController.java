package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.Util;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
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
@RequestMapping(value = "api/metadata")
public class MetaDataMgrApiController {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrApiController.class);

  @Autowired
  private MetaDataMgrApiService metaDataMgrApiService;


  /**
   * 由于多人同时编写，团队内部没有统一的规范，导致命名混乱，将在后期逐步规范
   *
   */

  @RequestMapping(value = "/datasource/list", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> list(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);
      String dsName = "";
      if (json.get("data_source_name") != null) {
        dsName = json.get("data_source_name").toString();
      }
      //int start = Integer.parseInt(json.get("page").toString());
      //int pagesize = Integer.parseInt(json.get("page_size").toString());
      Map<String, Object> _ret = metaDataMgrApiService
          .getDSMetaInfo(dsName, json.getString("page"), json.getString("page_size"));
      return Util
          .genRet(200, _ret.get("data"), "ok", Integer.parseInt(_ret.get("total").toString()));
    } catch (Exception e) {
      logger.error("list func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/connect", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> connectTest(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      if (json.get("data_source_host") == null
          || json.get("data_source_port") == null
          || json.get("data_source_username") == null
          || json.get("data_source_passwd") == null) {
        throw new Exception("miss data source connection info");
      } else {
        String host = json.get("data_source_host").toString();
        String port = json.get("data_source_port").toString();
        String username = json.get("data_source_username").toString();
        String pswd = Util.DecodePassword(json.get("data_source_passwd").toString());
        if (json.getString("data_source_type").toLowerCase().equals("hive")) {
          if (json.get("data_source_mysql_url") == null
              || json.get("data_source_mysql_usr") == null
              || json.get("data_source_mysql_pswd") == null) {
            throw new Exception("miss data source meta connection info");
          }
          String url = json.getString("data_source_mysql_url");
          String mysql_user = json.getString("data_source_mysql_usr");
          String mysql_pswd = Util.DecodePassword(json.getString("data_source_mysql_pswd"));
          boolean _retMeta = metaDataMgrApiService
              .connectMysqlTest(url, mysql_user, mysql_pswd);
          if (!_retMeta) {
            return Util.genRet(201, false, "meta 数据库连接失败", 0);
          }
        }

        boolean _ret = metaDataMgrApiService.connectTest(host, port, username, pswd);
        if (_ret) {
          return Util.genRet(200, _ret, "连接成功", 0);
        } else {
          return Util.genRet(201, _ret, "连接失败", 0);
        }
      }
    } catch (Exception e) {
      logger.error("connectTest func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/save", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> save(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      if (json.get("data_source_host") == null
          || json.get("data_source_port") == null
          || json.get("data_source_username") == null
          || json.get("data_source_passwd") == null) {
        throw new Exception("miss data source connection info");
      }

      boolean _ret = metaDataMgrApiService.saveMetaInfo(json);
      if (_ret) {
        return Util.genRet(200, _ret, "修改成功", 0);
      } else {
        return Util.genRet(201, _ret, "修改失败", 0);
      }

    } catch (Exception e) {
      logger.error("save func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/del", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> deleteDataSource(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      boolean _ret = metaDataMgrApiService.delMetaInfo(json);
      if (_ret) {
        return Util.genRet(200, _ret, "删除成功", 0);
      } else {
        return Util.genRet(201, _ret, "删除失败", 0);
      }
    } catch (Exception e) {
      logger.error("deleteDataSource func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/check", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> checkDSName(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      if (json.getString("data_source_name") == null) {
        throw new Exception("miss data source connection info");
      } else {
        String dsName = json.getString("data_source_name");

        boolean _ret = metaDataMgrApiService.chkDSName(dsName);
        if (_ret) {
          return Util.genRet(200, _ret, "成功", 0);
        } else {
          return Util.genRet(201, _ret, "重复", 0);
        }
      }
    } catch (Exception e) {
      logger.error("check func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/getsourcedata", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> extractTables(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      if (json.getString("data_source_name") == null) {
        throw new Exception("miss data source connection info");
      }

      String dsName = json.getString("data_source_name");
      String keyword = json.getString("keyword");
      Map<String, Object> _ret = metaDataMgrApiService.extractTables(dsName, keyword);
      return Util
          .genRet(200, _ret.get("data"), "成功", Integer.parseInt(_ret.get("total").toString()));
    } catch (Exception e) {
      logger.error("extractTables func err:", e.getMessage());
      return Util.genRet(500, null, e.getMessage(), 0);
    }


  }

  @RequestMapping(value = "/datasource/localsave", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> localSave(@RequestBody String bodyString) {
    try {

      JSONObject json = JSONObject.parseObject(bodyString);

      boolean _ret = metaDataMgrApiService.saveTablesAndFields(json);
      if (_ret) {
        return Util.genRet(200, _ret, "成功", 0);
      } else {
        return Util.genRet(201, _ret, "失败", 0);
      }
    } catch (Exception e) {
      logger.error("extractTables func err:", e.getMessage());
      return Util.genRet(500, null, "保存失败", 0);
    }
  }

  @RequestMapping(value = "/relation/list", method = {RequestMethod.POST})
  public
  @ResponseBody
  Map<String, Object> relationList(@RequestBody String bodyString) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      JSONObject json = JSONObject.parseObject(bodyString);
      Map<String, Object> map = metaDataMgrApiService.relationList(json);
      responseMap.put("data", map);
      responseMap.put("code", 200);
      responseMap.put("msg", "查询成功");
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", 500);
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }

  }

  @RequestMapping(value = "/relation/save", method = RequestMethod.POST)
  public
  @ResponseBody
  Map<String, Object> relationSave(@RequestBody String bodyString) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      JSONObject json = JSONObject.parseObject(bodyString);
      if (json.getString("from") == null) {
        throw new Exception("miss from");
      }
      if (json.getString("to") == null) {
        throw new Exception("miss to");
      }
      if (json.getString("relation") == null) {
        throw new Exception("miss relation");
      }
      if (metaDataMgrApiService.relationSave(json) <= 0) {
        throw new Exception("save fail");
      }
      responseMap.put("code", 200);
      responseMap.put("msg", "成功");
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", 500);
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }
  }

  @RequestMapping(value = "/relation/del", method = RequestMethod.POST)
  public
  @ResponseBody
  Map<String, Object> relationDel(@RequestBody String bodyString) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      JSONObject json = JSONObject.parseObject(bodyString);
      if (json.getString("relation_id") == null) {
        throw new Exception("miss relation_id");
      }
      if (metaDataMgrApiService.relationDel(json.getString("relation_id")) <= 0) {
        throw new Exception("delete fail");
      }
      responseMap.put("code", 200);
      responseMap.put("msg", "删除成功");
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", 500);
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }

  }

  @RequestMapping(value = "/source/createterm", method = RequestMethod.POST)
  public
  @ResponseBody
  Map<String, Object> createTerm(@RequestBody String bodyString) {
    Map<String, Object> responseMap = new HashMap<>();
    try {
      JSONObject json = JSONObject.parseObject(bodyString);
      if (json.getString("data_source_id") == null) {
        throw new Exception("miss data source id");
      }
      responseMap = metaDataMgrApiService.createTerm(json.getString("data_source_id"));
      return responseMap;
    } catch (Exception e) {
      logger.error(e.getMessage());
      responseMap.put("code", 500);
      responseMap.put("msg", e.getMessage());
      return responseMap;
    }
  }

  //加载数据表

  /**
   * @api {POST} /api/metadata/loadSheet 加载数据表回显表接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/loadSheet
   * @apiGroup MetaDataMgrApiController
   * @apiParam {String} [data_source_id] 数据源id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回数据
   * @apiSuccess {String} [data.id] id值
   * @apiSuccess {String} [data.datasource_id] 数据源id
   * @apiSuccess {String} [data.table_english_name] 数据表英文名
   * @apiContentType application/json
   */
  @ResponseBody
  @RequestMapping(value = "/loadSheet", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String loadSheet(@RequestBody Map<String, Object> map) {
    return metaDataMgrApiService.loadSheet(map);
  }

  //更新字段

  /**
   * @api {POST} /api/metadata/updateField 更新字段接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/updatefield
   * @apiGroup MetaDataMgrApiController
   * @apiParam {String} [tableEnglishName] 表英文名称
   * @apiParam {String} [id] 表id值
   * @apiParam {String} [data_source_id] 数据源id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Map} [data] 返回数据
   * @apiSuccess {List} [data.same] 以存在字段
   * @apiSuccess {Map} [data.newDifferent] 新增或者改名的字段
   * @apiSuccess {String} [data.newDifferent.name] 新增或者改名的字段名称
   * @apiSuccess {String} [data.newDifferent.type] 新增或者改名的字段类型
   * @apiSuccess {Map} [data.oldDifferent] 可能删除的字段
   * @apiSuccess {String} [data.newDifferent.name] 可能删除的字段名称
   * @apiSuccess {String} [data.newDifferent.id] 可能删除的字段id
   * @apiContentType application/json
   * @apiSuccessExample {json}
   * "data":{
   *  "newDifferent": [{"name":"","type":""}],
   *  "oldDifferent": [{"name":"","id":""}],
   *  "same":["branch_no",customer_no"]
   *  }
   *
   */
  @ResponseBody
  @RequestMapping(value = "/updateField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String updatefield(@RequestBody Map<String, Object> map) {
    return metaDataMgrApiService.updatefield(map);
  }

  //操作字段

  /**
   * @api {POST} /api/metadata/insertField 操作字段接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadata/insertField
   * @apiGroup MetaDataMgrApiController
   * @apiParam {String} id 表id值
   * @apiParam {String} data_source_id 数据源id
   * @apiParam {List} operationField 操作字段
   * @apiParam {String} operationField.fieldName 字段名称
   * @apiParam {String} [operationField.oldName] 原字段名称（新增和删除可不填，更新必填）
   * @apiParam {String} operationField.operate 具体操作（insert代表新增，update代表更新，delete代表删除）
   * @apiParam {String} [operationField.type] 字段类型（更新和删除可不填，新增必填）
   * @apiParam {String} [operationField.oldNameId] 字段id（新增可不填，更新和删除必填）
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiContentType application/json
   */
  @ResponseBody
  @RequestMapping(value = "/insertField", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String insertField(@RequestBody Map<String, Object> map) {
    return metaDataMgrApiService.insertField(map);
  }
}
