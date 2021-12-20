package model;

import maze.MBox;
import maze.Maze;

import java.awt.*;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final ArrayList<ArrayList<MazeBox>> boxes;
    private boolean modified = false;
    private int width = 10;
    private int height = 10;

    public MazeModel() {
        boxes = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            boxes.add(new ArrayList<>());
            for(int j = 0; j < height; j++) {
                boxes.get(i).add(new MazeBox(i*50,j*50,50,50,Color.BLACK));
            }
        }
        System.out.println(boxes);
    }

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
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
