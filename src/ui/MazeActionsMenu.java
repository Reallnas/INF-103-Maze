package ui;

import javax.swing.*;

public final class MazeActionsMenu extends JMenu implements NotifiableUIElement {

    private final NewMazeMenuItem newMazeMenuItem;
    private final ChangeBoxTypeMenuItem changeBoxTypeMenuItem;
    private final SetAsStartMenuItem setAsStartMenuItem;
    private final SetAsGoalMenuItem setAsGoalMenuItem;
    private final FindPathMenuItem findPathMenuItem;

    public MazeActionsMenu(MainWindow mainWindow) {
        super("Maze Actions");

        add(newMazeMenuItem = new NewMazeMenuItem(mainWindow));
        add(changeBoxTypeMenuItem = new ChangeBoxTypeMenuItem(mainWindow));
        add(setAsStartMenuItem = new SetAsStartMenuItem(mainWindow));
        add(setAsGoalMenuItem = new SetAsGoalMenuItem(mainWindow));
        add(findPathMenuItem = new FindPathMenuItem(mainWindow));
    }

    @Override
    public void notifyForUpdates() {
        newMazeMenuItem.notifyForUpdates();
        changeBoxTypeMenuItem.notifyForUpdates();
        setAsStartMenuItem.notifyForUpdates();
        setAsGoalMenuItem.notifyForUpdates();
        findPathMenuItem.notifyForUpdates();
    }
}
