package dijkstra;

public class Dijkstra {
    
    private PreviousInterface dijkstra(GraphInterface g, VertexInterface r, 
            ASetInterface a, PiInterface pi, PreviousInterface previous)
    {
        int n = g.getSize();
        a.add(r);
        VertexInterface pivot = r;
        pi.initialize(g,r);
        for(int j = 1; j <= n-1; j++)
        {
            pi.evaluateSuccessorsNotinA(a, previous, pivot);
            pivot = pi.getMinimallyEvaluatedVertex(a);
            a.add(pivot);
        }
        return previous;
    }
}
