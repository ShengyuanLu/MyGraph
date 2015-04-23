import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class DFS {

    Map<Node, List<Node>> graph = Maps.newHashMap();

    List<Node> dfs(String start) {
        graph.keySet().stream()
                .forEach(this::visit);
        return null;
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
    }
}
