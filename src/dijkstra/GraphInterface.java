package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {

    //Check if dst is a successor of src
    public boolean isSuccessor(VertexInterface src, VertexInterface dst);
    
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
