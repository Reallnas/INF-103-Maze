package ui;

import javax.swing.*;

public class MainWindow extends JFrame {

    private final MazeMenuBar mazeMenuBar ;

    public MainWindow() {
        super("Maze Application");
        setJMenuBar(mazeMenuBar = new MazeMenuBar(this)) ;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
}
