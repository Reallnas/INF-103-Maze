package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    public ButtonsPanel(MainWindow mainWindow) {
        setLayout(new GridLayout(1, 3));

        /*add(colorIndicator = new ColorIndicator     (drawingApp)) ;
        add(colorChooser   = new ColorChooserButton (drawingApp)) ;
        add(eraseSegment   = new EraseSegmentButton (drawingApp)) ;*/
    }

    /*private final ColorIndicator     colorIndicator ;
    private final ColorChooserButton colorChooser ;
    private final EraseSegmentButton eraseSegment ;*/

    public void notifyForUpdate() {
        //colorIndicator.notifyForUpdate() ;
        //eraseSegment.notifyForUpdate() ;
    }
}

