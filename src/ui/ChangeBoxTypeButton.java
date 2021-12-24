package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeBoxTypeButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public ChangeBoxTypeButton(MainWindow mainWindow) {
        super("Change Box Type");
        this.mainWindow = mainWindow;
        addActionListener(this);
        //this.setText();
    }

    public void actionPerformed(ActionEvent evt) {
    }
}
