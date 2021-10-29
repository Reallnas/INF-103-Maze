package tp07;

public abstract class MBox {

    public MBox(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int x;
    public int y;
    public abstract boolean isWalkable();
}
