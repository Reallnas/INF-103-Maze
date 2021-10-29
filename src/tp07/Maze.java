package tp07;

import java.util.ArrayList;

public class Maze implements GraphInterface{

    public ArrayList<ArrayList<MBox>> grid;
    
    public Maze(ArrayList<ArrayList<MBox>> grid) {
        this.grid = grid;
    }
}
