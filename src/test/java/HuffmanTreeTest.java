
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class HuffmanTreeTest {

    @Test
    public void testAB() {
        Set<HNode> nodes = Sets.newHashSet(new HNode("A", 2), new HNode("B", 3));
        HNode tree = HuffmanTree.buildHuffmanTree(nodes);
        assertEquals(5, tree.weight);
    }

    @Test
    public void testABC() {
        Set<HNode> nodes = Sets.newHashSet(
                new HNode("A", 2),
                new HNode("B", 3),
                new HNode("C", 4));
        HNode tree = HuffmanTree.buildHuffmanTree(nodes);
        assertEquals(9, tree.weight);
    }

}
