package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: This class should show a Popup that allow to choose the size of the new maze
public final class NewMazeMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public NewMazeMenuItem(MainWindow mainWindow) {
        super("New Maze");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        mainWindow.showNewMazeDialog();
    }

    @Override
    public void notifyForUpdates() {
    }
}
