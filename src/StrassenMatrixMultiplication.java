package src;

public class StrassenMatrixMultiplication {

    // Basically adds to two matrices
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // Basically subtract two matrices
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    // Strassen's matrix multiplication algorithm
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Base case when the matrix is 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Splitting matrices into 4 different submatrices
        int newSize = n / 2;
        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];

        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + newSize];
                a21[i][j] = A[i + newSize][j];
                a22[i][j] = A[i + newSize][j + newSize];

                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + newSize];
                b21[i][j] = B[i + newSize][j];
                b22[i][j] = B[i + newSize][j + newSize];
            }
        }

        // Calculating p1 to p7:
        int[][] p1 = strassenMultiply(a11, subtract(b12, b22));
        int[][] p2 = strassenMultiply(add(a11, a12), b22);
        int[][] p3 = strassenMultiply(add(a21, a22), b11);
        int[][] p4 = strassenMultiply(a22, subtract(b21, b11));
        int[][] p5 = strassenMultiply(add(a11, a22), add(b11, b22));
        int[][] p6 = strassenMultiply(subtract(a12, a22), add(b21, b22));
        int[][] p7 = strassenMultiply(subtract(a11, a21), add(b11, b12));

        // Calculating c11, c12, c21, c22:
        int[][] c11 = add(subtract(add(p5, p4), p2), p6);
        int[][] c12 = add(p1, p2);
        int[][] c21 = add(p3, p4);
        int[][] c22 = subtract(subtract(add(p5, p1), p3), p7);

        // Combining results into a single matrix
        int[][] C = new int[n][n];
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                C[i][j] = c11[i][j];
                C[i][j + newSize] = c12[i][j];
                C[i + newSize][j] = c21[i][j];
                C[i + newSize][j + newSize] = c22[i][j];
            }
        }

        return C;
    }
}
