package src;


public class Question7A {

    public static void main(String[] args) {
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();
        matrixMultiplication.runMatrixMultiplication();
    }

    public static class MatrixMultiplication {

        private static int[][] x;
        private static int[][] y;
        private static int[][] z;
        private static int n;
        private static int numThreads;

        public void runMatrixMultiplication() {
            n = 1000;
            numThreads = 4;
            x = new int[n][n];
            y = new int[n][n];
            z = new int[n][n];

            // Initialize matrices A and B with random values
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    x[i][j] = (int) (Math.random() * 100);
                    y[i][j] = (int) (Math.random() * 100);
                }
            }

            // Create threads to multiply slices of the matrices
            Thread[] threads = new Thread[numThreads];
            int sliceSize = n / numThreads;
            for (int i = 0; i < numThreads; i++) {
                int start = i * sliceSize;
                int end = (i + 1) * sliceSize;
                threads[i] = new Thread(new MatrixMultiplier(start, end));
                threads[i].start();
            }

            // Wait for all threads to complete
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Print the result
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(z[i][j] + " ");
                }
                System.out.println();
            }
        }

        private static class MatrixMultiplier implements Runnable {

            private int start;
            private int end;

            public MatrixMultiplier(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public void run() {
                for (int i = start; i < end; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            z[i][j] += x[i][k] * y[k][j];
                        }
                    }
                }
            }
        }
    }
}
