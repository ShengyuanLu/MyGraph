import com.google.common.collect.FluentIterable;

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
            return "Node{" +  name  + ", distance=" + distance +
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

    List<Node> dijkstra(String start, String end) {

        LinkedHashMap<Node, Node> path = new LinkedHashMap<>();
        bigSQueue.addAll(
                graph.keySet().stream()
                        .filter(node -> !node.name.equals(start))
                        .collect(Collectors.toList())
        );

        Node top = graph.keySet().stream()
                .filter(node -> node.name.equals(start))
                .findFirst()
                .get();

        path.put(top, null);   //v -> k
        while (!bigSQueue.isEmpty()) {

            System.out.println("top: " + top);

            List<Node> adj = graph.get(top).stream()
                    .filter(bigSQueue::contains)
                    .collect(Collectors.toList());

            for (Node v : adj) {
                System.out.println("top.distance: " + top.distance + ", v.getDistance(): " + v.distance);
                Integer alt = top.distance + v.distance;

                Node candi = bigSQueue.stream()
                        .filter(v::equals)
                        .findFirst()
                        .get();
                int accumulate = candi.distance;
                System.out.println("accumulate: " + accumulate);

                if (alt < accumulate) {
                    candi.distance = alt;
                }
            }

            System.out.println("--------- ");
            top = bigSQueue.stream()
                    .min((a, b) -> Integer.compare(a.distance, b.distance))
                    .get();


            Node prev = FluentIterable.from(path.keySet())
                    .last()
                    .get();
            path.put(top, prev);
            if (top.name.equals(end)) {
               System.out.println("return:" + path);
                return parse(path, top);
            }
            bigSQueue.remove(top);
        }

        System.out.println("return:" + path);
        return null;//parse(path);
    }

    List<Node> parse(LinkedHashMap<Node, Node> path, Node end) {
        List<Node> result = new ArrayList<>();

        Node n = end;
        while(path.get(n) != null) {
            result.add(n);
            n = path.get(n);
        }

        result.add(
                FluentIterable.from(path.keySet())
                .first()
                .get()
        );
        Collections.reverse(result);
        return result;
    }

}
