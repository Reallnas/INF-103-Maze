package tp07;

public interface ASetInterface {

    //Add an vertex to the set
    public void add(VertexInterface v);
    
    //Search if a given vertex is part of the set
    public boolean isPartOf(VertexInterface v);
}
