package es.jtorrente.datastructures.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jtorrente on 05/07/2015.
 */
public class GraphNodes<T> implements Graph<T>{

    private HashMap<T, Node<T>> nodes = new HashMap<>();
    private int nEdges = 0;
    private int nVertices = 0;

    public static class Node<T>{
        T value;
        List<Edge<T>> edges = new ArrayList<>();
    }

    public static class InternalEdge<T> extends Edge<T>{
        Node<T> toNode;

        public InternalEdge(Node<T> to) {
            super(to.value);
            toNode = to;
        }
    }

    @Override
    public boolean addVertex(T content) {
        if (!nodes.containsKey(content)){
            Node<T> newNode = new Node<>();
            newNode.value = content;
            nodes.put(content, newNode);
            nVertices++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(T content1, T content2, boolean directed, boolean isWeighted, int weight, String label) {
        addVertex(content1);
        addVertex(content2);
        Node<T> node1 = nodes.get(content1);
        Node<T> node2 = nodes.get(content2);
        boolean exists = false;
        for (Edge<T> edge: node1.edges){
            if (edge.to.equals(content2)){
                exists = true;
                break;
            }
        }
        boolean added = false;
        if (!exists){
            InternalEdge<T> newEdge = new InternalEdge<>(node2);
            if (isWeighted) {
                newEdge.setWeighted(weight);
            } else {
                newEdge.setNotWeighted();
            }
            newEdge.label = label;
            nEdges++;
            node1.edges.add(newEdge);
            added = true;
        }

        if (!directed){
            added |=addEdge(content2, content1, true, isWeighted, weight, label);
        }
        return added;
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
        return nodes.keySet();
    }

    @Override
    public Iterable<Edge<T>> edges() {
        List<Edge<T>> edges = new ArrayList<>();
        for (Node<T> node:nodes.values()){
            for (Edge<T> edge: node.edges){
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public Iterable<Edge<T>> edges(T vertex) {
        addVertex(vertex);
        return nodes.get(vertex).edges;
    }

    @Override
    public int degree(T content) {
        addVertex(content);
        return nodes.get(content).edges.size();
    }

    @Override
    public boolean hasVertex(T content) {
        return nodes.containsKey(content);
    }

    @Override
    public boolean hasEdge(T content1, T content2) {
        if(nodes.containsKey(content1)){
            for (Edge<T> edge: nodes.get(content1).edges){
                if (edge.to == content2){
                    return true;
                }
            }
        }
        return false;
    }
}
