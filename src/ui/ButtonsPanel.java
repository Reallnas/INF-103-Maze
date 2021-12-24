package ui;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final ChangeBoxTypeButton changeBoxTypeButton;
    private final SetAsStartButton setAsStartButton;
    private final SetAsGoalButton setAsGoalButton;
    private final LoadButton loadButton;
    private final SaveButton saveButton;
    private final FindPathButton findPathButton;

    public ButtonsPanel(MainWindow mainWindow) {
        setLayout(new GridLayout(2, 3));

        add(changeBoxTypeButton = new ChangeBoxTypeButton(mainWindow));
        add(setAsStartButton = new SetAsStartButton(mainWindow));
        add(setAsGoalButton = new SetAsGoalButton(mainWindow));
        add(loadButton = new LoadButton(mainWindow));
        add(saveButton = new SaveButton(mainWindow));
        add(findPathButton = new FindPathButton(mainWindow));
    }

    public void notifyForUpdate() {
        changeBoxTypeButton.notifyForUpdate();
        setAsStartButton.notifyForUpdate();
        setAsGoalButton.notifyForUpdate();
        saveButton.notifyForUpdate();
        findPathButton.notifyForUpdate();
    }
}

