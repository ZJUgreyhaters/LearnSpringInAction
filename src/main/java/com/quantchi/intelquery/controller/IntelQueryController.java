package com.quantchi.intelquery.controller;

import com.quantchi.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class IntelQueryController {

  private static final Logger logger = LoggerFactory.getLogger(IntelQueryController.class);

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
  @RequestMapping(value = "/getBusiCate", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> getBusiCate() {
    try {

            return Util.genRet(200, null, "", 0);
        }catch (Exception e){
            return Util.genRet(500, null, e.getMessage(), 0);
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
  @RequestMapping(value = "/getRecommendQuery", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> getRecommendQuery(
      @RequestParam(value = "businessTypeId") String businessTypeId) {
    try {

            return Util.genRet(200, null, "", 0);
        }catch (Exception e){
            return Util.genRet(500, null, e.getMessage(), 0);
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
  @RequestMapping(value = "/getRelatedQuery", method = {RequestMethod.GET})
  public
  @ResponseBody
  Map<String, Object> getRelatedQuery(@RequestParam(value = "keyword") String keyword) {
    try {

            return Util.genRet(200, null, "", 0);
        }catch (Exception e){
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
   * @apiSuccess {String} [data.candidates.composeList.begin] 问句起截断
   * @apiSuccess {String} [data.candidates.composeList.end] 问句始截断
   * @apiSuccess {List} [data.candidates.composeList.compose] 替换词集合
   * @apiSuccess {String} [data.candidates.composeList.compose.node] 替换词分词
   * @apiSuccess {String} [data.candidates.composeList.compose.serializeNode] 替换词分词的序列化
   * @apiSuccess {List} [data.steps] 返回数据结果
   * @apiSuccess {List} [data.indexInfo] 返回指标信息
   * @apiSuccess {String} [data.indexInfo.entityId] 指标id
   * @apiSuccess {String} [data.indexInfo.entityName] 指标英文名
   * @apiSuccess {String} [data.indexInfo.entityDesc] 指标中文名
   * @apiSuccess {String} [data.indexInfo.businessDefinition] 业务定义
   * @apiSuccess {String} [data.indexInfo.businessRule] 业务口径
   * @apiSuccess {List} [data.tabulate] 返回列表结果
   * @apiContentType application/json
   * @apiSuccessExample {json} Success-Response
   * {"data":{"candidates":{"queryNodes":{"node":"","serializeNode":""},
   * "composeList":[{"begin":"", "end":"", "compose":[{"node":"","serializeNode":""},{"node":"","serializeNode":""}]},
   * {"begin":"", "end":"","compose":[{"node":"","serializeNode":""},{"node":"","serializeNode":""}]}
   * ]}, "steps":{"",""}, "tabulate":[{id:"","name":"","amount":"","maintenance":"","totalAssets":""}],
   * "indexInfo":[{"entityId":"","entityName":"","entityDesc":"","businessDefinition":"","businessRule":""}]
   * } }
   */
  @ResponseBody
  @RequestMapping(value = "/basicQuery", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String basicQuery(@RequestBody Map<String, Object> map) {
    try {
      /*String q = "今天维保比例";
      Query query = new BasicQuery(q);
      StepResult result = QueryParser.getInstance().parse(query);
      if (result instanceof TokenizingResult) {
        QueryWithNodes queryWithNodes = ((TokenizingResult) result).getQuery();
        QueryNodes nodes = queryWithNodes.getNodes();
        List<Replacement> replacements = ((TokenizingResult) result).getReplacements();
        Integer begIndex = replacements.get(0).getBegIndex(); // 需要用户选择的起始node index
        Integer endIndex = replacements.get(0).getEndIndex(); // 需要用户选择的结束node index，左开右闭
        List<QueryNodes> candidates = replacements.get(0).getCandidates();
      }
      QueryWithTree queryTree = result.getFinalTree();
      String descText = queryTree.getTextForUser();
      SqlFormatter formatter = new Builder()
          .dateFormatter(new NormalFormatter(DateTimeFormatter.BASIC_ISO_DATE))
          .build();
      String sql = queryTree.getSql(formatter);
      List<String> selectedFields = queryTree.getSqlQuery().getSelectedFields();
      List<QueryWithTree> steps = result.getSteps();*/
      return "";
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * @api {post} /api/likenum 点赞接口
   * @apiPermission none
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/likenum
   * @apiName likenum
   * @apiGroup IntelQueryController
   * @apiParam {String} query 点赞语句
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
}