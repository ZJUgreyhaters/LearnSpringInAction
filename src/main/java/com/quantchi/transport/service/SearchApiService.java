package com.quantchi.transport.service;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.query.QueryNodes;
import com.quantchi.intelquery.tokenize.LtpTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchApiService {
	private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);

	private static final String searchField = AppProperties.getWithDefault("searchField","seg_name");
	private static final String highlightField = AppProperties.getWithDefault("highlightField","seg_name");
	private static final String colsField = AppProperties.getWithDefault("colsField","cn_name");

	private static final String WEIGHT = "weight";
	private static final String REPLACE_ORIGIN = "replace_origin";
	private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";
	private static final String DEFAULT_TRIM_WEIGHT = "0.5";

	@Autowired
	private HttpSolrClient httpSolr;

	public QueryResponse search(String str) throws Exception{

		Map<String,String> param = AppProperties.getPropertiesMap("solr.param");
		return searchSolr(str,param);
	}

	public QueryResponse searchInstance(String str) throws Exception{

		Map<String,String> param = AppProperties.getPropertiesMap("solr.instance");
		return searchSolr(str,param);
	}

	public QueryResponse searchSolr(String str,Map<String,String> param) throws Exception{

		SolrQuery query = new SolrQuery();
		Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.search");
		query.setQuery(searchField+":("+str+")");

		if(param != null){
			for(Map.Entry<String, String> entry:param.entrySet()){
				query.setParam(entry.getKey(),entry.getValue());
			}
		}
		Set<String> keys = solrParam.keySet();
		for(String key:keys){
			query.setParam(key, solrParam.get(key));
		}

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
	public SolrDocumentList handle(String query, SolrDocumentList docs , boolean filterRepeat) throws Exception{

		SolrDocumentList result = new SolrDocumentList();
		Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.handle");
		double threshold =Double.parseDouble(solrParam.get("threshold"));   //阈值

		//对每个doc做处理
		for(SolrDocument doc : docs){

			//如果查询的内容和seg_name一样，略过
			if(filterRepeat && query.equals((String)doc.get(searchField)))
				continue;

			String seg_name = (String) doc.get(searchField);
			List<String> queryWord= segment(query);
			Set<String> queryWords = new HashSet<>();

			for(String word : queryWord){
				queryWords.add(word);
			}
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
	public List<String> segment(String str) throws QPException, IOException {
		List<String> list = new ArrayList<>();
		String ltp = AppProperties.get("ltp.addr");
		QueryNodes _nodes = LtpTokenizer.tokenize(str,ltp);

		for(SemanticNode node : _nodes){
			list.add(node.getText());
		}

		//添加分词后处理策略
		removeName_with_seg_xx(list);

		return list;
	}

	public SolrDocumentList handleInst(String query, QueryResponse qRes) throws Exception {
		SolrDocumentList docs = handle(query,qRes.getResults(),true);
		SolrDocumentList docsAfterHandleReplaceOrigin = setReplaceOrigin(query,docs,qRes.getHighlighting());
		return docsAfterHandleReplaceOrigin;
	}

	private SolrDocumentList setReplaceOrigin(String query,SolrDocumentList docs,Map<String, Map<String, List<String>>> highlight)
			throws IOException{
		SolrDocumentList resultDocs = new SolrDocumentList();

		SolrDocumentList qualifiedDocs = new SolrDocumentList();
		for(SolrDocument doc:docs){
			List<String> hl = highlight.get(doc.getFieldValue("id").toString()).get(highlightField);
			if(hl == null)
				continue;

			//String _query_with_no_seg = query.replace(" ","");
			//String replace_origin = getMaxLengthSubWord(_query_with_no_seg,getHitWords(hl));
			String replaceStr = getMaxLengthSubWord(query,getHitWords(hl));
			String replace_origin = replaceStr.replace(" ","");

			if(replace_origin.equals( (String) doc.get(colsField) ))
				continue;

			Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.replaceOrigin");
			double hit_ratio = (double)doc.get("hit_ratio");
			double score_proirity = Double.parseDouble(solrParam.get("solr.score.proirity"));

			double len_ratio = (double)replace_origin.length() / (double)query.length();
			double len_proirity = Double.parseDouble(solrParam.get("solr.length.proirity"));

			double weight = hit_ratio * score_proirity + len_ratio * len_proirity;

			double standard = Double.parseDouble(solrParam.get("solr.standard"));

			//筛选符合标准的数据
			if(weight >= standard){
				doc.addField(WEIGHT,weight);
				doc.addField(REPLACE_ORIGIN,replace_origin);
				doc.addField(REPLACE_ORIGIN_WITH_SEG,replaceStr);
				qualifiedDocs.add(doc);
			}

		}//end for doc : docs
		//筛选最高分的组
		HashMap<String,Double> tmpList = new HashMap<>();

		HashSet<String> replaceStr = new HashSet<>();

		double maxScore = 0;
		double avgScore = 0;

		//算最高分
		for(SolrDocument doc : qualifiedDocs){
			double score = (double) doc.get(WEIGHT);
			if (score > maxScore)
				maxScore = score;
		}

		//算出哪个replace origin的权值是最大的
		for(SolrDocument doc : qualifiedDocs){
			if(maxScore == (double)doc.get(WEIGHT)){
				replaceStr.add((String) doc.get(REPLACE_ORIGIN));
			}
		}

		//得到最长的replace origin
		String maxLenStr = "";
		for(String str : replaceStr){
			if (str.length() > maxLenStr.length() )
				maxLenStr = str;
		}

		//将符合最终要求的同一replace origin的docs加入结果集合
		for(SolrDocument doc : qualifiedDocs){
			if(maxLenStr.equals( (String) doc.get(REPLACE_ORIGIN) )){
				resultDocs.add(doc);
			}
		}

		//替换字符串需要到结尾,并设置一个阈值,如果将要替换的词占比较低时则过滤不提示
		Iterator<SolrDocument> iter = resultDocs.listIterator();
		while (iter.hasNext()){
			SolrDocument doc = iter.next();
			if(!appendRelaceWordToEnd(doc,query))
				iter.remove();
		}

		return resultDocs;
	}

	private List<String> getHitWords(List<String> highlights){
		Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.tag");
		String leftTag = solrParam.get("leftTag");
		String rightTag = solrParam.get("rightTag");

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
			//如果后面的词排在了前面,暂时认为第一个词是最后最先匹配的词
			if(_st <= _end)
				_ret = query.substring(_st,_end+_lastWord.length());
		}
		return _ret;
	}

	private boolean appendRelaceWordToEnd(SolrDocument doc,String query){
		boolean _ret = false;
		String replace_with_seg = doc.getFieldValue(REPLACE_ORIGIN_WITH_SEG).toString();
		int _st = query.indexOf(replace_with_seg);
		String replaceWordsToEnd = query.substring(_st).replace(" ","");
		String replaceWordsToEnd_with_seg = "";
		if((_st+replace_with_seg.length()) != query.length())
			replaceWordsToEnd_with_seg = query.substring(_st+replace_with_seg.length()+1);
		String[] _list = replaceWordsToEnd_with_seg.split(" ");
		double _weight = (double)1/(double)(_list.length+1);
		double _conf_weight = Double.parseDouble(AppProperties.getWithDefault ("solr.trimWeight",DEFAULT_TRIM_WEIGHT));
		if(_weight >= _conf_weight){
			doc.setField(REPLACE_ORIGIN,replaceWordsToEnd);
			_ret = true;
		}
		return _ret;
	}

	//XX 被分词了，所以针对人名去除分词，但是人名xx的提示将不会出来
	private void removeName_with_seg_xx(List<String> segList){
		//脱敏后的人名都按 XX 来处理,不存在 X
		String _needRemoveFlag = "XX";
		int _idx = -1;
		for(String word: segList){
			if(_needRemoveFlag.equals(word)){
				_idx = segList.indexOf(word);
			}
		}
		if(_idx > -1 && segList.size() > 1){
			//如果XX是首个词，则连接第二个词
			if(_idx == 0){
				String _secondWord = _needRemoveFlag+segList.get(1);
				segList.set(1,_secondWord);
			}else{
				String _prevWord = segList.get(_idx-1)+_needRemoveFlag;
				segList.set(_idx-1,_prevWord);
			}
			segList.remove(_needRemoveFlag);
		}
	}



}