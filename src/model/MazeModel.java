package model;

import dijkstra.ASetInterface;
import dijkstra.VertexInterface;
import maze.MBox;
import maze.Maze;
import ui.MainWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ChangeListener> listeners = new ArrayList<>();
    private ArrayList<ArrayList<MazeBox>> boxes;
    private MazeBox selectedBox = null;
    private boolean modified = true;
    private ASetInterface path;
    //The number of box in a line
    private int nb_box_x = 10;
    //The number of box in a column
    private int nb_box_y = 10;
    private float boxWidth = 80;
    private float boxHeight = 80;
    private float windowsWidth = boxWidth * nb_box_x;
    private float windowsHeight = boxHeight * nb_box_y;
    private String currentFile = null;

    public MazeModel() {
        initializeEmptyMaze(nb_box_x, nb_box_y);
        this.maze.initializeEmptyMaze(nb_box_x, nb_box_y);
    }

    public void setCurrentFile(String filename) {
        if (!filename.endsWith(".txt"))
            filename += ".txt";
        currentFile = filename;
    }

    public boolean hasACurrentFile() {
        return currentFile != null;
    }

    public boolean saveToFile() {
        if (!modified || currentFile == null)
            return false;

        System.out.printf("Saving to file: %s\n", currentFile);
        maze.saveToTextFile(currentFile);
        modified = false;
        stateChanges();
        return true;
    }

    private void updateBoxesColor() {
        System.out.println("Updating Boxes Color...");
        for (VertexInterface vi : maze.getAllVertices()) {
            MBox mb = (MBox) vi;
            int boxX = mb.getX();
            int boxY = mb.getY();
            MazeBox box = boxes.get(boxX).get(boxY);

            //TODO: Maybe make MazeModel inherit from Maze and make several subclasses of *Box to have the
            // color linked to the type of the box instead of this switch
            char boxType = mb.getFileRepresentation();
            //System.out.printf("%d %d %c%n",mb.getX(),mb.getY(),boxType);
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

    private void updateBoxesSize() {
        System.out.println("Updating Boxes Size...");
        boxWidth = windowsWidth / nb_box_x;
        boxHeight = windowsHeight / nb_box_y;
        for (int x = 0; x < nb_box_x; x++) {
            for (int y = 0; y < nb_box_y; y++) {
                boxes.get(x).get(y).setRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
            }
        }
    }

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
        currentFile = filename;
        if (nb_box_x != maze.getWidth() || nb_box_y != maze.getHeight()) {
            this.boxes = new ArrayList<>();
            for (int x = 0; x < maze.getWidth(); x++) {
                boxes.add(new ArrayList<>());
                for (int y = 0; y < maze.getHeight(); y++) {
                    boxes.get(x).add(new MazeBox(x, y, boxWidth, boxHeight));
                }
            }
        }
        nb_box_x = maze.getWidth();
        nb_box_y = maze.getHeight();
        modified = false;
        path = null;
        updateBoxesColor();
        updateBoxesSize();
    }

    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    public void notifyWindowSizeChange(float width, float height) {
        windowsWidth = width;
        windowsHeight = height;
        updateBoxesSize();
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

    public final void setSelectedBox(MazeBox selectedBox) {
        if (this.selectedBox != selectedBox) {
            this.selectedBox = selectedBox;
            stateChanges();
        }
    }

    public final boolean isSelectedBoxAWall() {
        return maze.getVertexTypeByCoords(selectedBox.getXCoordinate(), selectedBox.getYCoordinate()) == 'W';
    }

    public final boolean isSelectedBoxEmpty() {
        char type = maze.getVertexTypeByCoords(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
        return type == 'E' || type == 'A' || type == 'D';
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

    private void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }

    public void setSelectedBoxAsStart() {
        if (hasASelectedBox()) {
            maze.setNewStart(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            modified = true;
            updateBoxesColor();
        }
    }

    public void setSelectedBoxAsGoal() {
        if (hasASelectedBox()) {
            modified = true;
            maze.setNewGoal(selectedBox.getXCoordinate(), selectedBox.getYCoordinate());
            updateBoxesColor();
        }
    }

    public void changeSelectedBoxType() {
        if (hasASelectedBox()) {
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

    public void reset() {
        selectedBox = null;
        modified = true;
        path = null;
        currentFile = null;
        initializeEmptyMaze(10, 10);
        this.maze.initializeEmptyMaze(nb_box_x, nb_box_y);
        updateBoxesColor();
        updateBoxesSize();
    }

    public void initializeEmptyMaze(int nb_box_x, int nb_box_y) {
        this.nb_box_x = nb_box_x;
        this.nb_box_y = nb_box_y;
        boxes = new ArrayList<>();
        for (int x = 0; x < nb_box_x; x++) {
            boxes.add(new ArrayList<>());
            for (int y = 0; y < nb_box_y; y++) {
                boxes.get(x).add(new MazeBox(x, y, boxWidth, boxHeight));
            }
        }
    }

    public void chooseFileToSave(MainWindow mainWindow) {
        JFileChooser fileChooser = new JFileChooser(".");
        int option = fileChooser.showOpenDialog(mainWindow);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainWindow.getMazeModel().setCurrentFile(file.getAbsolutePath());
        }
    }
}
