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
    
    public void evaluateSuccessorsNotinA(ASetInterface a, PreviousInterface p, VertexInterface pivot);
}
