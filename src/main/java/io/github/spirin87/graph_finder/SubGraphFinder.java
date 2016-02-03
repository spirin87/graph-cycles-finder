package io.github.spirin87.graph_finder;

import java.util.HashSet;
import java.util.Set;

/**
 * Nodes within node subgraph finder
 * <p>
 * only for undirected graph
 * <p>
 * @author spirin87@gmail.com
 * <p>
 * Feb 3, 2016, 5:01:57 PM
 */
public class SubGraphFinder {

    private Graph graph;

    public SubGraphFinder(Graph graph) {
        this.graph = graph;
    }

    public Set<Long> findSubgraphNodesNyNode(Long node) {
        Set<Long> nodes = new HashSet<Long>();
        findSubgraphNodesNyNode(nodes, node);
        return nodes;

    }

    private void findSubgraphNodesNyNode(Set<Long> nodes, Long node) {
        Set<GraphEdge> edges = graph.getNodes().get(node);
        if (edges != null)
            edges.forEach(new NodeChecker(nodes)::check);
    }

    private class NodeChecker {

        private Set<Long> nodes;

        public NodeChecker(Set<Long> nodes) {
            this.nodes = nodes;
        }

        public void check(GraphEdge edge) {
            if (!nodes.contains(edge.getX())) {
                nodes.add(edge.getX());
                findSubgraphNodesNyNode(nodes, edge.getX());
            }
            // undirected
            if (!nodes.contains(edge.getY())) {
                nodes.add(edge.getY());
                findSubgraphNodesNyNode(nodes, edge.getY());
            }
        }
    }
}