package src;
import java.util.*;
    public class Node{
        public int state;
        public Integer parent;
        public int distanceFromParent;
        public Node(int state, Integer parent, int distanceFromDistance){
            this.state = state;
            this.parent = parent;
            this.distanceFromParent = distanceFromDistance;
        }

    }

static class Queue {


    int front = -1;
    int back = -1;

    List<Integer> items = new ArrayList<>();
    public boolean isFull() {
        return back == items.size() - 1;
    }

    public boolean isEmpty() {
        return front == -1 && back == -1;
    }


    public void enQueue(int itemValue) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else if (isEmpty()) {
            front = back = 0;
            items.set(back, itemValue);
        } else {
            back++;
            items.set(back, itemValue);
        }
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Nothing to dequeue");
        } else if (front == back) {
            front = back = -1;
        } else {
            front++;
        }
        return items.get(front);
    }

    public void display() {
        int i;

        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            for (i = front; i <= back; i++) {
                System.out.println(items.get(i));
            }
        }
    }

    public void peak() {
        System.out.println("Front value is: " + items.get(front));
    }

    public void clear() {
        front = back = -1;
    }

    public boolean contains(int value){
        for(int i = front; i <= back; i++){
            if(items.get(i) == value){
                return true;
            }
        }
        return false;
    }
}

public List<Integer> neighboursOfNode(int [][] graph, int node){
    List<Integer> neighbours = new ArrayList<>();
    for(int i = 0; i < graph.length; i++){
        if(graph[node][i] != 0){
            neighbours.add(i);
        }
    }
    return neighbours;
}
}