package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBoxTypeButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public ChangeBoxTypeButton(MainWindow mainWindow) {
        super("Change Box Type");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().changeSelectedBoxType();
    }

    public void notifyForUpdate() {
        this.setEnabled(mainWindow.getMazeModel().hasASelectedBox());
    }
}
