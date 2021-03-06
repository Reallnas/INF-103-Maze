package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class FindPathMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public FindPathMenuItem(MainWindow mainWindow) {
        super("Find a Path");
        this.mainWindow = mainWindow;
        setEnabled(false);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.findAPath();
    }

    @Override
    public void notifyForUpdates() {
        setEnabled(mainWindow.getMazeModel().canFindAPath());
    }
}
