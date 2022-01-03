package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SetAsGoalMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public SetAsGoalMenuItem(MainWindow mainWindow) {
        super("Set as Goal");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().setSelectedBoxAsGoal();
    }

    @Override
    public void notifyForUpdates() {
        this.setEnabled(mainWindow.getMazeModel().hasASelectedBox());
    }
}
