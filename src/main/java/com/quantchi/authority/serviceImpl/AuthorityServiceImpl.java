package com.quantchi.authority.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantchi.authority.mapper.AuthorityMapper;
import com.quantchi.authority.pojo.Tauthcolrela;
import com.quantchi.authority.pojo.Tauthrolerela;
import com.quantchi.authority.pojo.Tuserrole;
import com.quantchi.authority.service.AuthorityService;
import com.quantchi.authority.mapper.AuthorityRoleMapper;
import java.util.Map;
import java.util.List;
import com.quantchi.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/8/23.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private  AuthorityRoleMapper authRoleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    private ObjectMapper oMapper = new ObjectMapper();

    @Override
    public String selectRoleList( ) {
        try {
            List<Map<String, Object>> list = authRoleMapper.getAuthRole();
            return JsonResult.successJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }

    @Override
    public String selectRoleListAndUserCount(){
        try {
            List<Map<String, Object>> list = authRoleMapper.getAuthRoleAndUserCount();
            return JsonResult.successJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }

    @Override
    public void addRole(Map<String, Object> map){

        authRoleMapper.insertRole(map);
    }

    @Override
    public  void addAuth(Map<String, Object> map){

        authorityMapper.insertAuth(map);
    }

    @Override
    public  void addRoleAuth(Map<String, Object> map){

         Map<String, Object> roleMap = (Map<String, Object>) map.get("role") ;
         authRoleMapper.insertRole(roleMap);
        Long roleid =  Long.parseLong(roleMap.get(new String("l_roleid")).toString()) ;

        List<Tauthrolerela> roleAuthRelas=(List)map.get("roleAuthRela");
        String roleAuthRelaObjects = JSONObject.toJSONString(roleAuthRelas);
        roleAuthRelas=JSONObject.parseArray(roleAuthRelaObjects,Tauthrolerela.class);

        for(Tauthrolerela tauthrolerela:roleAuthRelas){
            tauthrolerela.setL_Roleid(roleid);
            System.out.println(tauthrolerela.getL_roleid());
            System.out.println(tauthrolerela.getL_authid());
            Map<String, Object> tuserrolerelaMap =   oMapper.convertValue(tauthrolerela, Map.class);
            authorityMapper.inertAuthRoleRela(tuserrolerelaMap);
        }
    }


    @Override
    public String selectAuthList(){
        try {
            List<Map<String,Object>> list=authorityMapper.selectAuth();
            return JsonResult.successJson(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.errorJson("select error!");
        }

    }

    @Override
    public  void  addAuthRoleRela(Map<String, Object> map){

        List<Tauthrolerela> roleRelas=(List)map.get("authRoleRela");
        String roleRelaObjects = JSONObject.toJSONString(roleRelas);
        roleRelas=JSONObject.parseArray(roleRelaObjects,Tauthrolerela.class);

        for (Tauthrolerela tauthrolerela:roleRelas){
            Map<String, Object> tauthrolerelaMap =  oMapper.convertValue(tauthrolerela, Map.class);
            authorityMapper.inertAuthRoleRela(tauthrolerelaMap);
        }

    }

    @Override
    public String getRoleAuthDetail(Integer roleId){
        try {
            List<Map<String,Object>> list=authRoleMapper.getRoleAuthDetail(roleId);
            return JsonResult.successJson(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.errorJson("select error!");
        }

    }

    @Override
    public String getRoleAuthDetailByName(String name){
        try {
            List<Map<String,Object>> list=authRoleMapper.getRoleAuthDetailByName(name);
            return JsonResult.successJson(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.errorJson("select error!");
        }

    }

    @Override
    public void deleRole(String roleId){

        Integer roleIdInt = Integer.parseInt(roleId);

        authorityMapper.deleRoleRelaByRoleId(roleIdInt);
        authRoleMapper.deleUserRole(roleIdInt);
        authRoleMapper.deleRole(roleIdInt);

    }

    @Override
    public void deleRoleAuthRela(Map<String, Object> map){
        List<Tauthrolerela> roleRelas=(List)map.get("authRoleRela");
        String roleRelaObjects = JSONObject.toJSONString(roleRelas);
        roleRelas=JSONObject.parseArray(roleRelaObjects,Tauthrolerela.class);

        for (Tauthrolerela tauthrolerela:roleRelas){
            Long relaId = tauthrolerela.getL_roleauthid();
            System.out.println(relaId);
            authorityMapper.deleRoleRelaByRelaId(Integer.parseInt(relaId.toString()));
        }
    }
    @Override
    public  void modifyRole(Map<String, Object> map){
          authRoleMapper.updateRole(map);
    }

    @Override
    public  void modifyAuth(Map<String, Object> map){
          authorityMapper.updateAuth(map);
}
}
