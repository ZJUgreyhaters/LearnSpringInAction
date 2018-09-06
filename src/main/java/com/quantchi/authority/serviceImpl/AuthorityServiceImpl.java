package com.quantchi.authority.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantchi.authority.mapper.*;
import com.quantchi.authority.pojo.*;
import com.quantchi.authority.service.AuthorityService;

import java.util.HashMap;
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
    @Autowired
    private DataLineDetailMapper dataLineDetailMapper;
    @Autowired
    private FuncDetailMapper funcDetailMapper;
    @Autowired
    private TabColumnDetailMapper tabColumnDetailMapper;
    @Autowired
    private TableDetailMapper tableDetailMapper;
    private ObjectMapper oMapper = new ObjectMapper();

    @Override
    public String selectRoleList( ) {
        try {
            List<Map<String, Object>> list = authRoleMapper.getAuthRole();
            String total = list.size() + "";
            return JsonResult.successJson(total,list);
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
        Long roleid =  Long.parseLong(roleMap.get( "l_roleid").toString()) ;

        List<Tauthrolerela> roleAuthRelas=(List)map.get("roleAuthRela");
        String roleAuthRelaObjects = JSONObject.toJSONString(roleAuthRelas);
        roleAuthRelas=JSONObject.parseArray(roleAuthRelaObjects,Tauthrolerela.class);

        for(Tauthrolerela tauthrolerela:roleAuthRelas){
            tauthrolerela.setL_Roleid(roleid);
            Map<String, Object> tuserrolerelaMap =   oMapper.convertValue(tauthrolerela, Map.class);
            authorityMapper.inertAuthRoleRela(tuserrolerelaMap);
        }
    }


    @Override
    public String selectAuthList(){
        try {
            List<Map<String,Object>> list=authorityMapper.selectAuth();
            String total = list.size() + "";
            return JsonResult.successJson(total,list);
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

            authorityMapper.deleRoleRelaByRelaId(Integer.parseInt(relaId.toString()));
        }
    }
    @Override
    public  void modifyRole(Map<String, Object> map){
        Map<String,Object>  modifyRole = (Map<String,Object>) map.get("role");

        List<Tauthrolerela> addRela = (List)map.get("addauthRoleRela");
        authRoleMapper.updateRole(modifyRole);
        Integer roldId = (Integer)modifyRole.get("l_roleid");
        authorityMapper.deleRoleRelaByRoleId( roldId );

        String roleRelaObjects = JSONObject.toJSONString(addRela);
        List<Tauthrolerela>  roleRelas=JSONObject.parseArray(roleRelaObjects,Tauthrolerela.class);

        for (Tauthrolerela tauthrolerela:roleRelas){
            Map<String, Object> tauthrolerelaMap =  oMapper.convertValue(tauthrolerela, Map.class);
            tauthrolerelaMap.put("l_roleid",roldId);
            authorityMapper.inertAuthRoleRela(tauthrolerelaMap);
        }
        ;
    }

    @Override
    public  void modifyAuth(Map<String, Object> map){
        List<Map<String,Object>> relaMaps;

        Map<String,Object> auth = (Map<String,Object>) map.get("authority");

        authorityMapper.updateAuth(auth);

         Integer authIdInt=(Integer)auth.get("l_authid");

        String  dataType=(String) auth.get("l_datatype");
        String  c_authtype=(String)auth.get("c_authtype");

        relaMaps= dataLineDetailMapper.selectLineDetRelaByAuthId(authIdInt);
        dataLineDetailMapper.deleAuthLineRela(authIdInt);
        for (Map<String,Object> relamap:relaMaps ){
            Integer lineId = (Integer)relamap.get("l_linedetailid");
            dataLineDetailMapper.deleLineByLineId(lineId);
        }

        relaMaps=tabColumnDetailMapper.getTabColDetailRelaByAuthId(authIdInt);
        tabColumnDetailMapper.deleAuthColRela(authIdInt);
        for (Map<String,Object> relamap:relaMaps ){
            Integer lineId = (Integer)relamap.get("l_columndetailid");
            tabColumnDetailMapper.deleColByColId(lineId);
        }

        relaMaps=tableDetailMapper.getTableDetailRelaByAuthId(authIdInt);
        tableDetailMapper.deleAuthTabRela(authIdInt);
        for (Map<String,Object> relamap:relaMaps ){
            Integer lineId = (Integer)relamap.get("l_datatabledetailid");
            tableDetailMapper.deleTabByTabId(lineId);
        }

        relaMaps = funcDetailMapper.getFuncDetailRelaByAuthId(authIdInt);
        funcDetailMapper.deleAuthFuncRela(authIdInt);
        for (Map<String,Object> relamap:relaMaps ){
            Integer lineId = (Integer)relamap.get("l_funcdetail");
            funcDetailMapper.deleFuncByFuncId(lineId);
        }

        if (c_authtype.equals("1")  ){
            if (dataType.equals("3") ){
                //插入行数据过滤以及与权限的关系
                List<Ttablelinedetail> dataLineDetails=(List)map.get("authDetail");
                if (dataLineDetails != null  ){
                    String ataLineDetailObj = JSONObject.toJSONString(dataLineDetails);
                    dataLineDetails=JSONObject.parseArray(ataLineDetailObj, Ttablelinedetail.class);

                    for(Ttablelinedetail ttablelinedetail:dataLineDetails)
                    {
                        Map<String, Object> ttablelinedetailMap =   oMapper.convertValue(ttablelinedetail, Map.class);
                        dataLineDetailMapper.insertLineDetail(ttablelinedetailMap);

                        Tauthlinerela tauthlinerela = new Tauthlinerela();
                        tauthlinerela.setL_Authid(Long.parseLong(auth.get("l_authid").toString()));
                        tauthlinerela.setL_Linedetailid(Long.parseLong(ttablelinedetailMap.get("l_linedetailid").toString()));
                        Map<String, Object> authLineRelaMap =  oMapper.convertValue(tauthlinerela, Map.class);
                        dataLineDetailMapper.insertLineDetailRela(authLineRelaMap);
                    }
                }
            }
            if (dataType.equals("2")) {
                //插入字段过滤以及权限关系
                List<Tdatacolumndetail> dataColDetails = (List) map.get("authDetail");
                if (dataColDetails != null) {
                    String dataColDetailObj = JSONObject.toJSONString(dataColDetails);
                    dataColDetails = JSONObject.parseArray(dataColDetailObj, Tdatacolumndetail.class);

                    for (Tdatacolumndetail tdatacolumndetail : dataColDetails) {
                        Map<String, Object> tdatacolumndetailMap = oMapper.convertValue(tdatacolumndetail, Map.class);
                        tabColumnDetailMapper.insertTabColDetail(tdatacolumndetailMap);

                        Tauthcolrela tauthcolrela = new Tauthcolrela();
                        tauthcolrela.setL_Authid(Long.parseLong(auth.get("l_authid").toString()));
                        tauthcolrela.setL_Columndetailid(Long.parseLong(tdatacolumndetailMap.get("l_columndetailid").toString()));
                        Map<String, Object> authColRelaMap = oMapper.convertValue(tauthcolrela, Map.class);
                        tabColumnDetailMapper.insertColDetailRela(authColRelaMap);
                    }
                }
            }
            if (dataType.equals("1")) {
                //插入表过滤以及权限关系
                List<Tdatatabledetail> dataTabDetails=(List)map.get("authDetail");
                if ( dataTabDetails != null ) {
                    String dataTabDetailObj = JSONObject.toJSONString(dataTabDetails);
                    dataTabDetails = JSONObject.parseArray(dataTabDetailObj, Tdatatabledetail.class);

                    for (Tdatatabledetail tdatatabledetail : dataTabDetails) {
                        Map<String, Object> tdatatabledetailMap = oMapper.convertValue(tdatatabledetail, Map.class);
                        tableDetailMapper.insertTableDetail(tdatatabledetailMap);

                        Tauthtablerela tauthtablerela = new Tauthtablerela();
                        tauthtablerela.setL_Authid(Long.parseLong(auth.get("l_authid").toString()));
                        tauthtablerela.setL_Datatabledetailid(Long.parseLong(tdatatabledetailMap.get("l_datatabledetailid").toString()));
                        Map<String, Object> authColRelaMap = oMapper.convertValue(tauthtablerela, Map.class);
                        tableDetailMapper.insertTableDetailRela(authColRelaMap);
                    }
                }
            }
        }
        if (c_authtype.equals("0") ){
          //数据权限
            List<Map<String, Object>> funcDetailMaps=(List<Map<String, Object>>)map.get("authDetail");
            Map<String, Object> funcDetailMap   =  funcDetailMaps.get(funcDetailMaps.size()-1);
            funcDetailMapper.insertFuncDetail (funcDetailMap);

            Tauthfuncrela tauthfuncrela = new Tauthfuncrela();
            tauthfuncrela.setL_Authid(Long.parseLong(auth.get("l_authid").toString()));
            tauthfuncrela.setL_Funcdetail(Long.parseLong(funcDetailMap.get("l_funcdetail").toString()));
            Map<String, Object> authColRelaMap =  oMapper.convertValue(tauthfuncrela, Map.class);
            funcDetailMapper.insertFuncDetailRela(authColRelaMap);

        }

}

    @Override
    public String getAuthByFilter(Map<String, Object> map){
        List<Map<String,Object>> list =authorityMapper.getAuthByFilter(map);
        String total = list.size() + "";
        return  JsonResult.successJson(total,list);
    }

    @Override
    public  String getRoleByFilter(Map<String, Object> map){
        List<Map<String, Object>> list = authRoleMapper.getRoleByFilter(map);
        String total = list.size() + "";
        return JsonResult.successJson(total,list);
    }
}
