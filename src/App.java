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
        int preference = helpers.getChoice("Select an algorithm to run:", new ArrayList<>(Arrays.asList(
                "1. Quick Sort",
                "2. Merge Sort",
                "3. The Closest-Pair Algorithm",
                "4. The Travelling Salesman Algorithm",
                "5. The Quickhull Algorithm",
                "6. Prim's MST Algorithm",
                "7. Kruskal's MST Algorithm",
                "8. Dijkstra's Algorithm",
                "9. Huffman Encoding")));
        System.out.println("You selected: " + preference);
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