package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SaveMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public SaveMenuItem(MainWindow mainWindow) {
        super("Save");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        MazeModel mazeModel = mainWindow.getMazeModel();
        if (!mazeModel.hasACurrentFile()) {
            mazeModel.chooseFileToSave(mainWindow);
        }
        mazeModel.saveToFile();
    }

    @Override
    public void notifyForUpdates() {
        this.setEnabled(mainWindow.getMazeModel().isModified());
    }
}
