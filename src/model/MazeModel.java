package model;

import maze.Maze;

public class MazeModel {

    private final Maze maze = new Maze();
    private boolean modified = false;

    public void loadFromFile(String filename) {
        this.maze.initFromTextFile(filename);
    }

}
