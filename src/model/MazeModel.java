package model;

import maze.Maze;

import java.awt.*;
import java.util.ArrayList;

public class MazeModel {

    private final Maze maze = new Maze();
    private final MazeBox mb = new MazeBox(10,10,100,100, Color.BLACK);
    private boolean modified = false;


    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
    }

    public final void paintBoxes(Graphics g)
    {
        mb.paint(g,false);

    }
}
