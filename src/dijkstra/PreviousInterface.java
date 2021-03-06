package dijkstra;

public interface PreviousInterface {

    /**
     * Set the father of a given vertex.
     */
    void setFather(VertexInterface son, VertexInterface father);

    /**
     * Get the father of a given vertex.
     *
     * @return the father of the specified vertex if there is one; null otherwise.
     */
    VertexInterface getFather(VertexInterface son);
}
