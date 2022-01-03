package ui;

import model.MazeModel;

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
        MazeModel mazeModel = mainWindow.getMazeModel();
        boolean hasASelectedBox = mazeModel.hasASelectedBox();
        this.setEnabled(hasASelectedBox);
        if (hasASelectedBox) {
            if (mazeModel.isSelectedBoxAWall())
                setText("Change To Empty Box");
            else if (mazeModel.isSelectedBoxEmpty())
                setText("Change To Wall Box");
        } else {
            setText("Change Box Type");
        }
    }
}
