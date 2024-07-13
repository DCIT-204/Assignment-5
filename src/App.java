package src;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class App {

    private static Helpers helpers = new Helpers();

    public static void main(String[] args) {
        System.out.println("Welcome to the Algorithm Navigation App!");
        runProgram();
    }

    public static void runAlgorithm(Scanner scanner) {
        long timeTaken = 0;
         ArrayList<String> choices = new ArrayList<>(List.of(
                "1. Quick Sort",
                "2. Merge Sort",
                "3. The Closest-Pair Algorithm",
                "4. The Travelling Salesman Algorithm",
                "5. The Quickhull Algorithm",
                "6. Prim's MST Algorithm",
                "7. Kruskal's MST Algorithm",
                "8. Dijkstra's Algorithm",
                "9. Huffman Encoding",
                "10. Strassen Matrix Multiplication"));
        int preference = helpers.getChoice("Select an algorithm to run:",choices );
        System.out.println("You selected: " + choices.get(preference-1) );
        switch (preference) {
            case 1:
                int[] quickSortArr = helpers.getArray();
                timeTaken = helpers.measureTime(() -> {
                    QuickSort.quickSort(quickSortArr, 0, quickSortArr.length - 1);
                    System.out.println("Sorted list: " + Arrays.toString(quickSortArr));
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 2:
                int[] mergeSortArr = helpers.getArray();
                timeTaken = helpers.measureTime(() -> {
                    MergeSort.mergeSort(mergeSortArr);
                    System.out.println("Sorted list: " + Arrays.toString(mergeSortArr));
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 3:
                ClosestPair closestPair = new ClosestPair();
                Point[] inputPoints = closestPair.getPoints();
                timeTaken = helpers.measureTime(() -> {
                    float smallestDistance = closestPair.compute(inputPoints, inputPoints.length);
                    System.out.println("Smallest distance: " + smallestDistance);
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 4:
                int[][] graph = helpers.getMatrixTSP();
                int s = helpers.getSize("Enter the starting vertex: ");
                TravellingSalesman travellingSalesman = new TravellingSalesman();
                timeTaken = helpers.measureTime(() -> {
                    int min_path = travellingSalesman.compute(graph, s);
                    System.out.println("Minimum path weight: " + min_path);
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 5:
                ArrayList<double[]> points = helpers.getPoints();
                timeTaken = helpers.measureTime(() -> {
                    ArrayList<double[]> hull = QuickHull.quickHull(points);
                    System.out.println("Convex Hull points:");
                    for (double[] point: hull){
                        System.out.println("{" + point[0] + ", " + point[1] + "}");
                    }
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 6:
                int numberOfVertices = helpers.getSize("Enter the number of vertices (minimum 2): ", 2);
                List<List<Edge>> adjacencyList = new ArrayList<>();
                for (int i = 0; i < numberOfVertices; i++) {
                    adjacencyList.add(new ArrayList<>());
                }
                System.out.println("Enter the edges (format: startVertex endVertex weight):");
                System.out.println("Vertex values should be between 0 and " + (numberOfVertices - 1));

                // You need numberOfVertices - 1 edges to form a minimum spanning tree
                int edgesNeeded = numberOfVertices - 1;
                int edgesEntered = 0;

                while (edgesEntered < edgesNeeded) {
                    String input = scanner.nextLine().trim();
                    String[] parts = input.split("\\s+");
                    if (parts.length < 3) {
                        System.out.println("Invalid input format. Please enter again.");
                        continue;
                    }
                    try {
                        int startVertex = Integer.parseInt(parts[0]);
                        int endVertex = Integer.parseInt(parts[1]);
                        int weight = Integer.parseInt(parts[2]);
                        if (startVertex < 0 || startVertex >= numberOfVertices ||
                                endVertex < 0 || endVertex >= numberOfVertices) {
                            System.out.println("Vertex values should be between 0 and " + (numberOfVertices - 1));
                            continue;
                        }
                        adjacencyList.get(startVertex).add( new Edge(startVertex, endVertex, weight));
                        adjacencyList.get(endVertex).add(new Edge(endVertex, startVertex, weight)); // for undirected
                                                                                                    // graph
                        edgesEntered++;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter again.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Vertex index out of bounds. Please enter again.");
                    }
                }

                // After enough edges have been entered, proceed with Prim's MST algorithm
                timeTaken = helpers.measureTime(() -> {
                    PrimsMST.primMST(adjacencyList, numberOfVertices);
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 8:
                int[][] dijkGraph = helpers.getMatrixTSP();
                int source = helpers.getSize("Enter the source node: ");
                int target = helpers.getSize("Enter the target node: ");
                timeTaken = helpers.measureTime(() -> {
                    int min_path = Djikstra.dijkstra(dijkGraph,source-1,target-1);

                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            case 10:
                int size = Helpers.getMatrixSize(scanner);
                System.out.println("Enter the elements of the first matrix:");
                int[][] A = Helpers.getMatrixStrassen(scanner, size);

                System.out.println("Enter the elements of the second matrix:");
                int[][] B = Helpers.getMatrixStrassen(scanner, size);

                timeTaken = helpers.measureTime(() -> {
                    int[][] result = StrassenMatrixMultiplication.strassenMultiply(A, B);
                    System.out.println("Resulting matrix:");
                    Helpers.printMatrix(result);
                });
                System.out.println("Time taken (nanoseconds): " + timeTaken + "\n");
                break;

            default:
                System.out.println("Preference not Available. Try again.");
                break;
        }

    }

    public static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            runAlgorithm(scanner);
            ArrayList<String> programChoice = new ArrayList<>(
                    List.of("1. Run the program again", "2. Quit"));
            choice = helpers.getChoice("What would you like to do next? ", programChoice);
        } while (choice == 1);

        scanner.close();
        System.out.println("Bye 👋");

    }

}
