package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SaveMenuItem(MainWindow mainWindow) {
        super("Save");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        if (!mainWindow.getMazeModel().hasACurrentFile()) {
            JFileChooser fileChooser = new JFileChooser(".");
            int option = fileChooser.showOpenDialog(mainWindow);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                mainWindow.getMazeModel().setCurrentFile(file.getAbsolutePath());
                mainWindow.getMazeModel().saveToFile();
            }
        }
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().isModified());
    }
}
