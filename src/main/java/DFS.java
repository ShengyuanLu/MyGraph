import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DFS {

    Map<Node, List<Node>> graph = Maps.newHashMap();

    List<Node> order = Lists.newArrayList();

    List<Node> dfs(String start) {

        Set<Node> nodes = Sets.newLinkedHashSet();
        Node startNode = graph.keySet().stream()
                .filter(n -> n.name.equals(start))
                .findFirst()
                .get();

        nodes.add(startNode);
        nodes.addAll(graph.keySet().stream()
                .filter(n -> !n.name.equals(start))
                .collect(Collectors.toSet()));
        for (Node node : nodes) {
            if (node.color == Node.Color.WHITE)
               visit(node);
        }

        Collections.reverse(order);
        return order;
    }

    void visit(Node node) {
        node.color = Node.Color.GREY;
        graph.get(node).stream()
                .forEach(
                        n -> {
                            if (n.color == Node.Color.WHITE) {
                                visit(n);
                            }
                        }
                );
        node.color = Node.Color.BLACK;
        order.add(node);
    }
}
