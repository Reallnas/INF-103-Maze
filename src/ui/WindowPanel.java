package ui;

import javax.swing.*;
import java.awt.*;

public final class WindowPanel extends JPanel implements NotifiableUIElement {

    private final MazePanel mazePanel;
    private final ButtonsPanel buttonsPanel;

    public WindowPanel(MainWindow mainWindow) {
        setLayout(new BorderLayout());

        add(mazePanel = new MazePanel(mainWindow), BorderLayout.CENTER);
        add(buttonsPanel = new ButtonsPanel(mainWindow), BorderLayout.SOUTH);
    }

    @Override
    public void notifyForUpdates() {
        mazePanel.notifyForUpdates();
        buttonsPanel.notifyForUpdates();
    }

    public void noPathFoundPopup() {
        JOptionPane.showMessageDialog(this,
                "No path has been found !",
                "No path found",
                JOptionPane.WARNING_MESSAGE);
    }

    public void showCredits() {
        JOptionPane.showMessageDialog(this,
                "Application created by MINIER Arnaud for the INF103 course.\n2021-2022",
                "Credits",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

