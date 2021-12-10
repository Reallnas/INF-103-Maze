import maze.Maze;
import dijkstra.*;

public class MainTest {

    public static void main(String[] args) {
        Maze m = new Maze();
        m.initFromTextFile("data/labyrinthe.txt");
        m.saveToTextFile("data/labyrinthe2.txt");
        VertexInterface root = m.getRoot();
        PreviousInterface p = Dijkstra.dijkstra(m, root);
        
        ASet path = new ASet();
        VertexInterface current = m.getGoal();
        while(!path.contains(root))
        {
            path.add(current);
            current = p.getFather(current);
        }
        
        m.printWithPath(path);
    }

}
