package model;

import dijkstra.ASetInterface;
import dijkstra.VertexInterface;
import maze.MBox;
import maze.Maze;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ArrayList<MazeBox>> boxes;
    private final ArrayList<ChangeListener> listeners = new ArrayList<>();
    private MazeBox selectedBox = null;
    private boolean modified = false;
    private ASetInterface path;
    //The number of box in a line
    private int nb_box_x = 10;
    //The number of box in a column
    private int nb_box_y = 10;
    private float boxWidth = 80;
    private float boxHeight = 80;

    public MazeModel() {
        boxes = new ArrayList<>();
        for (int x = 0; x < nb_box_x; x++) {
            boxes.add(new ArrayList<>());
            for (int y = 0; y < nb_box_y; y++) {
                boxes.get(x).add(new MazeBox(x, y, boxWidth, boxHeight));
            }
        }
        this.maze.initializeEmptyMaze(nb_box_x, nb_box_y);
    }

    public void saveToFile() {
        System.out.println("Save to File");
        modified = false;
        stateChanges();
    }

    public void updateBoxesColor() {
        for (VertexInterface vi : maze.getAllVertices()) {
            MBox mb = (MBox) vi;
            MazeBox box = boxes.get(mb.getX()).get(mb.getY());

            //TODO: Maybe make MazeModel inherit from Maze and make several subclasses of *Box to have the
            // color linked to the type of the box instead of this switch
            char boxType = mb.getFileRepresentation();
            switch (boxType) {
                case 'E':
                    box.setBackgroundColor(Color.WHITE);
                    break;
                case 'W':
                    box.setBackgroundColor(Color.DARK_GRAY);
                    break;
                case 'A':
                    box.setBackgroundColor(Color.GREEN);
                    break;
                case 'D':
                    box.setBackgroundColor(Color.RED);
                    break;
                default:
                    box.setBackgroundColor(Color.MAGENTA);
            }
            if (path != null && path.contains(mb) && boxType != 'A' && boxType != 'D') {
                box.setBackgroundColor(Color.YELLOW);
            }
        }
        stateChanges();
    }

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
    }

    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyWindowSizeChange(float width, float height) {
        boxWidth = width / nb_box_x;
        boxHeight = height / nb_box_y;
        for (int x = 0; x < nb_box_x; x++) {
            for (int y = 0; y < nb_box_y; y++) {
                boxes.get(x).get(y).setRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
            }
        }
    }

    public boolean isModified() {
        return modified;
    }

    public final void paintBoxes(Graphics g) {
        for (ArrayList<MazeBox> column : boxes) {
            for (MazeBox box : column) {
                box.paint(g, false);
            }
        }
        if (selectedBox != null)
            selectedBox.paint(g, true);
    }

    public final boolean hasASelectedBox() {
        return selectedBox != null;
    }

    public final MazeBox getSelectedBox() {
        return selectedBox;
    }

    public final void setSelectedBox(MazeBox selectedBox) {
        if (this.selectedBox != selectedBox) {
            this.selectedBox = selectedBox;
            stateChanges();
        }
    }

    public final void setSelection(int x, int y) {
        for (ArrayList<MazeBox> col : boxes) {
            for (MazeBox box : col) {
                if (box.contains(x, y)) {
                    if (box == selectedBox)
                        setSelectedBox(null);
                    else
                        setSelectedBox(box);
                    return;
                }
            }
        }
        setSelectedBox(null);
    }

    public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }

    public void setSelectedBoxAsStart() {
        if (hasASelectedBox()) {
            System.out.println("New Start");
            maze.setNewStart(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            modified = true;
            updateBoxesColor();
        }
    }

    public void setSelectedBoxAsGoal() {
        if (hasASelectedBox()) {
            System.out.println("New Goal");
            modified = true;
            maze.setNewGoal(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            updateBoxesColor();
        }
    }

    public void changeSelectedBoxType() {
        if (hasASelectedBox()) {
            System.out.println("Changed Type");
            modified = true;
            maze.changeBoxType(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            updateBoxesColor();
        }
    }

    public boolean canFindAPath() {
        return maze.canFindAPath();
    }

    public void FindAPath() {
        if (maze.canFindAPath()) {
            System.out.println("Finding a Path...");
            path = maze.getPathToGoal();
            updateBoxesColor();
        }
    }
}
