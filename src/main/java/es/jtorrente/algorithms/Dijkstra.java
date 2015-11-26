package es.jtorrente.algorithms;

import es.jtorrente.datastructures.graphs.Edge;
import es.jtorrente.datastructures.graphs.GraphNodes;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by jtorrente on 05/09/2015.
 */
public class Dijkstra {

    public static class Link implements Comparable<Link>{
        String from;
        String to;
        int distance;
        boolean done;

        Link (String from, String to, int distance){
            this.from = from;
            this.to = to;
            this.distance = distance;
            done = false;
        }

        @Override
        public int compareTo(Link o) {
            return Integer.compare(distance, o.distance);
        }

        @Override
        public String toString(){
            return from+"->"+to+" = "+distance;
        }
    }

    private static final Link DUMMY = new Link(null, null, Integer.MIN_VALUE);

    /**
     Cost:
     Space: O(v)
     Time: O(v2 lgv)
     */
    public HashMap<String, Link> calculate(String start, GraphNodes<String> graph){
        // Init result vector and pq to track node with min distance
        HashMap<String, Link> result = new HashMap<>(graph.nVertices());
        PriorityQueue<Link> pq = new PriorityQueue<>(graph.nVertices());

        for (String node: graph.vertices()){
            Link link = null;
            if (node.equals(start)) {
                link = new Link(start, start, 0);
            } else {
                link = new Link(null, node, Integer.MAX_VALUE);
            }
            result.put(node, link);
            pq.offer(link);
        }

        // Iterate
        int verticesDone = 0;
        Link currentMin = null; // To keep track of the minimum value
        while (verticesDone < graph.nVertices() && !pq.isEmpty()) {
            // Mark currentMin as processed
            currentMin = pq.poll();
            currentMin.done = true;
            verticesDone++;
            String currentNode = currentMin.to;

            // Check all nodes adjacent to the one being examined
            for (Edge<String> edge: graph.edges(currentNode)) {
                String adjacentNode = edge.getTo();
                if (result.get(adjacentNode).done){
                    continue;
                }
                // If distance through current is smaller than the one stored, then update
                int distanceThroughCurrent = edge.weight + currentMin.distance;
                if (distanceThroughCurrent < result.get(adjacentNode).distance) {
                    Link updatedLink = result.get(adjacentNode);
                    updatedLink.distance = distanceThroughCurrent;
                    updatedLink.from = currentNode;
                    // Update pq so minimum bubbles up
                    pq.offer(DUMMY);
                    pq.poll();
                }
            }
        }

        return result;
    }

    public String printablePath(HashMap<String, Link> dijkstraResults, String end){
        Stack<String> tmp = new Stack<>();
        String oldCurrent = null;
        String current = end;
        while (oldCurrent!=current) {
            tmp.push(current);
            oldCurrent = current;
            current = dijkstraResults.get(current).from;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        while (!tmp.empty()) {
            stringBuilder.append(tmp.pop());
            if (!tmp.empty()){
                stringBuilder.append("->");
            }
        }
        stringBuilder.append("]=");
        stringBuilder.append(dijkstraResults.get(end).distance);
        return stringBuilder.toString();
    }

}
