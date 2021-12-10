package maze;

public final class ABox extends EBox {

    public ABox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public final String getLabel() {
        return "Type:Arrivée, " + super.getLabel();
    }

    @Override
    public final char getFileRepresentation() {
        return 'A';
    }
}
