package tp07;

import java.util.ArrayList;

public class Maze implements GraphInterface{

    public ArrayList<ArrayList<MBox>> boxGrid;
    public ArrayList<ArrayList<int>> weightMatrix;
    
    public Maze(ArrayList<ArrayList<MBox>> grid) {
        this.grid = grid;
    }
}
