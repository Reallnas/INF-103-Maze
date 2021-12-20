package model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MazeBox extends Rectangle2D.Float {

    private Color color ;

    public MazeBox(float x, float y, float w, float h, Color color)
    {
        super(x,y,w,h) ;

        this.color = color ;
    }

    public final void paint(Graphics g, boolean selected)
    {
        Graphics2D g2 = (Graphics2D)g ;

        g2.setColor(color);
        g2.draw(this) ;
    }
}
