package ui;

import javax.swing.*;
import java.awt.*;

public class NewMazeDialog extends JDialog {

    private final MainWindow mainWindow;

    public NewMazeDialog(MainWindow mainWindow) {

        super(mainWindow,"New Maze");
        setModal(true);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        this.mainWindow = mainWindow;
        Insets commonInset= new Insets(10, 10, 10, 10);

        GridBagConstraints constraints1 = new GridBagConstraints(0,0,1,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JLabel("Hauteur: "),constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints(1,0,GridBagConstraints.REMAINDER,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JTextField("10"),constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints(0,1,1,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JLabel("Largeur: "),constraints3);

        GridBagConstraints constraints4 = new GridBagConstraints(1,1,GridBagConstraints.REMAINDER,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JTextField("10"),constraints4);

        GridBagConstraints constraints5 = new GridBagConstraints(0,2,1,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JLabel(),constraints5);

        GridBagConstraints constraints6 = new GridBagConstraints(1,2,1,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JButton("Cancel"),constraints6);

        GridBagConstraints constraints7 = new GridBagConstraints(2,2,1,1,0,
                0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,commonInset,0,0);
        contentPane.add(new JButton("OK"),constraints7);

        pack();
        setLocationRelativeTo(mainWindow);
        setVisible(true);
    }
}
