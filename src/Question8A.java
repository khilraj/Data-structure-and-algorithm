package src;

import static src.Question8A.MaxSquareArea.getMaxSquareArea;

public class Question8A {

    public static void main(String[] args) {
        int[][] mat = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        int maxSquareArea = getMaxSquareArea(mat);
        System.out.println("Maximum area of square made by 0s: " + maxSquareArea);
    }
    public class MaxSquareArea {

        public static int getMaxSquareArea(int[][] mat) {
            int m = mat.length;
            int n = mat[0].length;
            int[][] dp = new int[m][n];
            int maxSize = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        dp[i][j] = 1 + Math.min(Math.min(get(dp, i-1, j), get(dp, i, j-1)), get(dp, i-1, j-1));
                        maxSize = Math.max(maxSize, dp[i][j]);
                    }
                }
            }
            return maxSize * maxSize;
        }

        public static int get(int[][] dp, int i, int j) {
            if (i < 0 || j < 0 || i >= dp.length || j >= dp[0].length) {
                return 0;
            }
            return dp[i][j];
        }
    }

}
