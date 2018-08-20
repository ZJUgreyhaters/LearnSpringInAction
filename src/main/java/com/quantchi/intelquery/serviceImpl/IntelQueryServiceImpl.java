package com.quantchi.intelquery.serviceImpl;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.QueryNodes;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.intelquery.mapper.IntelQueryMapper;
import com.quantchi.intelquery.node.SemanticNode;
import com.quantchi.intelquery.service.IntelQueryService;
import com.quantchi.intelquery.tokenize.LtpTokenizer;
import com.quantchi.transport.service.SearchApiService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/8/17.
 */
@Service
public class IntelQueryServiceImpl implements IntelQueryService {
  private static final Logger logger = LoggerFactory.getLogger(SearchApiService.class);

  private static final String searchField = AppProperties.getWithDefault("searchField","seg_name");
  private static final String highlightField = AppProperties.getWithDefault("highlightField","seg_name");
  private static final String colsField = AppProperties.getWithDefault("colsField","cn_name");

  private static final String WEIGHT = "weight";
  private static final String REPLACE_ORIGIN = "replace_origin";
  private static final String REPLACE_ORIGIN_WITH_SEG = "replace_origin_seg";
  private static final String DEFAULT_TRIM_WEIGHT = "0.5";

  @Autowired
  private IntelQueryMapper intelQueryMapper;

  @Autowired
  private HttpSolrClient httpSolr;

  @Override
  public List<Map<String, Object>> getBusiCate() {
    return intelQueryMapper.getBusiCate();
  }

  @Override
  public List<Map<String, Object>> getRecommendQuery(String businessTypeId) {
    return intelQueryMapper.getRecommendQuery(businessTypeId);
  }


}