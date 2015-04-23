import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DijkstraTest {

    @Test
    public void testDirect() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Node("A", 0), Arrays.asList(new Node("B", 2), new Node("C", 1)));
        dijkstra.graph.put(new Node("B", Integer.MAX_VALUE), Arrays.asList(new Node("A", 2), new Node("D", 2)));
        dijkstra.graph.put(new Node("C", Integer.MAX_VALUE), Arrays.asList(new Node("A", 1), new Node("D", 3)));
        dijkstra.graph.put(new Node("D", Integer.MAX_VALUE), Arrays.asList(new Node("B", 2), new Node("C", 3)));

        List<Node> r = dijkstra.dijkstra("A", "C");  //A -> C
        Node nodeC = Iterables.getLast(r);
        Assert.assertEquals("C", nodeC.name);
        Assert.assertEquals(1, nodeC.distance);
    }

    @Test
    public void testUnDirect() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Node("A", 0), Arrays.asList(new Node("B", 1), new Node("C", 10)));
        dijkstra.graph.put(new Node("B", Integer.MAX_VALUE), Arrays.asList(new Node("A", 1), new Node("D", 2)));
        dijkstra.graph.put(new Node("C", Integer.MAX_VALUE), Arrays.asList(new Node("A", 10), new Node("D", 3)));
        dijkstra.graph.put(new Node("D", Integer.MAX_VALUE), Arrays.asList(new Node("B", 2), new Node("C", 3)));

        List<Node> r = dijkstra.dijkstra("A", "C");  //A -> B -> D -> C
        Node nodeC = Iterables.getLast(r);
        Assert.assertEquals("C", nodeC.name);
        Assert.assertEquals(6, nodeC.distance);
    }


    @Test
    public void testNoReach() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Node("A", 0), Arrays.asList(new Node("B", 1)));
        dijkstra.graph.put(new Node("B", Integer.MAX_VALUE), Arrays.asList(new Node("A", 1)));
        dijkstra.graph.put(new Node("C", Integer.MAX_VALUE), Arrays.asList( ));

        List<Node> r = dijkstra.dijkstra("A", "C");
        Node nodeC = Iterables.getLast(r);
        Assert.assertEquals("C", nodeC.name);
        Assert.assertEquals(Integer.MAX_VALUE, nodeC.distance);
    }
}
