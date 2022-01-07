package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ResetMazeMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public ResetMazeMenuItem(MainWindow mainWindow) {
        super("Reset");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().reset();
    }

    @Override
    public void notifyForUpdates() {
    }
}