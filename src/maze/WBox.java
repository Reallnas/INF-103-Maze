package maze;

public final class WBox extends MBox{
    
    public WBox(Maze maze, int x, int y) 
    {
        super(maze, x, y);
    }

    @Override
    public final String getLabel()
    {
        return "Type:Wall, " + super.getLabel();
    }
    
    public final boolean isWalkable()
    {
        return false;
    }
    
    @Override
    public final char getFileRepresentation()
    {
        return 'W';
    }
}
