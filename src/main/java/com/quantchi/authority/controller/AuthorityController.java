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
    //查询所有角色
    @ResponseBody
    @RequestMapping(value = "/authorityRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String authorityRole(){

        return    authorityService.selectRoleList();

    }
    //根据id查询角色
    @ResponseBody
    @RequestMapping(value = "/listRoleByRoleId", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByRoleId(@RequestParam(value = "roleId", required = false)  String roleId){
        return    authorityService.getRoleAuthDetail(Integer.parseInt(roleId));
    }
    //根据name查询角色
    @ResponseBody
    @RequestMapping(value = "/listRoleByRoleName", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String listRoleByRoleName(@RequestParam(value = "roleName", required = false)   String roleName){
        return    authorityService.getRoleAuthDetailByName(roleName);
    }

    //插入角色和权限关系
    /*
    * 例子：
    * { "authRoleRela":[{"l_authid":1,"l_roleauthid":123,"l_roleid":3},{"l_authid":2,"l_roleauthid":1223,"l_roleid":3}]}
    * */
    @ResponseBody
    @RequestMapping(value = "/addRoleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addRoleAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.addAuthRoleRela(requestMap);

    }

    //查询所有权限
    @ResponseBody
    @RequestMapping(value = "/selectAllAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String selectAllAuth(){
        return authorityService.selectAuthList();
    }


    //插入新角色
    /*例子:
    * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
    * "roleAuthRela":[{"l_authid":1,"l_roleauthid":123,"l_roleid":789},{"l_authid":2,"l_roleauthid":1223,"l_roleid":789}]}
    * */
    @ResponseBody
    @RequestMapping(value = "/addRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addRole(@RequestBody Map<String, Object> requestMap){
        authorityService.addRoleAuth(requestMap);
    }

    //删除角色
    @ResponseBody
    @RequestMapping(value = "/deleRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleRole(@RequestParam(value = "roleId", required = false)  String roleId){
         authorityService.deleRole(roleId);
    }
    //修改角色名字
    /*例子:
    * {"role":{"c_isenable":"0","c_roledesc":"xxxx","c_rolename":"xxxxxx","l_roleid":0},
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyRole", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyRole(@RequestBody Map<String, Object> requestMap){
        authorityService.modifyRole(requestMap);
    }

    //删除权限与角色关系
    /*
    * 例子：
    * { "authRoleRela":[{"l_authid":1,"l_roleauthid":7,"l_roleid":3},{"l_authid":2,"l_roleauthid":8,"l_roleid":3}]}
    *
    * */
    @ResponseBody
    @RequestMapping(value = "/deleRoleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleRoleAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.deleRoleAuthRela(requestMap);
    }

    //删除权限
    @ResponseBody
    @RequestMapping(value = "/deleAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void deleAuth(@RequestBody  @RequestParam(value = "authId", required = false)  String authId){
        authoritydetailService.deleAuthByAuth(authId);
    }

    // 修改权限说明
    /*
    * 例子 {"c_isenable":"0","c_authname":"xxxx","c_authtype":"1","l_authid":1}
     * */
    @ResponseBody
    @RequestMapping(value = "/modifyAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyAuth(@RequestBody Map<String, Object> requestMap){
        authorityService.modifyAuth(requestMap);
    }

}
