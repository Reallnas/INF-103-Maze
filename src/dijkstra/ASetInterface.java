package dijkstra;

public interface ASetInterface {

    /**
     * Add a vertex to the set.
     * @return true if the vertex has been successfully added to the set;
     * false otherwise (ex: it was already present in the set).
     */
    boolean add(VertexInterface v);

    /**
     * Search if a given vertex is in the set.
     * @return true if the specified vertex is in the set; false otherwise.
     */
    boolean contains(VertexInterface v);
}
