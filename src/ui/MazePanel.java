package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MazePanel extends JPanel {

    private final MainWindow mainWindow;

    public MazePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 800));
        addMouseListener(new MazePanelMouseListener(mainWindow));

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                notifyWindowSizeChange();
            }
        });
    }

    public void notifyWindowSizeChange() {
        Dimension dimension = getSize();
        this.mainWindow.getMazeModel().notifyWindowSizeChange(dimension.width, dimension.height);
    }

    public void notifyForUpdate() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mainWindow.getMazeModel().paintBoxes(g);
    }
}
