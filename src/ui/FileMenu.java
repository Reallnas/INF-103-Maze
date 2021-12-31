package ui;

import javax.swing.*;

public class FileMenu extends JMenu {

    private final OpenMenuItem openMenuItem;
    private final SaveMenuItem saveMenuItem;
    private final SaveAsMenuItem saveAsMenuItem;
    private final QuitMenuItem quitMenuItem;

    public FileMenu(MainWindow mainWindow) {
        super("File");

        add(openMenuItem = new OpenMenuItem(mainWindow));
        add(saveMenuItem = new SaveMenuItem(mainWindow));
        add(saveAsMenuItem = new SaveAsMenuItem(mainWindow));
        add(quitMenuItem = new QuitMenuItem(mainWindow));
    }

    public void notifyForUpdates() {
        saveMenuItem.notifyForUpdate();
        saveAsMenuItem.notifyForUpdate();
    }
}
