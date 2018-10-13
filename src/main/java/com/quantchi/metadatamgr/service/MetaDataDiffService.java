package com.quantchi.metadatamgr.service;

import com.quantchi.common.Paging;
import com.quantchi.metadatamgr.data.mapper.MDFieldInfoDBMapper;
import com.quantchi.tianshu.common.SerializationUtils;
import com.quantchi.lineage.diff.Diff;
import com.quantchi.lineage.diff.Field;
import com.quantchi.lineage.diff.SchemaDiff;
import com.quantchi.lineage.diff.Table;
import com.quantchi.metadatamgr.data.entity.MDSchemaDiffDetail.MDSchemaDiffDetailBuilder;
import com.quantchi.metadatamgr.data.mapper.SchemaDiffMapper;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hetong.
 */
@Service
public class MetaDataDiffService {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataDiffService.class);

  @Autowired
  private MDFieldInfoDBMapper mdFieldInfoDBMapper;

  @Autowired
  private SchemaDiffMapper schemaDiffMapper;

  public Object getVersionDiff(String datasourceId, String sourceVersion, String targetVersion,
      String keyword, String operation, int page, int pageSize)
      throws IOException, ClassNotFoundException {
    if (sourceVersion.compareTo(targetVersion) > 0) {
      String temp = sourceVersion;
      sourceVersion = targetVersion;
      targetVersion = temp;
    }
    List<Map<String, Object>> rows = schemaDiffMapper
        .getVersionDiff(datasourceId, sourceVersion, targetVersion);
    Map<String, Object> startVersion = rows.remove(0);
    if (rows.size() < 1) {
      throw new IllegalArgumentException("不存在的版本或者数据源");
    }
    List<SchemaDiff> diffs = new ArrayList<>();
    for (Map<String, Object> row : rows) {
      diffs.add(SerializationUtils.fromSerializedString((String) row.get("diff")));
    }
    SchemaDiff diff = Diff.accumulateDiff(diffs);
    MDSchemaDiffDetailBuilder builder = new MDSchemaDiffDetailBuilder(diff, mdFieldInfoDBMapper);
    builder.sourceFieldAmount((Integer) startVersion.get("field_amount"))
        .targetFieldAmount((Integer) rows.get(rows.size() - 1).get("field_amount"))
        .sourceTableAmount((Integer) startVersion.get("table_amount"))
        .targetTableAmount((Integer) rows.get(rows.size() - 1).get("table_amount"))
        .keyword(keyword)
        .datasourceId(datasourceId)
        .operationFilter(operation);
    return builder.build(pageSize, page);
  }

  public Object getDatasourceDiff(String jobId, int pageSize, int page, String keyword,
      String operation) {
    List<Map<String, String>> source = schemaDiffMapper.getSourceDatasource(jobId);
    List<Map<String, String>> target = schemaDiffMapper.getTargetDatasource(jobId);
    Set<Table> sourceTables = getTableFromRow("source", source);
    Set<Table> targetTables = getTableFromRow("target", target);
    SchemaDiff diff = SchemaDiff.findDiff(sourceTables, targetTables);
    MDSchemaDiffDetailBuilder builder = new MDSchemaDiffDetailBuilder(diff, mdFieldInfoDBMapper);
    builder.sourceFieldAmount(source.size()).targetFieldAmount(target.size())
        .sourceTableAmount(sourceTables.size()).targetTableAmount(targetTables.size())
        .keyword(keyword)
        .datasourceId(source.size() == 0 ? null : source.get(0).get("datasourceId"))
        .operationFilter(operation);
    return builder.build(pageSize, page);
  }

  public Map<String, Object> getAllVersion(Map<String, Object> map) {
    List<Map<String, Object>> list = schemaDiffMapper.getAllVersion(map);
    return Paging.pagingWithTotal(list, (Integer) map.get("pageSize"), (Integer) map.get("page"));
  }

  public Map<String, Object> getAllJob(String keyword, String status, Integer pageSize,
      Integer page) {
    Map<String, Object> map = new HashMap<>(3);
    map.put("keyword", keyword);
    map.put("status", status);
    List<Map<String, Object>> list = schemaDiffMapper.getAllJob(map);
    return Paging.pagingWithTotal(list, pageSize, page);
  }

  public Object deleteJob(List<String> jobIdList) {
    int amount = 0;
    for (String jobId : jobIdList) {
      schemaDiffMapper.deleteJob(jobId);
      amount += 1;
    }
    return amount;
  }

  public Integer insertDiffJob(String jobName, String sourceId, String targetId) {
    Map<String, Object> map = new HashMap<>(5);
    map.put("jobName", jobName);
    map.put("sourceId", sourceId);
    map.put("targetId", targetId);
    schemaDiffMapper.insertDiffJob(map);
    return ((BigInteger) map.get("id")).intValue();
  }

  public Set<Table> getTableFromRow(String datasource, List<Map<String, String>> rows) {
    Map<String, Set<Field>> tableFields = new HashMap<>();
    for (Map<String, String> row : rows) {
      String tableName = row.get("table");
      Set<Field> tableField;
      if (tableFields.containsKey(tableName)) {
        tableField = tableFields.get(tableName);
      } else {
        tableField = new HashSet<>();
        tableFields.put(tableName, tableField);
      }
      if (row.get("field") != null) {
        tableField.add(new Field(row.get("field"), row.get("data_type")));
      }
    }
    return tableFields.keySet().stream()
        .map(tableName -> new Table(datasource, tableName, tableFields.get(tableName)))
        .collect(Collectors.toSet());
  }

  public Object updateSchemaDiff(Map<String, Object> map) {
    return schemaDiffMapper.updateDiff(map);
  }
}
