package src;

import java.util.Arrays;

public class Question7A {
    public class MatrixMultiplication {

        public static int[][] multiply(int[][] A, int[][] B, int numThreads) throws InterruptedException {
            int n = A.length;
            int[][] C = new int[n][n];

            // create an array of threads
            Thread[] threads = new Thread[numThreads];

            // calculate number of rows each thread should handle
            int rowsPerThread = n / numThreads;

            for (int t = 0; t < numThreads; t++) {
                // calculate start and end rows for this thread
                int startRow = t * rowsPerThread;
                int endRow = (t == numThreads-1) ? n : (t+1) * rowsPerThread;

                // create a new thread to handle the assigned rows
                threads[t] = new Thread(new MatrixMultiplicationThread(A, B, C, startRow, endRow));
                threads[t].start();
            }

            // wait for all threads to finish
            for (Thread thread : threads) {
                thread.join();
            }

            return C;
        }

        private static class MatrixMultiplicationThread implements Runnable {
            private int[][] A;
            private int[][] B;
            private int[][] C;
            private int startRow;
            private int endRow;

            public MatrixMultiplicationThread(int[][] A, int[][] B, int[][] C, int startRow, int endRow) {
                this.A = A;
                this.B = B;
                this.C = C;
                this.startRow = startRow;
                this.endRow = endRow;
            }

            public void run() {
                int n = A.length;
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};

        try {
            int[][] C = MatrixMultiplication.multiply(A, B, 2);
            // print the resulting matrix C
            for (int[] row : C) {
                System.out.println(Arrays.toString(row));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
