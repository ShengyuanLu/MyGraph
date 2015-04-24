import scala.collection.mutable.{LinkedHashMap, ListBuffer}

object SDijkstra {

  def dijkstra(graph: Map[SNode, List[SNode]], start: String, end: String): List[Node] = {
    val path = new LinkedHashMap[SNode, SNode]
    var bigSQueue = new ListBuffer[SNode]
    bigSQueue ++ graph.keySet

    val top:SNode = graph.keySet.find(_.name == start).get
    path += (top -> null)
    null
  }


}