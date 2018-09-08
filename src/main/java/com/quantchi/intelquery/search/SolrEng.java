package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import java.io.IOException;
import java.util.*;

import com.quantchi.intelquery.pojo.QuerySentence;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SolrEng extends SearchEng {

  private static final Logger logger = LoggerFactory.getLogger(SolrEng.class);

  private static SolrEng instance = null;

  private final Map<String, String> solrCommParam = AppProperties.getPropertiesMap("solr.param");
  private final Map<String, String> solrQuickParam = AppProperties
      .getPropertiesMap("solr.instance");

  private final Map<String, String> solrCorSentenceParam = AppProperties
      .getPropertiesMap("solr.sentence");

  private final String solrUrl = AppProperties.get("solr.url");

  //private static final String SEARCHFILED = AppProperties.getWithDefault("searchField", "seg_name");
  private static final String SEARCHFILED = AppProperties.getWithDefault("searchField", "cn_name");
  private static final String SEARCHDOCFILED = AppProperties.getWithDefault("searchDocField", "query");

  private static final String highlightField = AppProperties
      .getWithDefault("highlightField", "cn_name");
  private static final String colsField = AppProperties.getWithDefault("colsField", "cn_name");

  private static final String WEIGHT = "weight";
  private static final String REPLACE_ORIGIN = "replace_origin";
  private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";
  private static final String DEFAULT_TRIM_WEIGHT = "0.5";

  private HttpSolrClient httpSolr = null;

  private SolrEng(String query, String type) {
    super(query, type);
    httpSolr = new HttpSolrClient.Builder(solrUrl).build();
    httpSolr.setParser(new XMLResponseParser());
  }

  public static SolrEng getInstance(String query, String type) {
    if (instance == null) {
      instance = new SolrEng(query, type);
    } else {
      instance.init(query);
    }

    return instance;
  }


  @Override
  public List<Object> getMetrics(Map<String, String> queryMap) throws Exception {
    QueryResponse qr = searchSolrWithoutSeg(getQuery(),solrCommParam,queryMap);
		Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.handle");
		double threshold = Double.parseDouble(solrParam.get("threshold"));
    return documentListToObjectList(processDocs(qr, false,threshold));
  }

  @Override
  public List<Object> getQuickMacro() throws Exception {
    QueryResponse qr = searchSolr(solrQuickParam,SEARCHFILED);
    //键盘精灵里因为分词原因调低阈值
    SolrDocumentList solrDocuments = processDocs(qr, true,0.2);
    return documentListToObjectList(setReplaceOrigin(solrDocuments, qr.getHighlighting()));
  }

  @Override
  public String addQuerySentence(QuerySentence qs) {
    String qsId = "";
    try {
      QuerySentence solrRet = checkDocInSolr(qs.getQuery());
      //add doc into solr
      if (solrRet == null) {
        httpSolr.addBean(qs);
        httpSolr.commit();
        qsId = qs.getId();
      }
      //update count
      else {
        SolrInputDocument newDoc = new SolrInputDocument();
        newDoc.addField("id", solrRet.getId());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("inc", 1);
        newDoc.addField("times", map);
        if ("0".equals(qs.getType())) {
          newDoc.addField("disfavor", map);
        } else {
          newDoc.addField("favor", map);
        }
        httpSolr.add(newDoc);
        httpSolr.commit();
        qsId = solrRet.getId();
      }

    } catch (SolrServerException serverEx) {
      logger.error("solr add doc :{} error,and error info is: {}", qs.getQuery(),
          serverEx.getMessage());
    } catch (IOException ioEx) {
      logger.error("solr commit doc :{} error,and error info is: {}", qs.getQuery(),
          ioEx.getMessage());
    } catch (Exception ex) {
      logger
          .error("solr check doc :{} error,and error info is: {}", qs.getQuery(), ex.getMessage());
    }
    return qsId;
  }

  @Override
  public void addQueryLikeSentence(QuerySentence qs) {
    try {
      QuerySentence solrRet = checkDocInSolrById(qs.getId());
      //add doc into solr
      if (solrRet == null) {
        httpSolr.addBean(qs);
        httpSolr.commit();
      }
      //update count
      else {
        SolrInputDocument newDoc = new SolrInputDocument();
        newDoc.addField("id", solrRet.getId());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("inc", 1);
        newDoc.addField("times", map);
        if ("0".equals(qs.getType())) {
          newDoc.addField("disfavor", map);
        } else {
          newDoc.addField("favor", map);
        }
        httpSolr.add(newDoc);
        httpSolr.commit();
      }

    } catch (SolrServerException serverEx) {
      logger.error("solr add doc :{} error,and error info is: {}", qs.getQuery(),
          serverEx.getMessage());
    } catch (IOException ioEx) {
      logger.error("solr commit doc :{} error,and error info is: {}", qs.getQuery(),
          ioEx.getMessage());
    } catch (Exception ex) {
      logger
          .error("solr check doc :{} error,and error info is: {}", qs.getQuery(), ex.getMessage());
    }
  }

  @Override
  public List<QuerySentence> getCorrelativeSentence() throws Exception {
    QueryResponse qr = searchSolrWithoutSeg(getQuery(), solrCorSentenceParam, SEARCHDOCFILED);
    return qr.getBeans(QuerySentence.class);
  }

  private QuerySentence checkDocInSolr(String queryStr) throws Exception {
    QuerySentence qs = null;
    SolrQuery query = new SolrQuery();
    query.setQuery(SEARCHDOCFILED + ":\"" + queryStr + "\"");
    QueryResponse qr = httpSolr.query(query);
    if (qr.getResults().size() > 0) {
      qs = qr.getBeans(QuerySentence.class).get(0);
    }
    return qs;
  }

  private QuerySentence checkDocInSolrById(String id) throws Exception {
    QuerySentence qs = null;
    SolrQuery query = new SolrQuery();
    query.setQuery("id" + ":\"" + id + "\"");
    QueryResponse qr = httpSolr.query(query);
    if (qr.getResults().size() > 0) {
      qs = qr.getBeans(QuerySentence.class).get(0);
    }
    return qs;
  }

  private SolrDocumentList processDocs(QueryResponse qr, boolean filterRepeat,double threshold)
      throws IOException, QPException {
    SolrDocumentList result = new SolrDocumentList();

    //对每个doc做处理
    for (SolrDocument doc : qr.getResults()) {

      //如果查询的内容和seg_name一样，略过
      if (filterRepeat && getQuery().equals((String) doc.get(SEARCHFILED))) {
        continue;
      }

			double ratio = getRatioTmp(doc,qr);

      //如果比例大于等于阈值，添加到结果集合中
      if (ratio >= threshold) {
        doc.addField("hit_ratio", ratio);
        result.add(doc);
      }
    }
    return result;
  }

  private double getRatio(SolrDocument doc,QueryResponse qr) throws IOException, QPException {

		//默认id 先写死
		String id = (String) doc.get("id");

		//String seg_name = id;
		List<String> queryWord = segment();
		Set<String> queryWords = new HashSet<>();

		for (String word : queryWord) {
			queryWords.add(word);
		}
		//List<String> nameWords = java.util.Arrays.asList(seg_name.split(" "));
		List<String> nameWords = getHitWords(qr.getHighlighting().get(id).get(SEARCHFILED));

		double nameWordNum = nameWords.size(); //中文名的单词数
		double matchNum = 0; //匹配到的数量

		for (String word : queryWords) {
			if (nameWords.contains(word)) {
				matchNum++;
			}
		}

		return matchNum / nameWordNum;

	}

	//only word count to get ratio
	private double getRatioTmp(SolrDocument doc,QueryResponse qr) throws IOException, QPException {

		//默认id 先写死
		String id = (String) doc.get("id");
		//String query = (String) doc.get(SEARCHFILED);
    String query = getQuery();
		List<String> nameWords = getHitWords(qr.getHighlighting().get(id).get(SEARCHFILED));

		double nameWordNum = query.length(); //中文字数
		double matchNum = 0; //匹配字数

		for (String word : nameWords) {
			matchNum+=word.length();
		}

		return matchNum / nameWordNum;

	}

  private QueryResponse searchSolrWithoutSeg(String str, Map<String, String> param, String field)
      throws Exception {
    SolrQuery query = new SolrQuery();
    Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.search");
    query.setQuery(field + ":(" + str + ")");

    if (param != null) {
      for (Map.Entry<String, String> entry : param.entrySet()) {
        query.setParam(entry.getKey(), entry.getValue());
      }
    }
    Set<String> keys = solrParam.keySet();
    for (String key : keys) {
      query.setParam(key, solrParam.get(key));
    }

    QueryResponse response = httpSolr.query(query);
    return response;
  }

  private QueryResponse searchSolrWithoutSeg(String str, Map<String, String> param,
      Map<String, String> mapQuery) throws Exception {
    SolrQuery query = new SolrQuery();
    Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.search");
    StringBuilder builderQuery = new StringBuilder();
    builderQuery.append("cn_name:").append(str);
    builderQuery.append(" OR ").append("definition").append(":").append(str);
    builderQuery.append(" AND ").append("businessId").append(":")
        .append(mapQuery.get("businessId"));
    query.setQuery(builderQuery.toString());

    if (param != null) {
      for (Map.Entry<String, String> entry : param.entrySet()) {
        query.setParam(entry.getKey(), entry.getValue());
      }
    }
    Set<String> keys = solrParam.keySet();
    for (String key : keys) {
      query.setParam(key, solrParam.get(key));
    }

    QueryResponse response = httpSolr.query(query);
    return response;
  }

  private QueryResponse searchSolr(Map<String, String> param, String filed)
      throws Exception {
    String str = String.join(" ", segment());
    return searchSolrWithoutSeg(str, param, filed);
  }


  private QueryResponse searchSolr(Map<String, String> param, Map<String, String> queryMap)
      throws Exception {
    String str = String.join(" ", segment());
    return searchSolrWithoutSeg(str, param, queryMap);
  }

  private SolrDocumentList setReplaceOrigin(SolrDocumentList docs,
      Map<String, Map<String, List<String>>> highlight)
      throws IOException {
    SolrDocumentList resultDocs = new SolrDocumentList();

    SolrDocumentList qualifiedDocs = new SolrDocumentList();
    for (SolrDocument doc : docs) {
      List<String> hl = highlight.get(doc.getFieldValue("id").toString()).get(highlightField);
      if (hl == null) {
        continue;
      }

      //String _query_with_no_seg = query.replace(" ","");
      //String replace_origin = getMaxLengthSubWord(_query_with_no_seg,getHitWords(hl));
      String replaceStr = getMaxLengthSubWord(getQuery(), getHitWords(hl));
      String replace_origin = replaceStr.replace(" ", "");

      if (replace_origin.equals((String) doc.get(colsField))) {
        continue;
      }

      Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.replaceOrigin");
      double hit_ratio = (double) doc.get("hit_ratio");
      double score_proirity = Double.parseDouble(solrParam.get("solr.score.proirity"));

      double len_ratio = (double) replace_origin.length() / (double) getQuery().length();
      double len_proirity = Double.parseDouble(solrParam.get("solr.length.proirity"));

      double weight = hit_ratio * score_proirity + len_ratio * len_proirity;

      double standard = Double.parseDouble(solrParam.get("solr.standard"));

      //筛选符合标准的数据
      if (weight >= standard) {
        doc.addField(WEIGHT, weight);
        doc.addField(REPLACE_ORIGIN, replace_origin);
        doc.addField(REPLACE_ORIGIN_WITH_SEG, replaceStr);
        qualifiedDocs.add(doc);
      }

    }//end for doc : docs
    //筛选最高分的组
    HashMap<String, Double> tmpList = new HashMap<>();

    HashSet<String> replaceStr = new HashSet<>();

    double maxScore = 0;
    double avgScore = 0;

    //算最高分
    for (SolrDocument doc : qualifiedDocs) {
      double score = (double) doc.get(WEIGHT);
      if (score > maxScore) {
        maxScore = score;
      }
    }

    //算出哪个replace origin的权值是最大的
    for (SolrDocument doc : qualifiedDocs) {
      if (maxScore == (double) doc.get(WEIGHT)) {
        replaceStr.add((String) doc.get(REPLACE_ORIGIN));
      }
    }

    //得到最长的replace origin
    String maxLenStr = "";
    for (String str : replaceStr) {
      if (str.length() > maxLenStr.length()) {
        maxLenStr = str;
      }
    }

    //将符合最终要求的同一replace origin的docs加入结果集合
    for (SolrDocument doc : qualifiedDocs) {
      if (maxLenStr.equals((String) doc.get(REPLACE_ORIGIN))) {
        resultDocs.add(doc);
      }
    }

    //替换字符串需要到结尾,并设置一个阈值,如果将要替换的词占比较低时则过滤不提示
    Iterator<SolrDocument> iter = resultDocs.listIterator();
    while (iter.hasNext()) {
      SolrDocument doc = iter.next();
      if (!appendRelaceWordToEnd(doc, getQuery())) {
        iter.remove();
      }
    }

    return resultDocs;
  }

  protected boolean appendRelaceWordToEnd(SolrDocument doc, String query) {
    boolean _ret = false;
    String replace_with_seg = doc.getFieldValue(REPLACE_ORIGIN_WITH_SEG).toString();
    int _st = query.indexOf(replace_with_seg);
    String replaceWordsToEnd = query.substring(_st).replace(" ", "");
    String replaceWordsToEnd_with_seg = "";
    if ((_st + replace_with_seg.length()) != query.length()) {
      replaceWordsToEnd_with_seg = query.substring(_st + replace_with_seg.length() + 1);
    }
    String[] _list = replaceWordsToEnd_with_seg.split(" ");
    double _weight = (double) 1 / (double) (_list.length + 1);
    double _conf_weight = Double
        .parseDouble(AppProperties.getWithDefault("solr.trimWeight", DEFAULT_TRIM_WEIGHT));
    if (_weight >= _conf_weight) {
      doc.setField(REPLACE_ORIGIN, replaceWordsToEnd);
      _ret = true;
    }
    return _ret;
  }

  List<Object> documentListToObjectList(SolrDocumentList solrDocumentList) {
    List<Object> documentList = new ArrayList<>();
    for (SolrDocument doc : solrDocumentList) {
      documentList.add(doc);
    }
    return documentList;
  }


  public int metricsImport(){
    int affectnum = 0;
    SolrQuery startQuery = new SolrQuery();
    startQuery.setRequestHandler("/dataimport");
    startQuery.set("command", "full-import");
    startQuery.set("verbose", "false");
    startQuery.set("clean", "false");
    startQuery.set("commit", "true");
    startQuery.set("core", "dmp");
    startQuery.set("entity", "metrics");
    startQuery.set("name", "dataimport");

    try{
      QueryResponse response = httpSolr.query(startQuery);
      affectnum = Integer.parseInt(((SimpleOrderedMap)response.getResponse().get("statusMessages")).get("Total Documents Processed").toString());
    }
    catch (SolrServerException e){
      logger.error("solr server exception: {}",e.getMessage());
    }
    catch (IOException e){
      logger.error("solr io exception: {}",e.getMessage());
    }
    catch (Exception e){
      logger.error("normal exception happend while import data: {} ",e.getMessage());
    }
    return affectnum;

  }

}
