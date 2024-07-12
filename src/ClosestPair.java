package src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ClosestPair {

    private static Helpers helpers;

    public ClosestPair() {
        this.helpers = new Helpers();
    }

    // Closest Pair algorithm helper.
    // Get points from user.

    public Point[] getPoints() {
        int inputSize = 0;
        Scanner scanner = new Scanner(System.in);
        boolean enteredValidInputSize = false;
        do {
            System.out.println("Enter the number of points: ");
            inputSize = scanner.nextInt();
            if (inputSize > 0 && inputSize < 100) { // Let's keep it simple
                enteredValidInputSize = true;
            } else {
                System.out.println("Please enter a valid & feasible number.");
            }
        } while (!enteredValidInputSize);

        Point[] inputPoints = new Point[inputSize];

        for (int i = 0; i < inputSize; i++) {
            int x, y;
            x = helpers.getSize("Enter the X coordinate for point " + (i + 1));
            y = helpers.getSize("Enter the Y coordinate for point " + (i + 1));
            inputPoints[i] = new Point(x, y);
        }
        scanner.close();
        return inputPoints;
    }

    // Closest pair helper
    // Finding the distance between two points

    public static float pointDistance(Point pA, Point pB) {
        float result = (float) Math.sqrt(
                (pA.x - pB.x) * (pA.x - pB.y)
                        +
                        (pA.y - pB.y) * (pA.y - pB.y));
        return result;
    }

    // Closest pair helper
    // Find the smallest distance between two points
    // Exhaustive

    public static float exhaustiveSmallestDistance(Point[] Points, int numOfItems) {
        float min = Float.MAX_VALUE;
        float currentMin = 0;

        for (int i = 0; i < numOfItems; i++) {
            for (int j = i + 1; j < numOfItems; j++) {
                currentMin = pointDistance(Points[i], Points[j]);
                if (currentMin < min) {
                    min = currentMin;
                }
            }
        }
        return min;
    }

    // Closest Pair helper
    // Find the smallest distance in a subbset of the input points
    // Exhaustive

    public static float closestInAPartition(Point[] partition, int numOfItems, float minDistanceUpperBound) {
        float min = minDistanceUpperBound;

        Arrays.sort(partition, 0, numOfItems, new Comparator<Point>() {
            // Compare all points by y coordinate
            @Override
            public int compare(Point pA, Point pB) {
                return Integer.compare(pA.y, pB.y);
            }
        });

        for (int i = 0; i < numOfItems; i++) {
            for (int j = i + 1; j < numOfItems && (partition[j].y - partition[i].y) < min; j++) {
                float dist = pointDistance(partition[i], partition[j]);

                if (dist < min) {
                    min = dist;
                }
            }
        }

        return min;
    }
    // Closest pair helper
    // The name is self explanatory
    // Divide and Conquer

    public static float recursivelyFindSmallestDistance(Point[] points, int start, int end) {
        if ((end - start) <= 3) {
            return exhaustiveSmallestDistance(points, end);
        }

        int middle = start + (end - start) / 2;

        Point midPoint = points[middle];

        float smallestDistanceOnLeft = closestInAPartition(points, start, middle);
        float smallestDistanceOnRight = closestInAPartition(points, middle, end);

        float smallestDistance = Math.min(smallestDistanceOnLeft, smallestDistanceOnRight);

        Point[] partition = new Point[end];
        int count = 0;

        for (int i = 0; i < end; i++) {
            if (Math.abs(points[i].x - midPoint.x) < smallestDistance) {
                partition[count] = points[i];
                count++;
            }
        }

        return Math.min(smallestDistance, closestInAPartition(partition, count, smallestDistance));
    }

    // Closest Pair Problem
    public float compute(Point[] points, int numOfItems) {
        Arrays.sort(points, 0, numOfItems, new Comparator<Point>() {
            @Override
            public int compare(Point pA, Point pB) {
                return Integer.compare(pA.x, pB.x);
            }
        });

        return recursivelyFindSmallestDistance(points, 0, numOfItems);
    }

}

// Point class for Closest Pair Problem
class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
