import org.junit.Test;

import java.util.Arrays;

public class DijkstraTest {

    @Test
    public void test1() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Dijkstra.Node("A", 0), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 1)));
        dijkstra.graph.put(new Dijkstra.Node("B", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 2), new Dijkstra.Node("D", 2)));
        dijkstra.graph.put(new Dijkstra.Node("C", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 1), new Dijkstra.Node("D", 3)));
        dijkstra.graph.put(new Dijkstra.Node("D", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 3)));

        System.out.println(dijkstra.dijkstra("A", "C"));

    }

    @Test
    public void test2() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Dijkstra.Node("A", 0), Arrays.asList(new Dijkstra.Node("B", 1), new Dijkstra.Node("C", 10)));
        dijkstra.graph.put(new Dijkstra.Node("B", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 1), new Dijkstra.Node("D", 2)));
        dijkstra.graph.put(new Dijkstra.Node("C", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 10), new Dijkstra.Node("D", 3)));
        dijkstra.graph.put(new Dijkstra.Node("D", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 3)));

        System.out.println(dijkstra.dijkstra("A", "C"));

    }
}
