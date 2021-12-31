package ui;

import javax.swing.*;

public class MazeMenuBar extends JMenuBar {

    private final FileMenu fileMenu;
    private final MazeActionsMenu mazeActionsMenu;

    public MazeMenuBar(MainWindow mainWindow) {
        super();

        add(fileMenu = new FileMenu(mainWindow));
        add(mazeActionsMenu = new MazeActionsMenu(mainWindow));
    }

    public void notifyForUpdates() {
        fileMenu.notifyForUpdates();
        mazeActionsMenu.notifyForUpdates();
    }
}
