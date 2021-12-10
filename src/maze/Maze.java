package maze;

import java.util.ArrayList;

import dijkstra.*;

import java.io.*;

public class Maze implements GraphInterface{

    private ArrayList<ArrayList<MBox>> boxGrid;
    private ABox goal = null;
    private DBox root = null;
    private int horizontalSize = 0;
    private int verticalSize = 0;

    public VertexInterface getRoot()
    {
        return this.root;
    }
    
    public VertexInterface getGoal()
    {
        return this.goal;
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
       
       if(vBox.getX()>0)
       {
           MBox leftNeighbor = boxGrid.get(vBox.getX()-1).get(vBox.getY());
           //Look up if the left neighbor is an empty box
           if(isSuccessor(vBox,leftNeighbor))
               successors.add(leftNeighbor);
       }

       if(vBox.getX()<horizontalSize-1)
       {
           MBox rightNeighbor = boxGrid.get(vBox.getX()+1).get(vBox.getY());
         //Look up if the right neighbor is an empty box
           if(isSuccessor(vBox,rightNeighbor))
               successors.add(rightNeighbor);
       }
       
       if(vBox.getY()>0)
       {
           MBox upNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY()-1);
         //Look up if the upper neighbor is an empty box
           if(isSuccessor(vBox,upNeighbor))
               successors.add(upNeighbor);
       }
       
       if(vBox.getY()<verticalSize-1)
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
        if(!isSuccessor(src,dst))
            return Integer.MAX_VALUE;
        else
            return 1;
    }
    
    public ASetInterface getPathToGoal(PreviousInterface p)
    {
        ASet path = new ASet();
        VertexInterface current = this.goal;
        while(!path.contains(this.root))
        {
            path.add(current);
            current = p.getFather(current);
        }
        return path;
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
                //System.out.println(str);
                nbLine += 1;
                int nbColumn = str.length();
                if(horizontalSize == 0)
                {
                    horizontalSize = nbColumn;
                    boxGrid = new ArrayList<ArrayList<MBox>>();
                    for(int i = 0; i < nbColumn; i++)
                    {
                        boxGrid.add(new ArrayList<MBox>());
                    }
                }
                else if(nbColumn != horizontalSize)
                    throw new MazeReadingException("Error: Maze has varying line size",fileName,nbLine);
                
                for(int i = 0; i < horizontalSize; i++)
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
                        this.goal = new ABox(this,i,nbLine-1);
                        boxGrid.get(i).add(this.goal);
                    } else if(currentCharacter == 'D')
                    {
                        this.root = new DBox(this,i,nbLine-1);
                        boxGrid.get(i).add(this.root);
                    } else
                        throw new MazeReadingException("Error: Unknown box type: " + currentCharacter,fileName,nbLine);
                }
                //System.out.println(boxGrid);
                str = br.readLine();
            } 
            this.verticalSize = nbLine;
        
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
    
    public final void saveToTextFile(String fileName)
    {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try
        {
            fos = new FileOutputStream(fileName) ;
            pw = new PrintWriter(fos);
            
            //System.out.print(this.horizontalSize);
            //System.out.print(this.verticalSize);
            for(int y = 0; y <this.verticalSize; y++)
            {
                for(int x = 0; x <this.horizontalSize; x++)
                {
                    //System.out.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                    pw.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                }
                pw.println();
            }
            pw.flush();
        }
        catch (Exception e) {
            System.out.print(e);
        }
        finally 
        {
            try { fos.close() ; } catch (Exception e) {} ;
            try { pw.close() ; } catch (Exception e) {} ;
        }
    }
    
    public final void printWithPath(ASetInterface path)
    {
        for(int y = 0; y <this.verticalSize; y++)
        {
            for(int x = 0; x <this.horizontalSize; x++)
            {
                MBox current = this.boxGrid.get(x).get(y);
                char representation = current.getFileRepresentation();
                if(path.contains(current))
                    representation = '.';
                System.out.print(representation);
            }
            System.out.println();
        }
    }
    
    public final void printWithPath(PreviousInterface p)
    {
        this.printWithPath(this.getPathToGoal(p));
    }
}
