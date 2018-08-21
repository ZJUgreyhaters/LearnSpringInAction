package com.quantchi.intelquery.search;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.QueryNodes;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.tokenize.LtpTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public abstract class SearchEng {

  private static final String REPLACE_ORIGIN = "replace_origin";
  private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";
  private static final String DEFAULT_TRIM_WEIGHT = "0.5";
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

  public SearchEng(String query, String type) {
    this.type = type;
    this.query = query;
  }

  public static SearchEng instanceOf(String query, String type) throws Exception {
    SearchEng obj = null;
    switch (type) {
      case "solr":
        obj = new SolrEng(query, type);
        break;
      default:
        throw new Exception("not found type:" + type);
    }

    return obj;
  }

  public abstract List<Object> getMetrics() throws Exception;
  public abstract List<Object> getQuickMacro() throws Exception;

  protected List<String> segment() throws QPException, IOException {
    List<String> list = new ArrayList<>();
    String ltp = AppProperties.get("ltp.addr");
    QueryNodes _nodes = LtpTokenizer.tokenize(getQuery(), ltp);

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
