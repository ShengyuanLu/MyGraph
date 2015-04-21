import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra {

    Map<Node, List<Node>> graph = new LinkedHashMap<>();
    List<Node> bigSQueue = new ArrayList<>();

    static public class Node {
        String name;
        int distance = 0;

        public Node(String name, int distance) {
            this.distance = distance;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", distance=" + distance +
                    '}';
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

    Set<Node> dijkstra(String start) {
        bigSQueue.addAll(
                graph.keySet().stream()
                        .filter(node -> !node.name.equals(start))
                        .collect(Collectors.toList())
        );

        Node top = graph.keySet().stream()
                .filter(node -> node.name.equals(start))
                .findFirst()
                .get();

        while (!bigSQueue.isEmpty()) {

            System.out.println("poll: " + top);

            List<Node> adj = graph.get(top).stream()
                    .filter(bigSQueue::contains)
                    .collect(Collectors.toList());

            for (Node v : adj) {
                System.out.println("top.distance: " + top.distance + ", v.getDistance(): " + v.distance);
                Integer alt = top.distance + v.distance;

                Node candi = bigSQueue.stream().filter(e -> e.equals(v)).findFirst().get();
                int vDistance = candi.distance;
                System.out.println("vDistance: " + vDistance);

                if (alt < vDistance) {
                    candi.distance = alt;
                }
            }

            System.out.println("--------- ");
            top = bigSQueue.stream()
                    .min((a, b) -> Integer.compare(a.distance, b.distance))
                    .get();
            bigSQueue.remove(top);
        }

        return graph.keySet();
    }

}
