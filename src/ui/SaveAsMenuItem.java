package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SaveAsMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public SaveAsMenuItem(MainWindow mainWindow) {
        super("Save as...");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.chooseFileToSave();
        mainWindow.getMazeModel().saveToFile();
    }

    @Override
    public void notifyForUpdates() {
    }
}
