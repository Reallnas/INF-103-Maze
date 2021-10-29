package tp07;

public interface GraphInterface {

    //Search if other is a successor of pivot
    public boolean isSuccessor(VertexInterface pivot, VertexInterface other);
    
    /*Get the distance between two vertexes, 
     *return +infinity if they are not linked
     */
    public int getDistance(VertexInterface x,VertexInterface y);
    
    //Return the number of vertexes in the graph
    public int getSize();
}
