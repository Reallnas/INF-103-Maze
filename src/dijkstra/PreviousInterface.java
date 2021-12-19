package dijkstra;

public interface PreviousInterface {

    //Set the father of a given vertex
    void setFather(VertexInterface son, VertexInterface father);

    //Get the father of a given vertex
    VertexInterface getFather(VertexInterface son);
}
