package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPathMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public FindPathMenuItem(MainWindow mainWindow) {
        super("Find a Path");
        this.mainWindow = mainWindow;
        setEnabled(false);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().FindAPath();
    }

    public void notifyForUpdate() {
        setEnabled(mainWindow.getMazeModel().canFindAPath());
    }
}
