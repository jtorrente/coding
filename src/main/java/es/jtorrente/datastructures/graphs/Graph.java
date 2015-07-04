package es.jtorrente.datastructures.graphs;

/**
 * Created by jtorrente on 04/07/2015.
 */
public interface Graph<T> {

    boolean addVertex(T content);
    boolean addVertex(GraphNode vertex);

    boolean addEdge(T content1, T content2, boolean directed, boolean isWeighted, int weight, String label);
    boolean addEdge(GraphNode vertex1, GraphNode vertex2, boolean directed, boolean isWeighted, int weight, String label);
    boolean addEdge(T content1, T content2, boolean directed);
    boolean addEdge(GraphNode vertex1, GraphNode vertex2, boolean directed);

    int nVertices();

    int nEdges();

    Iterable<T> vertices();

    Iterable<Edge<T>> edges();
    Iterable<Edge<T>> edges(T vertex);

    int degree(T content);
    int degree(GraphNode vertex);

    boolean hasVertex(T content);
    boolean hasVertex(GraphNode vertex);

    boolean hasEdge(T content1, T content2);
    boolean hasEdge(GraphNode vertex1, GraphNode vertex2);
}
