package tp07;

import java.util.ArrayList;
import java.io.*;

public class Maze implements GraphInterface{

    private ArrayList<ArrayList<MBox>> boxGrid;
    private int horizontalSize = 0;
    private int verticalSize = 0;

    @Override
    public boolean isSuccessor(VertexInterface src, VertexInterface dst) {
        MBox srcBox = (MBox) src;
        MBox dstBox = (MBox) dst;
        if(!srcBox.isWalkable() || !dstBox.isWalkable() || !srcBox.isNeighborWith(dstBox))
            return false;
        else
            return true;
    }

    //Return the number of boxes in the maze
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

    //Returns an ArrayList of all the empty boxes that are connected to the MBox passed in argument
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
    
    public final void initFromTextFile(String fileName)
    {
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String str = br.readLine();
            int nbLine = 0;
            while (str != null)
            {
                System.out.println(str);
                nbLine += 1;
                int nbColumn = str.length();
                if(horizontalSize == 0)
                {
                    horizontalSize = nbColumn;
                    
                    //TODO: fix the fact that it doesn't reserve the capacity that was asked
                    boxGrid = new ArrayList<ArrayList<MBox>>(nbColumn);
                    boxGrid.add(new ArrayList<MBox>());
                }
                else if(nbColumn != horizontalSize)
                    throw new MazeReadingException("Error: Maze has varying line size",fileName,nbLine);
                
                System.out.println(horizontalSize);
                System.out.println(boxGrid);
                for(int i = 0; i < str.length(); i++)
                {
                    final char currentCharacter = str.charAt(i);
                    if(currentCharacter == 'E')
                    {
                        boxGrid.get(i).add(new EBox(this,i,nbLine-1));
                    } else if(currentCharacter == 'W')
                    {
                        boxGrid.get(i).add(new WBox(this,i,nbLine-1));
                    } else if(currentCharacter == 'A')
                    {
                        boxGrid.get(i).add(new ABox(this,i,nbLine-1));
                    } else if(currentCharacter == 'D')
                    {
                        boxGrid.get(i).add(new DBox(this,i,nbLine-1));
                    } else
                        throw new MazeReadingException("Error: Unknown box type: " + currentCharacter,fileName,nbLine);
                }
                str = br.readLine();
            } 
        
        } catch (MazeReadingException mre) {
            System.out.print(mre);
        }
        catch (Exception e) {
            System.out.print(e);
        }
        finally 
        {
            try { fr.close() ; } catch (Exception e) {} ;
            try { br.close() ; } catch (Exception e) {} ;
        }
    }
}
