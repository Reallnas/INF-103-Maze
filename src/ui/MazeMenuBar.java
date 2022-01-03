package ui;

import javax.swing.*;

public class MazeMenuBar extends JMenuBar implements NotifiableUIElement {

    private final FileMenu fileMenu;
    private final MazeActionsMenu mazeActionsMenu;
    private final HelpMenu helpMenu;

    public MazeMenuBar(MainWindow mainWindow) {
        super();

        add(fileMenu = new FileMenu(mainWindow));
        add(mazeActionsMenu = new MazeActionsMenu(mainWindow));
        add(helpMenu = new HelpMenu(mainWindow));
    }

    @Override
    public void notifyForUpdates() {
        fileMenu.notifyForUpdates();
        mazeActionsMenu.notifyForUpdates();
        helpMenu.notifyForUpdates();
    }
}
