package com.quantchi.transport.service;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.QueryNodes;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.intelquery.node.SemanticNode;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quantchi.intelquery.tokenize.LtpTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchApiService {
	private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);

	@Autowired
	private HttpSolrClient httpSolr;

	public QueryResponse search(String str) throws Exception{
		//匹配率
		String mm = AppProperties.get("solr.mm");
		if(mm == null)
			mm = "50%";
		return searchSolr(str,mm);
	}

	public QueryResponse searchInstance(String str) throws Exception{
		return searchSolr(str,null);
	}

	public QueryResponse searchSolr(String str,String mm) throws Exception{

		SolrQuery query = new SolrQuery();
		String rows = AppProperties.get("solr.rows");
		if(rows == null )
			rows = "20";
		query.setQuery("cn_name:"+str);
		if(mm != null){
			query.set("defType", "edismax");
			query.set("mm", mm);
		}
		query.setHighlight(true);
		query.setParam("hl.fl", "cn_name");
		query.setStart(0);
		query.setRows(Integer.parseInt(rows));
		QueryResponse response = new QueryResponse();
		try {
			response = httpSolr.query(query);
			//docs = response.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	//处理函数
	public SolrDocumentList handle(String query, SolrDocumentList docs) throws Exception{

	    SolrDocumentList result = new SolrDocumentList();
        String t = AppProperties.get("solr.threshold");
        if(t == null)
            t = "0.5";
	    double threshold =Double.parseDouble(t);   //阈值

	    //对每个doc做处理
	    for(SolrDocument doc : docs){
	        String cn_name = (String) doc.get("cn_name");
	        List<String> queryWords= segment(query);
	        List<String> nameWords = segment(cn_name);

	        double nameWordNum = nameWords.size(); //中文名的单词数
            double matchNum = 0; //匹配到的数量

	        for(String word : queryWords){
                if(nameWords.contains(word))
                    matchNum ++;
            }

            double ratio = matchNum / nameWordNum;

	        //如果比例大于等于阈值，添加到结果集合中
	        if(ratio >= threshold){
	        	doc.addField("hit_ratio",ratio);
				result.add(doc);
			}

        }

	    return result;
    }

    //分词函数
    public List<String> segment(String str) throws QPException {
	    List<String> list = new ArrayList<>();
        QueryNodes _nodes = LtpTokenizer.tokenize(str);
        for(SemanticNode node : _nodes){
            list.add(node.getText());
        }
	    return list;
    }

	public Boolean isIndex(String str){

		//TODO
		//1.查solr
		//2.匹配计算
		//3.高于 匹配值的 返回

		return false;
	}

	public SolrDocumentList handleInst(String query, QueryResponse qRes){

		SolrDocumentList docs = setHighlightInDoc(qRes);
		return qRes.getResults();
	}

	private SolrDocumentList setHighlightInDoc(QueryResponse qRes){
		Map<String, Map<String, List<String>>> _map = qRes.getHighlighting();
		SolrDocumentList solrDocs = new SolrDocumentList();
		for(SolrDocument doc:qRes.getResults()){
			List<String> hl = _map.get(doc.getFieldValue("id").toString()).get("cn_name");
			doc.addField("hit_word","");
			solrDocs.add(doc);
		}
		return solrDocs;
	}

	private String getHitWords(List<String> highlights){
		return  "";
	}
}



