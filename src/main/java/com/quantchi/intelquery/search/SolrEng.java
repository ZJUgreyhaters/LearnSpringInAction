package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;


public class SolrEng extends SearchEng {

  private final Map<String, String> solrCommParam = AppProperties.getPropertiesMap("solr.param");
  private final Map<String, String> solrQuickParam = AppProperties
      .getPropertiesMap("solr.instance");

  private final String solrUrl = AppProperties.get("solr.url");

  private static final String SEARCHFILED = AppProperties.getWithDefault("searchField", "seg_name");

  private static final String highlightField = AppProperties
      .getWithDefault("highlightField", "seg_name");
  private static final String colsField = AppProperties.getWithDefault("colsField", "cn_name");

  private static final String WEIGHT = "weight";
  private static final String REPLACE_ORIGIN = "replace_origin";
  private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";

  private HttpSolrClient httpSolr = null;

  public SolrEng(String query, String type) {
    super(query, type);
    httpSolr = new HttpSolrClient.Builder(solrUrl).build();
    httpSolr.setParser(new XMLResponseParser());
  }

  @Override
  public List<Object> getMetrics() throws Exception {
    QueryResponse qr = searchSolr(solrCommParam);
    return documentListToObjectList(processDocs(qr, false));
  }

  @Override
  public List<Object> getQuickMacro() throws Exception {
    QueryResponse qr = searchSolr(solrQuickParam);
    SolrDocumentList solrDocuments = processDocs(qr, true);
    return documentListToObjectList(setReplaceOrigin(solrDocuments, qr.getHighlighting()));
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
    //return result;
    return null;
  }

  private QueryResponse searchSolr(Map<String, String> param) throws Exception {

    String str = getQuery();
    SolrQuery query = new SolrQuery();
    Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.search");
    query.setQuery(SEARCHFILED + ":(" + str + ")");

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


}
