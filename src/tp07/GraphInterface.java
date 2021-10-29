package tp07;

import java.util.ArrayList;

public interface GraphInterface {

    //Search if other is a successor of pivot
    public boolean isSuccessor(VertexInterface pivot, VertexInterface other);
    
    //Returns the number of vertices in the graph
    public int getSize();
    
    //Returns the list of all vertices in the graph
    public ArrayList<VertexInterface> getAllVertices() ;
    
    //Returns the successors of a given vertex
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) ;
    
    /* Returns the weight of the link between two vertices
     * Returns +infinity if they are not linked
     */
    public int getWeight(VertexInterface src,VertexInterface dst) ;
}
