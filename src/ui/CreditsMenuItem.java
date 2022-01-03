package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsMenuItem extends JMenuItem implements ActionListener {

    private final MainWindow mainWindow;

    CreditsMenuItem(MainWindow mainWindow) {
        super("Credits");
        this.mainWindow = mainWindow;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object[] messages = {"Application created by MINIER Arnaud for the INF103 course.","2021-2022"};
        JOptionPane.showInternalOptionDialog(this,
                messages,
                "Credits",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, null, null);
    }
}
