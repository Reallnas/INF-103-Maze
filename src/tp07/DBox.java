package tp07;

public final class DBox extends EBox {

    public DBox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public String getLabel()
    {
        return "Type:Départ, " + super.getLabel();
    }
    
    @Override
    public char getFileRepresentation()
    {
        return 'D';
    }
}
