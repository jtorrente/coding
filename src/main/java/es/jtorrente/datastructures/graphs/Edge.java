package es.jtorrente.datastructures.graphs;

/**
 * Created by jtorrente on 04/07/2015.
 */
public class Edge<T> {

    private static int AUTO_ID = 1;

    T to;
    public int weight = 1;
    public boolean isWeighted = false;
    public String label;
    public void setWeighted(int weight){
        isWeighted = true;
        this.weight = weight;
    }

    public void setNotWeighted(){
        this.weight = 1;
        isWeighted = false;
    }

    public Edge(T to){
        label = ""+AUTO_ID;
        AUTO_ID++;
        this.to = to;
    }

    public T getTo(){
        return to;
    }
}
