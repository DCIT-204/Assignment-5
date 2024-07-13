package src;

import java.util.*;

class edge implements Comparable<Edge> {
    int startVertex;
    int endVertex;
    int weight;

    public edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    public edge() {

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
}
