
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;


public class HuffmanTreeTest {

    @Test
    public void testAB() {
        HNode.layer = -1;
        Set<HNode> nodes = Sets.newHashSet(new HNode("A", 2), new HNode("B", 3));
        HNode tree = HuffmanTree.buildHuffmanTree(nodes);
        assertEquals(5, tree.sum());
    }

    @Test
    public void testABC() {
        HNode.layer = -1;
        Set<HNode> nodes = Sets.newHashSet(
                new HNode("A", 2),
                new HNode("B", 3),
                new HNode("C", 4));
        HNode tree = HuffmanTree.buildHuffmanTree(nodes);
        System.out.println(tree);
        assertEquals(14, tree.sum());
    }

    @Test
    public void testComplex() {
        HNode.layer = -1;
        Set<HNode> nodes = Sets.newHashSet(
                new HNode("A", 5),
                new HNode("B", 2),
                new HNode("C", 4),
                new HNode("D", 7),
                new HNode("E", 9));
        HNode tree = HuffmanTree.buildHuffmanTree(nodes);
        assertEquals(60, tree.sum());
        //  http://zhidao.baidu.com/question/97252092.html?si=3
    }


}
