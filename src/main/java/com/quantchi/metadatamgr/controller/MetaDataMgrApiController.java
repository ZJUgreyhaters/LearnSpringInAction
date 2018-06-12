package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.util;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
import com.quantchi.tianshu.common.web.Status;
import com.quantchi.transport.controller.SearchApiController;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "metadata")
public class MetaDataMgrApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrApiController.class);

    @Autowired
    private MetaDataMgrApiService metaDataMgrApiService;

    @RequestMapping(value = "/list", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> list (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);
            String dsName = "";
            if(json.get("data_source_name") != null)
                dsName = json.get("data_source_name").toString();
            int start = Integer.parseInt(json.get("page").toString());
            int pagesize = Integer.parseInt(json.get("page_size").toString());
            Map<String, Object> _ret = metaDataMgrApiService.getDSMetaInfo(dsName,start,pagesize);
            return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("list func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    @RequestMapping(value = "/connectTest", method = { RequestMethod.POST })
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
                String pswd = json.get("data_source_passwd").toString();
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

    @RequestMapping(value = "/save", method = { RequestMethod.POST })
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

    @RequestMapping(value = "/del", method = { RequestMethod.POST })
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

    @RequestMapping(value = "/check", method = { RequestMethod.POST })
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
                    return util.genRet(201,_ret,"失败",0);
            }
        }catch (Exception e){
            logger.error("check func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

    /*table part*/

    @RequestMapping(value = "/extractTables", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> extractTables (@RequestBody String bodyString) {
        try{

            JSONObject json = JSONObject.parseObject(bodyString);

            if(json.getString("data_source_name") == null)
                throw new Exception("miss data source connection info");

            String dsName = json.getString("data_source_name");
            Map<String, Object>  _ret = metaDataMgrApiService.extractTables(dsName);
            return util.genRet(200,_ret.get("data"),"成功",0);
        }catch (Exception e){
            logger.error("extractTables func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }

}
