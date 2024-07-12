package src;

import java.util.ArrayList;
import java.util.Arrays;
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

    public int[][] getMatrixTSP() {
        Scanner scanner = new Scanner(System.in);
        int n = getSize("Enter the number of vertices in the graph: ");
        int[][] matrix = new int[n][n];
        try {
            for (int i = 0; i < n; i++) {
                System.out.println("Enter the distances from vertex " + (i + 1) + " to all other vertices: ");
                for (int j = 0; j < n; j++) {
                    System.out.print("Vertex " + (i + 1) + ": ");
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

    public int[][] getMatrixAssignment() {
        Scanner scanner = new Scanner(System.in);
        int m = getSize("Enter the number of workers: ");
        int n = getSize("Enter the number of tasks: ");
        int[][] matrix = new int[m][n];
        try {
            for (int i = 0; i < m; i++) {
                System.out.println("Enter the cost of worker " + (i + 1) + " for all tasks: ");
                for (int j = 0; j < n; j++) {
                    System.out.print("Task " + (j + 1) + ": ");
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        } catch (Exception e) {
            System.out.println("Please enter a valid number!");
            scanner.next(); // To clear the invalid input
            getMatrixAssignment();
        }
        return matrix;
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

}
