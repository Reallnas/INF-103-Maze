package ui;

import javax.swing.*;

public class MainWindow extends JFrame {

    private final MazeMenuBar mazeMenuBar;
    private final WindowPanel windowPanel;

    public MainWindow() {
        super("Maze Application");
        setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
        setContentPane(windowPanel = new WindowPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
}
