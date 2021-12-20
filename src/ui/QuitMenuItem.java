package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import model.* ;

public class QuitMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    public QuitMenuItem(MainWindow mainWindow) {
        super("Quit");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        /*DrawingAppModel drawingAppModel = mainWindow.getDrawingAppModel() ;

        if (drawingAppModel.isModified()) {
            int response = JOptionPane.showInternalOptionDialog(this,
                    "Drawing not saved. Save it ?",
                    "Quit application",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,null,null) ;
            switch (response) {
                case JOptionPane.CANCEL_OPTION:
                    return ;
                case JOptionPane.OK_OPTION:
                    //drawingAppModel.saveToFile() ;
                    break ;
                case JOptionPane.NO_OPTION:
                    break ;
            }
        }*/
        System.exit(0);
    }
}
