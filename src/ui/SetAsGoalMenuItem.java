package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAsGoalMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public SetAsGoalMenuItem(MainWindow mainWindow) {
        super("Set as Goal");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().setSelectedBoxAsGoal();
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().hasASelectedBox());
    }
}
