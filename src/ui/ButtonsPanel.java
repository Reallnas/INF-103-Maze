package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final LoadButton loadButton;
    private final SaveButton saveButton;
    private final FindPathButton findPathButton;

    public ButtonsPanel(MainWindow mainWindow) {
        setLayout(new GridLayout(1, 3));

        add(loadButton = new LoadButton(mainWindow));
        add(saveButton = new SaveButton(mainWindow));
        add(findPathButton = new FindPathButton(mainWindow));
    }

    public void notifyForUpdate() {
        //colorIndicator.notifyForUpdate() ;
        //eraseSegment.notifyForUpdate() ;
    }
}

