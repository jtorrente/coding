package es.jtorrente.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class GraphNode<T> {

    public T value;

    public List<Edge> neighbours = new ArrayList<>();

}
