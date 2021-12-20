package ui;

import javax.swing.*;

public class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;

    public FileMenu(MainWindow mainWindow) {
        super("File");

        add(quitMenuItem = new QuitMenuItem(mainWindow));
    }

}
