import scala.collection.mutable.LinkedHashMap

object SDijkstra {



  def dijkstra(graph: Map[SNode, List[SNode]], start: String, end: String): List[Node] = {
    val path = new LinkedHashMap[SNode, SNode]
    var bigSQueue = List[SNode]()
    bigSQueue ++ graph.keySet.filter(_.name != start)

    var top: SNode = graph.keySet.find(_.name == start).get
    path += (top -> null)

    while (!bigSQueue.isEmpty) {
      val adj: List[SNode] = graph.get(top).filter(bigSQueue.contains(_)).get
      adj.foreach(
        (v) => {

          val candi: SNode = bigSQueue.find(_ == v).get
          val sum = top.distance + v.distance
          candi.distance = if (sum < candi.distance) sum
          else candi.distance
        }

      )

      top = bigSQueue.minBy(_.distance)
      path += (top -> path.keySet.last)
      if (top.name == end) {
        if (top.distance == Integer.MAX_VALUE) List(top)

        else parse(path, top)
      }
    }
    null
  }

  def parse(path: LinkedHashMap[SNode, SNode], end: SNode): List[SNode] = {
    var result = List[SNode]()
    var n = end
    while (path.get(n) != null) {
      result = result :+ n
      n = path.get(n).get
    }
    result = result :+ path.keySet.head

    return result.reverse
  }

}