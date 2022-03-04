package maze;

/**
 * DBox stands for "Departure Box"
 */
public final class DBox extends EBox {

    public DBox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public String getLabel() {
        return String.format("Type:Departure %s", getDebugCoords());
    }

    @Override
    public char getFileRepresentation() {
        return 'D';
    }
}
