package dijkstra;

import java.util.Hashtable;

public class Previous implements PreviousInterface {

    private final Hashtable<VertexInterface, VertexInterface> fathers;

    public Previous() {
        this.fathers = new Hashtable<>();
    }

    @Override
    public final void setFather(VertexInterface son, VertexInterface father) {
        this.fathers.put(son, father);
    }

    @Override
    public final VertexInterface getFather(VertexInterface son) {
        return this.fathers.get(son);
    }

}
