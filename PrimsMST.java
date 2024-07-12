import java.util.*;

class Edge implements Comparable<Edge> {
    int startVertex;
    int endVertex;
    int weight;

    public Edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class PrimsMST {

    public static void primMST(List<List<Edge>> adjacencyList, int numberOfVertices) {
        boolean[] isInMST = new boolean[numberOfVertices];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        int[] minimumWeight = new int[numberOfVertices]; // Used to store the minimum weight to reach each vertex
        int[] parentVertex = new int[numberOfVertices]; // Array to store the MST

        // Initialize minimum weights to infinity
        Arrays.fill(minimumWeight, Integer.MAX_VALUE);
        minimumWeight[0] = 0; // Start from the first vertex
        parentVertex[0] = -1; // First node is the root of the MST

        // Add the first vertex to the priority queue
        minHeap.add(new Edge(-1, 0, 0));

        while (!minHeap.isEmpty()) {
            Edge currentEdge = minHeap.poll();
            int currentVertex = currentEdge.endVertex;

            if (isInMST[currentVertex]) {
                continue;
            }

            isInMST[currentVertex] = true;

            // Process all the adjacent vertices of the dequeued vertex
            for (Edge adjacentEdge : adjacencyList.get(currentVertex)) {
                int adjacentVertex = adjacentEdge.endVertex;
                int edgeWeight = adjacentEdge.weight;

                if (!isInMST[adjacentVertex] && edgeWeight < minimumWeight[adjacentVertex]) {
                    minimumWeight[adjacentVertex] = edgeWeight;
                    minHeap.add(new Edge(currentVertex, adjacentVertex, edgeWeight));
                    parentVertex[adjacentVertex] = currentVertex;
                }
            }
        }

        // Print the constructed MST
        System.out.println("Edge \tWeight");
        for (int vertex = 1; vertex < numberOfVertices; vertex++) {
            System.out.println(parentVertex[vertex] + " - " + vertex + "\t" + minimumWeight[vertex]);
        }
    }

    // public static void main(String[] args) {
    // int numberOfVertices = 6;
    // List<List<Edge>> adjacencyList = new ArrayList<>();

    // for (int i = 0; i < numberOfVertices; i++) {
    // adjacencyList.add(new ArrayList<>());
    // }

    // adjacencyList.get(0).add(new Edge(0, 1, 4));
    // adjacencyList.get(0).add(new Edge(0, 2, 3));
    // adjacencyList.get(1).add(new Edge(1, 2, 1));
    // adjacencyList.get(1).add(new Edge(1, 3, 2));
    // adjacencyList.get(2).add(new Edge(2, 3, 4));
    // adjacencyList.get(3).add(new Edge(3, 4, 2));
    // adjacencyList.get(4).add(new Edge(4, 5, 6));

    // // Since the graph is undirected, add reverse edges
    // adjacencyList.get(1).add(new Edge(1, 0, 4));
    // adjacencyList.get(2).add(new Edge(2, 0, 3));
    // adjacencyList.get(2).add(new Edge(2, 1, 1));
    // adjacencyList.get(3).add(new Edge(3, 1, 2));
    // adjacencyList.get(3).add(new Edge(3, 2, 4));
    // adjacencyList.get(4).add(new Edge(4, 3, 2));
    // adjacencyList.get(5).add(new Edge(5, 4, 6));

    // primMST(adjacencyList, numberOfVertices);
    // }
}
