package model;

import maze.MBox;
import maze.Maze;

import java.awt.*;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ArrayList<MazeBox>> boxes;
    private boolean modified = false;
    //The number of box in a line
    private int nb_box_x = 10;
    //The number of box in a column
    private int nb_box_y = 10;

    public MazeModel() {
        boxes = new ArrayList<>();
        for (int i = 0; i < nb_box_x; i++) {
            boxes.add(new ArrayList<>());
            for(int j = 0; j < nb_box_y; j++) {
                boxes.get(i).add(new MazeBox(i*50,j*50,50,50,Color.BLACK));
            }
        }
    }

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
    }

    public void notifyWindowSizeChange(float width, float height) {
        float boxWidth = width/nb_box_x;
        float boxHeight = height/nb_box_y;
        for (int x = 0; x < nb_box_x; x++) {
            for(int y = 0; y < nb_box_y; y++) {
                boxes.get(x).get(y).setRect(x*boxWidth,y*boxHeight,boxWidth,boxHeight);
            }
        }
    }

    public final void paintBoxes(Graphics g)
    {
        for(ArrayList<MazeBox> column: boxes) {
            for(MazeBox box : column) {
                box.paint(g,false);
            }
        }

    }
}
