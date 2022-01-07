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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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
        //To prevent crashes when there is no path between the root and the goal
        VertexInterface future = p.getFather(current);
        while (!path.contains(this.root) && future != null) {
            path.add(current);
            current = future;
            future = p.getFather(future);
        }
        return path;
    }

    public ASetInterface getPathToGoal() {
        return getPathToGoal(Dijkstra.dijkstra(this, root));
    }

    public final void initFromTextFile(String fileName) {
        FileReader fr = null;
        BufferedReader br = null;
        int newWidth = 0;
        int newHeight = 0;
        ArrayList<String> mazeRepresentation = new ArrayList<>();
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String str = br.readLine();
            while (str != null) {
                //System.out.println(str);
                newHeight += 1;
                int nbColumn = str.length();
                if (newWidth == 0) {
                    newWidth = nbColumn;
                } else if (nbColumn != newWidth)
                    throw new MazeReadingException("Error: Maze is not rectangular", fileName, newHeight);
                mazeRepresentation.add(str);
                str = br.readLine();
            }
        } catch (Exception e) {
            System.out.print(e);
            return;
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
        initializeEmptyMaze(newWidth, newHeight);
        try {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    final char currentCharacter = mazeRepresentation.get(y).charAt(x);
                    switch (currentCharacter) {
                        case 'E':
                            boxGrid.get(x).set(y, new EBox(this, x, y));
                            break;
                        case 'W':
                            boxGrid.get(x).set(y, new WBox(this, x, y));
                            break;
                        case 'A':
                            setNewGoal(x, y);
                            break;
                        case 'D':
                            setNewStart(x, y);
                            break;
                        default:
                            throw new MazeReadingException("Error: Unknown box type: " + currentCharacter, fileName, y);
                    }
                }
            }
        } catch (MazeReadingException mre) {
            System.out.print(mre);
        }

        System.out.printf("New Maze Width : %d%n", width);
        System.out.printf("New Maze Height : %d%n", height);
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
        System.out.printf("New Start %d %d%n", x, y);
        //If we overwrite the current goal, we need to update the reference to avoid referencing an outdated Box
        if (goal != null && goal.getX() == x && goal.getY() == y) {
            goal = null;
        }

        if (root != null) {
            int oldRootX = root.getX();
            int oldRootY = root.getY();
            this.boxGrid.get(oldRootX).set(oldRootY, new EBox(this, oldRootX, oldRootY));
        }
        this.boxGrid.get(x).set(y, new DBox(this, x, y));
        this.root = (DBox) this.boxGrid.get(x).get(y);
    }

    public void setNewGoal(int x, int y) {
        System.out.printf("New Goal %d %d%n", x, y);
        //If we overwrite the current root, we need to update the reference to avoid referencing an outdated Box
        if (root != null && root.getX() == x && root.getY() == y) {
            root = null;
        }

        if (goal != null) {
            int oldGoalX = goal.getX();
            int oldGoalY = goal.getY();
            this.boxGrid.get(oldGoalX).set(oldGoalY, new EBox(this, oldGoalX, oldGoalY));
        }
        this.boxGrid.get(x).set(y, new ABox(this, x, y));
        this.goal = (ABox) this.boxGrid.get(x).get(y);

    }

    public void changeBoxType(int x, int y) {
        //If we overwrite the current root or goal, we need to update the reference to avoid referencing an outdated Box
        if (goal != null && goal.getX() == x && goal.getY() == y) {
            goal = null;
        } else if (root != null && root.getX() == x && root.getY() == y) {
            root = null;
        }

        if (boxGrid.get(x).get(y).getFileRepresentation() == 'W') {
            this.boxGrid.get(x).set(y, new EBox(this, x, y));
            System.out.printf("Changed to Empty Box %d %d%n", x, y);
        } else {
            this.boxGrid.get(x).set(y, new WBox(this, x, y));
            System.out.printf("Changed to Wall Box %d %d%n", x, y);
        }
    }

    public boolean canFindAPath() {
        return root != null && goal != null;
    }

    public char getVertexTypeByCoords(int x, int y) {
        return boxGrid.get(x).get(y).getFileRepresentation();
    }
}
