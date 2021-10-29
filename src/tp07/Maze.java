package tp07;

import java.util.ArrayList;

public class Maze implements GraphInterface{

    public ArrayList<ArrayList<MBox>> boxGrid;
    
    public Maze(ArrayList<ArrayList<MBox>> grid) {
        this.boxGrid = grid;
    }

    @Override
    public boolean isSuccessor(VertexInterface pivot, VertexInterface other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getSize() {
        return boxGrid.size()*boxGrid.get(0).size();
    }

    @Override
    public ArrayList<VertexInterface> getAllVertices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getWeight(VertexInterface src, VertexInterface dst) {
        MBox srcBox = (MBox) src;
        MBox dstBox = (MBox) dst;
        if(!srcBox.isWalkable() || !dstBox.isWalkable() || !srcBox.isNeighboorWith(dstBox))
            return Integer.MAX_VALUE;
        else
            return 1;
    }
}
