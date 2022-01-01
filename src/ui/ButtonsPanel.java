package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final ChangeBoxTypeButton changeBoxTypeButton;
    private final SetAsStartButton setAsStartButton;
    private final SetAsGoalButton setAsGoalButton;
    private final FindPathButton findPathButton;

    public ButtonsPanel(MainWindow mainWindow) {
        setLayout(new GridLayout(2, 2));

        add(changeBoxTypeButton = new ChangeBoxTypeButton(mainWindow));
        add(setAsStartButton = new SetAsStartButton(mainWindow));
        add(setAsGoalButton = new SetAsGoalButton(mainWindow));
        add(findPathButton = new FindPathButton(mainWindow));
    }

    public void notifyForUpdate() {
        changeBoxTypeButton.notifyForUpdate();
        setAsStartButton.notifyForUpdate();
        setAsGoalButton.notifyForUpdate();
        findPathButton.notifyForUpdate();
    }
}

