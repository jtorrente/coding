package es.jtorrente.datastructures.graphs;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversal {

    private static class VertexInfo<T>{
        VertexState state = VertexState.DISCOVERED;
        T parent = null;
    }

    private static enum VertexState{
        DISCOVERED, PROCESSED;
    }

    public static <T> void breadthFirstTraversal(Graph<T> graph, VertexProcessor<T> vertexProcessor, EdgeProcessor edgeProcessor){
        // Structure to keep state of vertices and parents
        HashMap<T, VertexInfo> states = new HashMap<>();
        Iterable<T> vertices = graph.vertices();
        int nVertices = graph.nVertices();
        if (nVertices==0){
            return;
        }

        // To search: use a q
        Queue<T> q = new LinkedList<>();
        T firstV = vertices.iterator().next();
        discoverNode(q, states, firstV, null);

        while (!q.isEmpty()){
            T currentV = q.poll();
            vertexProcessor.process(currentV);
            // Visit adjacent vertices
            for (Edge<T> adjacentV : graph.edges(currentV)){
                edgeProcessor.process(adjacentV);
                VertexInfo adjacentInfo = states.get(adjacentV.to);
                // Undiscovered
                if (adjacentInfo == null){
                    discoverNode(q, states, adjacentV.to, currentV);
                }
            }
            states.get(currentV).state = VertexState.PROCESSED;
        }
    }

    private static <T> void discoverNode(Queue<T> q, HashMap<T, VertexInfo> states, T vertex, T parent){
        VertexInfo<T> vertexInfo = new VertexInfo<>();
        vertexInfo.state = VertexState.DISCOVERED;
        vertexInfo.parent = parent;
        states.put(vertex, vertexInfo);
        q.offer(vertex);
    }

    /**
     * Interface to define objects that can deal with processing of
     * vertices when traversing a graph, so the traversal algorithm
     * can be encapsulated and re-used
     * Created by jtorrente on 04/07/2015.
     */
    interface VertexProcessor<T>{
        void process(T vertex);
    }

    /**
     * Interface to define processors of edges
     */
    interface EdgeProcessor{
        void process(Edge edge);
    }
}
