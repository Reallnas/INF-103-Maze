package ui;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {

    private final MainWindow mainWindow;

    public MazePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(300, 300));

    }

    public void notifyForUpdate() {

    }
}
