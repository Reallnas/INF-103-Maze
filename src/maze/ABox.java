package maze;

/**
 * ABox stands for "Arrival Box"
 */
public final class ABox extends EBox {

    public ABox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public String getLabel() {
        return String.format("Type:Arrival %s", getDebugCoords());
    }

    @Override
    public char getFileRepresentation() {
        return 'A';
    }
}
