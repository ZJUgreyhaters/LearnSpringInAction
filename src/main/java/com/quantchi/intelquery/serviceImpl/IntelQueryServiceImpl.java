package com.quantchi.intelquery.serviceImpl;

import com.quantchi.intelquery.mapper.IntelQueryMapper;
import com.quantchi.intelquery.service.IntelQueryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/8/17.
 */
@Service
public class IntelQueryServiceImpl implements IntelQueryService {

  @Autowired
  private IntelQueryMapper intelQueryMapper;

  @Override
  public List<Map<String, Object>> getBusiCate() {
    return intelQueryMapper.getBusiCate();
  }

  @Override
  public List<Map<String, Object>> getRecommendQuery() {
    return intelQueryMapper.getRecommendQuery();
  }
}
