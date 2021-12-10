package dijkstra;

import java.util.*;

public class Pi implements PiInterface {

    private Hashtable<VertexInterface,Integer> distances;
    private GraphInterface graph;
    
    @Override
    public void initialize(GraphInterface g, VertexInterface pivot) {
        this.graph = g;
        ArrayList<VertexInterface> vertices = g.getAllVertices();
        for(VertexInterface v : vertices)
        {
            this.distances.put(v, Integer.MAX_VALUE);
        }
        this.distances.put(pivot,0);
    }

    @Override
    public int getDistance(VertexInterface v) {
        return this.distances.get(v);
    }

    @Override
    public void setDistance(VertexInterface v, int value) {
        this.distances.put(v, value);
    }

    @Override
    public VertexInterface getMinimallyEvaluatedVertex(ASetInterface aset) {
        Set<Map.Entry<VertexInterface,Integer>> vertices = this.distances.entrySet();
        Map.Entry<VertexInterface,Integer> min = null;
        for(Map.Entry<VertexInterface,Integer> pair : vertices)
        {
            if(!aset.contains(pair.getKey()))
            {
                if(min == null || pair.getValue() < min.getValue())
                    min = pair;
            }
        }
        return min.getKey();
    }

    @Override
    public void evaluateSuccessorsNotinA(ASetInterface aset, PreviousInterface p, VertexInterface pivot) {
        ArrayList<VertexInterface> successors = this.graph.getSuccessors(pivot);
        for(VertexInterface successor : successors)
        {
            if(!aset.contains(successor))
            {
                int distance_passing_by_pivot = this.getDistance(pivot) + this.graph.getWeight(pivot, successor);
                if(distance_passing_by_pivot < this.getDistance(successor))
                {
                    this.setDistance(successor, distance_passing_by_pivot);
                    p.setFather(successor, pivot);
                }
            }
        }
    }

}
