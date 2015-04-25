

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HuffmanTree {

    static HNode buildHuffmanTree(Set<HNode> nodes) {
        nodes.stream().forEach(
                n -> n.parent = n.left = n.right = null
        );

        HNode root = null;
        int builtIndex = 0;
        while (nodes.size() > 1) {
            List<HNode> mins = nodes.stream()
                    .sorted()
                    .limit(2)
                    .collect(Collectors.toList());

            HNode builtNode = new HNode(builtIndex++);
            builtNode.left = mins.get(0);
            builtNode.right = mins.get(1);
            mins.get(0).parent = builtNode;
            mins.get(1).parent = builtNode;

            builtNode.weight = mins.stream()
                    .mapToInt(n -> n.weight)
                    .sum();

            nodes.removeAll(mins);
            nodes.add(builtNode);
            root = builtNode;
        }

        return root;

    }
}

class HNode implements Comparable<HNode> {
    String name;
    int weight;
    HNode parent;
    HNode left;
    HNode right;
    boolean isBuilt = false;

    public HNode(int index) {
        name = "builtIn"+index;
        isBuilt = true;
    }

    public HNode(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    static int layer = -1;
    public int sum() {
        layer++;
        int myWeight = 0;
        if(!isBuilt) {
            myWeight = weight * layer;
        }

        int leftW = 0;
        if(left!=null) {
            leftW = left.sum();
            layer--;
        }


        int rRight= 0;

        if(right!=null) {
            rRight = right.sum();
            layer--;
        }
        //layer--;
        return myWeight + leftW +rRight;
    }

    @Override
    public String toString() {
        return  "{\n"+ name   +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right + "}";
    }

    @Override
    public int compareTo(HNode o) {
        return Integer.compare(weight, o.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HNode hNode = (HNode) o;

        if (name != null ? !name.equals(hNode.name) : hNode.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
