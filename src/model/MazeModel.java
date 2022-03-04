package model;

import dijkstra.ASetInterface;
import dijkstra.VertexInterface;
import maze.MBox;
import maze.Maze;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public final class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ChangeListener> listeners = new ArrayList<>();
    private ArrayList<ArrayList<MazeBox>> boxes;
    private MazeBox selectedBox = null;
    private boolean modified = true;
    private ASetInterface path;
    //The number of box in a line
    private int nbBoxX = 10;
    //The number of box in a column
    private int nbBoxY = 10;
    private float boxWidth = 80;
    private float boxHeight = 80;
    private float windowsWidth = boxWidth * nbBoxX;
    private float windowsHeight = boxHeight * nbBoxY;
    private String currentFile = null;

    public MazeModel() {
        initializeEmptyMaze(nbBoxX, nbBoxY);
    }

    /**
     * Associate a file with the model. This file will be used for future saves.
     *
     * @param filename The path of the file to associate with the model.
     */
    public void setCurrentFile(String filename) {
        if (!filename.endsWith(".txt"))
            filename += ".txt";
        currentFile = filename;
    }

    /**
     * Test if the model was recently loaded from or saved to a file.
     *
     * @return true if the model was recently loaded from or saved to a file; false otherwise.
     */
    public boolean hasACurrentFile() {
        return currentFile != null;
    }

    /**
     * Save the maze to the file associated with the model.
     *
     * @return true if there was no error during the saving process; false if there was an error, there was no file
     * associated (loaded from or saved to) or no modifications were made since the previous save.
     */
    public boolean saveToFile() {
        if (!modified || currentFile == null)
            return false;

        System.out.printf("Saving to file: %s\n", currentFile);
        modified = false;
        stateChanges();
        return maze.saveToTextFile(currentFile);
    }

    /**
     * Update the color of the visual representation of the maze's boxes.
     * This function must be called every time the "maze" member is modified to prevent desync with the view.
     */
    private void updateBoxesColor() {
        System.out.println("Updating Boxes Color...");
        for (VertexInterface vi : maze.getAllVertices()) {
            MBox mb = (MBox) vi;
            int boxX = mb.getX();
            int boxY = mb.getY();
            MazeBox box = boxes.get(boxX).get(boxY);
            char boxType = mb.getFileRepresentation();
            //System.out.printf("%d %d %c%n",mb.getX(),mb.getY(),boxType);
            box.setBackgroundColorFromRepresentation(boxType);
            box.markAsInPath(path != null && path.contains(mb));
        }
        stateChanges();
    }

    /**
     * Update the size of the boxes to fit the panel.
     */
    private void updateBoxesSize() {
        System.out.println("Updating Boxes Size...");
        boxWidth = windowsWidth / nbBoxX;
        boxHeight = windowsHeight / nbBoxY;
        for (int x = 0; x < nbBoxX; x++) {
            for (int y = 0; y < nbBoxY; y++) {
                boxes.get(x).get(y).setRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
            }
        }
    }

    /**
     * Load the maze from a file. It must have a rectangular shape with no hole.
     *
     * @param filename The path of the file to associate with the model.
     */
    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
        currentFile = filename;
        if (nbBoxX != maze.getWidth() || nbBoxY != maze.getHeight()) {
            this.boxes = new ArrayList<>();
            for (int x = 0; x < maze.getWidth(); x++) {
                boxes.add(new ArrayList<>());
                for (int y = 0; y < maze.getHeight(); y++) {
                    boxes.get(x).add(new MazeBox(x, y, boxWidth, boxHeight));
                }
            }
        }
        nbBoxX = maze.getWidth();
        nbBoxY = maze.getHeight();
        modified = false;
        path = null;
        updateSelectedBox();
        updateBoxesColor();
        updateBoxesSize();
    }

    /**
     * Add a listener to notify when the model is modified and the view need to be updated.
     *
     * @param listener A listener.
     */
    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Signal to the model that the view's size changed and to adapt the maze to fit the new size.
     * It is useful when there is only one view, and we want to resize the maze when its size changes.
     *
     * @param width  The width of the windows to fit.
     * @param height The height of the windows to fit.
     */
    public void notifyWindowSizeChange(float width, float height) {
        windowsWidth = width;
        windowsHeight = height;
        updateBoxesSize();
    }

    /**
     * Test if the file was modified since the last save or if the maze was just reset (to allow saving an empty maze).
     *
     * @return true if the maze was modified since the last save or if the maze was just reset; false otherwise.
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Paint the boxes on the given graphic surface.
     *
     * @param g The graphic surface to paint the boxes on.
     */
    public void paintBoxes(Graphics g) {
        for (ArrayList<MazeBox> column : boxes) {
            for (MazeBox box : column) {
                box.paint(g, false);
            }
        }
        if (selectedBox != null)
            selectedBox.paint(g, true);
    }

    /**
     * Test if a box has been selected.
     *
     * @return true if a box has been selected by the user; false otherwise.
     */
    public boolean hasASelectedBox() {
        return selectedBox != null;
    }

    /**
     * Allow to set the given MazeBox as selected.
     *
     * @param selectedBox The box to set as selected.
     */
    public void setSelectedBox(MazeBox selectedBox) {
        if (this.selectedBox != selectedBox) {
            this.selectedBox = selectedBox;
            stateChanges();
        }
    }

    /**
     * Update the selected box when the maze was just reset or loaded from a file.
     * It will try to set as selected the box in the new maze at the same coordinates as in the old maze.
     * If the old selected box is outside the boundaries of the new maze, no box is set as selected.
     */
    private void updateSelectedBox() {
        if (hasASelectedBox()) {
            int oldSelectedBoxX = selectedBox.getXCoordinate();
            int oldSelectedBoxY = selectedBox.getYCoordinate();
            if (oldSelectedBoxX < nbBoxX && oldSelectedBoxY < nbBoxY) {
                selectedBox = boxes.get(oldSelectedBoxX).get(oldSelectedBoxY);
            } else {
                selectedBox = null;
            }
        }
    }

    /**
     * Test if the current selected box is a wall.
     *
     * @return true if the current selected box is a wall; false otherwise.
     */
    public boolean isSelectedBoxAWall() {
        return maze.getVertexTypeByCoords(selectedBox.getXCoordinate(), selectedBox.getYCoordinate()) == 'W';
    }

    /**
     * Test if the current selected box is an empty box, the start or the goal.
     *
     * @return true if the current selected box is an empty box, the start or the goal; false otherwise.
     */
    public boolean isSelectedBoxEmpty() {
        char type = maze.getVertexTypeByCoords(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
        return type == 'E' || type == 'A' || type == 'D';
    }

    /**
     * Get the box which contains the point at the specified coordinates.
     *
     * @param pixX The X coordinates of the point (in pixels).
     * @param pixY The Y coordinates of the point (in pixels).
     * @return A reference to the box which contains the point at the specified coordinates if there is one; null otherwise.
     */
    private MazeBox getBox(int pixX, int pixY) {
        for (ArrayList<MazeBox> col : boxes) {
            for (MazeBox box : col) {
                if (box.contains(pixX, pixY)) {
                    return box;
                }
            }
        }
        return null;
    }

    /**
     * Set as selected the box that contains the given coordinates.
     * If it was already selected, it will be unselected.
     *
     * @param x The x coordinate (in pixel) of the selection.
     * @param y The y coordinate (in pixel) of the selection.
     */
    public void setSelection(int x, int y) {
        setSelectedBox(getBox(x, y));
    }

    /**
     * Notify all listener (set with addListener) that some changes occurred and that they need to be updated.
     */
    private void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }

    /**
     * Set the current selected box as the start of the maze.
     * Nothing happens if no box is currently selected.
     */
    public void setSelectedBoxAsStart() {
        if (hasASelectedBox()) {
            maze.setNewStart(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            modified = true;
            updateBoxesColor();
        }
    }

    /**
     * Set the current selected box as the goal of the maze.
     * Nothing happens if no box is currently selected.
     */
    public void setSelectedBoxAsGoal() {
        if (hasASelectedBox()) {
            modified = true;
            maze.setNewGoal(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            updateBoxesColor();
        }
    }

    /**
     * Set the box at the specified coordinates as a wall if it was empty or as an empty box if it was a wall
     *
     * @param x The X coordinate of the box
     * @param y The Y coordinate of the box
     */
    private void changeBoxType(int x, int y) {
        if (x >= 0 && x < nbBoxX && y >= 0 && y < nbBoxY) {
            modified = true;
            maze.changeBoxType(x, y);
            updateBoxesColor();
        }
    }

    /**
     * Set the current selected box as a wall if it was empty or as an empty box if it was a wall.
     * Nothing happens if no box is currently selected.
     */
    public void changeSelectedBoxType() {
        if (hasASelectedBox()) {
            changeBoxType(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
        }
    }

    /**
     * Set the box which contains the point at the specified coordinates as a wall if it was empty or as an empty box if it was a wall.
     * If no box contains the specified point, nothing happens.
     *
     * @param pixX The X coordinates of the point (in pixels).
     * @param pixY The Y coordinates of the point (in pixels).
     */
    public void changeHoveredBoxType(int pixX, int pixY) {
        MazeBox mazeBox = getBox(pixX, pixY);
        if (mazeBox != null) {
            changeBoxType(mazeBox.getXCoordinate(), mazeBox.getYCoordinate());
        }
    }

    /**
     * Test if the maze contains a start and a goal.
     * Warning: It doesn't tell if a path exists between the start and the goal, see the "hasFoundAPath" method for that.
     *
     * @return true if the maze contains a start and a goal; false otherwise.
     */
    public boolean canFindAPath() {
        return maze.canFindAPath();
    }

    /**
     * Apply dijkstra algorithm to find a path between the start and the goal.
     */
    public void FindAPath() {
        if (maze.canFindAPath()) {
            System.out.println("Finding a Path...");
            path = maze.getPathToGoal();
            if (hasFoundAPath())
                updateBoxesColor();
        }
    }

    /**
     * Create a new maze with the same size as the previous one containing only empty boxes.
     */
    public void reset() {
        modified = true;
        path = null;
        currentFile = null;
        initializeEmptyMaze(nbBoxX, nbBoxY);
    }

    /**
     * Create a new maze of the given size containing only empty boxes.
     *
     * @param nbBoxX The number of box horizontally
     * @param nbBoxY The number of box vertically
     */
    public void initializeEmptyMaze(int nbBoxX, int nbBoxY) {
        this.maze.initializeEmptyMaze(nbBoxX, nbBoxY);
        this.nbBoxX = nbBoxX;
        this.nbBoxY = nbBoxY;
        boxes = new ArrayList<>();
        for (int x = 0; x < nbBoxX; x++) {
            boxes.add(new ArrayList<>());
            for (int y = 0; y < nbBoxY; y++) {
                boxes.get(x).add(new MazeBox(x, y, boxWidth, boxHeight));
            }
        }
        updateSelectedBox();
        updateBoxesColor();
        updateBoxesSize();
    }

    /**
     * Test if a path has been found between the start and the goal.
     *
     * @return true if "FindAPath" has been run before and found a path; false otherwise.
     */
    public boolean hasFoundAPath() {
        return path != null && path.contains(maze.getRoot());
    }
}
