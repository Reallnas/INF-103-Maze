package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {

    /**
     * Check if dst is a successor of src.
     *
     * @return true if dst is a successor of src; false otherwise.
     */
    boolean isSuccessor(VertexInterface src, VertexInterface dst);

    /**
     * Get the number of vertices in the graph.
     *
     * @return the number of vertices in the graph (always positive or null).
     */
    int getSize();

    /**
     * Get the list of all vertices in the graph.
     *
     * @return the list of all vertices in the graph.
     */
    ArrayList<VertexInterface> getAllVertices();

    /**
     * Get the successors of a given vertex.
     *
     * @return a list of the successors of a given vertex.
     */
    ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);

    /**
     * Get the weight of the link between two vertices.
     *
     * @return the weight of the link between two vertices (+infinity if they are not linked).
     */
    int getWeight(VertexInterface src, VertexInterface dst);
}
