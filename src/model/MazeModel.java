package model;

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
    //The number of box in a line
    private int nb_box_x = 10;
    //The number of box in a column
    private int nb_box_y = 10;
    private float boxWidth = 80;
    private float boxHeight = 80;

    public MazeModel() {
        boxes = new ArrayList<>();
        for (int i = 0; i < nb_box_x; i++) {
            boxes.add(new ArrayList<>());
            for (int j = 0; j < nb_box_y; j++) {
                boxes.get(i).add(new MazeBox(i, j, boxWidth, boxHeight));
            }
        }
    }

    public void saveToFile() {
        System.out.println("Save to File");
        modified = false;
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
        System.out.println("New Start");
        modified = true;
        stateChanges();
    }

    public void setSelectedBoxAsGoal() {
        System.out.println("New Goal");
        modified = true;
        stateChanges();
    }

    public void changeSelectedBoxType() {
        System.out.println("Changed Type");
        modified = true;
        stateChanges();
    }
}
