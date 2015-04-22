import com.google.common.collect.Iterables;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DijkstraTest {

    @Test
    public void testDirect() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Dijkstra.Node("A", 0), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 1)));
        dijkstra.graph.put(new Dijkstra.Node("B", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 2), new Dijkstra.Node("D", 2)));
        dijkstra.graph.put(new Dijkstra.Node("C", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 1), new Dijkstra.Node("D", 3)));
        dijkstra.graph.put(new Dijkstra.Node("D", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("B", 2), new Dijkstra.Node("C", 3)));

        List<Dijkstra.Node> r = dijkstra.dijkstra("A", "C");  //A -> C
        Dijkstra.Node nodeC = Iterables.getLast(r);
        Assert.assertEquals("C", nodeC.name);
        Assert.assertEquals(1, nodeC.distance);
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


    @Test
    public void testNoReach() {
        Dijkstra dijkstra = new Dijkstra();

        dijkstra.graph.put(new Dijkstra.Node("A", 0), Arrays.asList(new Dijkstra.Node("B", 1)));
        dijkstra.graph.put(new Dijkstra.Node("B", Integer.MAX_VALUE), Arrays.asList(new Dijkstra.Node("A", 1)));
        dijkstra.graph.put(new Dijkstra.Node("C", Integer.MAX_VALUE), Arrays.asList( ));

        List<Dijkstra.Node> r = dijkstra.dijkstra("A", "C");
        Dijkstra.Node nodeC = Iterables.getLast(r);
        Assert.assertEquals("C", nodeC.name);
        Assert.assertEquals(Integer.MAX_VALUE, nodeC.distance);
    }
}
