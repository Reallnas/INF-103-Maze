package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public final class MazeBox extends Rectangle2D.Float {

    private final static BasicStroke usualStroke;
    //Used when the box is selected
    private final static BasicStroke largeStroke;


    static {
        usualStroke = new BasicStroke();
        largeStroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    }

    private final int XBoxCoordinate;
    private final int YBoxCoordinate;
    private Color color = Color.WHITE;
    private char representation = 'E';
    private boolean isInPath = false;

    public MazeBox(int x, int y, float w, float h) {
        super(x * w, y * h, w, h);
        XBoxCoordinate = x;
        YBoxCoordinate = y;
    }

    public int getXCoordinate() {
        return XBoxCoordinate;
    }

    public int getYCoordinate() {
        return YBoxCoordinate;
    }

    public void setBackgroundColor(Color color) {
        this.color = color;
    }

    public void setBackgroundColorFromRepresentation(char representation) {
        //TODO: Maybe make MazeModel inherit from Maze and make several subclasses of *Box to have the
        // color linked to the type of the box instead of this switch
        // Problem : Java only supports single inheritance which is a problem for the boxes
        // and we would need to override the initFromTextFile and saveToTextFile among other.
        // I don't think it is worth it just to get rid of this switch.
        this.representation = representation;
        switch (representation) {
            case 'E':
                this.color = Color.WHITE;
                break;
            case 'W':
                this.color = Color.DARK_GRAY;
                break;
            case 'A':
                this.color = Color.GREEN;
                break;
            case 'D':
                this.color =Color.RED;
                break;
            default:
                this.color = Color.MAGENTA;
        }
    }

    public void markAsInPath(boolean isInPath) {
        this.isInPath = isInPath;
        if(isInPath && representation != 'A' && representation != 'D') {
            this.color = Color.YELLOW;
        }
    }

    public void paint(Graphics g, boolean selected) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(color);
        g2.fill(this);
        g2.setColor(Color.BLACK);

        if (selected) {
            g2.setStroke(largeStroke);
            g2.draw(this);
            g2.setStroke(usualStroke);
        } else {
            g2.draw(this);
        }
    }
}
