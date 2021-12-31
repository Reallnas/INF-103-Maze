package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveAsMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SaveAsMenuItem(MainWindow mainWindow) {
        super("Save as...");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser(".");
        int option = fileChooser.showOpenDialog(mainWindow);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainWindow.getMazeModel().setCurrentFile(file.getAbsolutePath());
            mainWindow.getMazeModel().saveToFile();
        }
    }

    public void notifyForUpdate() {
    }
}
