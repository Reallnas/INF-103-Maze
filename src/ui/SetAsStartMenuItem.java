package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAsStartMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SetAsStartMenuItem(MainWindow mainWindow) {
        super("Set as Start");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().setSelectedBoxAsStart();
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().hasASelectedBox());
    }
}