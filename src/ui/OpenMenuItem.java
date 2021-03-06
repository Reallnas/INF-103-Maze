package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public final class OpenMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public OpenMenuItem(MainWindow mainWindow) {
        super("Open");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser(".");
        int option = fileChooser.showOpenDialog(mainWindow);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mainWindow.getMazeModel().loadFromFile(file.getAbsolutePath());
        }
    }

    @Override
    public void notifyForUpdates() {
    }
}
