
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
}
