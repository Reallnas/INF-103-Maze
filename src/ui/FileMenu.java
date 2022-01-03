package ui;

import javax.swing.*;

public class FileMenu extends JMenu implements NotifiableUIElement {

    private final NewMazeMenuItem newMazeMenuItem;
    private final OpenMenuItem openMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final SaveAsMenuItem saveAsMenuItem;
    private final QuitMenuItem quitMenuItem;

    public FileMenu(MainWindow mainWindow) {
        super("File");

        add(newMazeMenuItem = new NewMazeMenuItem(mainWindow));
        add(openMenuItem = new OpenMenuItem(mainWindow));
        add(saveMenuItem = new SaveMenuItem(mainWindow));
        add(saveAsMenuItem = new SaveAsMenuItem(mainWindow));
        add(quitMenuItem = new QuitMenuItem(mainWindow));
    }

    @Override
    public void notifyForUpdates() {
        newMazeMenuItem.notifyForUpdates();
        openMenuItem.notifyForUpdates();
        saveMenuItem.notifyForUpdates();
        saveAsMenuItem.notifyForUpdates();
        quitMenuItem.notifyForUpdates();
    }
}
