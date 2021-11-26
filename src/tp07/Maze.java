package tp07;

import java.util.ArrayList;

public class Maze implements GraphInterface{

    public ArrayList<ArrayList<MBox>> boxGrid;
    public int horizontalSize;
    public int verticalSize;
    
    public Maze(ArrayList<ArrayList<MBox>> grid) {
        this.boxGrid = grid;
        this.horizontalSize = grid.size();
        this.verticalSize = grid.get(0).size();
    }

    @Override
    public boolean isSuccessor(VertexInterface src, VertexInterface dst) {
        MBox srcBox = (MBox) src;
        MBox dstBox = (MBox) dst;
        if(!srcBox.isWalkable() || !dstBox.isWalkable() || !srcBox.isNeighborWith(dstBox))
            return false;
        else
            return true;
    }

    @Override
    public int getSize() {
        return horizontalSize*verticalSize;
    }

    @Override
    public ArrayList<VertexInterface> getAllVertices() {
        ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>(getSize());
        for(int i = 0; i < horizontalSize; i++)
        {
            vertices.addAll(verticalSize*i, boxGrid.get(i));
        }
        return vertices;
    }

    @Override
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        MBox vBox = (MBox) vertex;
        ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
       
       if(vBox.getX()>=0)
       {
           MBox leftNeighbor = boxGrid.get(vBox.getX()-1).get(vBox.getY());
           //Look up if the left neighbor is an empty box
           if(isSuccessor(vBox,leftNeighbor))
               successors.add(leftNeighbor);
       }

       if(vBox.getX()<horizontalSize)
       {
           MBox rightNeighbor = boxGrid.get(vBox.getX()+1).get(vBox.getY());
         //Look up if the right neighbor is an empty box
           if(isSuccessor(vBox,rightNeighbor))
               successors.add(rightNeighbor);
       }
       
       if(vBox.getY()>=0)
       {
           MBox upNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY()-1);
         //Look up if the upper neighbor is an empty box
           if(isSuccessor(vBox,upNeighbor))
               successors.add(upNeighbor);
       }
       
       if(vBox.getY()<verticalSize)
       {
           MBox downNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY()+1);
         //Look up if the lower neighbor is an empty box
           if(isSuccessor(vBox,downNeighbor))
               successors.add(downNeighbor);
       }
       
        return successors;
    }

    @Override
    public int getWeight(VertexInterface src, VertexInterface dst) {
        if(isSuccessor(src,dst))
            return Integer.MAX_VALUE;
        else
            return 1;
    }
}
