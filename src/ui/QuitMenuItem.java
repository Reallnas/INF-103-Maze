package ui;

import model.MazeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import model.* ;

public class QuitMenuItem extends JMenuItem implements ActionListener, NotifiableUIElement {

    private final MainWindow mainWindow;

    public QuitMenuItem(MainWindow mainWindow) {
        super("Quit");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (mainWindow.getMazeModel().isModified()) {
            int response = JOptionPane.showInternalOptionDialog(this,
                    "Maze not saved. Save it ?",
                    "Quit application",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null, null, null);
            switch (response) {
                case JOptionPane.CANCEL_OPTION:
                    return;
                case JOptionPane.OK_OPTION:
                    //If the user changed his mind and didn't want to quit, we return early to prevent exiting.
                    if(!save()) {
                        return;
                    }
                case JOptionPane.NO_OPTION:
                    break;
            }
        }
        System.exit(0);
    }

    private boolean save() {
        MazeModel mazeModel = mainWindow.getMazeModel();
        if (!mazeModel.hasACurrentFile()) {
            mazeModel.chooseFileToSave(mainWindow);
        }
        return mazeModel.saveToFile();
    }

    @Override
    public void notifyForUpdates() {
    }
}
