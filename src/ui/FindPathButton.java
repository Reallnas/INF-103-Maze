package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPathButton extends JButton implements ActionListener {

    private final MainWindow mainWindow;

    public FindPathButton(MainWindow mainWindow) {
        super("Find a Path");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
    }

    public void notifyForUpdate() {
    }
}
