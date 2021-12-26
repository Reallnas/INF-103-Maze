package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public LoadButton(MainWindow mainWindow) {
        super("Load Maze from File");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser(".");
        int option = fileChooser.showOpenDialog(mainWindow);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainWindow.getMazeModel().loadFromFile(file.getAbsolutePath());
        }
    }
}
