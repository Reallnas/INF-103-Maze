package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public SaveButton(MainWindow mainWindow) {
        super("Save Maze to File");
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
