package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class CreditsMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    CreditsMenuItem(MainWindow mainWindow) {
        super("Credits");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        mainWindow.showCredits();
    }

    @Override
    public void notifyForUpdates() {
    }
}
