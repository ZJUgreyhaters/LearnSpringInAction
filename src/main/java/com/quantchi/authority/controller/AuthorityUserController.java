package com.quantchi.authority.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
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
        List<Map<String,Object>> teams= new ArrayList<Map<String,Object>>();

        Map<String,Object> team =  new HashMap<String,Object>();
        team.put("branchname","业务组");
        team.put("branch_no","0111");
        team.put("children",new ArrayList< Map<String,Object>>());
        team.put("parentid","011");
        team.put("id","0111");
        teams.add(team);
        depart.put("branchname","武林路营业部");
        depart.put("branch_no","011");
        depart.put("children",teams);
        depart.put("parentid","01");
        depart.put("id","011");
        departs.add(depart);

        depart =  new HashMap<String,Object>();
        teams= new ArrayList<Map<String,Object>>();
        team =  new HashMap<String,Object>();
        team.put("branchname","业务组");
        team.put("branch_no","0121");
        team.put("children",new ArrayList< Map<String,Object>>());
        team.put("parentid","012");
        team.put("id","0121");
        teams.add(team);
        depart.put("branchname","滨盛路营业部");
        depart.put("branch_no","012");
        depart.put("children",teams);
        depart.put("parentid","01");
        depart.put("id","012");
        departs.add(depart);
        depart =  new HashMap<String,Object>();
        depart.put("branchname","天目山营业部");
        depart.put("branch_no","013");
        depart.put("parentid","01");
        depart.put("id","013");
        depart.put("children",new ArrayList<Map<String,Object>>());
        departs.add(depart);

        userTree.put("branchname","杭州第一分公司");
        userTree.put("branch_no","011,012,013");
        userTree.put("id","01");
        userTree.put("parentid","1");
        userTree.put("children", departs);

        companyTree.add(userTree);

        userTree = new HashMap<String,Object>();
        departs=new ArrayList< Map<String,Object>>();
        depart =  new HashMap<String,Object>();
        teams= new ArrayList<Map<String,Object>>();
        team =  new HashMap<String,Object>();
        team.put("branchname","运营组");
        team.put("branch_no","0211");
        team.put("children",new ArrayList< Map<String,Object>>());
        team.put("parentid","021");
        team.put("id","0211");
        teams.add(team);
        depart.put("branchname","上海宝山区营业部");
        depart.put("branch_no","021");
        depart.put("children",teams);
        depart.put("parentid","02");
        depart.put("id","021");
        departs.add(depart);

        depart =  new HashMap<String,Object>();
        depart.put("branchname","上海徐汇区营业部");
        depart.put("branch_no","022");
        depart.put("children",new ArrayList<Map<String,Object>>());
        depart.put("parentid","02");
        depart.put("id","022");
        departs.add(depart);
        depart =  new HashMap<String,Object>();
        depart.put("branchname","上海陆家嘴营业部");
        depart.put("branch_no","023");
        depart.put("parentid","02");
        depart.put("id","023");
        depart.put("children",new ArrayList<Map<String,Object>>());
        departs.add(depart);

        userTree.put("branchname","上海分公司");
        userTree.put("branch_no","021,022,023");
        userTree.put("id","02");
        userTree.put("parentid","1");
        userTree.put("children", departs);
        companyTree.add(userTree);

        List<Map<String,Object>> headquarterss= new ArrayList<Map<String,Object>>();
        Map<String,Object> headquarters = new HashMap<String,Object>();

        headquarters.put("branchname","总部");
        headquarters.put("branch_no","011,012,013,001,002,003");
        headquarters.put("id","1");
        headquarters.put("parentid","");
        headquarters.put("children", companyTree);
        headquarterss.add(headquarters);
        return JsonResult.successJson(headquarterss);

    }

    public String getUserList(Map<String, Object> map){
        List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
        Map<String,Object> user = new HashMap<String,Object>();
        user.put("userName","王小二");
        user.put("userId","20123");
        user.put("userAccount","139111201232");
        user.put("userRoles","营销部业务员,营销部销售");
        user.put("userStatus","1");
        users.add(user);


        user = new HashMap<String,Object>();
        user.put("userName","汪达尔");
        user.put("userId","20128");
        user.put("userAccount","139111201282");
        user.put("userRoles","技术部运维");
        user.put("userStatus","1");
        users.add(user);

        user = new HashMap<String,Object>();
        user.put("userName","王晓梅");
        user.put("userId","20134");
        user.put("userAccount","13911120134");
        user.put("userRoles","营销部业务员");
        user.put("userStatus","1");
        users.add(user);
        String total =users.size()+"";
        Integer page = (Integer) map.get("page");
        Integer pageSize = (Integer) map.get("page_size");
        if (page != null && pageSize != null) {
            users = Paging.pagingPlug(users, pageSize, page);
        }
        return JsonResult.successJson(total,users);
    }
    /**@api {post} /api/getUserByUserName 根据姓名查员工
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/getUserByUserName
     * @apiName getUserByUserName
     * @apiGroup AuthorityUserController
     * @apiParam {String} [userName] 角色姓名
     * @apiParam {String} [branch_no] 所在营业部id
     * @apiParam {String} [roleId] 角色id查询
     * @apiParam {Int} [page] 页数
     * @apiParam {Int} [page_size] 每页数量
     * @apiSuccess {String} code 成功或者错误代码200成功，500错误
     * @apiSuccess {String} msg  成功或者错误信息
     * @apiSuccess {List} [data] 返回数据 指标信息列表
     * @apiSuccess {String}  [data.userAccount]  客户账号
     * @apiSuccess {String}  [data.userId":]  客户id
     * @apiSuccess {String}  [data.userName]  客户姓名
     * @apiSuccess {String}  [data.userRoles]  客户角色
     * @apiSuccess {String}  [data.userStatus]  客户状态 0失效 1有效
     * */
    @ResponseBody
    @RequestMapping(value = "/getUserByUserName", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String getUserByUserName(@RequestBody Map<String, Object> map){

        List<Map<String,Object>> users = new ArrayList<Map<String,Object>>();
        Map<String,Object> user = new HashMap<String,Object>();

        String userName = (String) map.get("userName");
        String branchno = (String) map.get("branch_no");
        String roleId =  map.get("roleId").toString();
        if (roleId !=  null  && (!roleId.equals("") )){
            user.put("userName", "王小二");
            user.put("userId", "20123");
            user.put("userAccount", "139111201232");
            user.put("userRoles", "营销部业务员,营销部销售");
            user.put("userStatus", "1");
            users.add(user);
            user = new HashMap<String, Object>();
            user.put("userName", "王晓梅");
            user.put("userId", "20134");
            user.put("userAccount", "13911120134");
            user.put("userRoles", "营销部业务员");
            user.put("userStatus", "1");
            users.add(user);
            String total =users.size()+"";
            Integer page = (Integer) map.get("page");
            Integer pageSize = (Integer) map.get("page_size");
            if (page != null && pageSize != null) {
                users = Paging.pagingPlug(users, pageSize, page);
            }
            return JsonResult.successJson(total,users);

        }

            if(userName ==  null  ||  userName.equals("")   ) {
           if (branchno == null ||branchno.equals("1")){
               Integer page = (Integer) map.get("page");
               Integer pageSize = (Integer) map.get("page_size");
               if (page != null && pageSize != null) {
                   users = Paging.pagingPlug(users, pageSize, page);
               }
           return getUserList(map);
           }
       }

       if(branchno != null && branchno.startsWith("01") ){
           user.put("userName", "王小二");
           user.put("userId", "20123");
           user.put("userAccount", "139111201232");
           user.put("userRoles", "营销部业务员,营销部销售");
           user.put("userStatus", "1");
           users.add(user);

           String total =users.size()+"";
           Integer page = (Integer) map.get("page");
           Integer pageSize = (Integer) map.get("page_size");
           if (page != null && pageSize != null) {
               users = Paging.pagingPlug(users, pageSize, page);
           }
           return JsonResult.successJson(total,users);
       }
        if(branchno != null &&branchno.startsWith("02")  ){
            user = new HashMap<String, Object>();
            user.put("userName", "汪达尔");
            user.put("userId", "20128");
            user.put("userAccount", "139111201282");
            user.put("userRoles", "技术部运维");
            user.put("userStatus", "1");
            users.add(user);
            String total =users.size()+"";
            Integer page = (Integer) map.get("page");
            Integer pageSize = (Integer) map.get("page_size");
            if (page != null && pageSize != null) {
                users = Paging.pagingPlug(users, pageSize, page);
            }
            return JsonResult.successJson(total,users);
        }
        if (userName.equals( "王小二" )) {

            user.put("userName", "王小二");
            user.put("userId", "20123");
            user.put("userAccount", "139111201232");
            user.put("userRoles", "营销部业务员,营销部销售");
            user.put("userStatus", "1");
            users.add(user);
        }
        if (userName.equals(  "汪达尔" )) {
            user = new HashMap<String, Object>();
            user.put("userName", "汪达尔");
            user.put("userId", "20128");
            user.put("userAccount", "139111201282");
            user.put("userRoles", "技术部运维");
            user.put("userStatus", "1");
            users.add(user);
        }
        if (userName.equals( "王晓梅") ) {
            user = new HashMap<String, Object>();
            user.put("userName", "王晓梅");
            user.put("userId", "20134");
            user.put("userAccount", "13911120134");
            user.put("userRoles", "营销部业务员");
            user.put("userStatus", "1");
            users.add(user);
        }

        String total =users.size()+"";
        Integer page = (Integer) map.get("page");
        Integer pageSize = (Integer) map.get("page_size");
        if (page != null && pageSize != null) {
            users = Paging.pagingPlug(users, pageSize, page);
        }

        return JsonResult.successJson(total,users);
    }


}
