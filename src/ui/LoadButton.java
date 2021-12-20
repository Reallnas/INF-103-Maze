package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public LoadButton(MainWindow mainWindow) {
        super("Load Maze from File");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
    }
}
