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

    /**
     * Get the width of the maze.
     *
     * @return The width of the maze.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the maze.
     *
     * @return The height of the maze.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the root of the maze.
     *
     * @return A reference to the root of the maze if there is one defined; null otherwise.
     */
    public VertexInterface getRoot() {
        return this.root;
    }

    @Override
    public boolean isSuccessor(VertexInterface src, VertexInterface dst) {
        MBox srcBox = (MBox) src;
        MBox dstBox = (MBox) dst;
        return srcBox.isWalkable() && dstBox.isWalkable() && srcBox.isNeighborWith(dstBox);
    }

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

    /**
     * Get the list of all the empty boxes that are connected to the MBox passed in argument
     *
     * @param vertex the box to list the neighbors of
     * @return The list of all the empty boxes that are connected to the specified MBox
     */
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

    /**
     * Get a set of connected vertices that form a path between the start and the goal.
     *
     * @param p The connections between the vertices (often the result of the Dijkstra function).
     * @return A set of connected vertices that form a path between the start and the goal.
     * Warning: the path may be incomplete if it is not possible to join the start and the goal.
     */
    public ASetInterface getPathToGoal(PreviousInterface p) {
        ASet path = new ASet();
        VertexInterface current = this.goal;
        //We check if the current Vertex is not null
        //to prevent crashes when there is no path between the root and the goal
        while (current != null && !path.contains(this.root)) {
            path.add(current);
            current = p.getFather(current);
        }
        return path;
    }

    /**
     * Get a set of connected vertices that form a path between the start and the goal.
     *
     * @return A set of connected vertices that form a path between the start and the goal if there is one;
     * an empty set otherwise.
     */
    public ASetInterface getPathToGoal() {
        return getPathToGoal(Dijkstra.dijkstra(this, root));
    }

    /**
     * Initialize the maze with the data found in the specified file.
     *
     * @param fileName The name of the file that contains the maze data.
     */
    public final void initFromTextFile(String fileName) {
        int newWidth = 0;
        int newHeight = 0;
        ArrayList<String> mazeRepresentation = new ArrayList<>();
        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
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
            e.printStackTrace();
            return;
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
            mre.printStackTrace();
        }

        System.out.printf("New Maze Width : %d%n", width);
        System.out.printf("New Maze Height : %d%n", height);
    }

    /**
     * Save the maze to the specified file.
     *
     * @param fileName The name of the file where the data will be saved.
     * @return true if the maze has been successfully saved; false otherwise.
     */
    public final boolean saveToTextFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             PrintWriter pw = new PrintWriter(fos)) {
            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    //System.out.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                    pw.print(this.boxGrid.get(x).get(y).getFileRepresentation());
                }
                pw.println();
            }
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Print the maze in the console with the specified path.
     *
     * @param path the vertices that form a path.
     */
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

    /**
     * Print the maze in the console with the path between the start and the goal.
     *
     * @param p The connections between the vertices (often the result of the Dijkstra function).
     */
    public final void printWithPath(PreviousInterface p) {
        this.printWithPath(this.getPathToGoal(p));
    }

    /**
     * Initialize an empty maze with the specified width and height.
     *
     * @param width  The new width of the maze.
     * @param height The new height of the maze.
     */
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

    /**
     * Remove the start of the maze if there is one and replace it with an empty box.
     * If there is none, nothing happens.
     */
    private void removeStart() {
        if (root != null) {
            int oldRootX = root.getX();
            int oldRootY = root.getY();
            this.boxGrid.get(oldRootX).set(oldRootY, new EBox(this, oldRootX, oldRootY));
        }
    }

    /**
     * Remove the goal of the maze if there is one and replace it with an empty box.
     * If there is none, nothing happens.
     */
    private void removeGoal() {
        if (goal != null) {
            int oldGoalX = goal.getX();
            int oldGoalY = goal.getY();
            this.boxGrid.get(oldGoalX).set(oldGoalY, new EBox(this, oldGoalX, oldGoalY));
        }
    }

    /**
     * Set the start of the maze at the given coordinates.
     * If the start was already defined, it replaces the old one with an empty box.
     * If the new start is at the same coordinates as the current goal, it removes the current goal.
     *
     * @param x The X coordinates of the start.
     * @param y The Y coordinates of the start.
     */
    public void setNewStart(int x, int y) {
        System.out.printf("New Start %d %d%n", x, y);
        //If we overwrite the current goal, we need to update the reference to avoid referencing an outdated Box
        if (goal != null && goal.getX() == x && goal.getY() == y) {
            goal = null;
        }

        removeStart();
        this.root = new DBox(this, x, y);
        this.boxGrid.get(x).set(y, this.root);
    }

    /**
     * Set the goal of the maze at the given coordinates.
     * If the goal was already defined, it replaces the old one with an empty box.
     * If the new goal is at the same coordinates as the current start, it removes the current start.
     *
     * @param x The X coordinates of the goal.
     * @param y The Y coordinates of the goal.
     */
    public void setNewGoal(int x, int y) {
        System.out.printf("New Goal %d %d%n", x, y);
        //If we overwrite the current root, we need to update the reference to avoid referencing an outdated Box
        if (root != null && root.getX() == x && root.getY() == y) {
            root = null;
        }

        removeGoal();
        this.goal = new ABox(this, x, y);
        this.boxGrid.get(x).set(y, this.goal);
    }

    /**
     * Change the box type at the specified coordinates.
     * If it was an empty box, the start or the goal, it will be changed to a wall.
     * If it was a wall, it will be changed to an empty box.
     *
     * @param x The X coordinates of the box.
     * @param y The Y coordinates of the box.
     */
    public void changeBoxType(int x, int y) {
        //If we overwrite the current root or goal, we need to update the reference to avoid referencing an outdated Box
        if (goal != null && goal.getX() == x && goal.getY() == y) {
            goal = null;
        } else if (root != null && root.getX() == x && root.getY() == y) {
            root = null;
        }

        if (!boxGrid.get(x).get(y).isWalkable()) {
            this.boxGrid.get(x).set(y, new EBox(this, x, y));
            System.out.printf("Changed to Empty Box %d %d%n", x, y);
        } else {
            this.boxGrid.get(x).set(y, new WBox(this, x, y));
            System.out.printf("Changed to Wall Box %d %d%n", x, y);
        }
    }

    /**
     * Check if it is theoretically possible to find a path between the start and the goal (ie they are both set).
     *
     * @return true if the start and the goal are set; false otherwise.
     */
    public boolean canFindAPath() {
        return root != null && goal != null;
    }

    /**
     * Get the box at the specified coordinates.
     *
     * @param x The X coordinates of the box.
     * @param y The Y coordinates of the box.
     * @return The box at the specified coordinates
     */
    public MBox getMBoxByCoords(int x, int y) {
        return boxGrid.get(x).get(y);
    }
}
