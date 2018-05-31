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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchApiService {
	private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);
	private static final String leftTag = "<em>";
	private static final String rightTag = "</em>";
	private static final String searchField = "seg_name";
	private static final String highlightField = "cn_name";

	@Autowired
	private HttpSolrClient httpSolr;

	public QueryResponse search(String str) throws Exception{
		//匹配率
		String mm = AppProperties.get("solr.mm");
		if(mm == null)
			mm = "50%";

		Map<String,String> param = new HashMap<>();
        param.put("defType","edismax");
		param.put("mm",mm);
		param.put("fq","type:entity");
		return searchSolr(str,param);
	}

	public QueryResponse searchInstance(String str) throws Exception{
		return searchSolr(str,null);
	}

	public QueryResponse searchSolr(String str,Map<String,String> param) throws Exception{

		SolrQuery query = new SolrQuery();
		String rows = AppProperties.get("solr.rows");
		if(rows == null )
			rows = "20";
		query.setQuery(searchField+":("+str+")");

		if(param != null){
			for(Map.Entry<String, String> entry:param.entrySet()){
				query.setParam(entry.getKey(),entry.getValue());
			}
		}
		query.setHighlight(true);
		query.setParam("hl.fl", highlightField);
		query.setStart(0);
		query.setParam("rows",rows);
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

	    	//如果查询的内容和seg_name一样，略过
	    	if(query.equals((String)doc.get("seg_name")))
	    		continue;

			String seg_name = (String) doc.get(searchField);
	        List<String> queryWords= segment(query);
            List<String> nameWords =  java.util.Arrays.asList(seg_name.split(" "));

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

	public SolrDocumentList handleInst(String query, QueryResponse qRes) throws Exception {
		SolrDocumentList docs = handle(query,qRes.getResults());
		SolrDocumentList docsAfterHandleReplaceOrigin = setReplaceOrigin(query,docs,qRes.getHighlighting());
		return docsAfterHandleReplaceOrigin;
	}

	private SolrDocumentList setReplaceOrigin(String query,SolrDocumentList docs,Map<String, Map<String, List<String>>> highlight)
	throws IOException{
		SolrDocumentList solrDocs = new SolrDocumentList();
		for(SolrDocument doc:docs){
			List<String> hl = highlight.get(doc.getFieldValue("id").toString()).get(highlightField);
			String replace_origin = getMaxLengthSubWord(query,getHitWords(hl));

			double hit_ratio = (double)doc.get("hit_ratio");
			double score_proirity = Double.parseDouble(AppProperties.get("solr.score.proirity"));

			double len_ratio = (double)replace_origin.length() / (double)query.length();
			double len_proirity = Double.parseDouble(AppProperties.get("solr.length.proirity"));

			double weight = hit_ratio * score_proirity + len_ratio * len_proirity;

			double standard = Double.parseDouble(AppProperties.get("solr.standard"));
			if(weight >= standard){
				doc.addField("replace_origin",replace_origin);
				solrDocs.add(doc);
			}

		}
		return solrDocs;
	}

	private List<String> getHitWords(List<String> highlights){

		List<String> hits = new ArrayList<>();
		String _hl = highlights.get(0).toString();
		String _hit = "";
		int _start = _hl.indexOf(leftTag);
		int _end = _hl.indexOf(rightTag);

		while (_start != -1 && _end != -1){
			_hit = _hl.substring(_start+4,_end);
			hits.add(_hit);
			_hl = _hl.substring(_end+1);
			_start = _hl.indexOf(leftTag);
			_end = _hl.indexOf(rightTag);
		}

		return  hits;
	}

	private String getMaxLengthSubWord(String query,List<String> hitWords){
		String _firstWord = hitWords.get(0);
		String _ret = _firstWord;
		if(hitWords.size() > 1){
			String _lastWord = hitWords.get(hitWords.size()-1);
			int _st = query.indexOf(_firstWord);
			int _end = query.lastIndexOf(_lastWord);
			_ret = query.substring(_st,_end+_lastWord.length());
		}
		return _ret;
	}
}



