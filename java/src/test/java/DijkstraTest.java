import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DijkstraTest {

    Dijkstra dijkstra = new Dijkstra();

    @Test
    public void test(){
        dijkstra.graph.put("A", Arrays.asList(new Dijkstra.QElement("B", 1), new Dijkstra.QElement("C", 10)));
        dijkstra.graph.put("A", Arrays.asList(new Dijkstra.QElement("B", 1), new Dijkstra.QElement("C", 10)));
        dijkstra.graph.put("B", Arrays.asList(new Dijkstra.QElement("A", 1), new Dijkstra.QElement("D", 2)));
        dijkstra.graph.put("C", Arrays.asList(new Dijkstra.QElement("A", 10), new Dijkstra.QElement("D", 3)));
        dijkstra.graph.put("D", Arrays.asList(new Dijkstra.QElement("B", 2), new Dijkstra.QElement("C", 3)));
        System.out.println(dijkstra.dijkstra("A"));

        Assert.assertTrue( dijkstra.S.stream()
                .anyMatch(q -> q.name.equals("C") && q.distance==6) );
    }
}
