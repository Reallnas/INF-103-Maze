package ui;

import javax.swing.*;

public class HelpMenu extends JMenu implements NotifiableUIElement {

    private final CreditsMenuItem creditsMenuItem;

    public HelpMenu(MainWindow mainWindow) {
        super("Help");

        add(creditsMenuItem = new CreditsMenuItem(mainWindow));
    }

    @Override
    public void notifyForUpdates() {
        creditsMenuItem.notifyForUpdates();
    }
}
