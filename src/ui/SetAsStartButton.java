package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class SetAsStartButton extends JButton implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public SetAsStartButton(MainWindow mainWindow) {
        super("Set as Start");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().setSelectedBoxAsStart();
    }

    @Override
    public void notifyForUpdates() {
        this.setEnabled(mainWindow.getMazeModel().hasASelectedBox());
    }
}
