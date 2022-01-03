package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class FindPathButton extends JButton implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public FindPathButton(MainWindow mainWindow) {
        super("Find a Path");
        this.mainWindow = mainWindow;
        setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.getMazeModel().FindAPath();
    }

    @Override
    public void notifyForUpdates() {
        setEnabled(mainWindow.getMazeModel().canFindAPath());
    }
}
