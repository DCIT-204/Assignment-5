package src;

import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        } else {
            int var1 = array.length / 2;
            int[] var2 = Arrays.copyOfRange(array, 0, var1);
            int[] var3 = Arrays.copyOfRange(array, var1, array.length);
            array = merge(mergeSort(var2), mergeSort(var3));
            return array;
        }
    }

    private static int[] merge(int[] var0, int[] var1) {
        int[] var2 = new int[var0.length + var1.length];
        int var3 = 0;
        int var4 = 0;
        int var5;
        for (var5 = 0; var3 < var0.length && var4 < var1.length; ++var5) {
            if (var0[var3] < var1[var4]) {
                var2[var5] = var0[var3];
                ++var3;
            } else {
                var2[var5] = var1[var4];
                ++var4;
            }
        }
        while (var3 < var0.length) {
            var2[var5] = var0[var3];
            ++var3;
            ++var5;
        }
        while (var4 < var1.length) {
            var2[var5] = var1[var4];
            ++var4;
            ++var5;
        }
        return var2;
    }

}
