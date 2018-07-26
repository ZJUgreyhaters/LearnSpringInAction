package com.quantchi.lineage.controller;


import com.quantchi.common.util;
import com.quantchi.lineage.service.LineAgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class LineAgeController {

    private static final Logger logger = LoggerFactory.getLogger(LineAgeController.class);

    @Autowired
    private LineAgeService lineAgeService;


    /**
     * @api {get} /api/lineage 血缘关系查询接口
     * @apiPermission none
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/appi/lineage
     * @apiName lineage
     * @apiGroup LineAgeController
     * @apiParam {String} termId 指标编号
     * @apiParam {String} [type] 查询类型:ALL 代表全链路
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回血缘数据列表
     * @apiSuccess {String} [data.metricId] 指标id
     * @apiSuccess {String} [data.metricName] 指标名称
     * @apiSuccess {String} [data.phsicalFieldId] 物理字段id
     * @apiSuccess {String} [data.phsicalFieldName] 物理字段名称
     * @apiSuccess {String} [data.hasChild] 是否有孩子节点
     * @apiSuccess {List} [data.edges] 边信息列表
     * @apiSuccess {List} [data.edges.source] 边源节点id
     * @apiSuccess {List} [data.edges.target] 边目标节点id
     */
    @RequestMapping(value = "/lineage", method = {RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> list(@RequestBody String bodyString) {
        try{
            return util.genRet(200, null, "", 0);
        }catch (Exception e){
            return util.genRet(500, null, e.getMessage(), 0);
        }
    }

}
