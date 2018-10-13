package com.quantchi.termInfo.serviceImpl;

import com.quantchi.termInfo.mapper.TermFileMapper;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/7/26.
 */
@Service
public class TermFileServiceImpl implements TermFileService {

  @Autowired
  private TermFileMapper termFileMapper;

  @Override
  public void insertStandard(Map<String, Object> map) {
    termFileMapper.insertStandard(map);
  }

  @Override
  public List<Map<String, Object>> selectStandard(Map<String, Object> map) {
    return termFileMapper.selectStandard(map);
  }

  @Override
  public void updateStandard(Map<String, Object> map) {
    termFileMapper.updateStandard(map);
  }

  @Override
  public void updatePhysicalFieldChineseName(Map<String, Object> map) {
    termFileMapper.updatePhysicalFieldChineseName(map);
  }

  @Override
  public List<Map<String, Object>> selectPhysicalInfo(Map<String, Object> map) {
    return termFileMapper.selectPhysicalInfo(map);
  }

  @Override
  public void insertPhysicalFile(Map<String, Object> map) {
    termFileMapper.insertPhysicalFile(map);
  }

  @Override
  public void updatePhysicalFile(Map<String, Object> map) {
    termFileMapper.updatePhysicalFile(map);
  }

  @Override
  public List<Map<String, Object>> selectPhysicalFile(Map<String, Object> map) {
    return termFileMapper.selectPhysicalFile(map);
  }

  @Override
  public Map<String, Object> selectDomainByName(Map<String, Object> map) {
    return termFileMapper.selectDomainByName(map);
  }

  @Override
  public Map<String, Object> selectStandardCategory(Map<String, Object> map) {
    return termFileMapper.selectStandardCategory(map);
  }

  @Override
  public List<Map<String, Object>> selectStandardMain(Map<String, Object> map) {
    return termFileMapper.selectStandardMain(map);
  }

  @Override
  public void insertStandardMain(Map<String, Object> map) {
    termFileMapper.insertStandardMain(map);
  }

  @Override
  public void updateStandardMain(Map<String, Object> map) {
    termFileMapper.updateStandardMain(map);
  }

  @Override
  public List<Map<String, Object>> selectTargetMain(Map<String, Object> map) {
    return termFileMapper.selectTargetMain(map);
  }

  @Override
  public void insertTargetMain(Map<String, Object> map) {
    termFileMapper.insertTargetMain(map);
  }

  @Override
  public void updateTargetMain(Map<String, Object> map) {
    termFileMapper.updateTargetMain(map);
  }

  @Override
  public void insertOperation(Map<String, Object> map) {
    termFileMapper.insertOperation(map);
  }

  @Override
  public Map<String, Object> selectDominById(Map<String, Object> map) {
    String entityCategory = map.get("entityCategory").toString();
    String[] split = entityCategory.split("--");
    Map<String, Object> dominMap = new HashMap();
    dominMap.put("business_type_id",split[0]);
    dominMap.put("domain_id",split[1]);
    dominMap.put("logic_table_id",split[2]);
    return termFileMapper.selectDominById(dominMap);
  }
}
