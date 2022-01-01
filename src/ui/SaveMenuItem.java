package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SaveMenuItem(MainWindow mainWindow) {
        super("Save");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        MazeModel mazeModel = mainWindow.getMazeModel();
        if (!mazeModel.hasACurrentFile()) {
            mazeModel.chooseFileToSave(mainWindow);
        }
        mazeModel.saveToFile();
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().isModified());
    }
}
