package dijkstra;

import java.util.HashSet;

public final class ASet extends HashSet<VertexInterface> implements ASetInterface {

    public boolean contains(VertexInterface v) {
        return super.contains(v);
    }
}
