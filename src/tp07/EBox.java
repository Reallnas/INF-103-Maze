package tp07;

public class EBox extends MBox{
    
    public EBox(Maze maze, int x, int y) {
        super(maze,x, y);
    }

    public boolean isWalkable()
    {
        return true;
    }

}
