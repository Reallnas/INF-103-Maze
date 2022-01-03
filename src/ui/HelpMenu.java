package ui;

import javax.swing.*;

public class HelpMenu extends JMenu {

    private final CreditsMenuItem creditsMenuItem;
    public HelpMenu(MainWindow mainWindow) {
        super("Help");

        add(creditsMenuItem = new CreditsMenuItem(mainWindow));
    }

    public void notifyForUpdates() {

    }
}
