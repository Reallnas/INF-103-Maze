package ui.NewMazeWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OKButton extends JButton implements ActionListener {

    private final NewMazeDialog newMazeDialog;

    OKButton(NewMazeDialog newMazeDialog) {
        super("OK");
        this.newMazeDialog = newMazeDialog;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        newMazeDialog.OKClicked();
    }
}
