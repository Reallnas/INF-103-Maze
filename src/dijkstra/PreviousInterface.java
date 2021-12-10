package dijkstra;

public interface PreviousInterface {

    //Set the father of a given vertex
    public void setFather(VertexInterface son, VertexInterface father);

    //Get the father of a given vertex
    public VertexInterface getFather(VertexInterface son);
}
