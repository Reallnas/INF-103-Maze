package maze;

public final class WBox extends MBox{
    
    public WBox(Maze maze, int x, int y) 
    {
        super(maze, x, y);
    }

    @Override
    public String getLabel()
    {
        return "Type:Wall, " + super.getLabel();
    }
    
    public boolean isWalkable()
    {
        return false;
    }
    
    @Override
    public char getFileRepresentation()
    {
        return 'W';
    }
}