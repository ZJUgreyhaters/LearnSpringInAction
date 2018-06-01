package com.quantchi.transport.controller;


import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.util;
import com.quantchi.intelquery.Query;
import com.quantchi.intelquery.QueryParser;
import com.quantchi.intelquery.QueryResult;
import com.quantchi.intelquery.exception.InterpreterException;
import com.quantchi.intelquery.intelQuery;
import com.quantchi.tianshu.common.web.ErrorResponse;
import com.quantchi.tianshu.common.web.Response;
import com.quantchi.tianshu.common.web.Status;
import com.quantchi.tianshu.common.web.SuccessResponse;
import com.quantchi.transport.service.SearchApiService;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping(value = "api")
public class SearchApiController {

    private static final Logger logger = LoggerFactory.getLogger(SearchApiController.class);

    @Autowired
    private SearchApiService searchApiService;

    @Autowired
    private intelQuery intelquery;

    @RequestMapping(value = "/query", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> query (@RequestParam("q") String q) throws Exception {

        String _query = String.join(" ",searchApiService.segment(q));
        //String _query = q;
        QueryResponse rets = searchApiService.search(_query);
        //处理后的结果集
        SolrDocumentList afterHandle = searchApiService.handle(q,rets.getResults(),false);
        if(afterHandle.size() > 0){
            Map<String, Object> _retRes =  util.genRet(200,afterHandle,"ok",afterHandle.size());
            _retRes.put("type","entity");
            return _retRes;
        }
        else{

            Map<String, Object> _intelRet = intelquery.query(q);
            if(_intelRet.containsKey(Status.INTERNAL_SERVER_ERROR.getStatus())){
                return util.genRet(500,null,_intelRet.get(Status.INTERNAL_SERVER_ERROR.getStatus()).toString(),0);
            }else{
                Map<String, Object> _intelRetData = (Map<String, Object>)_intelRet.get("data");
                if(_intelRetData.get("phase").toString().equals("afterSearch")){
                    return util.genRet(200,_intelRet.get("data"),"ok",0);
                }else{
                    Map<String, Object> _searchData = (Map<String, Object>)_intelRetData.get("searchResults");
                    return util.genRet(200,_searchData.get("data"),"ok",Integer.parseInt(_searchData.get("total").toString()));
                }

            }
        }
    }

    @RequestMapping(value = "/queryInstance", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> queryInstance (@RequestParam("q") String q) throws Exception {
        String _query = String.join(" ",searchApiService.segment(q));
        //String _query = q;
        QueryResponse rets = searchApiService.searchInstance(_query);
        SolrDocumentList data =  searchApiService.handleInst(_query,rets);
        return util.genRet(200,data,"ok",data.size());
    }

    @RequestMapping(value = "/queryFromSearchToData", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> queryFromSearchToData(@RequestBody String bodyString) {
        JSONObject json = JSONObject.parseObject(bodyString);
        String serialization = json.get("serial").toString();
        Map<String, Object> _intelRet = intelquery.queryFromSearch(serialization);
        if(_intelRet.containsKey("data")){
            Map<String, Object> _searchData = (Map<String, Object>)_intelRet.get("data");
            return util.genRet(200,_searchData.get("data"),"ok",Integer.parseInt(_searchData.get("total").toString()));
        }else{
            Iterator<Map.Entry<String,Object>> iter = _intelRet.entrySet().iterator();
            Map.Entry<String, Object> entry = iter.next();
            return util.genRet(500,null,entry.getValue().toString(),0);
        }

    }
}
