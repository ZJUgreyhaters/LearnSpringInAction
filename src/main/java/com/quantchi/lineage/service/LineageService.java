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
    @Autowired
    private LineageMapper lineageMapper;

    public Map<String,Object> getNodesInfo(String metricId,String type) throws Exception{
        if(FETCH_ALL_NODE_TYPE.equals(type))
            return getAllNodes(metricId);
        else
            return getNeighborNodes(metricId);
    }

    private Map<String,Object> getNeighborNodes(String metricId) throws Exception {
        return graph2nodes(MetricLineage.getAdjacentMetricLineage(metricId),metricId);
    }

    private Map<String,Object> getAllNodes(String metricId) throws Exception {
        return graph2nodes(MetricLineage.getMetricLineage(metricId),metricId);
    }

    private Map<String,Object> graph2nodes(Graph<MetricLineageNode> graph,String metricId) throws Exception{
        Map<String,Object> _total_ret = new HashMap<>();
        _total_ret.put("nodes",lineage2nodes(graph,metricId));
        _total_ret.put("edges",lineage2edges(graph,metricId));
        return _total_ret;
    }

    private List<Map<String, Object>> lineage2nodes (Graph<MetricLineageNode> graph,String metricId) throws Exception{
        List<Map<String, Object>> _lineage_nodes = new ArrayList<>();
        for(MetricLineageNode _lineage_node : graph.nodes()){
            Map<String,Object> _node = new HashMap<>();
            _node.put("metricId",_lineage_node.getMetricId());
            _node.put("metricName",_lineage_node.getEntityDesc());
            _node.put("phsicalFieldName",_lineage_node.getPhysicalField());
            _node.put("phsicalFieldDesc",_lineage_node.getPhysicalFieldDesc());
            _node.put("isOpen",!(_lineage_node.getLineageNode().isEndNode() || _lineage_node.getLineageNode().isStartNode()));
            _lineage_nodes.add(_node);
        }
        //if return empty from lineage service, set current node info in list
        if(_lineage_nodes.size() == 0)
            _lineage_nodes.add(setInitialNode(metricId));

        return _lineage_nodes;
    }

    private List<Map<String, Object>> lineage2edges(Graph<MetricLineageNode> graph,String metricId){
        List<Map<String, Object>> _lineage_edges = new ArrayList<>();
        for(EndpointPair<MetricLineageNode> _pair : graph.edges()){
            Map<String,Object> _edge = new HashMap<>();
            _edge.put("source",_pair.source().getMetricId());
            _edge.put("target",_pair.target().getMetricId());
            _lineage_edges.add(_edge);
        }
        return _lineage_edges;
    }

    private Map<String, Object> setInitialNode(String metricId) throws Exception{
        List<Map<String, Object>> _rets = lineageMapper.getlineageInfo(metricId);
        if(_rets.size() > 0)
            return  _rets.get(0);
        else{
            logger.error("not found {} in db",metricId);
            throw new Exception("not found "+metricId+" in db");
        }

    }
}
