package tp07;

import java.util.ArrayList;

public class Maze implements GraphInterface{

    public ArrayList<ArrayList<MBox>> boxGrid;
    
    public Maze(ArrayList<ArrayList<MBox>> grid) {
        this.boxGrid = grid;
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
        return boxGrid.size()*boxGrid.get(0).size();
    }

    @Override
    public ArrayList<VertexInterface> getAllVertices() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        MBox vBox = (MBox) vertex;
        ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
       
       if(vBox.getX()>=0)
       {
           MBox leftNeighbor = boxGrid.get(vBox.getX()-1).get(vBox.getY());
           //Look up if the left neighbor is an empty box
           if(leftNeighbor.isWalkable())
               successors.add(leftNeighbor);
       }

       if(vBox.getX()<boxGrid.size())
       {
           MBox rightNeighbor = boxGrid.get(vBox.getX()+1).get(vBox.getY());
         //Look up if the right neighbor is an empty box
           if(rightNeighbor.isWalkable())
               successors.add(rightNeighbor);
       }
       
       if(vBox.getY()>=0)
       {
           MBox upNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY()-1);
         //Look up if the upper neighbor is an empty box
           if(upNeighbor.isWalkable())
               successors.add(upNeighbor);
       }
       
       if(vBox.getY()<boxGrid.get(0).size())
       {
           MBox downNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY()+1);
         //Look up if the lower neighbor is an empty box
           if(downNeighbor.isWalkable())
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
