package io.github.spirin87.graph_finder.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import io.github.spirin87.graph_finder.DirectedGraphEdge;
import io.github.spirin87.graph_finder.Graph;
import io.github.spirin87.graph_finder.GraphEdge;
import io.github.spirin87.graph_finder.GraphСycleFinder;
import io.github.spirin87.graph_finder.UndirectedGraphEdge;

/**
 * @author spirin87@gmail.com
 * <p>
 * Nov 5, 2015, 8:17:06 AM
 */
public class GraphEdgeTest {

    @Test
    public void testOrderUndirectedGraphEdge() {
        GraphEdge e1 = new UndirectedGraphEdge(1123123L, 2L);
        GraphEdge e2 = new UndirectedGraphEdge(2L, 1123123L);
        GraphEdge e3 = new UndirectedGraphEdge(1L, 1123123L);
        Assert.assertEquals(e1, e2);
        Assert.assertNotEquals(e1, e3);
        Assert.assertNotEquals(e2, e3);
        Assert.assertEquals(e1.hashCode(), e2.hashCode());
        Assert.assertNotEquals(e1.hashCode(), e3.hashCode());
        Assert.assertNotEquals(e2.hashCode(), e3.hashCode());
    }

    @Test
    public void testOrderDirectedGraphEdge() {
        GraphEdge e1 = new DirectedGraphEdge(1123123L, 2L);
        GraphEdge e2 = new DirectedGraphEdge(2L, 1123123L);
        GraphEdge e3 = new DirectedGraphEdge(1L, 1123123L);
        GraphEdge e4 = new DirectedGraphEdge(2L, 1123123L);
        Assert.assertNotEquals(e1, e2);
        Assert.assertNotEquals(e1, e3);
        Assert.assertNotEquals(e2, e3);
        Assert.assertNotEquals(e1.hashCode(), e2.hashCode());
        Assert.assertNotEquals(e1.hashCode(), e3.hashCode());
        Assert.assertNotEquals(e2.hashCode(), e3.hashCode());
        Assert.assertEquals(e2.hashCode(), e4.hashCode());
        Assert.assertEquals(e2, e4);
    }

    @Test
    public void testCycles() {
        long[][] data = new long[][] { { 0L, 1L }, { 0L, 2L }, { 0L, 3L }, { 1L, 3L }, { 2L, 3L } };
        Graph graph = Graph.createUndirectedGraph(data);
        GraphСycleFinder graphСycleFinder = new GraphСycleFinder(graph, false);
        Set<List<Long>> сycles = graphСycleFinder.findСycles();
        Assert.assertEquals(3, сycles.size());

        data = new long[][] { { 0L, 1L }, { 0L, 2L }, { 0L, 3L }, { 1L, 3L }, { 2L, 3L }, { 3L, 2L }, { 2L, 0L } };
        graph = Graph.createDirectedGraph(data);
        graphСycleFinder = new GraphСycleFinder(graph, true);
        сycles = graphСycleFinder.findСycles();
        Assert.assertEquals(2, сycles.size());
    }

    @Test
    public void testLargeData() {
        int count = 100000;
        long[][] data = new long[count][];
        for (int i = 0; i < count; i++) {
            data[i] = new long[2];
            data[i][0] = i;
            fillEdge(count, data, i);
            while (data[i][0] == data[i][1]) {
                fillEdge(count, data, i);
            }
        }
        data[555][1] = 33L;
        data[33][1] = 478L;
        data[478][1] = 555L;
        List<Long> сycle = new ArrayList<>();
        сycle.add(33L);
        сycle.add(478L);
        сycle.add(555L);
        сycle.add(33L);
        Graph graph = Graph.createDirectedGraph(data);
        GraphСycleFinder graphСycleFinder = new GraphСycleFinder(graph, true);
        Set<List<Long>> сycles = graphСycleFinder.findСycles();
        Assert.assertTrue(сycles.size() > 0);
        Assert.assertEquals(сycles.iterator().next(), сycle);
    }

    private void fillEdge(int count, long[][] data, int i) {
        if (i < count / 2) {
            data[i][1] = getRandomNumberInRange(i + 1, count - 1);
        } else {
            data[i][1] = getRandomNumberInRange(0, count / 2);
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min: " + max + ", " + min);
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
