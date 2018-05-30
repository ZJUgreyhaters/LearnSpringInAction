package com.quantchi.transport.service;

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

	public SolrDocumentList search(String str){
		SolrQuery query = new SolrQuery();
		query.setQuery("cn_name:"+str);
		query.set("defType", "edismax");
		query.set("mm", "50%");
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
}
