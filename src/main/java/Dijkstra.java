import com.google.common.collect.*;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {

    Map<Node, List<Node>> graph = Maps.newHashMap();
    List<Node> bigSQueue = Lists.newArrayList();

    List<Node> dijkstra(String start, String end) {

        LinkedHashMap<Node, Node> path = Maps.newLinkedHashMap();

        bigSQueue.addAll(
                Sets.filter(graph.keySet(), node -> !node.name.equals(start))
        );

        Node top = Iterables.find(graph.keySet(), node -> node.name.equals(start));

        path.put(top, null);   //v -> k
        while (!bigSQueue.isEmpty()) {

            System.out.println("top: " + top);

            List<Node> adj = graph.get(top).stream()
                    .filter(bigSQueue::contains)
                    .collect(Collectors.toList());

            for (Node v : adj) {
                System.out.println("top.distance: " + top.distance + ", v.getDistance(): " + v.distance);
                Integer sum = top.distance + v.distance;

                Node candi = firstOf(bigSQueue.stream()
                        .filter(v::equals));

                int accumulate = candi.distance;
                System.out.println("accumulate: " + accumulate);

                if (sum < accumulate) {
                    candi.distance = sum;
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
