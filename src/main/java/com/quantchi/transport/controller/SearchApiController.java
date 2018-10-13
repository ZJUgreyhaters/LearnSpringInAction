package com.quantchi.transport.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.JsonResult;
import com.quantchi.common.Util;
import com.quantchi.transport.service.SearchApiService;
import java.util.Map;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "api")
public class SearchApiController {

    private static final Logger logger = LoggerFactory.getLogger(SearchApiController.class);

    @Autowired
    private SearchApiService searchApiService;

  /*  @Autowired
    private intelQuery intelquery;*/

    @RequestMapping(value = "/query", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> query (@RequestParam("q") String q) throws Exception {
        String _query = String.join(" ",searchApiService.segment(q));
        QueryResponse rets = searchApiService.search(_query);
        //处理后的结果集
        SolrDocumentList afterHandle = searchApiService.handle(q,rets.getResults(),false);
        if(afterHandle.size() > 0){
            Map<String, Object> _retRes =  Util.genRet(200,afterHandle,"ok",afterHandle.size());
            _retRes.put("type","entity");
            return _retRes;
        }
        //Map<String, Object> _intelRet = intelquery.query(q);
        //if(_intelRet.containsKey(Status.INTERNAL_SERVER_ERROR.getStatus())){
            return Util.genRet(500,null,null,0);
        //}else{
            //return Util.genRet(200,null,"ok",0;
        //}
    }

    /*@RequestMapping(value = "/queryInstance", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> queryInstance (@RequestParam("q") String q) throws Exception {
        String _query = String.join(" ",searchApiService.segment(q));
        //String _query = q;
        QueryResponse rets = searchApiService.searchInstance(_query);
        SolrDocumentList data =  searchApiService.handleInst(_query,rets);
        return Util.genRet(200,data,"ok",data.size());
    }*/

    //@RequestMapping(value = "/queryFromSearchToData", method = { RequestMethod.POST })
    @ResponseBody
    @RequestMapping(value = "/queryFromSearch", method = { RequestMethod.POST })
    public String queryFromSearchToData(@RequestBody String bodyString) {
        try {
            JSONObject json = JSONObject.parseObject(bodyString);
            String serialization = json.get("data").toString();
            return JsonResult.successJson("");
        }catch (Exception e){
            logger.error("queryFromSearch error happen:{}",e);
            return JsonResult.errorJson(e.getMessage());
        }

    }

}
