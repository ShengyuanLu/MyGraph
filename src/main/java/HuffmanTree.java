

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

public class HuffmanTree {

    static HNode buildHuffmanTree(Set<HNode> nodes) {
        nodes.stream().forEach(
                n -> n.parent = n.left = n.right = null
        );

        HNode root = null;
        while (nodes.stream().anyMatch(n -> !n.isBuilt && n.parent == null)) {
            List<HNode> mins = nodes.stream()
                    .sorted()
                    .limit(2)
                    .collect(Collectors.toList());

            HNode builtNode = new HNode();
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

    public HNode() {
        name = "builtIn";
        isBuilt = true;
    }

    public HNode(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", left=" + left +
                ", right=" + right +
                ", isBuilt=" + isBuilt + "}";
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
