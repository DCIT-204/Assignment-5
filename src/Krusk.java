package src;

import java.util.Arrays;

class Edge implements Comparable<Edge> {
    public int endVertex;
    int start, end, weight;

    public Edge(int currentVertex, int adjacentVertex, int edgeWeight) {
    }

    public Edge() {
        
    }

    // Comparator function used for sorting edges based on their weight
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}


class Subset {
    int parent, rank;
}

class Graph {
    int vertices, edges; // Number of vertices and edges
    Edge[] edgeArray; // Collection of all edges

    // Graph creation with V vertices and E edges
    Graph(int v, int e) {
        vertices = v;
        edges = e;
        edgeArray = new Edge[e];
        for (int i = 0; i < e; ++i) {
            edgeArray[i] = new Edge();
        }
    }
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y.
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // The main function to construct MST using Kruskal's algorithm
    void KruskalMST() {
        Edge[] result = new Edge[vertices];
        int e = 0; // An index variable for result[]
        int i = 0; // An index variable for sorted edges

        for (i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        Arrays.sort(edgeArray);

        // Allocate memory for creating V subsets
        Subset[] subsets = new Subset[vertices];
        for (i = 0; i < vertices; ++i) {
            subsets[i] = new Subset();
        }

        // Create V subsets with single elements
        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < vertices - 1) {

            Edge next_edge = edgeArray[i++];

            int x = find(subsets, next_edge.start);
            int y = find(subsets, next_edge.end);

            // increment the index of result for the next edge
            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }

        // Print the contents of result[] to display the built MST
        System.out.println("These are the edges in the constructed MST:");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].start + " -- " + result[i].end + " == " + result[i].weight);
        }
    }
}