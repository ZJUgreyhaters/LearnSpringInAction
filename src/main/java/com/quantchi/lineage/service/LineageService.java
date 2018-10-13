package com.quantchi.lineage.service;


import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.quantchi.lineage.mapper.LineageMapper;
import com.quantchi.lineage.metric.MetricLineage;
import com.quantchi.lineage.metric.MetricLineageNode;
import com.quantchi.lineage.physical.LineageNode;
import com.quantchi.lineage.physical.PhysicalLineage;
import com.quantchi.metadatamgr.data.mapper.MDFieldInfoDBMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineageService {

  private static final Logger logger = LoggerFactory.getLogger(LineageService.class);
  private static final String FETCH_ALL_NODE_TYPE = "ALL";
  @Autowired
  private LineageMapper lineageMapper;

  @Autowired
  private MDFieldInfoDBMapper mdFieldInfoDBMapper;

  public Map<String, Object> getNodesInfo(String metricId, String type) throws Exception {
    if (FETCH_ALL_NODE_TYPE.equals(type)) {
      return metricGraph2nodes(MetricLineage.getMetricLineage(metricId), metricId);
    } else {
      return metricGraph2nodes(MetricLineage.getAdjacentMetricLineage(metricId), metricId);
    }
  }

  public Map<String, Object> getPhysicalFieldLineage(String fieldId, String type) throws Exception {
    String entityId = getFieldEntityId(fieldId);
    if (FETCH_ALL_NODE_TYPE.equals(type)) {
      return physicalGraph2nodes(PhysicalLineage.getAllLineage(entityId), entityId);
    } else {
      return physicalGraph2nodes(PhysicalLineage.getAdjacentMetricLineage(entityId), entityId);
    }
  }

  public Object getDownStream(String fieldId, int level) throws Exception {
    String entityId = getFieldEntityId(fieldId);
    return physicalGraph2nodes(PhysicalLineage.getDownstream(entityId, level), entityId);
  }

  public Object getUpStream(String fieldId, int level) throws Exception {
    String entityId = getFieldEntityId(fieldId);
    return physicalGraph2nodes(PhysicalLineage.getUpstream(entityId, level), entityId);
  }

  public Object getDownStream(String datasourceId, String fieldName, int level)
      throws Exception {
    String id = getFieldEntityId(datasourceId, fieldName);
    return physicalGraph2nodes(PhysicalLineage.getDownstream(id, level), id);
  }

  public Object getUpStream(String datasourceId, String fieldName, int level) throws Exception {
    String id = getFieldEntityId(datasourceId, fieldName);
    return physicalGraph2nodes(PhysicalLineage.getUpstream(id, level), id);
  }

  private String getFieldEntityId(String fieldId) {
    return mdFieldInfoDBMapper.selectEntityIdById(fieldId).get(0);
  }

  private String getFieldEntityId(String datasourceId, String fieldName) {
    Matcher matcher = Pattern.compile("([^.]+)\\.([^.]+)\\.([^.]+)").matcher(fieldName);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("field name must be in db.table.field format");
    }
    String dbName = matcher.group(1);
    String tableName = matcher.group(2);
    fieldName = matcher.group(3);
    return mdFieldInfoDBMapper
        .selectEntityIdByName(datasourceId, dbName, tableName, fieldName).get(0);
  }

  private Map<String, Object> physicalGraph2nodes(Graph<LineageNode> graph, String physicalId)
      throws Exception {
    Map<String, Object> _total_ret = new HashMap<>();
    _total_ret.put("nodes", physicalLineage2nodes(graph, physicalId));
    _total_ret.put("edges", physicalLineage2edges(graph));
    return _total_ret;
  }

  private Map<String, Object> metricGraph2nodes(Graph<MetricLineageNode> graph, String metricId)
      throws Exception {
    Map<String, Object> _total_ret = new HashMap<>();
    _total_ret.put("nodes", metricLineage2nodes(graph, metricId));
    _total_ret.put("edges", metricLineage2edges(graph));
    return _total_ret;
  }

  private List<Map<String, Object>> metricLineage2nodes(Graph<MetricLineageNode> graph,
      String metricId)
      throws Exception {
    List<Map<String, Object>> _lineage_nodes = new ArrayList<>();
    for (MetricLineageNode _lineage_node : graph.nodes()) {
      Map<String, Object> _node = new HashMap<>();
      _node.put("metricId", _lineage_node.getMetricId());
      _node.put("metricName", _lineage_node.getEntityDesc());
      _node.put("phsicalFieldName", _lineage_node.getLineageNode().getPhysicalField());
      _node.put("phsicalFieldDesc", _lineage_node.getLineageNode().getPhysicalFieldDesc());
      _node.put("isOpen",
          !(_lineage_node.getLineageNode().isEndNode() || _lineage_node.getLineageNode()
              .isStartNode()));
      _lineage_nodes.add(_node);
    }
    //if return empty from lineage service, set current node info in list
    if (_lineage_nodes.size() == 0) {
      _lineage_nodes.add(setInitialNode(metricId));
    }

    return _lineage_nodes;
  }

  private List<Map<String, Object>> physicalLineage2nodes(Graph<LineageNode> graph, String metricId)
      throws Exception {
    List<Map<String, Object>> _lineage_nodes = new ArrayList<>();
    for (LineageNode _lineage_node : graph.nodes()) {
      Map<String, Object> _node = new HashMap<>();
      _node.put("phsicalFieldId", mdFieldInfoDBMapper.selectIdByEntityId(_lineage_node.getPhysicalFieldId()).get(0));
      _node.put("phsicalFieldName", _lineage_node.getPhysicalField());
      _node.put("phsicalFieldDesc", _lineage_node.getPhysicalFieldDesc());
      _node.put("isOpen", !(_lineage_node.isEndNode() || _lineage_node.isStartNode()));
      _lineage_nodes.add(_node);
    }
    //if return empty from lineage service, set current node info in list
    if (_lineage_nodes.size() == 0) {
      _lineage_nodes.add(setInitialNode(metricId));
    }

    return _lineage_nodes;
  }

  private List<Map<String, Object>> metricLineage2edges(Graph<MetricLineageNode> graph) {
    List<Map<String, Object>> _lineage_edges = new ArrayList<>();
    for (EndpointPair<MetricLineageNode> _pair : graph.edges()) {
      Map<String, Object> _edge = new HashMap<>();
      _edge.put("source", _pair.source().getMetricId());
      _edge.put("target", _pair.target().getMetricId());
      _lineage_edges.add(_edge);
    }
    return _lineage_edges;
  }

  private List<Map<String, Object>> physicalLineage2edges(Graph<LineageNode> graph) {
    List<Map<String, Object>> _lineage_edges = new ArrayList<>();
    for (EndpointPair<LineageNode> _pair : graph.edges()) {
      Map<String, Object> _edge = new HashMap<>();
      // TODO: 先这样写着，后面看md_physical_field.entity_id有啥用，去掉算球了
      _edge.put("source", mdFieldInfoDBMapper.selectIdByEntityId(_pair.source().getPhysicalFieldId()).get(0));
      _edge.put("target", mdFieldInfoDBMapper.selectIdByEntityId(_pair.target().getPhysicalFieldId()).get(0));
      _lineage_edges.add(_edge);
    }
    return _lineage_edges;
  }

  private Map<String, Object> setInitialNode(String metricId) throws Exception {
    List<Map<String, Object>> _rets = lineageMapper.getlineageInfo(metricId);
    if (_rets.size() > 0) {
      return _rets.get(0);
    } else {
      logger.error("not found {} in db", metricId);
      throw new Exception("not found " + metricId + " in db");
    }

  }
}
