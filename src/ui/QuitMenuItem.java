package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

//import model.* ;

public class QuitMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public QuitMenuItem(MainWindow mainWindow) {
        super("Quit");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        if (mainWindow.getMazeModel().isModified()) {
            int response = JOptionPane.showInternalOptionDialog(this,
                    "Maze not saved. Save it ?",
                    "Quit application",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, null, null);
            switch (response) {
                case JOptionPane.CANCEL_OPTION:
                    return;
                case JOptionPane.OK_OPTION:
                    save();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        }
        System.exit(0);
    }

    private void save() {
        if (!mainWindow.getMazeModel().hasACurrentFile()) {
            JFileChooser fileChooser = new JFileChooser(".");
            int option = fileChooser.showOpenDialog(mainWindow);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filepath = file.getAbsolutePath();
                if (!filepath.endsWith(".txt"))
                    filepath += ".txt";
                mainWindow.getMazeModel().setCurrentFile(filepath);
                mainWindow.getMazeModel().saveToFile();
            }
        }
    }
}
