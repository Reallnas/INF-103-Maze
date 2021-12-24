package maze;

import dijkstra.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Maze implements GraphInterface {

    private ArrayList<ArrayList<MBox>> boxGrid;
    private ABox goal = null;
    private DBox root = null;
    private int width = 0;
    private int height = 0;

    public VertexInterface getRoot() {
        return this.root;
    }

    @Override
    public boolean isSuccessor(VertexInterface src, VertexInterface dst) {
        MBox srcBox = (MBox) src;
        MBox dstBox = (MBox) dst;
        return srcBox.isWalkable() && dstBox.isWalkable() && srcBox.isNeighborWith(dstBox);
    }

    //Return the number of boxes in the maze
    @Override
    public int getSize() {
        return width * height;
    }

    @Override
    public ArrayList<VertexInterface> getAllVertices() {
        ArrayList<VertexInterface> vertices = new ArrayList<>(getSize());
        for (int i = 0; i < width; i++) {
            vertices.addAll(height * i, boxGrid.get(i));
        }
        return vertices;
    }

    //Returns an ArrayList of all the empty boxes that are connected to the MBox passed in argument
    @Override
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
        MBox vBox = (MBox) vertex;
        ArrayList<VertexInterface> successors = new ArrayList<>();

        if (vBox.getX() > 0) {
            MBox leftNeighbor = boxGrid.get(vBox.getX() - 1).get(vBox.getY());
            //Look up if the left neighbor is an empty box
            if (isSuccessor(vBox, leftNeighbor))
                successors.add(leftNeighbor);
        }

        if (vBox.getX() < width - 1) {
            MBox rightNeighbor = boxGrid.get(vBox.getX() + 1).get(vBox.getY());
            //Look up if the right neighbor is an empty box
            if (isSuccessor(vBox, rightNeighbor))
                successors.add(rightNeighbor);
        }

        if (vBox.getY() > 0) {
            MBox upNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY() - 1);
            //Look up if the upper neighbor is an empty box
            if (isSuccessor(vBox, upNeighbor))
                successors.add(upNeighbor);
        }

        if (vBox.getY() < height - 1) {
            MBox downNeighbor = boxGrid.get(vBox.getX()).get(vBox.getY() + 1);
            //Look up if the lower neighbor is an empty box
            if (isSuccessor(vBox, downNeighbor))
                successors.add(downNeighbor);
        }

        return successors;
    }

    @Override
    public int getWeight(VertexInterface src, VertexInterface dst) {
        if (!isSuccessor(src, dst))
            return Integer.MAX_VALUE;
        else
            return 1;
    }

    public ASetInterface getPathToGoal(PreviousInterface p) {
        ASet path = new ASet();
        VertexInterface current = this.goal;
        //Workaround to prevent crashes when there is no path between the root and the goal
        //TODO: remove workaround and fix Dijkstra Algorithm (goal shouldn't have a father if there is no path)
        VertexInterface future = p.getFather(current);
        while (!path.contains(this.root) && future != null) {
            path.add(current);
            current = future;
            future = p.getFather(future);
        }
        return path;
    }

    public final void initFromTextFile(String fileName) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String str = br.readLine();
            int nbLine = 0;
            while (str != null) {
                //System.out.println(str);
                nbLine += 1;
                int nbColumn = str.length();
                if (width == 0) {
                    width = nbColumn;
                    boxGrid = new ArrayList<>();
                    for (int i = 0; i < nbColumn; i++) {
                        boxGrid.add(new ArrayList<>());
                    }
                } else if (nbColumn != width)
                    throw new MazeReadingException("Error: Maze has varying line size", fileName, nbLine);

                for (int i = 0; i < width; i++) {
                    final char currentCharacter = str.charAt(i);
                    if (currentCharacter == 'E') {
                        boxGrid.get(i).add(new EBox(this, i, nbLine - 1));
                    } else if (currentCharacter == 'W') {
                        boxGrid.get(i).add(new WBox(this, i, nbLine - 1));
                    } else if (currentCharacter == 'A') {
                        this.goal = new ABox(this, i, nbLine - 1);
                        boxGrid.get(i).add(this.goal);
                    } else if (currentCharacter == 'D') {
                        this.root = new DBox(this, i, nbLine - 1);
                        boxGrid.get(i).add(this.root);
                    } else
                        throw new MazeReadingException("Error: Unknown box type: " + currentCharacter, fileName, nbLine);
                }
                //System.out.println(boxGrid);
                str = br.readLine();
            }
            this.height = nbLine;

        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
            }
            try {
                br.close();
            } catch (Exception e) {
            }
        }
    }

    public final void saveToTextFile(String fileName) {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            fos = new FileOutputStream(fileName);
            pw = new PrintWriter(fos);

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    //System.out.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                    pw.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                }
                pw.println();
            }
            pw.flush();
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
            try {
                pw.close();
            } catch (Exception e) {
            }
        }
    }

    public final void printWithPath(ASetInterface path) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                MBox current = this.boxGrid.get(x).get(y);
                char representation = current.getFileRepresentation();
                if (path.contains(current))
                    representation = '.';
                System.out.print(representation);
            }
            System.out.println();
        }
    }

    public final void printWithPath(PreviousInterface p) {
        this.printWithPath(this.getPathToGoal(p));
    }

    public void initializeEmptyMaze(int width, int height) {
        goal = null;
        root = null;
        this.width = width;
        this.height = height;
        boxGrid = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            boxGrid.add(new ArrayList<>());
            for (int y = 0; y < height; y++) {
                boxGrid.get(x).add(new EBox(this, x, y));
            }
        }
    }

    public void setNewStart(int x, int y) {
        if (root != null) {
            int oldRootX = root.getX();
            int oldRootY = root.getY();
            this.boxGrid.get(oldRootX).set(oldRootY, new EBox(this, oldRootX, oldRootY));
        }
        this.boxGrid.get(x).set(y, new DBox(this, x, y));
        this.root = (DBox) this.boxGrid.get(x).get(y);
    }

    public void setNewGoal(int x, int y) {
        if (goal != null) {
            int oldGoalX = goal.getX();
            int oldGoalY = goal.getY();
            this.boxGrid.get(oldGoalX).set(oldGoalY, new EBox(this, oldGoalX, oldGoalY));
        }
        this.boxGrid.get(x).set(y, new ABox(this, x, y));
        this.goal = (ABox)this.boxGrid.get(x).get(y);
    }
}
