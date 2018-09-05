package com.quantchi.authority.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.authority.mapper.*;
import com.quantchi.authority.pojo.*;
import com.quantchi.authority.service.AuthorityDetailService;
import com.quantchi.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorityDetailServiceImpl implements  AuthorityDetailService {

    @Autowired
    private DataLineDetailMapper dataLineDetailMapper;
    @Autowired
    private FuncDetailMapper funcDetailMapper;
    @Autowired
    private TabColumnDetailMapper tabColumnDetailMapper;
    @Autowired
    private TableDetailMapper tableDetailMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    private  ObjectMapper oMapper = new ObjectMapper();

    @Override
    public  String selectTableDetailList(){
        try {
            List<Map<String, Object>> list = tableDetailMapper.selectTableDetail();
            return JsonResult.successJson(list);
         } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }
    @Override
    public void addTableDetail(Map<String, Object> map){
         tableDetailMapper.insertTableDetail(map);
    }

    @Override
    public  void addTableColDetail(Map<String, Object> map){
      tabColumnDetailMapper.insertTabColDetail(map);
    }

    @Override
    public String selectTableColDetailList(){
        try {
            List<Map<String, Object>> list = tabColumnDetailMapper.selectTabColDetail();
            return JsonResult.successJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }
    @Override
    public void addDataLineDetail(Map<String, Object> map){
      dataLineDetailMapper.insertLineDetail(map);

    }
    @Override
    public String selectDataLineDetail(){
        try {
            List<Map<String, Object>> list = dataLineDetailMapper.selectLineDetail();
            return JsonResult.successJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }
    @Override
    public void addFuncDetail(Map<String, Object> map){
        funcDetailMapper.insertFuncDetail(map);

    }
    @Override
    public String selectFuncDetail(){
        try {
            List<Map<String, Object>> list = funcDetailMapper.selectFuncDetail();
            return JsonResult.successJson(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorJson("select error！");
        }
    }
    @Override
    public void addAuthAndDataDetail(Map<String, Object> map){
        Map <String, Object> authMap= (Map<String, Object>)map.get("authority");

        authorityMapper.insertAuth(authMap);

        String dataType = (String)authMap.get("l_datatype");
        String c_authtype = (String)authMap.get("c_authtype");

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
            tauthlinerela.setL_Authid(Long.parseLong(authMap.get( "l_authid").toString()));
            tauthlinerela.setL_Linedetailid(Long.parseLong(ttablelinedetailMap.get( "l_linedetailid").toString()));
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
                    tauthcolrela.setL_Authid(Long.parseLong(authMap.get( "l_authid").toString()));
                    tauthcolrela.setL_Columndetailid(Long.parseLong(tdatacolumndetailMap.get( "l_columndetailid").toString()));
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
                tauthtablerela.setL_Authid(Long.parseLong(authMap.get("l_authid").toString()));
                tauthtablerela.setL_Datatabledetailid(Long.parseLong(tdatatabledetailMap.get( "l_datatabledetailid").toString()));
                Map<String, Object> authColRelaMap = oMapper.convertValue(tauthtablerela, Map.class);
                tableDetailMapper.insertTableDetailRela(authColRelaMap);
            }
         }
        }
        }
        if (c_authtype.equals("0") ){

          List<Map<String, Object>> funcDetailMaps=(List<Map<String, Object>>)map.get("authDetail");
          Map<String, Object> funcDetailMap   =  funcDetailMaps.get(funcDetailMaps.size()-1);
          funcDetailMapper.insertFuncDetail (funcDetailMap);

          Tauthfuncrela tauthfuncrela = new Tauthfuncrela();
          tauthfuncrela.setL_Authid(Long.parseLong(authMap.get( "l_authid").toString()));
          tauthfuncrela.setL_Funcdetail(Long.parseLong(funcDetailMap.get( "l_funcdetail").toString()));
          Map<String, Object> authColRelaMap =  oMapper.convertValue(tauthfuncrela, Map.class);
          funcDetailMapper.insertFuncDetailRela(authColRelaMap);

        }
    }

    @Override
    public void addAuthAndFuncDetail(Map<String, Object> map){

        Map<String, Object>   authMap = (Map<String, Object>)map.get("authority");
        authorityMapper.insertAuth(authMap);
        Map<String, Object> funcDetailMap=(Map<String, Object>)map.get("funcDetail");
        funcDetailMapper.insertFuncDetail (funcDetailMap);

        Tauthfuncrela tauthfuncrela = new Tauthfuncrela();
        tauthfuncrela.setL_Authid(Long.parseLong(authMap.get( "l_authid").toString()));
        tauthfuncrela.setL_Funcdetail(Long.parseLong(funcDetailMap.get("l_funcdetail").toString()));
        Map<String, Object> authColRelaMap =  oMapper.convertValue(tauthfuncrela, Map.class);
        funcDetailMapper.insertFuncDetailRela(authColRelaMap);

    }
   @Override
   public void deleAuthByAuth(Integer authId){
        Integer authIdInt =authId;
        List<Map<String,Object>> relaMaps;

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

        authorityMapper.deleRoleRelaByAuthId(authIdInt);

        authorityMapper.deleAuth(authIdInt);
   }
   @Override
    public void deleLineDetail(String lineId){
        Integer lineIdInt = Integer.parseInt(lineId);
        dataLineDetailMapper.deleAuthLineRelaByLineId(lineIdInt);
        dataLineDetailMapper.deleLineByLineId(lineIdInt);
    }
    @Override
    public void deleColuDetail(String coluId){
        Integer coluIdInt =Integer.parseInt(coluId);
        tabColumnDetailMapper.deleAuthColRelaByColId(coluIdInt);
        tabColumnDetailMapper.deleColByColId(coluIdInt);
    }

    @Override
    public void deleTableDetail(String tableId){
        Integer tabIdInt =Integer.parseInt(tableId);
        tableDetailMapper.deleAuthTabRelaByTabId(tabIdInt);
        tableDetailMapper.deleTabByTabId(tabIdInt);
    }

    @Override
    public void deleFuncDetail(String funcId){
        Integer funcIdInt =Integer.parseInt(funcId);
        funcDetailMapper.deleAuthFuncRelaByFuncId(funcIdInt);
        funcDetailMapper.deleFuncByFuncId(funcIdInt);
    }

    @Override
    public void modifyLineDetail(Map<String, Object> map){

        dataLineDetailMapper.modifyLineDetail(map);
    }
    @Override
    public void modifyColuDetail(Map<String, Object> map){

        tabColumnDetailMapper.modifyColuDetail(map);
    }

    @Override
    public void modifyTableDetail(Map<String, Object> map){

        tableDetailMapper.modifyTableDetail(map);
    }

    @Override
    public void modifyFuncDetail(Map<String, Object> map){

        funcDetailMapper.modifyFuncDetail(map);
    }
    @Override
    public   String getAuthdetail(Map<String, Object> map){
         Integer authId= (Integer) map.get("l_authid");
         List<Map<String,Object>> authroitys =  authorityMapper.getAuthByAuthId(authId);

         if (authroitys.isEmpty()){
             return JsonResult.successJson("该权限不存在");
         }
        Map<String,Object>authroity = authroitys.get(authroitys.size()-1);
         Map<String,Object> authDetail =new HashMap<String, Object>();
         authDetail.put("authiorty" , authroity);

         List<Map<String,Object>> details= new ArrayList<Map<String,Object>>()  ;

         String dataType= (String) authroity.get("l_datatype");
         String authType= (String) authroity.get("c_authtype");

         if(authType.equals("0")){

             details = funcDetailMapper.getFuncDetailByAuth(authId) ;
         }
         if(authType.equals("1")){
            if (dataType.equals("1")){
                details =tableDetailMapper.getTableDetailByAuthId(authId);

            }
             if (dataType.equals("2")){
                 details =tabColumnDetailMapper.getTabColDetailByAuthId(authId);

             }
             if (dataType.equals("3")){
                 details =dataLineDetailMapper.selectLineDetByAuthId(authId);
             }
          }
        authDetail.put("authDetail",details);
        return JsonResult.successJson(authDetail);
    }
}
