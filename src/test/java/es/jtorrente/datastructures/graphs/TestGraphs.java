package es.jtorrente.datastructures.graphs;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class TestGraphs {

    @Test
    public void testAllTypes(){
        testType(GraphAdjacencyList.class);
        testType(GraphAdjacencyMatrix.class);
        testType(GraphNodes.class);
    }

    public void testType(Class<? extends Graph> clazz){
        Graph<Integer> graph = clazz == GraphAdjacencyList.class? new GraphAdjacencyList<>():new GraphAdjacencyMatrix<>(5);
        graph.addEdge(1, 2, false);
        graph.addEdge(1, 3, false);
        graph.addVertex(4);
        graph.addEdge(2, 4, false);
        graph.addEdge(4, 5, false);
        graph.addEdge(3, 6, false);
        graph.addEdge(6, 7, false);
        graph.addEdge(6, 7, false);
        graph.addEdge(5, 7, true);
        graph.addEdge(5, 7, true);

        assertEquals(7, graph.nVertices());
        assertEquals(13, graph.nEdges());
        for (int i=1; i<=7; i++) {
            assertTrue(graph.hasVertex(i));
        }
        assertFalse(graph.hasVertex(8));
        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2,1));
        assertTrue(graph.hasEdge(5, 7));
        assertFalse(graph.hasEdge(7,5));

        // Test Breadth-first search
        int[] expectedOrder = {1,2,3,4,6,5,7};
        checkBreadthFirstSearch(graph, expectedOrder);
        // Depth-first search
        int[] expectedOrder2 = {1,2,4,5,7,6,3};
        checkDepthFirstSearch(graph, expectedOrder2);
    }

    private void checkBreadthFirstSearch(Graph<Integer> graph, int[]expectedOrder){
        int[] results = new int[expectedOrder.length];
        int[] current = {0};
        GraphTraversal.breadthFirstTraversal(graph, vertex -> results[current[0]++]=vertex, edge -> {
        });
        // Check elements are equals
        assertTrue(Arrays.equals(results, expectedOrder));
    }

    private void checkDepthFirstSearch(Graph<Integer> graph, int[]expectedOrder){
        int[] results = new int[expectedOrder.length];
        int[] results2 = new int[expectedOrder.length];
        int[] current = {0,0};
        GraphTraversal.depthFirstTraversalRecursive(graph, vertex -> results[current[0]++]=vertex, edge -> {
        });
        GraphTraversal.depthFirstTraversalIterative(graph, vertex -> results2[current[1]++] = vertex, edge -> {
        });
        // Check elements are equals
        assertTrue(Arrays.equals(results, expectedOrder));
        assertTrue(Arrays.equals(results2, expectedOrder));
    }
}
