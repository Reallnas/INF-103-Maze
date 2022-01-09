package ui.newMazeWindow;

import ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class NewMazeDialog extends JDialog {

    private final MainWindow mainWindow;
    private final IntegerField widthField;
    private final IntegerField heightField;
    private Option chosenOption;
    private int chosenWidth = 1;
    private int chosenHeight = 1;

    public NewMazeDialog(MainWindow mainWindow) {

        super(mainWindow, "New Maze");
        setModal(true);
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        this.mainWindow = mainWindow;
        Insets commonInset = new Insets(10, 10, 10, 10);

        GridBagConstraints constraints1 = new GridBagConstraints(0, 0, 1, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(new JLabel("Width: "), constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints(1, 0, GridBagConstraints.REMAINDER, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(widthField = new IntegerField(10), constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints(0, 1, 1, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(new JLabel("Height: "), constraints3);

        GridBagConstraints constraints4 = new GridBagConstraints(1, 1, GridBagConstraints.REMAINDER, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(heightField = new IntegerField(10), constraints4);

        GridBagConstraints constraints5 = new GridBagConstraints(0, 2, 1, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(new JLabel(), constraints5);

        GridBagConstraints constraints6 = new GridBagConstraints(1, 2, 1, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(new CancelButton(this), constraints6);

        GridBagConstraints constraints7 = new GridBagConstraints(2, 2, 1, 1, 0,
                0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, commonInset, 0, 0);
        contentPane.add(new OKButton(this), constraints7);

        pack();
    }

    public Option showDialog() {
        setLocationRelativeTo(mainWindow);
        setVisible(true);
        return chosenOption;
    }

    public void CancelClicked() {
        chosenOption = Option.CANCEL;
        dispose();
    }

    public void OKClicked() {
        chosenOption = Option.APPROVE;
        chosenWidth = widthField.getValue();
        chosenHeight = heightField.getValue();
        dispose();
    }

    public int getChosenWidth() {
        return chosenWidth;
    }

    public int getChosenHeight() {
        return chosenHeight;
    }

    public enum Option {CANCEL, APPROVE}
}
