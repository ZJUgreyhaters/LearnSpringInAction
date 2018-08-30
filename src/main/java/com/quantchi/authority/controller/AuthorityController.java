package com.quantchi.authority.controller;

import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.authority.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     /**@api {post} /api/authorityRole 查询所有角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/authorityRole
     * @apiName authorityRole
     * @apiGroup AuthorityController
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     *
     **/
    @ResponseBody
    @RequestMapping(value = "/authorityRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String authorityRole(){

        return    authorityService.selectRoleList();

    }
    /**@api {post} /api/listRoleByRoleId 根据id查询角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/listRoleByRoleId
     * @apiName listRoleByRoleId
     * @apiGroup AuthorityController
     * @apiParam {String} [roleId] 角色id
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     * @apiSuccess {String} [data.c_authname] 权限名称
     * @apiSuccess {String} [data.c_authtype] 权限类型
     * @apiSuccess {String} [data.l_roleauthid] 权限与角色关系id
     **/
    @ResponseBody
    @RequestMapping(value = "/listRoleByRoleId", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByRoleId(@RequestParam(value = "roleId", required = false)  String roleId){
        return    authorityService.getRoleAuthDetail(Integer.parseInt(roleId));
    }
    /**@api {post} /api/listRoleByRoleName 根据name查询角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/listRoleByRoleName
     * @apiName listRoleByRoleName
     * @apiGroup AuthorityController
     * @apiParam {String} [roleName] 角色名称
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     * @apiSuccess {String} [data.c_authname] 权限名称
     * @apiSuccess {String} [data.c_authtype] 权限类型
     * @apiSuccess {String} [data.l_roleauthid] 权限与角色关系id
     **/
    @ResponseBody
    @RequestMapping(value = "/listRoleByRoleName", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByRoleName(@RequestParam(value = "roleName", required = false)   String roleName){
        return    authorityService.getRoleAuthDetailByName(roleName);
    }

    /** @api {post} /api/addRoleAuth 插入角色和权限关系
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/addRoleAuth
     * @apiName addRoleAuth
     * @apiGroup AuthorityController
     * @apiParam {List} [authRoleRela] 表数据权限明细 可以装入多个
     * @apiParam {Int} [authRoleRela.l_authid] 权限ID
     * @apiParam {Int} [authRoleRela.l_roleauthid] 关系id  因为还未插入表 填0
     * @apiParam {Int} [authRoleRela.l_roleid]  角色ID
     * @apiParamExample {json} Request-example:
    * { "authRoleRela":[{"l_authid":1,"l_roleauthid":123,"l_roleid":3},{"l_authid":2,"l_roleauthid":1223,"l_roleid":3}]}
    * */
    @ResponseBody
    @RequestMapping(value = "/addRoleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addRoleAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.addAuthRoleRela(requestMap);

    }

    /**@api {post} /api/selectAllAuth 查询所有权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/selectAllAuth
     * @apiName selectAllAuth
     * @apiGroup AuthorityController
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String} [data.l_roleid] 角色id
     * @apiSuccess {String} [data.c_rolename] 角色名称
     * @apiSuccess {String} [data.c_isenable] 是否有效
     * @apiSuccess {String} [data.c_roledesc] 角色描述信息
     * @apiSuccess {String} [data.c_authname] 权限名称
     * @apiSuccess {String} [data.c_authtype] 权限类型
     * @apiSuccess {String} [data.l_roleauthid] 权限与角色关系id
     **/
    @ResponseBody
    @RequestMapping(value = "/selectAllAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String selectAllAuth(){
        return authorityService.selectAuthList();
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
     * @apiParamExample {json} Request-example:
     * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
     * "roleAuthRela":[{"l_authid":1,"l_roleauthid":123,"l_roleid":789},{"l_authid":2,"l_roleauthid":1223,"l_roleid":789}]}
     **/
    @ResponseBody
    @RequestMapping(value = "/addRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addRole(@RequestBody Map<String, Object> requestMap){
        authorityService.addRoleAuth(requestMap);
    }

    /** @api {post} /api/deleRole 删除角色
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleRole
     * @apiName deleRole
     * @apiGroup AuthorityController
     * @apiParam {String} [roleId] 角色ID
     * */
    @ResponseBody
    @RequestMapping(value = "/deleRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleRole(@RequestParam(value = "roleId", required = false)  String roleId){
         authorityService.deleRole(roleId);
    }

    /** @api {post} /api/modifyRole 修改角色名字
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyRole
     * @apiName modifyRole
     * @apiGroup AuthorityController
     * @apiParam {Object} [role] 角色
     * @apiParam {String} [role.c_isenable] 权限名称
     * @apiParam {String} [role.c_roledesc] 是否生效
     * @apiParam {String} [role.c_rolename] 角色名称
     * @apiParam {String} [role.l_roleid] 角色ID
     * @apiParamExample {json} Request-example:例子:
    * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyRole(@RequestBody Map<String, Object> requestMap){
        authorityService.modifyRole(requestMap);
    }

    /**@api {post} /api/deleRoleAuth 删除权限与角色关系
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleRoleAuth
     * @apiName deleRoleAuth
     * @apiGroup AuthorityController
     * @apiParam {List} [authRoleRela] 角色权限关系
     * @apiParam {String} [authRoleRela.l_authid] 权限id
     * @apiParam {String} [authRoleRela.l_roleauthid] 角色权限关系
     * @apiParam {String} [authRoleRela.l_roleid] 角色ID
     * @apiParamExample {json} Request-example:
    * { "authRoleRela":[{"l_authid":1,"l_roleauthid":7,"l_roleid":3},{"l_authid":2,"l_roleauthid":8,"l_roleid":3}]}
    *
    * */
    @ResponseBody
    @RequestMapping(value = "/deleRoleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleRoleAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.deleRoleAuthRela(requestMap);
    }

    /** @api {post} /api/deleAuth 删除权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleAuth
     * @apiName deleAuth
     * @apiGroup AuthorityController
     * @apiParam {String} [authId] 权限ID
     * */
    @ResponseBody
    @RequestMapping(value = "/deleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleAuth(@RequestBody  @RequestParam(value = "authId", required = false)  String authId){
        authoritydetailService.deleAuthByAuth(authId);
    }

    /**@api {post} /api/modifyAuth 修改权限说明
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyAuth
     * @apiName modifyAuth
     * @apiGroup AuthorityController
     * @apiParam {String} [c_isenable] 权限id
     * @apiParam {String} [ac_authname] 角色权限关系
     * @apiParam {String} [l_authid] 角色ID
     * @apiParam {String} [c_authtype] 权限类型
     * @apiParamExample {json} Request-example:
     *  {"c_isenable":"0","c_authname":"xxxx","c_authtype":"1","l_authid":1}
     * */
    @ResponseBody
    @RequestMapping(value = "/modifyAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.modifyAuth(requestMap);
    }

}
