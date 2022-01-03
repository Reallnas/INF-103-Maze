package ui;

import model.MazeModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class MainWindow extends JFrame implements ChangeListener {

    private final MazeMenuBar mazeMenuBar;
    private final WindowPanel windowPanel;
    private MazeModel mazeModel = new MazeModel();

    public MainWindow() {
        super("Maze Application");
        setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
        setContentPane(windowPanel = new WindowPanel(this));

        mazeModel.addObserver(this);

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

    public void stateChanged(ChangeEvent evt) {
        mazeMenuBar.notifyForUpdates();
        windowPanel.notifyForUpdates();
    }
}
