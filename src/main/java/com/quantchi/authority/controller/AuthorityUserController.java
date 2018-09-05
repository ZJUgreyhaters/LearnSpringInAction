package com.quantchi.authority.controller;

import com.quantchi.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api")
public class AuthorityUserController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorityUserController.class);
   /**@api {post} /api/getUserTree 查询公司信息
    * @apiVersion 1.0.0
    * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getUserTree
    * @apiName getUserTree
    * @apiGroup AuthorityUserController
    * @apiSuccess {String} code 成功或者错误代码200成功，500错误
    * @apiSuccess {String} msg  成功或者错误信息
    * @apiSuccess {List} [data] 返回数据 指标信息列表
    * @apiSuccess {String}  [data.company]  公司名称
    * @apiSuccess {List} [depart]  部门
    *
   * */
    @ResponseBody
    @RequestMapping(value = "/getUserTree", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String getUserTree(){

        List<Map<String,Object>> companyTree = new ArrayList<Map<String,Object>>();
        Map<String,Object> userTree = new HashMap<String,Object>();
        List< Map<String,Object>> departs= new ArrayList< Map<String,Object>>();
        Map<String,Object> depart =  new HashMap<String,Object>();
        Map<String,Object> team =  new HashMap<String,Object>();
        depart.put("branchname","武林路营业部");
        depart.put("branchno","001");
        departs.add(depart);

        depart =  new HashMap<String,Object>();
        depart.put("branchname","滨盛路营业部");
        depart.put("branchno","002");
        departs.add(depart);
        depart =  new HashMap<String,Object>();
        depart.put("branchname","天目山营业部");
        depart.put("branchno","003");
        departs.add(depart);

        userTree.put("company","杭州第一分公司");
        userTree.put("branchno","001,002,003");
        userTree.put("depart", departs);

        companyTree.add(userTree);

        userTree = new HashMap<String,Object>();
        departs=new ArrayList< Map<String,Object>>();
        depart =  new HashMap<String,Object>();
        depart.put("branchname","上海陆家嘴营业部");
        depart.put("branchno","011");
        departs.add(depart);
        depart =  new HashMap<String,Object>();
        depart.put("branchname","上海徐汇区营业部");
        depart.put("branchno","012");
        departs.add(depart);
        depart =  new HashMap<String,Object>();
        depart.put("branchname","上海宝山区营业部");
        depart.put("branchno","013");
        departs.add(depart);

        userTree.put("company","上海直属营业部");
        userTree.put("branchno","011,012,013");
        userTree.put("depart", departs);

        companyTree.add(userTree);
        return JsonResult.successJson(companyTree);

    }

    public String getUserList(){
        List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
        Map<String,Object> user = new HashMap<String,Object>();
        user.put("userName","王小二");
        user.put("userId","20123");
        user.put("userAccount","139111201232");
        user.put("userRoles","营销部业务员,营销部销售");
        user.put("userStatus","0");
        users.add(user);


        user = new HashMap<String,Object>();
        user.put("userName","汪达尔");
        user.put("userId","20128");
        user.put("userAccount","139111201282");
        user.put("userRoles","技术部运维");
        user.put("userStatus","0");
        users.add(user);

        user = new HashMap<String,Object>();
        user.put("userName","王晓梅");
        user.put("userId","20134");
        user.put("userAccount","13911120134");
        user.put("userRoles","营销部业务员");
        user.put("userStatus","0");
        users.add(user);
        String total =users.size()+"";
        return JsonResult.successJson(total,users);
    }
    /**@api {post} /api/getUserByUserName 根据姓名查员工
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getUserByUserName
     * @apiName getUserByUserName
     * @apiGroup AuthorityUserController
     * @apiParam {String} [userName] 角色姓名
     * @apiParam {String} [branchno] 所在营业部id
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String}  [data.userAccount]  客户账号
     * @apiSuccess {String}  [data.userId":]  客户id
     * @apiSuccess {String}  [data.userName]  客户姓名
     * @apiSuccess {String}  [data.userRoles]  客户角色
     * @apiSuccess {String}  [data.userStatus]  客户状态
     * */
    @ResponseBody
    @RequestMapping(value = "/getUserByUserName", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String getUserByUserName(@RequestBody Map<String, Object> map){

        List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
        Map<String,Object> user = new HashMap<String,Object>();

        String userName = (String) map.get("userName");
        String branchno = (String) map.get("branchno");

       if(userName.equals("") &&  userName.equals("")) {
           return getUserList();
       }
       if(branchno.equals("001") || branchno.equals("001,002,003") ){
           user.put("userName", "王小二");
           user.put("userId", "20123");
           user.put("userAccount", "139111201232");
           user.put("userRoles", "营销部业务员,营销部销售");
           user.put("userStatus", "0");
           users.add(user);

           String total =users.size()+"";
           return JsonResult.successJson(total,users);
       }
        if (userName.equals( "王小二" )) {

            user.put("userName", "王小二");
            user.put("userId", "20123");
            user.put("userAccount", "139111201232");
            user.put("userRoles", "营销部业务员,营销部销售");
            user.put("userStatus", "0");
            users.add(user);
        }
        if (userName.equals(  "汪达尔" )) {
            user = new HashMap<String, Object>();
            user.put("userName", "汪达尔");
            user.put("userId", "20128");
            user.put("userAccount", "139111201282");
            user.put("userRoles", "技术部运维");
            user.put("userStatus", "0");
            users.add(user);
        }
        if (userName.equals( "王晓梅") ) {
            user = new HashMap<String, Object>();
            user.put("userName", "王晓梅");
            user.put("userId", "20134");
            user.put("userAccount", "13911120134");
            user.put("userRoles", "营销部业务员");
            user.put("userStatus", "0");
            users.add(user);
        }

        String total =users.size()+"";
        return JsonResult.successJson(total,users);
    }
}
