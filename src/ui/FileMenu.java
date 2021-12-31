package ui;

import javax.swing.*;

public class FileMenu extends JMenu {

    private final SaveMenuItem saveMenuItem;
    private final QuitMenuItem quitMenuItem;

    public FileMenu(MainWindow mainWindow) {
        super("File");

        add(saveMenuItem = new SaveMenuItem(mainWindow));
        add(quitMenuItem = new QuitMenuItem(mainWindow));
    }

    public void notifyForUpdates() {
        saveMenuItem.notifyForUpdate();
    }
}
