package ui;

import model.MazeModel;

import javax.swing.*;

public class MainWindow extends JFrame {

    private final MazeMenuBar mazeMenuBar;
    private final WindowPanel windowPanel;
    private MazeModel mazeModel = new MazeModel();

    public MainWindow() {
        super("Maze Application");
        setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
        setContentPane(windowPanel = new WindowPanel(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        //setResizable(false);
        setVisible(true);
    }

    public MazeModel getMazeModel() {
        return mazeModel;
    }

    public void setMazeModel(MazeModel mazeModel) {
        this.mazeModel = mazeModel;
    }
}
