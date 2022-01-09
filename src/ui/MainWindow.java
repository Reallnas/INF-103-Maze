package ui;

import model.MazeModel;
import ui.NewMazeWindow.NewMazeDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

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

    public void noPathFoundPopup() {
        windowPanel.noPathFoundPopup();
    }

    public void showCredits() {
        windowPanel.showCredits();
    }

    public void chooseFileToSave() {
        JFileChooser fileChooser = new JFileChooser(".");
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mazeModel.setCurrentFile(file.getAbsolutePath());
        }
    }

    public void showNewMazeDialog() {
        NewMazeDialog newMazeDialog = new NewMazeDialog(this);
        NewMazeDialog.Option option = newMazeDialog.showDialog();
        if(option == NewMazeDialog.Option.APPROVE) {
            mazeModel.initializeEmptyMaze(newMazeDialog.getChosenWidth(),newMazeDialog.getChosenHeight());
        }
    }

    public void findAPath() {
        mazeModel.FindAPath();
        if (!mazeModel.hasFoundAPath()) {
            noPathFoundPopup();
        }
    }
}
