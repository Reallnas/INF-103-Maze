package dijkstra;

import java.util.Hashtable;

public class Previous implements PreviousInterface {
    
    private Hashtable<VertexInterface,VertexInterface> fathers;
    
    public Previous() {
        this.fathers = new Hashtable<VertexInterface,VertexInterface>();
    }
    
    @Override
    public void setFather(VertexInterface son, VertexInterface father) {
        this.fathers.put(son, father);
    }

    @Override
    public VertexInterface getFather(VertexInterface son) {
        return this.fathers.get(son);
    }

}
