package es.jtorrente.datastructures.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class GraphAdjacencyMatrix<T> implements Graph<T>{

    private int nMaxVertices;

    private int nVertices=0;

    private int nEdges=0;

    // T must implement hashCode, equals(), etc
    private HashMap<T, Integer> indices = new HashMap<>();
    private HashMap<Integer, T> reverseIndices = new HashMap<>();

    private InternalEdge adjMatrix[][];

    public GraphAdjacencyMatrix(int nMaxVertices){
        this.nMaxVertices = nMaxVertices;
        adjMatrix = new InternalEdge[nMaxVertices][nMaxVertices];
        for (int i=0; i<nMaxVertices; i++){
            adjMatrix[i] = new InternalEdge[nMaxVertices];
        }
    }

    @Override
    public boolean addVertex(T content) {
        return checkElementAssignedIndex(content);
    }

    @Override
    public boolean addVertex(GraphNode vertex) {
        // Not implemented
        return false;
    }

    @Override
    public boolean addEdge(T content1, T content2, boolean directed, boolean isWeighted, int weight, String label) {
        boolean added = false;
        checkElementAssignedIndex(content1);
        checkElementAssignedIndex(content2);
        int i1 = indices.get(content1);
        int i2 = indices.get(content2);
        if (adjMatrix[i1][i2].weight == Integer.MIN_VALUE) {
            adjMatrix[i1][i2].weight = isWeighted?weight:1;
            adjMatrix[i1][i2].label = label;
            nEdges++;
            added = true;
        }
        if (!directed){
            added |= addEdge(content2, content1, true, isWeighted, weight, label);
        }
        return added;
    }

    @Override
    public boolean addEdge(GraphNode vertex1, GraphNode vertex2, boolean directed, boolean isWeighted, int weight, String label) {
        // Not implemented
        return false;
    }

    @Override
    public boolean addEdge(T content1, T content2, boolean directed) {
        return addEdge(content1, content2, directed, false, -1, null);
    }

    @Override
    public boolean addEdge(GraphNode vertex1, GraphNode vertex2, boolean directed) {
        // Not implemented
        return false;
    }

    private boolean checkElementAssignedIndex(T content){
        boolean added = false;
        if (!indices.containsKey(content)){
            indices.put(content, nVertices++);
            reverseIndices.put(nVertices-1, content);
            added = true;
            checkResizeMatrix();
            for (int i=0; i<nMaxVertices; i++){
                adjMatrix[nVertices-1][i] = new InternalEdge();
                adjMatrix[i][nVertices-1] = new InternalEdge();
            }
        }
        return added;
    }

    private void checkResizeMatrix(){
        if (nVertices < nMaxVertices){
            return;
        }
        InternalEdge[][] newMatrix = Arrays.copyOf(adjMatrix, nMaxVertices*2);
        for (int i=0; i<newMatrix.length; i++){
            if (i<nMaxVertices) {
                newMatrix[i] = Arrays.copyOf(adjMatrix[i], nMaxVertices * 2);
            } else {
                newMatrix[i] = new InternalEdge[nMaxVertices * 2];
            }
        }
        nMaxVertices *=2;
        adjMatrix = newMatrix;
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
        return indices.keySet();
    }

    @Override
    public Iterable<Edge<T>> edges() {
        List<Edge<T>> edgeList = new ArrayList<>();
        for (int i=0; i<nVertices; i++){
            T value = reverseIndices.get(i);
            edgesInternal(edgeList, value);
        }
        return edgeList;
    }

    @Override
    public Iterable<Edge<T>> edges(T vertex) {
        List<Edge<T>> edgeList = new ArrayList<>();
        edgesInternal(edgeList, vertex);
        return edgeList;
    }

    private void edgesInternal(List<Edge<T>> edgeList, T vertex) {
        checkElementAssignedIndex(vertex);
        int index = indices.get(vertex);
        for (int i = 0; i < nVertices; i++) {
            if (adjMatrix[index][i].weight != Integer.MIN_VALUE) {
                Edge edge = new Edge(reverseIndices.get(i));
                edge.setWeighted(adjMatrix[index][i].weight);
                edge.label = adjMatrix[index][i].label;
                edgeList.add(edge);
            }
        }
    }

    @Override
    public int degree(T content) {
        checkElementAssignedIndex(content);
        int index = indices.get(content);
        int degree = 0;
        for (int i=0; i<nVertices; i++){
            if (adjMatrix[index][i].weight!=Integer.MIN_VALUE){
                degree++;
            }
        }
        return degree;
    }

    @Override
    public int degree(GraphNode vertex) {
        // Not implemented
        return 0;
    }

    @Override
    public boolean hasVertex(T content) {
        return indices.containsKey(content);
    }

    @Override
    public boolean hasVertex(GraphNode vertex) {
        // Not implemented
        return false;
    }

    @Override
    public boolean hasEdge(T content1, T content2) {
        checkElementAssignedIndex(content1);
        checkElementAssignedIndex(content2);
        int i1=indices.get(content1);
        int i2=indices.get(content2);
        return adjMatrix[i1][i2].weight!=Integer.MIN_VALUE;
    }

    @Override
    public boolean hasEdge(GraphNode vertex1, GraphNode vertex2) {
        // Not implemented
        return false;
    }

    private static class InternalEdge {
        int weight = Integer.MIN_VALUE;
        String label = null;

        @Override
        public InternalEdge clone(){
            InternalEdge copy = new InternalEdge();
            copy.label = label;
            copy.weight = weight;
            return copy;
        }
    }
}
