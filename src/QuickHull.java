package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class QuickHull {
    public static ArrayList<double[]> quickHull(ArrayList<double[]> points){
        ArrayList<double[]> hull = new ArrayList<double[]>();
        if (points.size() < 3) {
            return points;
        }
        try {
            ArrayList<double[]> arrangedPoints = new ArrayList<double[]>();
            ArrayList<double[]> minAndMaxPoints = new ArrayList<double[]>();
            ArrayList<double[]> pointsAbovePartition = new ArrayList<double[]>();
            ArrayList<double[]> pointsBelowPartition = new ArrayList<double[]>();
            arrangedPoints = arrangePoints(points);
            minAndMaxPoints = findMinAndMaxPoints(arrangedPoints);
            double[] minPoint = minAndMaxPoints.get(0);
            double[] maxPoint = minAndMaxPoints.get(1);

            hull.add(minPoint);
            hull.add(maxPoint);

            pointsAbovePartition = findPointsAbovePartition(arrangedPoints, minAndMaxPoints);
            pointsBelowPartition = findPointsBelowPartition(arrangedPoints, minAndMaxPoints);
            ArrayList<double[]> pointsWithMaxDistance = findMaxDistanceFromMaxAndMinPoints(pointsAbovePartition, pointsBelowPartition, minAndMaxPoints);
            ArrayList<ArrayList<double[]>> pointsAfterQuickHull = removePointsInsideTriangle(minAndMaxPoints, pointsWithMaxDistance.get(0), pointsWithMaxDistance.get(1), pointsAbovePartition, pointsBelowPartition);

            hull.addAll(quickHull(pointsAfterQuickHull.get(0)));
            hull.addAll(quickHull(pointsAfterQuickHull.get(1)));

            if (!pointsAfterQuickHull.get(0).isEmpty()) {
                quickHull(pointsAfterQuickHull.get(0));
            }
            if (!pointsAfterQuickHull.get(1).isEmpty()) {
                quickHull(pointsAfterQuickHull.get(1));
            }
        }catch (Exception e){
            System.out.println("Please enter an array of points");
        }
        return hull;
    }
    public static ArrayList<double[]> arrangePoints(ArrayList<double[]> points){
        int n = points.size();
        // using bubble sort to sort the points from lowest x value to highest x value
        for (int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if (points.get(j)[0] > points.get(j + 1)[0]) {
                    double[] temp = points.get(j);
                    points.set(j, points.get(j + 1));
                    points.set((j + 1), temp);
                }
            }
        }
        return points;
    }
    public static ArrayList<double[]> findMinAndMaxPoints(ArrayList<double[]> points){
        int n = points.size();
        ArrayList<double[]> minAndMaxPoints = new ArrayList<double[]>();
        double minimum_x = points.get(0)[0];
        double minimum_x_y_val = points.get(0)[1];
        double maximum_x = points.get(n - 1)[0];
        double maximum_x_y_val = points.get(n - 1)[1];

        double[] min_x = {minimum_x, minimum_x_y_val};
        double[] max_x = {maximum_x, maximum_x_y_val};
        minAndMaxPoints.add(min_x);
        minAndMaxPoints.add(max_x);

        return minAndMaxPoints;
    }

    public static double findYValueOfPointInPartitionEquation(ArrayList<double[]> min_max_points, double x){
        double x1 = min_max_points.get(0)[0];
        double x2 = min_max_points.get(1)[0];
        double y1 = min_max_points.get(0)[1];
        double y2 = min_max_points.get(1)[1];
        double slope = (y2 - y1) / (x2 - x1);
        return (slope * (x - x1)) + y1;
    }
    public static ArrayList<double[]> findPointsAbovePartition(ArrayList<double[]> points, ArrayList<double[]> min_max_points){
        ArrayList<double[]> pointsAbovePartition = new ArrayList<double[]>();
        for (double[] point : points) {
            if (findYValueOfPointInPartitionEquation(min_max_points, point[0]) < point[1]) {
                pointsAbovePartition.add(point);
            }
        }
        return pointsAbovePartition;
    }
    public static ArrayList<double[]> findPointsBelowPartition(ArrayList<double[]> points, ArrayList<double[]> min_max_points){
        ArrayList<double[]> pointsBelowPartition = new ArrayList<double[]>();
        for (double[] point : points) {
            if (findYValueOfPointInPartitionEquation(min_max_points, point[0]) > point[1]) {
                pointsBelowPartition.add(point);
            }
        }
        return pointsBelowPartition;
    }
    public static double findDistanceFromPointToPartition(double[] point1, double[] point2, double[] point3){
        double x1 = point1[0];
        double y1 = point1[1];
        double x2 = point2[0];
        double y2 = point2[1];
        double x0 = point3[0];
        double y0 = point3[1];

        double numerator = Math.abs((x0 * (y2 - y1)) - (y0 * (x2 - x1)) + ((x2 * y1) - (y2 * x1)));
        double denominator = Math.sqrt(Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2));
        return numerator / denominator;
    }
    public static ArrayList<double[]> findMaxDistanceFromMaxAndMinPoints(ArrayList<double[]> pointsAbovePartition, ArrayList<double[]> pointsBelowPartition, ArrayList<double[]> max_min_points){
        double maxDistanceAbove = Integer.MIN_VALUE;
        double maxDistanceBelow = Integer.MIN_VALUE;
        double[] maxPointAbove = {Double.NaN, Double.NaN};
        double[] maxPointBelow = {Double.NaN, Double.NaN};
        ArrayList<double[]> pointsBelowPartitionWithMaxDistance = new ArrayList<double[]>();
        ArrayList<double[]> pointsWithMaxDistance = new ArrayList<double[]>();

        // above
        for(double[] point: pointsAbovePartition){
            double distance = findDistanceFromPointToPartition(max_min_points.get(0), max_min_points.get(1), point);
            if(distance > maxDistanceAbove){
                maxDistanceAbove = distance;
                maxPointAbove[0] = point[0];
                maxPointAbove[1] = point[1];
            }
        }

        // below
        for(double[] point: pointsBelowPartition){
            double distance = findDistanceFromPointToPartition(max_min_points.get(0), max_min_points.get(1), point);
            if(distance > maxDistanceBelow){
                maxDistanceBelow = distance;
                maxPointBelow[0] = point[0];
                maxPointBelow[1] = point[1];
            }
        }

        pointsWithMaxDistance.add(maxPointAbove);
        pointsWithMaxDistance.add(maxPointBelow);

        return pointsWithMaxDistance;
    }
    public static ArrayList<ArrayList<double[]>> removePointsInsideTriangle(ArrayList<double[]> minXY, double[] pointAbove, double[] pointBelow, ArrayList<double[]> pointsAboveToCheck, ArrayList<double[]> pointsBelowToCheck){
        double[] vertex1 = minXY.get(0);
        double[] vertex2 = minXY.get(1);

        ArrayList<ArrayList<double[]>> pointsAfterRemoval = new ArrayList<ArrayList<double[]>>();
        double areaOfBiggerTriangleAbove = areaOfTriangle(vertex1, vertex2, pointAbove);
        double areaOfBiggerTriangleBelow = areaOfTriangle(vertex1, vertex2, pointBelow);
        Iterator<double[]> iterator = pointsAboveToCheck.iterator();

        while (iterator.hasNext()) {
            double[] point = iterator.next();
            double area1 = areaOfTriangle(vertex1, vertex2, point);
            double area2 = areaOfTriangle(vertex1, pointAbove, point);
            double area3 = areaOfTriangle(vertex2, pointAbove, point);
            if ((Math.abs(areaOfBiggerTriangleAbove - (area1 + area2 + area3)) < 1e-6) && (!Arrays.equals(point, pointAbove))) {
                iterator.remove();
            }
        }

        iterator = pointsBelowToCheck.iterator();
        while (iterator.hasNext()) {
            double[] point = iterator.next();
            double area1 = areaOfTriangle(vertex1, vertex2, point);
            double area2 = areaOfTriangle(vertex1, pointBelow, point);
            double area3 = areaOfTriangle(vertex2, pointBelow, point);
            if ((Math.abs(areaOfBiggerTriangleBelow - (area1 + area2 + area3)) < 1e-6) && (!Arrays.equals(point, pointBelow))){
                iterator.remove();
            }
        }
        pointsAfterRemoval.add(pointsAboveToCheck);
        pointsAfterRemoval.add(pointsBelowToCheck);
        return pointsAfterRemoval;
    }
    public static double areaOfTriangle(double[] pointA, double[] pointB, double[] pointC){
        double x1 = pointA[0];
        double y1 = pointA[1];
        double x2 = pointB[0];
        double y2 = pointB[1];
        double x3 = pointC[0];
        double y3 = pointC[1];
        return (1.0 / 2.0) * Math.abs((x1 * (y2 - y3)) + (x2 * (y3 - y1)) + (x3 * (y1 - y2)));
    }
}