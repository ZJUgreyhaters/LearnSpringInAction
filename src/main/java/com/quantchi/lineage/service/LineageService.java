package com.quantchi.lineage.service;

import com.google.common.graph.Graph;
import com.quantchi.lineage.metric.MetricLineage;
import com.quantchi.lineage.metric.MetricLineageNode;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class LineageService {

    public void test() throws SQLException {
        Graph<MetricLineageNode> graph = MetricLineage.getAdjacentMetricLineage("PTU0002");
        System.out.println(graph.toString());
    }
}
