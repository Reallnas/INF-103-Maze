package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetAsGoalButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public SetAsGoalButton(MainWindow mainWindow) {
        super("Set as Goal");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().getSelectedBox() != null);
    }
}
