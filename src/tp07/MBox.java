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
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public boolean isNeighborWith(MBox other)
    {
        final int diffX = Math.abs(this.x - other.x);
        final int diffY = Math.abs(this.y - other.y);
        return (diffX + diffY) <= 1;
    }
    
    public abstract boolean isWalkable();
    
    public String toString()
    {
        return this.getLabel();
    }
}
