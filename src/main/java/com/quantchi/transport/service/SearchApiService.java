package com.quantchi.transport.service;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchApiService {
	private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);

	/*@Autowired
	private HttpSolrClient server;*/

	public void search(){
		/*SolrQuery query = new SolrQuery();
		query.setQuery("name_s:wanglctest*");
		query.setHighlight(true);//开启高亮功能
		query.addHighlightField("name_s");//高亮字段
		query.setHighlightSimplePre("<font color='red'>");//渲染标签
		query.setHighlightSimplePost("</font>");//渲染标签
		query.setStart(0);
		query.setRows(20);
		QueryResponse queryResponse;
		try {
			queryResponse = server.query(query);
			SolrDocumentList lists = queryResponse.getResults();//查询结果集
			List<student> items = new ArrayList<student>();
			String tmpId = "";
			Map<String, Map<String, List<String>>> highLightMap = queryResponse.getHighlighting();
			for(SolrDocument solrDocument: lists){
				student stu = new student();
				tmpId = solrDocument.getFieldValue("id").toString();
				stu.setId(tmpId);
				stu.setScore_i((int) solrDocument.getFieldValue("score_i"));
				stu.setName_s(solrDocument.getFieldValue("name_s").toString());

				List<String> nameList = highLightMap.get(tmpId).get("name_s");
				if(nameList!=null && nameList.size()>0){
					stu.setName_s(nameList.get(0));//获取并设置高亮字段name
				}
				System.out.println(stu.getScore_i() + " | " +stu.getName_s());
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
