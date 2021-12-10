package maze;

public class EBox extends MBox{
    
    public EBox(Maze maze, int x, int y) {
        super(maze,x, y);
    }
    
    @Override
    public String getLabel()
    {
        return "Type:Empty, " + super.getLabel();
    }
    
    public boolean isWalkable()
    {
        return true;
    }

    @Override
    public char getFileRepresentation()
    {
        return 'E';
    }
}
