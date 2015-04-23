
public class Node {

    String name;
    int distance = 0;

    public Node(String name, int distance) {
        this.distance = distance;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" + name + ", distance=" + (distance == Integer.MAX_VALUE ? "infinite" : distance) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node qElement = (Node) o;

        return name.equals(qElement.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
