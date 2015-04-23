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

        graph.keySet().stream()
                .forEach(n -> n.dfsStatus = Node.DFSStatus.NOT_VISIT);

        Set<Node> nodes = Sets.newLinkedHashSet();
        Node startNode = graph.keySet().stream()
                .filter(n -> n.name.equals(start))
                .findFirst()
                .get();

        nodes.add(startNode); //First as first
        nodes.addAll(graph.keySet().stream()
                .filter(n -> !n.name.equals(start))
                .collect(Collectors.toSet()));

        for (Node node : nodes) {
            if (node.dfsStatus == Node.DFSStatus.NOT_VISIT)
               visit(node);
        }

        return order;
    }

    void visit(Node node) {
        order.add(node);
        node.dfsStatus = Node.DFSStatus.VISITING;
        graph.get(node).stream()
                .forEach(
                        n -> {
                            if (n.dfsStatus == Node.DFSStatus.NOT_VISIT) {
                                visit(n);
                            }
                        }
                );
        node.dfsStatus = Node.DFSStatus.VISITED;
    }
}
