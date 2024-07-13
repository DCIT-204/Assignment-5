package src;

import java.util.*;

public class Djikstra {


 static List<String> getSolution(util.Node node){
  ArrayList<String> path = new ArrayList<>();
   while(node.parent != null){
        path.add("From " + (node.parent.state + 1)+ " to " + (node.state + 1));
        node = node.parent;
   }
   return path.reversed();
}
  public static int dijkstra(int[][] graph, int start, int end) {
    Set<Integer> visited = new HashSet<>();
    util.QueueFrontier queue = new util.QueueFrontier();
    queue.enQueue(new util.Node(start, null, 0));

    while (!queue.isEmpty()) {
      util.Node node = queue.deQueue();
      if (node == null) {
        break;
      }

      int currentNode = node.state;
      int currentDistance = node.distanceFromParent;

      if (currentNode == end) {
        System.out.println("Path: " + getSolution(node).toString());
        System.out.println("Distance: " + currentDistance);
        continue;
      }

      if (visited.contains(currentNode)) {
        continue;
      }

      visited.add(currentNode);

      for (int i = 0; i < graph[currentNode].length; i++) {
        if (graph[currentNode][i] != 0) {
          int newDistance = currentDistance + graph[currentNode][i];
          queue.enQueue(new util.Node(i, node, newDistance));

        }
      }
    }
    return -1; // Return -1 if there is no path found
  }

}