package maze;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {

    private final int x;
    private final int y;
    private final Maze maze;

    public MBox(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    public String getLabel() {
        return "X:" + this.x + ", Y:" + this.y;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final boolean isNeighborWith(MBox other) {
        final int diffX = Math.abs(this.x - other.x);
        final int diffY = Math.abs(this.y - other.y);
        return (diffX + diffY) <= 1;
    }

    public abstract boolean isWalkable();

    public String toString() {
        return this.getLabel();
    }

    public abstract char getFileRepresentation();
}
