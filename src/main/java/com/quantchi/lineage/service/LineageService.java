package com.quantchi.lineage.service;


import java.sql.SQLException;
import java.util.*;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.quantchi.lineage.mapper.LineageMapper;
import com.quantchi.lineage.metric.MetricLineage;
import com.quantchi.lineage.metric.MetricLineageNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineageService {

    private static final Logger logger = LoggerFactory.getLogger(LineageService.class);
    private static final String FETCH_ALL_NODE_TYPE = "ALL";
    /*@Autowired
    private LineageMapper lineageMapper;*/

    public Map<String,Object> getNodesInfo(String metricId,String type) throws Exception{
        if(FETCH_ALL_NODE_TYPE.equals(type))
            return getAllNodes(metricId);
        else
            return getNeighborNodes(metricId);
    }

    private Map<String,Object> getNeighborNodes(String metricId) throws Exception {
        return graph2nodes(MetricLineage.getAdjacentMetricLineage(metricId));
    }

    private Map<String,Object> getAllNodes(String metricId) throws Exception {
        return graph2nodes(MetricLineage.getMetricLineage(metricId));
    }

    private Map<String,Object> graph2nodes(Graph<MetricLineageNode> graph) throws Exception{
        List<Map<String, Object>> _lineage_nodes = new ArrayList<>();
        List<Map<String, Object>> _lineage_edges = new ArrayList<>();
        for(MetricLineageNode _lineage_node : graph.nodes()){
            String _metricId = _lineage_node.getMetricId();
            Map<String,Object> _node = new HashMap<>();
            _node.put("metricId",_lineage_node.getMetricId());
            _node.put("metricName",_lineage_node.getEntityDesc());
            _node.put("phsicalFieldName",_lineage_node.getPhysicalField());
            _node.put("phsicalFieldDesc",_lineage_node.getPhysicalFieldDesc());
            _node.put("isOpen",!(_lineage_node.getLineageNode().isEndNode() && _lineage_node.getLineageNode().isStartNode()));
            _lineage_nodes.add(_node);
        }

        for(EndpointPair<MetricLineageNode> _pair : graph.edges()){
            Map<String,Object> _edge = new HashMap<>();
            _edge.put("source",_pair.source().getMetricId());
            _edge.put("target",_pair.target().getMetricId());
            _lineage_edges.add(_edge);
        }

        Map<String,Object> _total_ret = new HashMap<>();
        _total_ret.put("nodes",_lineage_nodes);
        _total_ret.put("edges",_lineage_edges);
        return _total_ret;
    }
}
