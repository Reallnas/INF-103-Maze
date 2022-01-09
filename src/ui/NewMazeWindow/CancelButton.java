package ui.NewMazeWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButton extends JButton implements ActionListener {

    private final NewMazeDialog newMazeDialog;

    CancelButton(NewMazeDialog newMazeDialog) {
        super("Cancel");
        this.newMazeDialog = newMazeDialog;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.newMazeDialog.CancelClicked();
    }
}
