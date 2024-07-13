package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Helpers {

    public int[] getArray() {
        Scanner scanner = new Scanner(System.in);
        int n = getSize("Enter the number of elements in the array: ");
        int[] arr = new int[n];
        try {
            System.out.println("Enter the elements of the array: ");
            for (int i = 0; i < n; i++) {
                System.out.print("Element " + (i + 1) + ": ");
                arr[i] = scanner.nextInt();
            }
            System.out.println("The array is " + Arrays.toString(arr));
            return arr;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            arr = getArray();
        }
        System.out.println("The array is: " + Arrays.toString(arr));
        return arr;
    }

    public int getSize(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        int n = 0;
        try {
            n = scanner.nextInt();
            if (n < 1) {
                System.out.println("Please enter a number greater than 0!");
                n = getSize(message);
            }
            return n;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            n = getSize(message);
        }
        return n;
    }

    public int getSize(String message, int min) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        int n = 0;
        try {
            n = scanner.nextInt();
            if (n < 1) {
                System.out.println("Please enter a number greater than 0!");
                n = getSize(message,min);
            }

            if(n < min){
                System.out.println("Please enter a number greater than "+min+"!");
                n = getSize(message,min);
            }
            return n;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            n = getSize(message,min);
        }
        return n;
    }

    public int[][] getMatrixTSP() {
        Scanner scanner = new Scanner(System.in);
        int n = getSize("Enter the number of vertices in the graph: ");
        int[][] matrix = new int[n][n];
        try {
            for (int i = 0; i < n; i++) {
                System.out.println("Enter the distances from vertex " + (i + 1) + " to all other vertices: ");
                for (int j = 0; j < n; j++) {
                    System.out.print("Vertex " + (j + 1) + ": ");
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            matrix = getMatrixTSP();
        }
        return matrix;
    }

    public int[][] getMatrixKnapsack() {
        Scanner scanner = new Scanner(System.in);
        int n = getSize("Enter the number of items: ");
        int[][] matrix = new int[n][2];
        try {
            for (int i = 0; i < n; i++) {
                System.out.print("Enter the weight of item " + (i + 1) + ": ");
                matrix[i][0] = scanner.nextInt();
                System.out.print("Enter the value of item " + (i + 1) + ": ");
                matrix[i][1] = scanner.nextInt();
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            matrix = getMatrixKnapsack();
        }
        return matrix;
    }

    public static int getMatrixSize(Scanner scanner) {
        int size = 0;
        System.out.print("Enter the size of the matrices (n x n): ");
        while (true) {
            try {
                size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Size must be a positive integer. Try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.next(); // Consume invalid input
            }
        }
        return size;
    }

    public static int[][] getMatrixStrassen(Scanner scanner, int size) {
        int[][] matrix = new int[size][size];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("Element at row %d, column %d: ", i + 1, j + 1);
                try {
                    matrix[i][j] = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter an integer value.");
                    scanner.next(); // Consume invalid input
                    j--; // Retry current element
                }
            }
        }
        return matrix;
    }

    // Method to get points from the user for the Quickhull algorithm
    public ArrayList<double[]> getPoints() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<double[]> points = new ArrayList<>();
        int numPoints = getSize("Enter number of points: ");
        for (int i = 0; i < numPoints; i++) {
            double[] point = new double[2]; // Assuming 2D points (x, y)
            System.out.print("Enter x-coordinate for point " + (i + 1) + ": ");
            point[0] = scanner.nextDouble();
            System.out.print("Enter y-coordinate for point " + (i + 1) + ": ");
            point[1] = scanner.nextDouble();
            points.add(point);
        }
        return points;
    }

    // Helper method to print a matrix
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public int getChoice(String question, ArrayList<String> choices) {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(question);
            for (String opt : choices) {
                System.out.println(opt);
            }
            choice = scanner.nextInt();
            while (choice < 1 || choice > choices.size()) {
                System.out.println("Enter a number within the specified range");
                choice = getChoice(question, choices);
            }

            return choice;

        } catch (Exception e) {
            System.out.println("Enter a valid number!");
            choice = getChoice(question, choices);
        }
        return choice;
    }

    // Method to measure execution time
    public long measureTime(Runnable algorithm) {
        long startTime = System.nanoTime();
        algorithm.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
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
