package com.quantchi.lineage.controller;


import com.quantchi.common.Util;
import com.quantchi.lineage.service.LineageService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api")
public class LineageController {
private static final Logger logger = LoggerFactory.getLogger(LineageController.class);

  @Autowired
  private LineageService lineageService;

  /**
   * @api {get} /api/lineage 血缘关系查询接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/appi/lineage
   * @apiName lineage
   * @apiGroup LineageController
   * @apiParam {String} metricId 指标编号
   * @apiParam {String} [type] 查询类型:ALL 代表全链路
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 返回血缘数据列表
   * @apiSuccess {List} [data.nodes] 返回血缘数据列表
   * @apiSuccess {String} [data.nodes.metricId] 指标id
   * @apiSuccess {String} [data.nodes.metricName] 指标名称
   * @apiSuccess {String} [data.nodes.phsicalFieldName] 物理字段名称
   * @apiSuccess {String} [data.nodes.phsicalFieldDesc] 物理字段中文名
   * @apiSuccess {String} [data.nodes.isOpen] 是否可点击
   * @apiSuccess {List} [data.edges] 边信息列表
   * @apiSuccess {String} [data.edges.source] 边源节点id
   * @apiSuccess {String} [data.edges.target] 边目标节点id
   */
  @RequestMapping(value = "/lineage", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> list(@RequestParam(value = "metricId", required = false) String metricId,
      @RequestParam(value = "type", required = false) String type) {
    try {
      logger.info("get metric id is {}", metricId);
      logger.debug("get type is {}", type);
      Map<String, Object> _ret = lineageService.getNodesInfo(metricId, type);
      return Util.genRet(200, _ret, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {get} /api/lineage 血缘关系查询接口
   * @apiVersion 1.0.0
   * @apiName physical lineage api
   * @apiGroup LineageController
   * @apiParam {String} fieldId 指标编号
   * @apiParam {String} [type] 查询类型:ALL 代表全链路
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 返回血缘数据列表
   * @apiSuccess {List} [data.nodes] 返回血缘数据列表
   * @apiSuccess {String} [data.nodes.physicalFieldId] 指标id
   * @apiSuccess {String} [data.nodes.phsicalFieldName] 物理字段名称
   * @apiSuccess {String} [data.nodes.phsicalFieldDesc] 物理字段中文名
   * @apiSuccess {String} [data.nodes.isOpen] 是否可点击
   * @apiSuccess {List} [data.edges] 边信息列表
   * @apiSuccess {String} [data.edges.source] 边源节点id
   * @apiSuccess {String} [data.edges.target] 边目标节点id
   */
  @RequestMapping(value = "/physicalLineage", method = {RequestMethod.GET})
  @ResponseBody
  public Map<String, Object> physicalLineage(
      @RequestParam(value = "fieldId") String fieldId,
      @RequestParam(value = "type", required = false) String type) {
    try {
      Map<String, Object> _ret = lineageService.getPhysicalFieldLineage(fieldId, type);
      return Util.genRet(200, _ret, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {get} /api/physicalLineage/downstream 查询downstream
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiName physical Lineage downstream
   * @apiGroup LineageController
   * @apiParam {String} fieldName 字段名(db.table.field)
   * @apiParam {String} datasourceId
   * @apiParam {int} level 要查看几层的呢
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 返回血缘数据列表
   * @apiSuccess {List} [data.nodes] 返回血缘数据列表
   * @apiSuccess {String} [data.nodes.phsicalFieldId] 字段id
   * @apiSuccess {String} [data.nodes.phsicalFieldName] 物理字段名称
   * @apiSuccess {String} [data.nodes.phsicalFieldDesc] 物理字段中文名
   * @apiSuccess {String} [data.nodes.isOpen] 是否可点击
   * @apiSuccess {List} [data.edges] 边信息列表
   * @apiSuccess {String} [data.edges.source] 边源节点id
   * @apiSuccess {String} [data.edges.target] 边目标节点id
   */
  @RequestMapping(value = "/physicalLineage/downstream", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> downstream(@RequestParam(value = "fieldName") String fieldName,
      @RequestParam(value = "datasourceId") String datasourceId,
      @RequestParam(value = "level") int level) {
    try {
      Object o = lineageService.getDownStream(datasourceId, fieldName, level);
      return Util.genRet(200, o, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {get} /api/physicalLineage/upstream 查询upstream
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiName physical Lineage upstream
   * @apiGroup LineageController
   * @apiParam {String} fieldName 字段名(db.table.field)
   * @apiParam {String} datasourceId
   * @apiParam {int} level 要查看几层的呢
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 与上面相同
   */
  @RequestMapping(value = "/physicalLineage/upstream", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> upstream(@RequestParam(value = "fieldName") String fieldName,
      @RequestParam(value = "datasourceId") String datasourceId,
      @RequestParam(value = "level") int level) {
    try {
      Object o = lineageService.getUpStream(datasourceId, fieldName, level);
      return Util.genRet(200, o, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {get} /api/physicalLineage/upstreamByFieldId 查询upstream
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiName physical Lineage upstream: get by field id
   * @apiGroup LineageController
   * @apiParam {String} fieldId
   * @apiParam {int} level 要查看几层的呢
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 与上面相同
   */
  @RequestMapping(value = "/physicalLineage/upstreamByFieldId", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> upstream(@RequestParam(value = "fieldId") String fieldId,
      @RequestParam(value = "level") int level) {
    try {
      Object o = lineageService.getUpStream(fieldId, level);
      return Util.genRet(200, o, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {get} /api/physicalLineage/downstreamByFieldId 查询downstream
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiName physical Lineage downstream: get by field id
   * @apiGroup LineageController
   * @apiParam {String} fieldId
   * @apiParam {int} level 要查看几层的呢
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {Object} [data] 与上面相同
   */
  @RequestMapping(value = "/physicalLineage/downstreamByFieldId", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> downstream(@RequestParam(value = "fieldId") String fieldId,
      @RequestParam(value = "level") int level) {
    try {
      Object o = lineageService.getDownStream(fieldId, level);
      return Util.genRet(200, o, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

}
