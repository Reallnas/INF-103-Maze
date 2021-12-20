package ui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
    private final MazePanel mazePanel;
    private final ButtonsPanel buttonsPanel;

    public WindowPanel(MainWindow mainWindow) {
        setLayout(new BorderLayout());

        add(mazePanel = new MazePanel(mainWindow), BorderLayout.CENTER);
        add(buttonsPanel = new ButtonsPanel(mainWindow), BorderLayout.SOUTH);
    }

    public void notifyForUpdate() {
        mazePanel.notifyForUpdate();
        buttonsPanel.notifyForUpdate();
    }

}

