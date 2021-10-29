package tp07;

public final class WBox extends MBox{
    
    public WBox(Maze maze, int x, int y) 
    {
        super(maze, x, y);
    }

    public boolean isWalkable()
    {
        return false;
    }

}
