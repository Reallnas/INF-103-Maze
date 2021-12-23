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

    private Color color;

    public MazeBox(float x, float y, float w, float h, Color color) {
        super(x, y, w, h);

        this.color = color;
    }

    public final void paint(Graphics g, boolean selected) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);

        if (selected) {
            g2.setStroke(largeStroke);
            g2.draw(this);
            g2.setStroke(usualStroke);
        } else {
            g2.draw(this);
        }
    }
}
