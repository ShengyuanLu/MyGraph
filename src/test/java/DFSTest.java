
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static java.util.Arrays.asList;
import java.util.List;

public class DFSTest {

    @Test
    public void test2Nodes() {
        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);

        dfs.graph.put(a, asList(b));
        dfs.graph.put(b, asList(a));

        List<Node> r = dfs.dfs("A");  //A -> B
        assertEquals(Lists.newArrayList(a, b), r);
    }


    @Test
    public void testLinear() {
        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        dfs.graph.put(a, asList(b));
        dfs.graph.put(b, asList(c));
        dfs.graph.put(c, asList());

        List<Node> r = dfs.dfs("A");  //A -> B -> C
        assertEquals(Lists.newArrayList(a, b, c), r);
    }

    @Test
    public void testCircle() {
        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        dfs.graph.put(a, asList(b));
        dfs.graph.put(b, asList(c));
        dfs.graph.put(c, asList(a));

        List<Node> r = dfs.dfs("A");  //A -> B -> C
        assertEquals(Lists.newArrayList(a, b, c), r);
    }

    @Test
    public void test() {

        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        dfs.graph.put(a, asList(b));
        dfs.graph.put(b, asList(a, c));
        dfs.graph.put(c, asList(b));

        List<Node> r = dfs.dfs("A");  //A -> B -> C
        assertEquals(Lists.newArrayList(a, b, c), r);
    }

    @Test
    public void testTree() {

        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        Node d = new Node("D", 0);

        /**
         *  A -> B -> D
         *  |
         *  -> C
         */
        dfs.graph.put(a, asList(b, c));
        dfs.graph.put(b, asList(d));
        dfs.graph.put(d, asList());
        dfs.graph.put(c, asList());

        List<Node> r = dfs.dfs("A");
        assertEquals(Lists.newArrayList(a, b, d, c), r);

        dfs.order.clear();
        List<Node> r2 = dfs.dfs("B");
        assertEquals(Lists.newArrayList(b, d, a, c), r2);

        dfs.order.clear();
        List<Node> r3 = dfs.dfs("D");
        assertEquals(Lists.newArrayList(d, a, b, c), r3);
    }
}
