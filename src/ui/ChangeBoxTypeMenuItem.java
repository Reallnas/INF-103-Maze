package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ChangeBoxTypeMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public ChangeBoxTypeMenuItem(MainWindow mainWindow) {
        super("Change Box Type");
        this.mainWindow = mainWindow;
        this.setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().changeSelectedBoxType();
    }

    @Override
    public void notifyForUpdates() {
        MazeModel mazeModel = mainWindow.getMazeModel();
        boolean hasASelectedBox = mazeModel.hasASelectedBox();
        this.setEnabled(hasASelectedBox);
        if (hasASelectedBox) {
            if (mazeModel.isSelectedBoxAWall())
                setText("Change To Empty Box");
            else
                setText("Change To Wall Box");
        } else {
            setText("Change Box Type");
        }
    }
}
