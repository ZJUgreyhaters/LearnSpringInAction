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


    /** @api {post} /api/addDataAuth 添加新的数据权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/addDataAuth
     * @apiName addDataAuth
     * @apiGroup AuthorityDetailController
     * @apiParam {Object} [authority] 权限
     * @apiParam {String} [authority.c_authname] 权限名称
     * @apiParam {String} [authority.c_isenable] 是否生效
     * @apiParam {String} [authority.c_authtype] 权限类型  0:功能权限  1:数据权限'
     * @apiParam {Int} [authority.l_authid] 权限id   因为还未插入表 填0
     * @apiParam {List} [dataTabDetail] 表数据权限明细 可以装入多个
     * @apiParam {Int} [dataTabDetail.l_datatabledetailid] 表权限ID    因为还未插入表 填0
     * @apiParam {String} [dataTabDetail.c_tablename] 表名字
     * @apiParam {String} [dataTabDetail.c_isenable]  是否有效
     * @apiParam {List} [dataColDetail] 表字段数据权限明细 可以装入多个
     * @apiParam {Int} [dataColDetail.l_columndetailid] 表字段权限ID    因为还未插入表 填0
     * @apiParam {String} [dataColDetail.c_tablename] 表名字
     * @apiParam {String} [dataColDetail.c_column] 表字段名字
     * @apiParam {String} [dataColDetail.c_isenable]  是否有效
     * @apiParam {List} [dataLineDetail] 行数据权限明细 可以装入多个
     * @apiParam {Int} [dataLineDetail.l_linedetailid] 行数据权限ID    因为还未插入表 填0
     * @apiParam {String} [dataLineDetail.c_tablename] 表名字
     * @apiParam {String} [dataLineDetail.c_column] 表字段名字
     * @apiParam {String} [dataLineDetail.c_fiterdesc] 过滤描述
     * @apiParam {String} [dataLineDetail.c_fiter]  过滤条件
     * @apiParam {String} [dataLineDetail.c_isenable]  是否有效
     * @apiParamExample {json} Request-example:
     *
     * {"authority":{"c_authname":"quantchix","c_isenable":"1","c_authtype":"0","l_authid":0},
     * "dataTabDetail":[{"l_datatabledetailid":1,"c_tablename":"tcustomerinfo","c_isenable":0},{"l_datatabledetailid":1,"c_tablename":"taccoinfo","c_isenable":0}],
     * "dataColDetail":[{"l_columndetailid":1,"c_tablename":"tstock","c_column":"c_stockcode","c_isenable":"0" }]}
     *
     **/
    //也可以同时加入 dataLineDetail:[] 内部配装按照数据库字段全小写 类似上面 可以上个权限同时添加
    @ResponseBody
    @RequestMapping(value = "/addDataAuth", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void addDataAuth(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.addAuthAndDataDetail(requestMap);
    }
    /**@api {post} /api/addFuncAuth 插入功能权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/addFuncAuth
     * @apiName addFuncAuth
     * @apiGroup AuthorityDetailController
     * @apiParam {Object} [authority] 权限
     * @apiParam {String} [authority.c_authname] 权限名称
     * @apiParam {String} [authority.c_isenable] 是否生效
     * @apiParam {String} [authority.c_authtype] 权限类型  0:功能权限  1:数据权限'
     * @apiParam {Int} [authority.l_authid] 权限id   因为还未插入表 填0
     * @apiParam {Object} [funcDetail] 功能权限明细
     * @apiParam {Int} [funcDetail.l_funcdetail] 功能权限ID    因为还未插入表 填0
     * @apiParam {String} [funcDetail.c_funcname] 功能名称
     * @apiParam {String} [funcDetail.c_url]功能路径
     * @apiParam {String} [funcDetail.c_isenable]  是否有效
     *
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
     /**@api {post} /api/modifyLineDetail 修改行数明细
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyLineDetail
     * @apiName modifyLineDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [l_linedetailid] 行数据权限ID
      * @apiParam {String} [c_tablename] 表名字
      * @apiParam {String} [c_column] 表字段名字
      * @apiParam {String} [c_fiterdesc] 过滤描述
      * @apiParam {String} [c_fiter]  过滤条件
      * @apiParam {String} [c_isenable]  是否有效
    * 例子
    *{"l_linedetailid":1,"c_tablename":"tdcuqocallogfo","c_column":"logs","c_fiterdesc":"xxx","c_fiter":"123123","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyLineDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyLineDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyLineDetail(requestMap);
    }
     /**@api {post} /api/modifyColumnDetail 修改字段权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyColumnDetail
     * @apiName modifyColumnDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [ l_columndetailid] 表字段权限ID
     * @apiParam {String} [c_tablename] 表名字
     * @apiParam {String} [c_column] 表字段名字
     * @apiParam {String} [c_isenable]  是否有效
      * 例子
    * {"l_columndetailid":1,"c_tablename":"dytable","c_column":"cc_cc","c_isenable":0}
     *
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyColumnDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyColumnDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyColuDetail(requestMap);
    }

    /**@api {post} /api/modifyTableDetail 修改表权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyTableDetail
     * @apiName modifyTableDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [ l_datatabledetailid] 表字段权限ID
     * @apiParam {String} [c_tablename] 表名字
     * @apiParam {String} [c_isenable]  是否有效
    * 例子
    * {"l_datatabledetailid":1,"c_tablename":"tcustomerinfo","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyTableDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyTableDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyTableDetail(requestMap);
    }


    /**@api {post} /api/modifyFuncDetail 修改功能权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/modifyFuncDetail
     * @apiName modifyFuncDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [l_funcdetail] 功能权限ID
     * @apiParam {String} [c_funcname] 功能名称
     * @apiParam {String} [c_url]功能路径
     * @apiParam {String} [c_isenable]  是否有效
    * 例子
    * {"l_funcdetail":1,"c_funcname":"alaifunc","c_url":"127.0.0.1","c_isenable":0}
    * */
    @ResponseBody
    @RequestMapping(value = "/modifyFuncDetail", method = {
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public void modifyFuncDetail(@RequestBody Map<String, Object> requestMap){
        authoritydetailService.modifyFuncDetail(requestMap);
    }


    /**@api {post} /api/deleAuthDetail 删除数据明细权限
     * @apiVersion 1.0.0
     * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/api/deleAuthDetail
     * @apiName deleAuthDetail
     * @apiGroup AuthorityDetailController
     * @apiParam {Int} [detailType] 数据明细类型
     * @apiParam {Int} [detailDataId] 要删除的明细Id
     * detailDataId 传入明细权限的Id   ;  detailType： 0 行数据类型  ，1 字段类型 ，2 表类型
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
