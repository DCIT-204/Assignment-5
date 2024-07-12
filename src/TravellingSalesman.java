package src;

import java.util.ArrayList;

public class TravellingSalesman {

    // Travelling Salesman Problem
    public int compute(int graph[][], int s) {
        int V = graph[0].length;

        ArrayList<Integer> vertex = new ArrayList<Integer>();

        for (int i = 0; i < V; i++)
            if (i != s)
                vertex.add(i);

        int min_path = Integer.MAX_VALUE;
        do {
            int current_pathweight = 0;
            int k = s;
            for (Integer integer : vertex) {
                current_pathweight += graph[k][integer];
                k = integer;
            }
            current_pathweight += graph[k][s];
            min_path = Math.min(min_path, current_pathweight);

        } while (findNextPermutation(vertex));

        return min_path;
    }

    // Travelling Salesman Helper
    public static ArrayList<Integer> swap(ArrayList<Integer> data, int left, int right) {
        int temp = data.get(left);
        data.set(left, data.get(right));
        data.set(right, temp);
        return data;
    }

    // Travelling Salesman Helper
    public static ArrayList<Integer> reverse(ArrayList<Integer> data, int left, int right) {
        while (left < right) {
            int temp = data.get(left);
            data.set(left++, data.get(right));
            data.set(right--, temp);
        }
        return data;
    }

    // Travelling Salesman Helper
    public static boolean findNextPermutation(ArrayList<Integer> data) {
        if (data.size() <= 1)
            return false;
        int last = data.size() - 2;

        while (last >= 0) {
            if (data.get(last) < data.get(last + 1)) {
                break;
            }
            last--;
        }

        if (last < 0)
            return false;

        int nextGreater = data.size() - 1;
        for (int i = data.size() - 1; i > last; i--) {
            if (data.get(i) > data.get(last)) {
                nextGreater = i;
                break;
            }
        }

        data = swap(data, nextGreater, last);

        data = reverse(data, last + 1, data.size() - 1);

        return true;
    }

}
