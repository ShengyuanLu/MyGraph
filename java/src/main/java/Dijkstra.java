import java.util.*;
import java.util.stream.Collectors;

public class Dijkstra{

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.graph.put("A", Arrays.asList(new QElement("B", 1), new QElement("C", 10)));
        dijkstra.graph.put("A", Arrays.asList(new QElement("B", 1), new QElement("C", 10)));
        dijkstra.graph.put("B", Arrays.asList(new QElement("A", 1), new QElement("D", 2)));
        dijkstra.graph.put("C", Arrays.asList(new QElement("A", 10), new QElement("D", 3)));
        dijkstra.graph.put("D", Arrays.asList(new QElement("B", 2), new QElement("C", 3)));
        System.out.println(dijkstra.dijkstra("A"));
    }

    Map<String, List<QElement>> graph = new LinkedHashMap<>();
    List<QElement> bigSQueue = new ArrayList<>();

    static public class QElement {
        String name;
        int distance = 0;

        public QElement(int distance, String name) {
            this.distance = distance;
            this.name = name;
        }

        public QElement(String name, int distance) {
            this.distance = distance;
            this.name = name;
        }

        @Override
        public String toString() {
            return "QElement{" +
                    "distance=" + distance +
                    ", name=" + name +
                    "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            QElement qElement = (QElement) o;

            return name.equals(qElement.name);

        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    List<QElement> S = new ArrayList<>();

     String dijkstra(String start) {
        graph.forEach((k, v) -> {
            if (!k.equals(start)) {
                bigSQueue.add(new QElement(Integer.MAX_VALUE, k));
            }
        });

        QElement top = new QElement(0, start);
        S.add(top);
        while (!bigSQueue.isEmpty()) {
            top = S.get(S.size() - 1);
            System.out.println("poll: " + top);

            List<QElement> adj = graph.get(top.name).stream()
                    .filter(v -> bigSQueue.contains(v))
                    .collect(Collectors.toList());

            for (QElement v : adj) {
                System.out.println("top.distance: " + top.distance + ", v.getDistance(): " + v.distance);
                Integer alt = top.distance + v.distance;

                QElement candi = bigSQueue.stream().filter(e -> e.equals(v)).findFirst().get();
                int vDistance = candi.distance;
                System.out.println("vDistance: " + vDistance);

                if (alt < vDistance) {
                    candi.distance = alt;
                }
            }

            System.out.println("--------- ");
            QElement min = bigSQueue.stream().min((a, b) ->
                            Integer.compare(a.distance, b.distance)
            ).get();
            bigSQueue.remove(min);
            S.add(min);
        }

        System.out.println(S);
        return null;
    }


    public void add(String name, List<QElement> q) {
        graph.put(name, q);
    }
}
