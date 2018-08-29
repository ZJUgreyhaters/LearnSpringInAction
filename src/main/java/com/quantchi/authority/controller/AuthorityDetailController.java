package com.quantchi.authority.controller;


import com.quantchi.authority.service.AuthorityDetailService;
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


    /*插入数据权限
    *例子
    *{"authority":{"c_authname":"quantchix","c_isenable":"1","c_authtype":"0","l_authid":0},
    * "dataTabDetail":[{"l_datatabledetailid":1,"c_tablename":"tcustomerinfo","c_isenable":0},{"l_datatabledetailid":1,"c_tablename":"taccoinfo","c_isenable":0}],
    * "dataColDetail":[{"l_columndetailid":1,"c_tablename":"tstock","c_column":"c_stockcode","c_isenable":"0" }]}
    * */
    @ResponseBody
    @RequestMapping(value = "/addDataAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addDataAuth(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.addAuthAndDataDetail(requestMap);
    }
    /*插入功能权限
    *例子
    *{"authority":{"c_authname":"alibaba","c_isenable":"1","c_authtype":"0","l_authid":0},
    * "funcDetail":{"l_funcdetail":1,"c_funcname":"alaifunc","c_url":"127.0.0.1","c_isenable":0} }
     */
    @ResponseBody
    @RequestMapping(value = "/addFuncAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addFuncAuth(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.addAuthAndFuncDetail(requestMap);
    }
    //修改行数据权限
    /*
    * 例子
    *{"l_linedetailid":1,"c_tablename":"tdcuqocallogfo","c_column":"logs","c_fiterdesc":"xxx","c_fiter":"123123","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyLineDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyLineDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyLineDetail(requestMap);
    }
    //修改字段权限
    /*例子
    * {"l_columndetailid":1,"c_tablename":"dytable","c_column":"cc_cc","c_isenable":0}
     *
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyColumnDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyColumnDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyColuDetail(requestMap);
    }
    //修改表权限
    /*
    * 例子
    * {"l_datatabledetailid":1,"c_tablename":"tcustomerinfo","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyTableDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyTableDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyTableDetail(requestMap);
    }

    //修改功能权限
    /*
    * 例子
    * {"l_funcdetail":1,"c_funcname":"alaifunc","c_url":"127.0.0.1","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyFuncDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyFuncDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyFuncDetail(requestMap);
    }

    // 删除数据明细权限
    /* detailDataId 传入明细权限的Id   ;  detailType： 0 行数据类型  ，1 字段类型 ，2 表类型
    * 例子{"detailType":2,"detailDataId":1}
    * */
    @ResponseBody
    @RequestMapping(value = "/deleAuthDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public  void  deleAuthDetail(@RequestBody Map<String, Object> requestMap){
        Integer detailType = (Integer) requestMap.get("detailType");
        Integer detailDataId = (Integer) requestMap.get("detailDataId");
          if (detailType == 0 ) {
           authoritydetailService.deleLineDetail(detailDataId.toString());
          }
          else if ( detailType == 1 ){
              authoritydetailService.deleColuDetail(detailDataId.toString());
          }
          else if (detailType == 2){
              authoritydetailService.deleTableDetail(detailDataId.toString());
          }

    }
}
