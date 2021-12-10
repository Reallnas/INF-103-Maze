package maze;

public final class DBox extends EBox {

    public DBox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public final String getLabel()
    {
        return "Type:Départ, " + super.getLabel();
    }
    
    @Override
    public final char getFileRepresentation()
    {
        return 'D';
    }
}
