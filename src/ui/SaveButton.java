package ui;

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
        if (!mainWindow.getMazeModel().hasACurrentFile()) {
            JFileChooser fileChooser = new JFileChooser(".");
            int option = fileChooser.showOpenDialog(mainWindow);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filepath = file.getAbsolutePath();
                if(!filepath.endsWith(".txt"))
                    filepath += ".txt";
                mainWindow.getMazeModel().setCurrentFile(filepath);
            }
        }
        mainWindow.getMazeModel().saveToFile();
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().isModified());
    }
}
