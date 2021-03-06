package com.quantchi.authority.controller;


import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import com.quantchi.common.JsonResult;
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
@RequestMapping("api")
public class AuthorityDetailController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityDetailController.class);

    @Autowired
    private AuthorityDetailService authoritydetailService;
    @Autowired
    private AuthorityService authorityService;


    /** @api {post} /api/addDataAuth 添加新的数据权限 或 修改权限 有传l_authid 则为修改
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/addDataAuth
     * @apiName addDataAuth
     * @apiGroup AuthorityDetailController
     * @apiParam {Object} [authority] 权限
     * @apiParam {String} [authority.c_authname] 权限名称
     * @apiParam {String} [authority.c_isenable] 是否生效
     * @apiParam {String} [authority.d_createdate] 是否生效
     * @apiParam {String} [authority.c_authtype] 权限类型  0:功能权限  1:数据权限'
     * @apiParam {String} [authority.l_datatype] 数据权限类型  0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限
     * @apiParam {Int} [authority.l_authid] 权限id   因为还未插入表 填0
     * @apiParam {List} [authDetail]  权限明细 可以装入多个
     * @apiParam {Int} [authDetail.l_authdetailid] 明细权限ID    因为还未插入表 填0
     * @apiParam {String} [authDetail.c_database] 库名字
     * @apiParam {String} [authDetail.c_tablename] 表名字
     * @apiParam {String} [authDetail.c_column] 表字段名字
     * @apiParam {String} [authDetail.c_fiter]  过滤条件
     * @apiParam {String} [authDetail.c_url]功能路径
     * @apiParam {String} [authDetail.c_isenable]  是否有效
     * @apiParamExample {json} Request-example:
     *{ "authority":{"c_authname":"dyaln1645","c_isenable":"0","c_authtype":"1","l_datatype":"3","l_authid":0},
     * "authDetail":[{"l_authdetailid":0,"c_tablename":"tstockholder","c_column":"Ace","c_fiterdesc":"ccss","c_fiter":"1122","c_isenable":"0"},{"l_authdetailid":0,"c_tablename":"tdercor","c_column":"Ace","c_fiterdesc":"ccss","c_fiter":"1122","c_isenable":"0"}]}
     * 或者
     * { "authority":{"c_authname":"dyaln1648","c_isenable":"0","c_authtype":"0","l_datatype":"0","l_authid":0},"authDetail":[{"l_authdetailid":0,"c_funcname":"tstockholder","c_url":"Ace" ,"c_isenable":"0"}]}
     **/
    @ResponseBody
    @RequestMapping(value = "/addDataAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String addDataAuth(@RequestBody Map<String, Object> requestMap){
        Map<String, Object> auth = (Map<String, Object>) requestMap.get("authority") ;

        String authid  = new String();
        if(auth.get("l_authid") != null){
          authid =auth.toString();
        }
        if( authid.isEmpty()||authid.equals("")||authid.equals("0"))
        {
            try {
                authoritydetailService.addAuthAndDataDetail(requestMap);
                return JsonResult.successJson();

            }catch (Exception e ){
                e.printStackTrace();
                return JsonResult.errorJson("error!");
            }
        }

        try {
            authorityService.modifyAuth(requestMap);
            return JsonResult.successJson();
        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }

    }
    /** @api {post} /api/getDataAuthDetail 查询权限的明细
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getDataAuthDetail
     * @apiName getDataAuthDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [l_authid] 权限id
     * @apiSuccess {Object} [authority] 权限
     * @apiSuccess {String} [authority.c_authname] 权限名称
     * @apiSuccess {String} [authority.c_isenable] 是否生效
     * @apiSuccess {String} [authority.d_createdate] 是否生效
     * @apiSuccess {String} [authority.c_authtype] 权限类型  0:功能权限  1:数据权限'
     * @apiSuccess {String} [authority.l_datatype] 数据权限类型  0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限
     * @apiSuccess {Int} [authority.l_authid] 权限id   因为还未插入表 填0
     * @apiSuccess {List} [authDetail]  权限明细 可以装入多个
     * @apiSuccess {Int} [authDetail.l_authdetailid] 明细权限ID    因为还未插入表 填0
     * @apiSuccess {String} [authDetail.c_database] 库名字
     * @apiSuccess {String} [authDetail.c_tablename] 表名字
     * @apiSuccess {String} [authDetail.c_column] 表字段名字
     * @apiSuccess {String} [authDetail.c_fiter]  过滤条件
     * @apiSuccess {String} [authDetail.c_url]功能路径
     * @apiSuccess {String} [authDetail.c_isenable]  是否有效
     * @apiParamExample {json} Request-example:
     * {"l_authid":1,"c_authtype":"0","l_datatype":"1"}
     * */
    @ResponseBody
    @RequestMapping(value = "/getDataAuthDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String getDataAuthDetail(@RequestBody Map<String, Object> requestMap){
        try {
            return  authoritydetailService.getAuthdetail(requestMap) ;
        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }

    }



    /** @api {post} /api/deleAuth 删除权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleAuth
     * @apiName deleAuth
     * @apiGroup AuthorityController
     * @apiParam {Int} [authId] 权限ID
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiParamExample {json} Request-example:
     * {"authId":1}
     * */
    @ResponseBody
    @RequestMapping(value = "/deleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String deleAuth(@RequestBody  Map<String, Object> requestMap){
        try {
            Integer authId= (Integer)requestMap.get("authId");
            authoritydetailService.deleAuthByAuth(authId);
            return JsonResult.successJson();
        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }

    }



    /**
     * @api {post} /api/getFuncDetailList 获取功能权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getFuncDetailList
     * @apiName modifyAuth
     * @apiGroup AuthorityDetailController
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {List} [data.c_url] 路径
     * @apiSuccess {String} [data.l_funcdetail] 功能权限id
     * @apiSuccess {String} [data.total] 返回总条数
     * */
    @ResponseBody
    @RequestMapping(value = "/getFuncDetailList", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String getFuncDetailList(){
        try {
            return  authoritydetailService.getFuncDetailList() ;
        }catch (Exception e )
        {
            return JsonResult.errorJson("error!");
        }

    }
}
