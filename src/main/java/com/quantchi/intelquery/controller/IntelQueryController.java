package com.quantchi.intelquery.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.common.Util;
import com.quantchi.intelquery.QueryParser;
import com.quantchi.intelquery.SqlFormatter;
import com.quantchi.intelquery.SqlFormatter.Builder;
import com.quantchi.intelquery.StepResult;
import com.quantchi.intelquery.TokenizingResult;
import com.quantchi.intelquery.date.formatter.NormalFormatter;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.query.BasicQuery;
import com.quantchi.intelquery.query.QueryNodes;
import com.quantchi.intelquery.query.QueryWithNodes;
import com.quantchi.intelquery.query.QueryWithTree;
import com.quantchi.intelquery.service.IntelQueryService;
import com.quantchi.intelquery.sqlquery.SqlQuery;
import com.quantchi.intelquery.tokenize.search.Replacement;
import com.quantchi.intelquery.utils.SerializationUtils;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping(value = "api")
public class IntelQueryController {

  private static final Logger logger = LoggerFactory.getLogger(IntelQueryController.class);

  @Autowired
  private IntelQueryService intelQueryService;

  /**
   * @api {get} /api/getBusiCate 智能取数页面获取业务接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getBusiCate
   * @apiName getBusiCate
   * @apiGroup IntelQueryController
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回业务类别数据列表
   * @apiSuccess {String} [data.id] 返回业务类别id
   * @apiSuccess {String} [data.businessTypeName] 返回业务类别名称
   */
  @RequestMapping(value = "/getBusiCate", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String getBusiCate() {
    try {
      List<Map<String, Object>> busiCate = intelQueryService.getBusiCate();
      return JsonResult.successJson(busiCate);
    } catch (Exception e) {
      logger.info("get busiCate error", e);
      return JsonResult.errorJson("select busiCate error");
    }
  }

  /**
   * @api {get} /api/getRecommendQuery 获取推荐问句接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getRecommendQuery
   * @apiName getRecommendQuery
   * @apiGroup IntelQueryController
   * @apiParam {String} businessTypeId 业务分类id
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiSuccess {String} [data.id] 返回推荐问句id
   * @apiSuccess {String} [data.query] 返回推荐问句
   */
  @RequestMapping(value = "/getRecommendQuery", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String getRecommendQuery(String businessTypeId) {
    try {
      List<Map<String, Object>> recommendQuery = intelQueryService
          .getRecommendQuery(businessTypeId);
      return JsonResult.successJson(recommendQuery);
    } catch (Exception e) {
      logger.info("get Recommend error", e);
      return JsonResult.errorJson("select Recommend error");
    }
  }

  /**
   * @api {get} /api/getRelatedQuery 获取相关问句接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getRelatedQuery
   * @apiName getRelatedQuery
   * @apiGroup IntelQueryController
   * @apiParam {String} keyword 关键词
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiSuccess {String} [data.id] 返回相关问句id
   * @apiSuccess {String} [data.query] 返回相关问句
   */
  @RequestMapping(value = "/getRelatedQuery", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  Map<String, Object> getRelatedQuery(@RequestParam(value = "keyword") String keyword) {
    try {

      return Util.genRet(200, null, "", 0);
    } catch (Exception e) {
      return Util.genRet(500, null, e.getMessage(), 0);
    }
  }

  /**
   * @api {post} /api/basicQuery 智能取数查询接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/basicQuery
   * @apiName basicQuery
   * @apiGroup IntelQueryController
   * @apiParam {String} query 查询语句
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiSuccess {Map} [data.candidates] 返回联想问句
   * @apiSuccess {List} [data.candidates.queryNodes] 返回问句的分词集合
   * @apiSuccess {String} [data.candidates.queryNodes.node] 问句的分词
   * @apiSuccess {String} [data.candidates.queryNodes.serializeNode] 问句的分词的序列化
   * @apiSuccess {List} [data.candidates.composeList] 问句截断结合
   * @apiSuccess {String} [data.candidates.composeList.begin] 问句起截断
   * @apiSuccess {String} [data.candidates.composeList.end] 问句始截断
   * @apiSuccess {List} [data.candidates.composeList.compose] 替换词集合
   * @apiSuccess {String} [data.candidates.composeList.compose.node] 替换词分词
   * @apiSuccess {String} [data.candidates.composeList.compose.serializeNode] 替换词分词的序列化
   * @apiSuccess {List} [data.steps] 返回数据结果
   * @apiSuccess {String} [data.steps.node] 数据
   * @apiSuccess {String} [data.steps.serializeNode] 序列化数据
   * @apiSuccess {List} [data.indexInfo] 返回指标信息
   * @apiSuccess {String} [data.indexInfo.entityId] 指标id
   * @apiSuccess {String} [data.indexInfo.entityName] 指标英文名
   * @apiSuccess {String} [data.indexInfo.entityDesc] 指标中文名
   * @apiSuccess {String} [data.indexInfo.businessDefinition] 业务定义
   * @apiSuccess {String} [data.indexInfo.businessRule] 业务口径
   * @apiSuccess {List} [data.tabulate] 返回列表结果
   * @apiSuccess {List} [data.metrics] 返回指标结果，如果没有则返回空数组
   * @apiSuccess {List} [data.metrics] 返回指标结果，如果没有则返回空数组
   * @apiSuccess {String} [data.metrics.category] 指标分类目录
   * @apiSuccess {String} [data.metrics.seg_name] 指标分词结果
   * @apiSuccess {String} [data.metrics.definition] 指标业务定义
   * @apiSuccess {String} [data.metrics.db_field] 指标数据库字段
   * @apiSuccess {String} [data.metrics.id] 指标id
   * @apiSuccess {String} [data.metrics.type] 指标类型
   * @apiSuccess {String} [data.metrics.dept] 两融部门
   * @apiSuccess {String} [data.metrics.en_name] 指标英文名
   * @apiSuccess {double} [data.metrics.hit_ratio] 指标搜索分数
   * @apiSuccess {double} [data.metrics._version_] 搜索系统里的版本号
   * @apiContentType application/json
   * @apiSuccessExample {json} Success-Response: {"data":{"candidates":{"queryNodes":[{"node":"","serializeNode":""}],
   * "composeList":[{"begin":"", "end":"", "compose":[{"node":"","serializeNode":""},{"node":"","serializeNode":""}]},
   * {"begin":"", "end":"","compose":[{"node":"","serializeNode":""},{"node":"","serializeNode":""}]}
   * ]}, "steps":[{"node":"","serializeNode":""}], "tabulate":[{id:"","name":"","amount":"","maintenance":"","totalAssets":""}],
   * "metrics":[ { "cn_name": "融资负债", "category": "融资融券>两融客户>资产负债", "seg_name": "融资 负债",
   * "definition": "融资负债", "db_field": "dmp_demo.fact_cust_balance.fin_debit", "id": "224", "type":
   * "entity", "dept": "两融部门", "en_name": "fin_debit", "_version_": 1602053511199064069,
   * "hit_ratio": 1 } ], "indexInfo":[{"entityId":"","entityName":"","entityDesc":"","businessDefinition":"","businessRule":""}]
   * } }
   */
  @ResponseBody
  @RequestMapping(value = "/basicQuery", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String basicQuery(@RequestBody Map<String, Object> map) {
    try {
      Map<String, Object> resultMap = new HashMap();
      String query = map.get("q").toString();
      List<Object> metricsRet = intelQueryService.getMetricsRet(query);
      if (map.get("page_size") != null && map.get("page") != null) {
        metricsRet = Paging.pagingPlugObject(metricsRet, Integer.parseInt(map.get("page_size").toString()),
            Integer.parseInt(map.get("page").toString()));
      }
      resultMap.put("metrics", metricsRet);
      if (metricsRet != null && !metricsRet.isEmpty()) {
        return JsonResult.successJson(resultMap);
      }
      BasicQuery basicquery = new BasicQuery(query);
      StepResult result = QueryParser.getInstance().parse(basicquery);
      Map<String,Object> candidates = null;
      if (result instanceof TokenizingResult) {
        candidates = new HashMap<>();
        QueryWithNodes queryWithNodes = ((TokenizingResult) result).getQuery();
        QueryNodes nodes = queryWithNodes.getNodes();
        List<Map<String, Object>> queryNodes = new ArrayList<>();
        for (SemanticNode node : nodes) {
          Map<String, Object> nodeMap = new HashMap<>();
          String base64String = SerializationUtils.toSerializedString(node);
          nodeMap.put("node", node.getText());
          nodeMap.put("serializeNode", base64String);
          queryNodes.add(nodeMap);
        }
        List<Map<String, Object>> composeList = new ArrayList<>();
        List<Replacement> replacements = ((TokenizingResult) result).getReplacements();
        for (Replacement replacement : replacements) {
          Map<String, Object> composeMap = new HashMap<>();
          Integer begIndex = replacement.getBegIndex(); // 需要用户选择的起始node index
          Integer endIndex = replacement.getEndIndex(); // 需要用户选择的结束node index，左闭右开
          List<QueryNodes> compose = replacement.getAllCandidates(); // 所有可能的结果
          List<Map<String, Object>> composeNodeList = new ArrayList<>();
          for (QueryNodes queryNode : compose) {
            Map<String, Object> queryNodeMap = new HashMap<>();
            StringBuilder nodeBuilder = new StringBuilder();
            for (SemanticNode node : queryNode) {
              nodeBuilder.append(node.getText());
            }
            queryNodeMap.put("node", nodeBuilder);
            queryNodeMap.put("serializeNode", SerializationUtils.toSerializedString(queryNode));
            composeNodeList.add(queryNodeMap);
          }
          composeMap.put("begIndex", begIndex);
          composeMap.put("endIndex", endIndex);
          composeMap.put("compose", composeNodeList);
          composeList.add(composeMap);
        }
        candidates.put("queryNodes",queryNodes);
        candidates.put("composeList",composeList);
      }
      QueryWithTree queryTree = result.getFinalTree();
      //String descText = queryTree.getTextForUser();
      SqlFormatter formatter = new Builder()
          .dateFormatter(new NormalFormatter(DateTimeFormatter.BASIC_ISO_DATE))
          .build();
      SqlQuery sqlQuery = queryTree.getSqlQuery(formatter);
      Map<String, Object> tabulate = intelQueryService.execsql(sqlQuery.toSql(),map);

      List<Optional<String>> selectedFields = queryTree.getSqlQuery().getSelectedFields();
      List<QueryWithTree> steps = result.getSteps();
      List<Map<String,Object>> stepsList = new ArrayList<>();
      for(QueryWithTree queryWithTree:steps){
        Map<String,Object> stepsMap = new HashMap<>();
        stepsMap.put("node",queryWithTree.getTextForUser());
        stepsMap.put("serializeNode",SerializationUtils.toSerializedString(queryWithTree));
        stepsList.add(stepsMap);
      }
      resultMap.put("tabulate", tabulate);
      resultMap.put("steps", stepsList);
      resultMap.put("candidates", candidates);
      return JsonResult.successJson(resultMap);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("get basicQuery error", e);
      return JsonResult.errorJson("get basicQuery error");
    }
  }

  /**
   * @api {post} /api/likenum 点赞接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/likenum
   * @apiName likenum
   * @apiGroup IntelQueryController
   * @apiParam {String} id 点赞语句id
   * @apiParam {String} type 是否点赞(1:点赞，2:不点赞)
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   */
  @ResponseBody
  @RequestMapping(value = "/likenum", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String likenum(@RequestBody Map<String, Object> map) {
    try {
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @api {get} /api/download 智能取数下载接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/download
   * @apiName download
   * @apiGroup IntelQueryController
   * @apiParam {String} query 查询语句
   */
  @ResponseBody
  @RequestMapping(value = "/download", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public String download(HttpServletResponse response, String query) {
    try {
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @api {post} /api/associateQuery 智能取数联想语句查询接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/associateQuery
   * @apiName associateQuery
   * @apiGroup IntelQueryController
   * @apiParam {String} query 序列化的语句
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiSuccess {List} [data.steps] 返回数据结果
   * @apiSuccess {String} [data.steps.node] 数据
   * @apiSuccess {String} [data.steps.serializeNode] 序列化数据
   * @apiSuccess {List} [data.indexInfo] 返回指标信息
   * @apiSuccess {String} [data.indexInfo.entityId] 指标id
   * @apiSuccess {String} [data.indexInfo.entityName] 指标英文名
   * @apiSuccess {String} [data.indexInfo.entityDesc] 指标中文名
   * @apiSuccess {String} [data.indexInfo.businessDefinition] 业务定义
   * @apiSuccess {String} [data.indexInfo.businessRule] 业务口径
   * @apiSuccess {List} [data.tabulate] 返回列表结果
   * @apiContentType application/json
   * @apiSuccessExample {json} Success-Response: {"data":{ "steps":[{"node":"","serializeNode":""}],
   * "tabulate":[{id:"","name":"","amount":"","maintenance":"","totalAssets":""}],
   * "indexInfo":[{"entityId":"","entityName":"","entityDesc":"","businessDefinition":"","businessRule":""}]
   * } }
   */
  @ResponseBody
  @RequestMapping(value = "/associateQuery", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String associateQuery(@RequestBody Map<String, Object> map) {
    try {
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @api {post} /api/stepsQuery 智能取数步骤查询接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/stepsQuery
   * @apiName stepsQuery
   * @apiGroup IntelQueryController
   * @apiParam {String} query 查询语句
   * @apiParam {String} querySerialize 序列化之后的查询语句
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiSuccess {List} [data.tabulate] 返回列表结果
   */
  @ResponseBody
  @RequestMapping(value = "/stepsQuery", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String stepsQuery(@RequestBody Map<String, Object> map) {
    try {
      /*QueryNodes queryNodes = queryWithNodes.getNodes();
      queryNodes.replace(begIndex, endIndex, candidate); // 调用 replace API 将原来的Nodes替换成用户选择的候选项
      StepResult result = QueryParser.getInstance().parse(queryWithNodes);*/
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @api {get} /api/queryInstance 键盘精灵接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/queryInstance
   * @apiName queryInstance
   * @apiGroup IntelQueryController
   * @apiParam {String} q 查询语句
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   * @apiSuccess {List} [data] 返回推荐问句列表
   * @apiContentType application/json
   * @apiSuccessExample {json} Success-Response: "data": [ { "_version_": 1602053511204306951,
   * "category": "融资融券>两融合同>合同主信息", "cn_name": "融资头寸", "db_field": "dmp_demo.dim_contract.fin_cashgroup_no",
   * "definition": "融资头寸", "dept": "两融部门", "en_name": "fin_cashgroup_no", "hit_ratio": 0.5, "id":
   * "348", "replace_origin": "融资\"", "replace_origin_seg": "融资", "seg_name": "融资 头寸", "type":
   * "entity", "weight": 0.65 } ]
   */
  @RequestMapping(value = "/queryInstance", method = {
      RequestMethod.GET}, produces = "application/json;charset=UTF-8")
  public
  @ResponseBody
  String queryInstance(@RequestParam("q") String q) {
    try {
      List<Object> quickMacroQuery = intelQueryService.getQuickMacroQuery(q);
      return JsonResult.successJson(quickMacroQuery);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("queryInstance error", e);
      return JsonResult.errorJson("queryInstance error");
    }


  }
}