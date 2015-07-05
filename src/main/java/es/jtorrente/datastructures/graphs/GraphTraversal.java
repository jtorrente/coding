package es.jtorrente.datastructures.graphs;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversal {

    private static class VertexInfo<T>{
        VertexState state = VertexState.DISCOVERED;
        T parent = null;
    }

    private static class VertexInfoDFS<T> extends VertexInfo<T>{
        int entryTime;
        int exitTime;
    }

    private enum VertexState{
        DISCOVERED, PROCESSED
    }

    public static <T> void depthFirstTraversalIterative(Graph<T> graph, VertexProcessor<T> vertexProcessor, EdgeProcessor edgeProcessor){
        HashMap<T, VertexInfoDFS<T>> states = new HashMap<>();
        Iterable<T> vertices = graph.vertices();
        Stack<T> pendingVertices = new Stack<>();
        Stack<T> localStack=new Stack<>();
        pendingVertices.push(vertices.iterator().next());
        VertexInfoDFS<T> info = new VertexInfoDFS<>();
        int time=0;
        info.entryTime=time;
        info.parent = null;
        info.state = VertexState.DISCOVERED;
        states.put(pendingVertices.peek(), info);

        while(!pendingVertices.empty()){
            T current = pendingVertices.pop();
            time++;
            vertexProcessor.process(current);
            states.get(current).state = VertexState.PROCESSED;
            for (Edge<T> edge: graph.edges(current)) {
                if (!states.containsKey(edge.to)) {
                    edgeProcessor.process(edge);
                    VertexInfoDFS<T> info2 = new VertexInfoDFS<>();
                    info2.entryTime = time;
                    info2.parent = current;
                    info2.state = VertexState.DISCOVERED;
                    states.put(edge.to, info2);
                    localStack.push(edge.to);
                }
            }
            while(!localStack.empty()){
                pendingVertices.push(localStack.pop());
            }
            time++;
            states.get(current).exitTime=time;
        }
    }

    public static <T> void depthFirstTraversalRecursive(Graph<T> graph, VertexProcessor<T> vertexProcessor, EdgeProcessor edgeProcessor){
        T currentNode = graph.vertices().iterator().next();
        HashMap<T, VertexInfoDFS<T>> states = new HashMap<>();
        int time=0;
        VertexInfoDFS<T> newInfo = new VertexInfoDFS<>();
        newInfo.parent = null;
        newInfo.entryTime = time;
        states.put(currentNode, newInfo);
        depthFirstTraversalRecursiveInternal(graph, currentNode, states, time, vertexProcessor, edgeProcessor);
    }

    private static <T> int depthFirstTraversalRecursiveInternal(Graph<T> graph, T currentNode, HashMap<T, VertexInfoDFS<T>> states, int time, VertexProcessor<T> vertexProcessor, EdgeProcessor edgeProcessor){
        VertexInfoDFS<T> info = states.get(currentNode);
        vertexProcessor.process(currentNode);
        info.state = VertexState.PROCESSED;
        time++;

        for (Edge<T> edge: graph.edges(currentNode)){
            if (states.get(edge.to)==null){
                edgeProcessor.process(edge);
                VertexInfoDFS<T> newInfo = new VertexInfoDFS<>();
                newInfo.parent = currentNode;
                newInfo.entryTime = time;
                states.put(edge.to, newInfo);
                time = depthFirstTraversalRecursiveInternal(graph, edge.to, states, time, vertexProcessor, edgeProcessor);
            }
        }

        info.exitTime = time;
        time++;
        return time;
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
