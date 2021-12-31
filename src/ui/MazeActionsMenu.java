package ui;

import javax.swing.*;

public class MazeActionsMenu extends JMenu {

    private final NewMazeMenuItem newMazeMenuItem;

    public MazeActionsMenu(MainWindow mainWindow) {
        super("Maze Actions");

        add(newMazeMenuItem = new NewMazeMenuItem(mainWindow));
    }

    public void notifyForUpdates() {

    }
}
