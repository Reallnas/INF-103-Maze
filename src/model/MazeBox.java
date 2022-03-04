package model;

import maze.*;

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
    private MBox linkedBox = null;
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

    public void setBackgroundColorFromBox(MBox box) {
        // Question: Maybe make MazeModel inherit from Maze and make several subclasses of *Box to have the
        // color linked to the type of the box instead this if/else and to eliminate the use of instanceof.
        // Problem : Java only supports single inheritance which is a problem for the boxes,
        // and we would need to override the initFromTextFile and saveToTextFile among others.
        // I don't think it is worth it just to get rid of this if/else.
        this.linkedBox = box;
        //The Abox and Dbox checks need to happen before the EBox check as both inherit from it.
        if (box instanceof ABox) {
            this.color = Color.GREEN;
        } else if (box instanceof DBox) {
            this.color = Color.RED;
        } else if (box instanceof EBox) {
            this.color = Color.WHITE;
        } else if (box instanceof WBox) {
            this.color = Color.DARK_GRAY;
        } else {
            //Default case: should never happen
            System.out.printf("Unknown box type: %s%n", box.getClass());
            this.color = Color.MAGENTA;
        }
    }

    public void markAsInPath(boolean isInPath) {
        this.isInPath = isInPath;
        if (isInPath && !(linkedBox instanceof ABox) && !(linkedBox instanceof DBox)) {
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
