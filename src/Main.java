import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import maze.Maze;
import ui.MainWindow;

public class Main {

    public static void main(String[] args) {
        Maze m = new Maze();
        m.initFromTextFile("data/labyrinthe.txt");
        m.saveToTextFile("data/labyrinthe2.txt");

        testMaze("data/labyrinthe_vide.txt");
        testMaze("data/labyrinthe_1_mur.txt");
        testMaze("data/labyrinthe.txt");
        testMaze("data/labyrinthe_pas_de_chemin.txt");

        MainWindow mw = new MainWindow();
    }

    private static void testMaze(String filename) {
        Maze m = new Maze();
        m.initFromTextFile(filename);
        PreviousInterface p = Dijkstra.dijkstra(m, m.getRoot());
        m.printWithPath(p);
        System.out.println();
    }
}
