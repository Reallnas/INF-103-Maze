package maze;

/**
 * WBox stands for "Wall Box"
 */
public final class WBox extends MBox {

    public WBox(Maze maze, int x, int y) {
        super(maze, x, y);
    }

    @Override
    public String getLabel() {
        return String.format("Type:Wall %s", getDebugCoords());
    }

    public boolean isWalkable() {
        return false;
    }

    @Override
    public char getFileRepresentation() {
        return 'W';
    }
}
