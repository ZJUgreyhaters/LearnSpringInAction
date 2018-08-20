package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolrEng extends SearchEng{

  private final Map<String,String> solrCommParam = AppProperties.getPropertiesMap("solr.param");
  private final	Map<String,String> solrQuickParam = AppProperties.getPropertiesMap("solr.instance");

  private static final String SEARCHFILED = AppProperties.getWithDefault("searchField","seg_name");

  @Autowired
	private HttpSolrClient httpSolr;

  public SolrEng(String query,String type){
	  super(query,type);
	}

	@Override
	public List<Object> getMetrics() throws Exception {
    QueryResponse qr =  searchSolr(solrCommParam);
		return processDocs(qr,false);
	}

  @Override
  public List<Object> getQuickMacro()throws Exception{
			QueryResponse qr =  searchSolr(solrQuickParam);
			return processDocs(qr,true);
	}

  private List<Object> processDocs(QueryResponse qr,boolean filterRepeat) throws IOException, QPException {
			SolrDocumentList result = new SolrDocumentList();
			Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.handle");
			double threshold =Double.parseDouble(solrParam.get("threshold"));   //阈值

			//对每个doc做处理
			for(SolrDocument doc : qr.getResults()){

					//如果查询的内容和seg_name一样，略过
					if(filterRepeat && getQuery().equals((String)doc.get(SEARCHFILED)))
							continue;

					String seg_name = (String) doc.get(SEARCHFILED);
					List<String> queryWord= segment();
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
			//return result;
			return null;
	}

	private QueryResponse searchSolr(Map<String,String> param) throws Exception{

			String str = getQuery();
			SolrQuery query = new SolrQuery();
			Map<String,String> solrParam = AppProperties.getPropertiesMap("solr.search");
			query.setQuery(SEARCHFILED+":("+str+")");

			if(param != null){
					for(Map.Entry<String, String> entry:param.entrySet()){
							query.setParam(entry.getKey(),entry.getValue());
					}
			}
			Set<String> keys = solrParam.keySet();
			for(String key:keys){
					query.setParam(key, solrParam.get(key));
			}

			QueryResponse response = httpSolr.query(query);
			return response;
	}

}
