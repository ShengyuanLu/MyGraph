
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DFSTest {

    @Test
    public void test() {

        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        dfs.graph.put(a, Arrays.asList(b));
        dfs.graph.put(b, Arrays.asList(a, c));
        dfs.graph.put(c, Arrays.asList(b));

        List<Node> r = dfs.dfs("A");  //A -> B -> C
        Assert.assertEquals(a, r.get(0));
        Assert.assertEquals(b, r.get(1));
        Assert.assertEquals(c, r.get(2));
    }

    @Test
    public void test1() {

        DFS dfs = new DFS();

        Node a = new Node("A", 0);
        Node b = new Node("B", 0);
        Node c = new Node("C", 0);
        Node d = new Node("D", 0);

        dfs.graph.put(a, Arrays.asList(b, c));
        dfs.graph.put(b, Arrays.asList(a, d));
        dfs.graph.put(c, Arrays.asList(a));
        dfs.graph.put(d, Arrays.asList(b));

        List<Node> r = dfs.dfs("A");  //A -> C -> B -> D
        Assert.assertEquals(a, r.get(0));
        Assert.assertEquals(c, r.get(1));
        Assert.assertEquals(b, r.get(2));
        Assert.assertEquals(d, r.get(3));

        dfs.order.clear();
        List<Node> r2 = dfs.dfs("B");  //B -> D -> A ->C
        Assert.assertEquals(b, r.get(0));
        Assert.assertEquals(d, r.get(1));
        Assert.assertEquals(a, r.get(2));
        Assert.assertEquals(c, r.get(3));
    }
}
