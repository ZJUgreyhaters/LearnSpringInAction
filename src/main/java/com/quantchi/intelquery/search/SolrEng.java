package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.quantchi.intelquery.pojo.QuerySentence;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SolrEng extends SearchEng {

  private static final Logger logger = LoggerFactory.getLogger(SolrEng.class);

  private final Map<String, String> solrCommParam = AppProperties.getPropertiesMap("solr.param");
  private final Map<String, String> solrQuickParam = AppProperties
      .getPropertiesMap("solr.instance");

  private final String solrUrl = AppProperties.get("solr.url");

  private static final String SEARCHFILED = AppProperties.getWithDefault("searchField", "seg_name");
  private static final String CHECKDOCFILED = AppProperties.getWithDefault("checkDocField", "query");

  private static final String highlightField = AppProperties
      .getWithDefault("highlightField", "seg_name");
  private static final String colsField = AppProperties.getWithDefault("colsField", "cn_name");

  private static final String WEIGHT = "weight";
  private static final String REPLACE_ORIGIN = "replace_origin";
  private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";
  private static final String DEFAULT_TRIM_WEIGHT = "0.5";

  private HttpSolrClient httpSolr = null;

  public SolrEng(String query, String type) {
    super(query, type);
    httpSolr = new HttpSolrClient.Builder(solrUrl).build();
    httpSolr.setParser(new XMLResponseParser());
  }

  @Override
  public List<Object> getMetrics() throws Exception {
    QueryResponse qr = searchSolr(solrCommParam,SEARCHFILED);
    return documentListToObjectList(processDocs(qr, false));
  }

  @Override
  public List<Object> getQuickMacro() throws Exception {
    QueryResponse qr = searchSolr(solrQuickParam,SEARCHFILED);
    SolrDocumentList solrDocuments = processDocs(qr, true);
    return documentListToObjectList(setReplaceOrigin(solrDocuments, qr.getHighlighting()));
  }

  @Override
  public String addQuerySentence(QuerySentence qs){
    String qsId = "";
    try {
      QuerySentence solrRet = checkDocInSolr(qs.getQuery());
      //add doc into solr
      if(solrRet == null){
				/*segmentWithLTP(qs.getQuery())*/
        httpSolr.addBean(qs);
        httpSolr.commit();
        qsId = qs.getId();
      }
      //update count
      else{
        SolrInputDocument newDoc = new SolrInputDocument();
        newDoc.addField("id", solrRet.getId());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("inc", 1);
        newDoc.addField("count", map);
        httpSolr.add(newDoc);
        httpSolr.commit();
        qsId = solrRet.getId();
      }


    }catch (SolrServerException serverEx){
      logger.error("solr add doc :{} error,and error info is: {}",qs.getQuery(),serverEx.getMessage());
    }catch (IOException ioEx){
      logger.error("solr commit doc :{} error,and error info is: {}",qs.getQuery(),ioEx.getMessage());
    } catch (Exception ex) {
      logger.error("solr check doc :{} error,and error info is: {}",qs.getQuery(),ex.getMessage());
    }
    return qsId;
  }

  @Override
  public List<Object> getCorrelativeSentence() throws Exception{
    QueryResponse qr = searchSolr(solrCommParam,CHECKDOCFILED);
    return documentListToObjectList(qr.getResults());
  }

  private QuerySentence checkDocInSolr(String queryStr) throws Exception {
    QuerySentence qs = null;
    SolrQuery query = new SolrQuery();
    query.setQuery(CHECKDOCFILED + ":(" + queryStr + ")");
    QueryResponse qr = httpSolr.query(query);
    if(qr.getResults().size() > 0){
      qs = qr.getBeans(QuerySentence.class).get(0);
    }
    return qs;
  }

  private SolrDocumentList processDocs(QueryResponse qr, boolean filterRepeat)
      throws IOException, QPException {
    SolrDocumentList result = new SolrDocumentList();
    Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.handle");
    double threshold = Double.parseDouble(solrParam.get("threshold"));   //阈值

    //对每个doc做处理
    for (SolrDocument doc : qr.getResults()) {

      //如果查询的内容和seg_name一样，略过
      if (filterRepeat && getQuery().equals((String) doc.get(SEARCHFILED))) {
        continue;
      }

      String seg_name = (String) doc.get(SEARCHFILED);
      List<String> queryWord = segment();
      Set<String> queryWords = new HashSet<>();

      for (String word : queryWord) {
        queryWords.add(word);
      }
      List<String> nameWords = java.util.Arrays.asList(seg_name.split(" "));

      double nameWordNum = nameWords.size(); //中文名的单词数
      double matchNum = 0; //匹配到的数量

      for (String word : queryWords) {
        if (nameWords.contains(word)) {
          matchNum++;
        }
      }

      double ratio = matchNum / nameWordNum;

      //如果比例大于等于阈值，添加到结果集合中
      if (ratio >= threshold) {
        doc.addField("hit_ratio", ratio);
        result.add(doc);
      }
    }
    return result;
  }

  private QueryResponse searchSolr(Map<String, String> param,String field) throws Exception {
    String str = String.join(" ",segment());
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

}
