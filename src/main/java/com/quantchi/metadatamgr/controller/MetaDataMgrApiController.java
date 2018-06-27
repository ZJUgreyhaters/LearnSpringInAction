package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.AppProperties;
import com.quantchi.common.util;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
import com.quantchi.termInfo.pojo.TermGenInfo;
import com.quantchi.tianshu.common.web.Status;
import com.quantchi.transport.controller.SearchApiController;
import javafx.application.Application;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/metadata")
public class MetaDataMgrApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrApiController.class);

    @Autowired
    private MetaDataMgrApiService metaDataMgrApiService;

    @RequestMapping(value = "/datasource/list", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> list (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);
            String dsName = "";
            if(json.get("data_source_name") != null)
                dsName = json.get("data_source_name").toString();
            //int start = Integer.parseInt(json.get("page").toString());
            //int pagesize = Integer.parseInt(json.get("page_size").toString());
            Map<String, Object> _ret = metaDataMgrApiService.getDSMetaInfo(dsName,json.getString("page"),json.getString("page_size"));
            return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("list func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/connect", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> connectTest (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.get("data_source_host") == null
            || json.get("data_source_port") == null
            || json.get("data_source_username") == null
            || json.get("data_source_passwd") == null)
                throw new Exception("miss data source connection info");
            else{
                String host = json.get("data_source_host").toString();
                String port = json.get("data_source_port").toString();
                String username = json.get("data_source_username").toString();
                String pswd = util.DecodePassword(json.get("data_source_passwd").toString());
                if(json.getString("data_source_type").toLowerCase().equals("hive")){
                    if(json.get("data_source_mysql_url") == null
                            || json.get("data_source_mysql_usr") == null
                            || json.get("data_source_mysql_pswd") == null)
                        throw new Exception("miss data source meta connection info");
                    String url = json.getString("data_source_mysql_url");
                    String mysql_user = json.getString("data_source_mysql_usr");
                    String mysql_pswd = util.DecodePassword(json.getString("data_source_mysql_pswd"));
                    boolean _retMeta =  metaDataMgrApiService.connectMysqlTest(url,mysql_user,mysql_pswd);
                    if(!_retMeta)
                        return util.genRet(201,false,"meta 数据库连接失败",0);
                }

                boolean _ret = metaDataMgrApiService.connectTest(host,port,username,pswd);
                if(_ret)
                    return util.genRet(200,_ret,"连接成功",0);
                else
                    return util.genRet(201,_ret,"连接失败",0);
            }
        }catch (Exception e){
            logger.error("connectTest func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/save", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> save (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.get("data_source_host") == null
                    || json.get("data_source_port") == null
                    || json.get("data_source_username") == null
                    || json.get("data_source_passwd") == null)
                throw new Exception("miss data source connection info");

            boolean _ret = metaDataMgrApiService.saveMetaInfo(json);
            if(_ret)
                return util.genRet(200,_ret,"修改成功",0);
            else
                return util.genRet(201,_ret,"修改失败",0);

        }catch (Exception e){
            logger.error("save func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/del", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> deleteDataSource (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.getString("data_source_name") == null)
                throw new Exception("miss data source connection info");
            else{
                String dsName = json.getString("data_source_name");

                boolean _ret = metaDataMgrApiService.delMetaInfo(dsName);
                if(_ret)
                    return util.genRet(200,_ret,"删除成功",0);
                else
                    return util.genRet(201,_ret,"删除失败",0);
            }
        }catch (Exception e){
            logger.error("deleteDataSource func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/check", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> checkDSName (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.getString("data_source_name") == null)
                throw new Exception("miss data source connection info");
            else{
                String dsName = json.getString("data_source_name");

                boolean _ret = metaDataMgrApiService.chkDSName(dsName);
                if(_ret)
                    return util.genRet(200,_ret,"成功",0);
                else
                    return util.genRet(201,_ret,"重复",0);
            }
        }catch (Exception e){
            logger.error("check func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/getsourcedata", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> extractTables (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.getString("data_source_name") == null)
                throw new Exception("miss data source connection info");

            String dsName = json.getString("data_source_name");
            String keyword = json.getString("keyword");
            Map<String, Object>  _ret = metaDataMgrApiService.extractTables(dsName,keyword);
            return util.genRet(200,_ret.get("data"),"成功",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("extractTables func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/datasource/localsave", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> localSave (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.getString("data_source_name") == null)
                throw new Exception("miss data source name");

            if(json.get("table_names") == null)
                throw new Exception("miss tables name info");

            String dsName = json.getString("data_source_name");
            List<String> tbs = (List) json.get("table_names");
            boolean _ret = metaDataMgrApiService.saveTablesAndFields(dsName,tbs);
            if(_ret)
                return util.genRet(200,_ret,"成功",0);
            else
                return util.genRet(201,_ret,"失败",0);
        }catch (Exception e){
            logger.error("extractTables func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }
    }

    @RequestMapping(value = "/relation/list", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> relationList(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("data_source_name") == null){
                throw new Exception("miss data source name");
            }
            String dsName = json.getString("data_source_name");
            List<String> tbList = (List<String>) json.get("table_list");
            Map<String,Object> map = metaDataMgrApiService.relationList(dsName, tbList);
            responseMap.put("data",map);
            responseMap.put("code",200);
            responseMap.put("msg","查询成功");
            return responseMap;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }

    }

    @RequestMapping(value = "/relation/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> relationSave(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("data_source_name") == null){
                throw new Exception("miss data source name");
            }
            if(json.getString("from") == null){
                throw new Exception("miss from");
            }
            if(json.getString("to") == null){
                throw new Exception("miss to");
            }
            if(json.getString("relation") == null){
                throw new Exception("miss relation");
            }
            if(metaDataMgrApiService.relationSave(json) <=0){
                throw new Exception("save fail");
            }
            responseMap.put("code",200);
            responseMap.put("msg","保存成功");
            return responseMap;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }
    }

    @RequestMapping(value = "/relation/del", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> relationDel(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("relation_id") == null){
                throw new Exception("miss relation_id");
            }
            if(metaDataMgrApiService.relationDel(json.getString("relation_id")) <=0){
                throw new Exception("delete fail");
            }
            responseMap.put("code",200);
            responseMap.put("msg","删除成功");
            return responseMap;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }

    }

    @RequestMapping(value = "/source/createterm", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> createTerm(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("data_source_id") == null){
                throw new Exception("miss data source id");
            }
            responseMap = metaDataMgrApiService.createTerm(json.getString("data_source_id"));
            return responseMap;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }
    }
}
