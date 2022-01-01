package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAsMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SaveAsMenuItem(MainWindow mainWindow) {
        super("Save as...");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        MazeModel mazeModel = mainWindow.getMazeModel();
        mazeModel.chooseFileToSave(mainWindow);
        mazeModel.saveToFile();
    }
}
