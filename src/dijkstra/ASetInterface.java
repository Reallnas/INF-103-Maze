package dijkstra;

public interface ASetInterface {

    //Add a vertex to the set
    boolean add(VertexInterface v);

    //Search if a given vertex is part of the set
    boolean contains(Object o);
}
