package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class MazePanelMouseListener extends MouseAdapter {

    private final MainWindow mainWindow;

    public MazePanelMouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        switch(me.getButton()) {
            case MouseEvent.BUTTON1:
                //Left Click
                mainWindow.getMazeModel().setSelection(me.getX(), me.getY());
                break;
            case MouseEvent.BUTTON3:
                //Right Click
                mainWindow.getMazeModel().changeHoveredBoxType(me.getX(),me.getY());
                break;
        }

    }
}
