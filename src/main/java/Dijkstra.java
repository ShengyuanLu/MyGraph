import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    Map<Node, List<Node>> graph = Maps.newHashMap();
    List<Node> bigSQueue = Lists.newArrayList();

    static public class Node {
        String name;
        int distance = 0;

        public Node(String name, int distance) {
            this.distance = distance;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +  name  + ", distance=" + (distance == Integer.MAX_VALUE ? "infinite" : distance) + '}';
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

        LinkedHashMap<Node, Node> path = Maps.newLinkedHashMap();
        bigSQueue.addAll(
                graph.keySet().stream()
                        .filter(node -> !node.name.equals(start))
                        .collect(Collectors.toList())
        );

        Node top = firstOf(graph.keySet().stream()
                              .filter(node -> node.name.equals(start)));

        path.put(top, null);   //v -> k
        while (!bigSQueue.isEmpty()) {

            System.out.println("top: " + top);

            List<Node> adj = graph.get(top).stream()
                    .filter(bigSQueue::contains)
                    .collect(Collectors.toList());

            for (Node v : adj) {
                System.out.println("top.distance: " + top.distance + ", v.getDistance(): " + v.distance);
                Integer alt = top.distance + v.distance;

                Node candi = firstOf(bigSQueue.stream()
                        .filter(v::equals));

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

            Node prev = lastOf(path.keySet());

            path.put(top, prev);
            if (top.name.equals(end)) {
                if(top.distance == Integer.MAX_VALUE) {
                    return Lists.newArrayList(top);
                }
                return parse(path, top);
            }
            bigSQueue.remove(top);
        }

        return null;
    }

    List<Node> parse(LinkedHashMap<Node, Node> path, Node end) {
        List<Node> result = Lists.newArrayList();

        Node n = end;
        while(path.get(n) != null) {
            result.add(n);
            n = path.get(n);
        }

        result.add(firstOf(path.keySet()));
        Collections.reverse(result);
        System.out.println("return: " + result);
        return result;
    }

    <T> T firstOf(Collection<T> c) {
         return FluentIterable.from(c)
                 .first()
                 .get();
    }

    <T> T firstOf(Stream<T> s) {
        return s.findFirst().get();
    }

    <T> T lastOf(Collection<T> c) {
        return FluentIterable.from(c)
                .last()
                .get();
    }
}
