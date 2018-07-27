package com.quantchi.lineage.controller;


import com.quantchi.common.util;
import com.quantchi.lineage.service.LineageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @apiGroup LineAgeController
     * @apiParam {String} metricId 指标编号
     * @apiParam {String} [type] 查询类型:ALL 代表全链路
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {Object} [data] 返回血缘数据列表
     * @apiSuccess {List} [data.nodes] 返回血缘数据列表
     * @apiSuccess {String} [data.nodes.metricId] 指标id
     * @apiSuccess {String} [data.nodes.metricName] 指标名称
     * @apiSuccess {String} [data.nodes.phsicalFieldName] 物理字段名称
     * @apiSuccess {String} [data.isOpen] 是否可点击
     * @apiSuccess {List} [data.edges] 边信息列表
     * @apiSuccess {List} [data.edges.source] 边源节点id
     * @apiSuccess {List} [data.edges.target] 边目标节点id
     */
    @RequestMapping(value = "/lineage", method = {RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> list(@RequestParam(value = "metricId", required = false) String metricId,
                             @RequestParam(value ="type", required = false) String type) {
        try{
            Map<String,Object> _ret = lineageService.getNodesInfo(metricId,type);
            return util.genRet(200, _ret, "", 0);
        }catch (Exception e){
            return util.genRet(500, null, e.getMessage(), 0);
        }
    }

}
