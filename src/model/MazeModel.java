package model;

import maze.Maze;

import java.awt.*;
import javax.swing.event.* ;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ArrayList<MazeBox>> boxes;
    private final ArrayList<ChangeListener> listeners = new ArrayList<>() ;
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
                boxes.get(i).add(new MazeBox(i * boxWidth, j * boxHeight, boxWidth, boxHeight, Color.BLACK));
            }
        }
    }

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
    }

    public void addObserver(ChangeListener listener) {
        listeners.add(listener) ;
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

    public final void paintBoxes(Graphics g) {
        for (ArrayList<MazeBox> column : boxes) {
            for (MazeBox box : column) {
                box.paint(g, false);
            }
        }
        if (selectedBox != null)
            selectedBox.paint(g,true) ;
    }

    public final MazeBox getSelectedBox() {
        return selectedBox;
    }

    public final void setSelectedSBox(MazeBox selectedBox) {
        if (this.selectedBox != selectedBox) {
            this.selectedBox = selectedBox;
            modified = true;
            stateChanges();
        }
    }

    public final void setSelection(int x, int y) {
        for (ArrayList<MazeBox> col : boxes) {
            for (MazeBox box : col) {
                if (box.contains(x, y)) {
                    setSelectedSBox(box);
                    return;
                }
            }
        }
        setSelectedSBox(null);
    }

    public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this) ;
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }
}
