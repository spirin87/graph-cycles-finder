package io.github.spirin87.graph_finder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Graph сycle finder
 * <p>
 * @author spirin87@gmail.com
 * <p>
 * Nov 5, 2015, 8:48:44 AM
 */
public class GraphСycleFinder {

    private Graph graph;

    private boolean directed;

    public GraphСycleFinder(Graph graph, boolean directed) {
        this.graph = graph;
        this.directed = directed;
    }

    public Set<List<Long>> findСycles() {
        Set<List<Long>> result = new HashSet<>();
        Set<Set<Long>> repeatChecker = new HashSet<>();
        graph.getNodes().forEach((nodeKey, edges) -> {
            List<Long> passedNodes = new ArrayList<>();
            Set<List<Long>> cycles = new HashSet<>();
            passedNodes.add(nodeKey);
            findСycles(nodeKey, edges, passedNodes, cycles);
            cycles.forEach(cycle -> {
                HashSet<Long> ids = new HashSet<>();
                cycle.forEach(id -> ids.add(id));
                if (!repeatChecker.contains(ids)) {
                    repeatChecker.add(ids);
                    result.add(cycle);
                }
            });
        });
        return result;
    }

    private void findСycles(Long nodeKey, Set<GraphEdge> edges, List<Long> passedNodes, Set<List<Long>> cycles) {
        edges.forEach(edge -> findСycles(nodeKey, passedNodes, edge, cycles));
    }

    private void findСycles(Long nodeKey, List<Long> passedNodes, GraphEdge edge, Set<List<Long>> cycles) {
        Set<GraphEdge> subEdges = null;
        Long nextKey = null;
        if (edge.getX() == nodeKey) {
            nextKey = edge.getY();
            subEdges = graph.getEdges(nextKey);
        } else if (!directed) {
            nextKey = edge.getX();
            subEdges = graph.getEdges(nextKey);
        }
        if (nextKey != null && subEdges != null && subEdges.size() > 0) {
            if (!passedNodes.contains(nextKey)) {
                ArrayList<Long> _passedNodes = new ArrayList<>(passedNodes);
                _passedNodes.add(nextKey);
                findСycles(nextKey, subEdges, _passedNodes, cycles);
            } else if ((directed && passedNodes.size() > 1 && passedNodes.get(passedNodes.size() - 1) != nextKey)
                    || (!directed && passedNodes.size() > 2 && passedNodes.get(passedNodes.size() - 2) != nextKey)) {
                List<Long> cycle = new ArrayList<>(passedNodes);
                if (cycle.indexOf(nextKey) != 0) {
                    cycle = cycle.subList(cycle.indexOf(nextKey), cycle.size());
                }
                cycle.add(nextKey);
                cycles.add(cycle);
            }
        }
    }
}
