package tp07;

public abstract class MBox implements VertexInterface{

    private int x;
    private int y;
    private Maze maze;
    
    public MBox(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }
    
    public String getLabel()
    {
        return "X:" + this.x + ", Y:"+ this.y;
    }
    
    public abstract boolean isWalkable();
}
