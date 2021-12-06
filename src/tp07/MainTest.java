package tp07;

public class MainTest {

    public static void main(String[] args) {
        Maze m = new Maze();
        m.initFromTextFile("data/labyrinthe.txt");
        m.saveToTextFile("data/labyrinthe_saved.txt");
    }

}
