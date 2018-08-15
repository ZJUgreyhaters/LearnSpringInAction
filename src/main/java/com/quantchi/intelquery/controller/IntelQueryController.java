package com.quantchi.intelquery.controller;

import com.quantchi.common.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class IntelQueryController {

    private static final Logger logger = LoggerFactory.getLogger(IntelQueryController.class);

    /**
     * @api {get} /api/getBusiCate 智能取数页面获取业务接口
     * @apiPermission none
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/appi/getBusiCate
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
        try{

            return util.genRet(200, null, "", 0);
        }catch (Exception e){
            return util.genRet(500, null, e.getMessage(), 0);
        }
    }

    /**
     * @api {get} /api/getRecommendQuery 获取推荐问句接口
     * @apiPermission none
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/appi/getRecommendQuery
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
    Map<String, Object> getRecommendQuery(@RequestParam(value = "businessTypeId") String businessTypeId) {
        try{

            return util.genRet(200, null, "", 0);
        }catch (Exception e){
            return util.genRet(500, null, e.getMessage(), 0);
        }
    }

    /**
     * @api {get} /api/getRelatedQuery 获取相关问句接口
     * @apiPermission none
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/appi/getRelatedQuery
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
        try{

            return util.genRet(200, null, "", 0);
        }catch (Exception e){
            return util.genRet(500, null, e.getMessage(), 0);
        }
    }

}
