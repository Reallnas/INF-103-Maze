package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MazePanelMouseListener extends MouseAdapter {

    private final MainWindow mainWindow;

    public MazePanelMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mainWindow.getMazeModel().setSelection(e.getX(), e.getY());
    }
}
