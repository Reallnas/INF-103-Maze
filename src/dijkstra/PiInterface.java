package dijkstra;

public interface PiInterface {

    /**
     * Set the valuation of all vertex except pivot to +infinity and set pivot valuation to 0.
     */
    void initialize(GraphInterface g, VertexInterface pivot);

    /**
     * Get the distance from start of a given vertex.
     *
     * @return the distance from start of the specified vertex;
     * +infinity if there is no path between the vertex and the start.
     */
    int getDistance(VertexInterface v);

    /**
     * Set the distance from start of a given vertex to an arbitrary number.
     */
    void setDistance(VertexInterface v, int value);

    /**
     * Get the vertex with the lowest evaluation that is not in aset.
     *
     * @return the vertex with the lowest evaluation that is not in aset.
     */
    VertexInterface getMinimallyEvaluatedVertex(ASetInterface aset);

    /**
     * Set the evaluation of the successors of pivot that are not in aset to the lowest one between
     * their current evaluation and the evaluation from the pivot and change their father accordingly.
     */
    void evaluateSuccessorsNotInA(ASetInterface aset, PreviousInterface p, VertexInterface pivot);
}
