package es.jtorrente.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class GraphAdjacencyList<T> implements Graph<T>{

    private static class EdgeNode<T>{
        Edge<T> edge;
        EdgeNode<T> next;
        EdgeNode(Edge<T> edge){
            this.edge = edge;
        }
    }

    private HashMap<T, EdgeNode<T>> edges = new HashMap<>();
    private int nVertices = 0;
    private int nEdges = 0;

    @Override
    public boolean addVertex(T content) {
        if (!edges.containsKey(content)) {
            edges.put(content, new EdgeNode<T>(null));
            nVertices++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(T content1, T content2, boolean directed, boolean isWeighted, int weight, String label) {
        addVertex(content1);
        addVertex(content2);
        boolean added = false;

        EdgeNode<T> edges1 = edges.get(content1);
        Edge<T> edge = new Edge<>(content2);
        if (isWeighted) {
            edge.setWeighted(weight);
        }
        edge.label = label;
        if (edges1.edge == null){
            edges1.edge = edge;
            added = true;
        } else {
            boolean found = false;
            EdgeNode<T> parent = null;
            while (edges1!=null){
                if (edges1.edge.to == content2){
                    found = true;
                    break;
                }
                parent = edges1;
                edges1=edges1.next;
            }
            if (!found){
                EdgeNode<T> newNode = new EdgeNode<>(edge);
                newNode.next = null;
                parent.next = newNode;
                added = true;
            }

        }

        if (added){
            nEdges++;
        }

        if (!directed){
            addEdge(content2, content1, true, isWeighted, weight, label);
        }
        return true;
    }

    @Override
    public boolean addEdge(T content1, T content2, boolean directed) {
        return addEdge(content1, content2, directed, false, -1, null);
    }

    @Override
    public int nVertices() {
        return nVertices;
    }

    @Override
    public int nEdges() {
        return nEdges;
    }

    @Override
    public Iterable<T> vertices() {
        return edges.keySet();
    }

    @Override
    public Iterable<Edge<T>> edges() {
        List<Edge<T>> list = new ArrayList<>();
        for (EdgeNode<T> edgeNode: edges.values()){
            if (edgeNode.edge!=null){
                list.add(edgeNode.edge);
            }
        }
        return list;
    }

    @Override
    public Iterable<Edge<T>> edges(T vertex) {
        addVertex(vertex);
        List<Edge<T>> list = new ArrayList<>();
        EdgeNode<T> edgeNode = edges.get(vertex);
        while (edgeNode!=null && edgeNode.edge!=null){
            list.add(edgeNode.edge);
            edgeNode = edgeNode.next;
        }
        return list;
    }

    @Override
    public int degree(T content) {
        return ((List)edges(content)).size();
    }

    @Override
    public boolean hasVertex(T content) {
        return edges.containsKey(content);
    }

    @Override
    public boolean hasEdge(T content1, T content2) {
        EdgeNode<T> edgeNode = edges.get(content1);
        while (edgeNode!=null && edgeNode.edge!=null){
            if (edgeNode.edge.to.equals(content2)){
                return true;
            }
            edgeNode = edgeNode.next;
        }
        return false;
    }
}
