package io.github.spirin87.graph_finder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Node
 * <p>
 * @author spirin87@gmail.com
 * <p>
 * Nov 5, 2015, 8:38:25 AM
 */
public class Graph {

    private Map<Long, Set<GraphEdge>> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public static Graph createUndirectedGraph(long[][] data) {
        Graph graph = new Graph();
        graph.nodes = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            long[] edge = data[i];
            if (graph.nodes.get(edge[0]) == null) {
                graph.nodes.put(edge[0], new HashSet<>());
            }
            if (graph.nodes.get(edge[1]) == null) {
                graph.nodes.put(edge[1], new HashSet<>());
            }
            graph.nodes.get(edge[0]).add(new UndirectedGraphEdge(edge[0], edge[1]));
            graph.nodes.get(edge[1]).add(new UndirectedGraphEdge(edge[0], edge[1]));
        }
        return graph;
    }

    public static Graph createDirectedGraph(long[][] data) {
        Graph graph = new Graph();
        graph.nodes = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            long[] edge = data[i];
            if (graph.nodes.get(edge[0]) == null) {
                graph.nodes.put(edge[0], new HashSet<>());
            }
            if (graph.nodes.get(edge[1]) == null) {
                graph.nodes.put(edge[1], new HashSet<>());
            }
            graph.nodes.get(edge[0]).add(new DirectedGraphEdge(edge[0], edge[1]));
        }
        return graph;
    }

    public void addNode(Long key, Set<GraphEdge> edges) {
        if (nodes.containsKey(key)) {
            throw new RuntimeException("node with key " + key + " already exist");
        }
        nodes.put(key, edges);
    }

    public Set<GraphEdge> getEdges(Long key) {
        if (!nodes.containsKey(key)) {
            throw new RuntimeException("node with key " + key + " not found");
        }
        return nodes.get(key);
    }

    public Map<Long, Set<GraphEdge>> getNodes() {
        return nodes;
    }
}
