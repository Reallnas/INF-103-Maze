package dijkstra;

public final class Dijkstra {

    private static PreviousInterface dijkstra(GraphInterface g, VertexInterface r,
                                              ASetInterface a, PiInterface pi, PreviousInterface previous) {
        int n = g.getSize();
        a.add(r);
        VertexInterface pivot = r;
        pi.initialize(g, r);
        for (int j = 1; j <= n - 1; j++) {
            pi.evaluateSuccessorsNotInA(a, previous, pivot);
            pivot = pi.getMinimallyEvaluatedVertex(a);
            a.add(pivot);
            //If the pivot has a distance of Integer.MAX_VALUE, it means that we evaluated every vertex in the same
            //connected components as the start without evaluating the goal.
            //Therefore, it is impossible to find a path between the root and the goal.
            //We then skip the rest of the graph.
            //If we could set this distance as infinity there would be no problem, but we can only use finite values.
            if(pi.getDistance(pivot)== Integer.MAX_VALUE)
                break;
        }
        return previous;
    }

    public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) {
        Previous prev = new Previous();
        Pi pi = new Pi(g, r);
        ASet aset = new ASet();
        return dijkstra(g, r, aset, pi, prev);
    }
}
