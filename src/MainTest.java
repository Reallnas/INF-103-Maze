import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import maze.Maze;

public class MainTest {

    public static void main(String[] args) {
        Maze m = new Maze();
        m.initFromTextFile("data/labyrinthe.txt");
        m.saveToTextFile("data/labyrinthe2.txt");

        testMaze("data/labyrinthe_vide.txt");
        testMaze("data/labyrinthe_1_mur.txt");
        testMaze("data/labyrinthe.txt");
    }

    private static void testMaze(String filename) {
        Maze m = new Maze();
        m.initFromTextFile(filename);
        PreviousInterface p = Dijkstra.dijkstra(m, m.getRoot());
        m.printWithPath(p);
        System.out.println();
    }
}
