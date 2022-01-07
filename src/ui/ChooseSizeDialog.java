package ui;

import javax.swing.*;
import java.awt.*;

//TODO: Finish
public class ChooseSizeDialog extends JDialog {

    private final MainWindow mainWindow;

    public ChooseSizeDialog(MainWindow mainWindow) {

        super(mainWindow,"Test");
        setModal(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2,2));

        this.mainWindow = mainWindow;

        int nbColumns = 4;


        for (int i = 0; i < 10; i++) {

            String name = "modèle" + Integer.toString(i + 1);
            int x = i % nbColumns;
            int y = i / nbColumns;

            JButton button = new JButton(name);
            button.setFocusPainted(false);

            GridBagConstraints constraints = new GridBagConstraints();
            // set constraints
            constraints.gridx = x;
            constraints.gridy = y;
            constraints.insets = new Insets(10, 10, 10, 10);

            contentPane.add(button, constraints);
        }

        setLocationRelativeTo(mainWindow);

        pack();
        setVisible(true);
    }
}
