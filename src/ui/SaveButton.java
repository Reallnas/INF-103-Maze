package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public SaveButton(MainWindow mainWindow) {
        super("Save Maze to File");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {

    }
}
