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
/**
 * Created by 49537 on 2018/8/23.
 */
@Controller
@RequestMapping("api")
public class AuthorityController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthorityDetailService authoritydetailService;

    /**@api {post} /api/listRoleByFilter 根据条件查询角色 传空则返回所有
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/listRoleByFilter
     * @apiName listRoleByFilter
     * @apiGroup AuthorityController
     * @apiParam {String} [c_rolename] 角色名字
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     * @apiSuccess {String} [data.total] 返回总条数
     **/
    @ResponseBody
    @RequestMapping(value = "/listRoleByFilter", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByFilter(@RequestBody  Map<String, Object> requestMap){
        String  c_rolename =(String)requestMap.get("c_rolename");
        if (c_rolename.equals("") ){
            return authorityService.selectRoleList();
        }
        return    authorityService.getRoleByFilter(requestMap);
    }
    /**@api {post} /api/listRoleByRoleid 根据角色ID 查询角色明细
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/listRoleByRoleid
     * @apiName listRoleByRoleid
     * @apiGroup AuthorityController
     * @apiParam {Int} [l_roleid] 角色
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     * @apiSuccess {String} [data.c_authname] 权限名称
     * @apiSuccess {String} [data.c_authtype] 权限类型 0:功能权限  1:数据权限
     * @apiSuccess {String} [data.l_authid_] 权限id
     **/
    @ResponseBody
    @RequestMapping(value = "/listRoleByRoleid", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByRoleid(@RequestBody  Map<String, Object> requestMap){
        Integer roleId= (Integer)requestMap.get("l_roleid");
        return    authorityService.getRoleAuthDetail(roleId);
    }




    /**@api {post} /api/selectAuthByFilter 根据权限名称查权限 传空则返回所有
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/selectAuthByFilter
     * @apiName selectAuthByFilter
     * @apiGroup AuthorityController
     * @apiParam {String} [c_authname] 权限名称
     * @apiParam {String} [c_authtype] 权限类型 0:功能权限  1:数据权限
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.c_authname] 权限名称
     * @apiSuccess {String} [data.c_authtype] 权限类型 0:功能权限  1:数据权限
     * @apiSuccess {Int} [data.l_authid] 权限id
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_isenable] d_createdate
     * @apiSuccess {Int} [data.l_datatype] 数据权限类型 0表示非数据权限 1表示表权限 2 表示字段权限 3 表示行数据权限
     * @apiSuccess {String} [total] 返回总条数
     * @apiParamExample {json} Request-example:
     * {"c_authname":"xxx"}    或者 {"c_authname":"xxxxx","c_authtype":"0"}
     *
     **/
    @ResponseBody
    @RequestMapping(value = "/selectAuthByFilter", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String selectAuthByFilter(@RequestBody Map<String, Object> requestMap){
        String  c_authname =(String)requestMap.get("c_authname");
        if (c_authname.equals("")){
            return authorityService.selectAuthList();
        }
        else{
        return authorityService.getAuthByFilter(requestMap);
        }
    }

    /** @api {post} /api/addRole 插入新角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/addRole
     * @apiName addRole
     * @apiGroup AuthorityController
     * @apiParam {Object} [role] 角色
     * @apiParam {String} [role.c_isenable] 是否有效
     * @apiParam {String} [role.c_roledesc] 角色描述
     * @apiParam {String} [role.c_rolename] 角色名称
     * @apiParam {Int} [role.l_roleid] 角色id   因为还未插入表 填0
     * @apiParam {List} [roleAuthRela] 角色权限关系 可以装入多个
     * @apiParam {Int} [roleAuthRela.l_authid] 权限ID
     * @apiParam {Int} [roleAuthRela.l_roleauthid] 关系id  因为还未插入表 填0
     * @apiParam {Int} [roleAuthRela.l_roleid]  角色ID 因为还未插入表 填0
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiParamExample {json} Request-example:
     * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
     * "roleAuthRela":[{"l_authid":1,"l_roleauthid":123,"l_roleid":789},{"l_authid":2,"l_roleauthid":1223,"l_roleid":789}]}
     **/
    @ResponseBody
    @RequestMapping(value = "/addRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String addRole(@RequestBody Map<String, Object> requestMap){
        try {
            authorityService.addRoleAuth(requestMap);
            return JsonResult.successJson();

        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }
    }

    /** @api {post} /api/deleRole 删除角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleRole
     * @apiName deleRole
     * @apiGroup AuthorityController
     * @apiParam {String} [roleId] 角色ID
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiParamExample {json} Request-example:
     * { "roleId":"1"}
     * */
    @ResponseBody
    @RequestMapping(value = "/deleRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String deleRole(@RequestBody Map<String, Object> requestMap){
        try {
            String roleId=(String)requestMap.get("roleId") ;
            authorityService.deleRole(roleId);
            return JsonResult.successJson();

        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }
    }

    /** @api {post} /api/modifyRole 修改角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyRole
     * @apiName modifyRole
     * @apiGroup AuthorityController
     * @apiParam {Object} [role] 角色
     * @apiParam {String} [role.c_isenable] 权限名称
     * @apiParam {String} [role.c_roledesc] 是否生效
     * @apiParam {String} [role.c_rolename] 角色名称
     * @apiParam {Int} [role.l_roleid] 角色ID
     * @apiParam {List} [addauthRoleRela] 表数据权限明细 可以装入多个
     * @apiParam {Int} [addauthRoleRela.l_authid] 权限ID
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiParamExample {json} Request-example:例子:
    * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String modifyRole(@RequestBody Map<String, Object> requestMap){
        try {
            authorityService.modifyRole(requestMap);
            return JsonResult.successJson();

        }catch (Exception e ){
            e.printStackTrace();
            return JsonResult.errorJson("error!");
        }
    }



}
