package com.quantchi.transport.service;

import com.quantchi.common.AppProperties;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchApiService {
	private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);

	@Autowired
	private HttpSolrClient httpSolr;

	public SolrDocumentList search(String str) throws Exception{
		//匹配率
		String mm = AppProperties.get("solr.mm");
		if(mm == null)
			mm = "50%";
		return searchSolr(str,mm);
	}

	public SolrDocumentList searchInstance(String str) throws Exception{
		return searchSolr(str,null);
	}

	public SolrDocumentList searchSolr(String str,String mm) throws Exception{

		SolrQuery query = new SolrQuery();
		query.setQuery("cn_name:"+str);
		if(mm != null){
			query.set("defType", "edismax");
			query.set("mm", mm);
		}
		query.setStart(0);
		query.setRows(20);
		SolrDocumentList docs = null;
		QueryResponse response = new QueryResponse();
		try {
			response = httpSolr.query(query);
			docs = response.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docs;
	}

	public Boolean isIndex(String str){

		//TODO
		//1.查solr
		//2.匹配计算
		//3.高于 匹配值的 返回

		return false;
	}
}
