package com.quantchi.metadatamgr.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.metadatamgr.data.entity.MDMountNodeInfo;
import com.quantchi.metadatamgr.service.MetaDataTreeMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "api")
public class MetaDataTreeMenuController {

	private static final Logger logger = LoggerFactory.getLogger(MetaDataTreeMenuController.class);

	private static final int MAX_NUM_RET = 20;

	@Autowired
	MetaDataTreeMenuService metaDataTreeMenuService;

	/**
	 * @api {get} /api/metadataTree 获取元数据树形列表
	 * @apiDescription 不带数据源,则返回数据源上层,带数据源参数，则返回表列表，带表参数，则返回对应的字段
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/metadataTree/
	 * @apiName metadataTree_menu
	 * @apiGroup MetaDataTreeMenuController
	 * @apiParam {String} [datasourceId] 数据源id
	 * @apiParam {String} [tableId] 表id
	 * @apiParam {String} [keyword] 搜索关键字
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = {"/metadataTree"}, method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
	public
	@ResponseBody
	String getMetaDataTreeMenu(@RequestParam(value="datasourceId",required = false) String  datasourceId,
														 @RequestParam(value="tableId",required = false) String  tableId,
														 @RequestParam(value="keyword",required = false) String keyword) {
		try {
			Object result = null;

			if(keyword != null && !"".equals(keyword))
				//搜索关键字
				result = metaDataTreeMenuService.searchMenuTree(keyword);
			else if(datasourceId != null) {
				//取表信息
				result = metaDataTreeMenuService.getTablesInMenuTree(datasourceId);
				result = ((List<MDMountNodeInfo>)result).stream().limit(MAX_NUM_RET).collect(Collectors.toList());
			}
			else if(tableId != null){
				//取字段信息
				result = metaDataTreeMenuService.getFieldsInMenuTree(tableId);
				result = ((List<MDMountNodeInfo>)result).stream().limit(MAX_NUM_RET).collect(Collectors.toList());
			}else
				result = metaDataTreeMenuService.getMenuTree();

			return JsonResult.successJson(result, ResultCode.SUCCESS, "getMetaDataTreeMenu job success.");
		} catch (Exception e) {
			logger.error("getMetaDataTreeMenu func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getMetaDataTreeMenu job failed.");
		}
	}

	/**
	 * @api {get} /api/getBusinSystemList 获取业务系统列表
 	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getBusinSystemList
	 * @apiName getBusinSystemList
	 * @apiGroup MetaDataTreeMenuController
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = {"/getBusinSystemList"}, method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  String getBusinSystemList( ) {
		try {
			Object result = null;
            result=metaDataTreeMenuService.getBusinSystemList(new HashMap<>());
			return JsonResult.successJson(result, ResultCode.SUCCESS, "getBusinSystemList job success.");
		} catch (Exception e) {
			logger.error("getExtractorJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getBusinSystemList job failed.");
		}
	}

	/**
	 * @api {get} /api/getRuleClassTree 获取规则类树
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getRuleClassTree
	 * @apiName getRuleClassTree
	 * @apiGroup MetaDataTreeMenuController
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = {"/getRuleClassTree"}, method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  String getRuleClassTree( ) {
		try {
			Object result = null;
			result=metaDataTreeMenuService.getRuleTypeTree();
			return JsonResult.successJson(result, ResultCode.SUCCESS, "getRuleClassTree job success.");
		} catch (Exception e) {
			logger.error("getExtractorJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getRuleClassTree job failed.");
		}
	}
	/**
	 * @api {get} /api/getRuleClass 获取规则大小类数据
	 * @apiVersion 1.0.0
	 * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getRuleClass
	 * @apiName getRuleClass
	 * @apiGroup MetaDataTreeMenuController
	 * @apiParam {String} [type] 大小类 big 大类
	 * @apiSuccess {String} code 成功或者错误代码200成功，500错误
	 * @apiSuccess {String} msg  成功或者错误信息
	 * @apiSuccess {List} [data] 返回数据
	 */
	@RequestMapping(value = {"/getRuleClass"}, method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
	@ResponseBody
	public  String getRuleClass(@RequestParam(value="type",required = false) String  type) {
		try {
			return metaDataTreeMenuService.getRuleType(type);
		} catch (Exception e) {
			logger.error("getExtractorJob func err: {}", e);
			return JsonResult.successJson(null, ResultCode.ERROR, "getRuleClass job failed.");
		}
	}
}
