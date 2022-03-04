package maze;

/**
 * EBox stands for "Empty Box"
 */
public class EBox extends MBox {

    public EBox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public String getLabel() {
        return String.format("Type:Empty %s", getDebugCoords());
    }

    public final boolean isWalkable() {
        return true;
    }

    @Override
    public char getFileRepresentation() {
        return 'E';
    }
}
