package es.jtorrente.algorithms;

import es.jtorrente.datastructures.graphs.GraphNodes;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 05/09/2015.
 */
public class TestDijkstra {

    @Test
    public void test(){
        GraphNodes<String> graph = new GraphNodes<>();
        addVertices(graph, "a,b,c,d,e,z");
        graph.addEdge("a", "b", false, true, 2, null);
        graph.addEdge("a", "c", false, true, 3, null);

        graph.addEdge("b", "d", false, true, 5, null);
        graph.addEdge("b", "e", false, true, 2, null);

        graph.addEdge("c", "e", false, true, 5, null);

        graph.addEdge("d", "e", false, true, 1, null);
        graph.addEdge("d", "z", false, true, 2, null);

        graph.addEdge("e", "z", false, true, 4, null);

        Dijkstra dijkstra = new Dijkstra();
        HashMap<String, Dijkstra.Link> result = dijkstra.calculate("a", graph);
        String path = dijkstra.printablePath(result, "z");
        assertEquals("[a->b->e->d->z]=7", path);
    }

    private static void addVertices(GraphNodes<String> graph, String commaSeparatedVertices) {
        for (String vertex: commaSeparatedVertices.split(",")){
            graph.addVertex(vertex);
        }
    }
}
