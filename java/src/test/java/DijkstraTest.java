import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DijkstraTest {
    

    @Test
    public void test2() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Dijkstra.Node("A", 0), Arrays.asList(new Dijkstra.Node("B", 1), new Dijkstra.Node("C", 10)));
        dijkstra.graph.put(new Dijkstra.Node("B", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 1), new Dijkstra.Node("D", 2)));
        dijkstra.graph.put(new Dijkstra.Node("C", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 10), new Dijkstra.Node("D", 3)));
        dijkstra.graph.put(new Dijkstra.Node("D", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 3)));

        Set<Dijkstra.Node> result = dijkstra.dijkstra("A");
        System.out.println(result);

        Assert.assertTrue(
                result.stream().anyMatch(n -> n.name.equals("C") && n.distance == 6));
    }
}
