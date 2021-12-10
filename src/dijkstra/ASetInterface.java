package dijkstra;

public interface ASetInterface {

    //Add a vertex to the set
    public boolean add(VertexInterface v);

    //Search if a given vertex is part of the set
    public boolean contains(Object o);
}
