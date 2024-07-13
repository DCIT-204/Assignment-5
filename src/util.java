package src;

import java.util.ArrayList;
import java.util.List;

public  class util {
    static class Node {
        public int state;
        public Node parent;
        public int distanceFromParent;

        public Node(int state, Node parent, int distanceFromParent) {
            this.state = state;
            this.parent = parent;
            this.distanceFromParent = distanceFromParent;
        }
    }

    static class QueueFrontier {
        List<Node> items = new ArrayList<>();

        public QueueFrontier(){
            Node endNode = new Node(Integer.MAX_VALUE,null,Integer.MAX_VALUE);
            items.add(endNode);
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public void enQueue(Node item) {

            for(Node node: items){
                if(node.distanceFromParent > item.distanceFromParent){
                    items.add(items.indexOf(node), item);

                    if(node.parent == item.parent && node.state == item.state){
                        items.remove(node);
                    }
                    return;
                }
                //maybe another check to avoid repeating nodes.
            }
                items.add(item);
        }

        public Node deQueue() {
            if (isEmpty()) {
                System.out.println("Queue is empty. Nothing to dequeue");
                return null;
            }
                Node item = items.get(0);
                items.remove(0);
                return item;

        }

        public boolean contains(int value) {
            for (int i = 0; i <= items.size(); i++) {
                if (items.get(i).state == value) {
                    return true;
                }
            }
            return false;
        }
    }

    public static List<Integer> neighboursOfNode(int[][] graph, int node) {
        List<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                neighbours.add(i);
            }
        }
        return neighbours;
    }
}