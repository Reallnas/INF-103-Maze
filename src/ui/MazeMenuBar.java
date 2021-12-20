package ui;

import javax.swing.* ;

public class MazeMenuBar extends JMenuBar{

    private final FileMenu fileMenu ;

    public MazeMenuBar(MainWindow mainWindow)
    {
        super() ;

        add(fileMenu = new FileMenu(mainWindow)) ;
    }

}
