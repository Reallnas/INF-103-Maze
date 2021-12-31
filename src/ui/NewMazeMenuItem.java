package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMazeMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public NewMazeMenuItem(MainWindow mainWindow) {
        super("New Maze");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().reset();
    }
}
