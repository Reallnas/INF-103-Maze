package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MazeBox extends Rectangle2D.Float {

    private final static BasicStroke usualStroke;
    private final static BasicStroke largeStroke;

    static {
        usualStroke = new BasicStroke();
        largeStroke = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
    }

    private final int XBoxCoordinate;
    private final int YBoxCoordinate;
    private Color color = Color.WHITE;

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

    public final void paint(Graphics g, boolean selected) {
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
