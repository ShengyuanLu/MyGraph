import java.util.Set;

public class HuffmanTree {

    static HNode buildHuffmanTree(Set<HNode> nodes) {
        nodes.stream().forEach(
                n -> n.parent = n.left = n.right = null
        );


        return null;

    }
}

class HNode {
    String name;
    int weight;
    HNode parent;
    HNode left;
    HNode right;

    public HNode(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", parent=" + parent +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
