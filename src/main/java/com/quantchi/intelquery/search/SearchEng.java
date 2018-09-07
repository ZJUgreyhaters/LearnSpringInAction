package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.pojo.QuerySentence;
import com.quantchi.intelquery.query.QueryNodes;
import com.quantchi.intelquery.tokenize.LtpTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SearchEng {

  private final String type;
  private String query;

  public String getType() {
    return type;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  protected SearchEng(String query, String type) {
    this.type = type;
    this.query = query;
  }

  protected void init(String query){
    this.query = query;
  }

  public static SearchEng instanceOf(String query, String type) throws Exception {
    SearchEng obj = null;
    switch (type) {
      case "solr":
        obj = SolrEng.getInstance(query, type);
        break;
      default:
        throw new Exception("not found type:" + type);
    }

    return obj;
  }

  public abstract List<Object> getMetrics(Map<String,String> queryMap) throws Exception;
  public abstract List<Object> getQuickMacro() throws Exception;
  public abstract String addQuerySentence(QuerySentence qs) throws Exception;
  public abstract List<QuerySentence> getCorrelativeSentence() throws Exception;

  protected List<String> segment() throws QPException, IOException {
		return segmentWithLTP(getQuery());
  }

	protected List<String> segmentWithLTP(String query) throws QPException, IOException {
		List<String> list = new ArrayList<>();
		String ltp = AppProperties.get("ltp.addr");
		QueryNodes _nodes = LtpTokenizer.tokenize(query, ltp);

		for (SemanticNode node : _nodes) {
			list.add(node.getText());
		}

		//添加分词后处理策略
		removeName_with_seg_xx(list);

		return list;
	}

  //XX 被分词了，所以针对人名去除分词，但是人名xx的提示将不会出来
  private void removeName_with_seg_xx(List<String> segList) {
    //脱敏后的人名都按 XX 来处理,不存在 X
    String _needRemoveFlag = "XX";
    int _idx = -1;
    for (String word : segList) {
      if (_needRemoveFlag.equals(word)) {
        _idx = segList.indexOf(word);
      }
    }
    if (_idx > -1 && segList.size() > 1) {
      //如果XX是首个词，则连接第二个词
      if (_idx == 0) {
        String _secondWord = _needRemoveFlag + segList.get(1);
        segList.set(1, _secondWord);
      } else {
        String _prevWord = segList.get(_idx - 1) + _needRemoveFlag;
        segList.set(_idx - 1, _prevWord);
      }
      segList.remove(_needRemoveFlag);
    }
  }

  protected List<String> getHitWords(List<String> highlights) {
    Map<String, String> solrParam = AppProperties.getPropertiesMap("solr.tag");
    String leftTag = solrParam.get("leftTag");
    String rightTag = solrParam.get("rightTag");

    List<String> hits = new ArrayList<>();
    String _hl = highlights.get(0).toString();
    String _hit = "";
    int _start = _hl.indexOf(leftTag);
    int _end = _hl.indexOf(rightTag);

    while (_start != -1 && _end != -1) {
      _hit = _hl.substring(_start + 4, _end);
      hits.add(_hit);
      _hl = _hl.substring(_end + 1);
      _start = _hl.indexOf(leftTag);
      _end = _hl.indexOf(rightTag);
    }

    return hits;
  }

  protected String getMaxLengthSubWord(String query, List<String> hitWords) {
    String _firstWord = hitWords.get(0);
    String _ret = _firstWord;
    if (hitWords.size() > 1) {
      String _lastWord = hitWords.get(hitWords.size() - 1);
      int _st = query.indexOf(_firstWord);
      int _end = query.lastIndexOf(_lastWord);
      //如果后面的词排在了前面,暂时认为第一个词是最后最先匹配的词
      if (_st <= _end) {
        _ret = query.substring(_st, _end + _lastWord.length());
      }
    }
    return _ret;
  }


}
