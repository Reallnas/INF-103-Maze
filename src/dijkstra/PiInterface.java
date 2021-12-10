package dijkstra;

public interface PiInterface {

    /*Set the valuation of all vertex except 
     *pivot to +infinity and set pivot valuation to 0
     */
    public void initialize(GraphInterface g, VertexInterface pivot);
    
    //Get the distance from start of a given vertex
    public int getDistance(VertexInterface v);
    
    /*Set the distance from start of a given 
     *vertex to an arbitrary number
     */
    public void setDistance(VertexInterface v, int value);
        
    /*Get the vertex with the lowest evaluation 
     *that is not in aset
     */
    public VertexInterface getMinimallyEvaluatedVertex(ASetInterface aset);
    
    /*Set the evaluation of the successors of pivot that are not in aset to the lowest one between 
     * their current evaluation and the evaluation from the pivot and change their father accordingly
     * 
     */
    public void evaluateSuccessorsNotInA(ASetInterface aset, PreviousInterface p, VertexInterface pivot);
}
