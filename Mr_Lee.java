import java.util.*;

public class Mr_Lee {
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = sc.nextInt();
                    if (matrix[i][j] == 0) {
                        matrix[i][j] = (int) 1e9;
                    }
                }
            }

            dp = new int[n][1 << n];
            for (int[] row : dp) Arrays.fill(row, -1);

            int ans = helper(0, 1, matrix, n);
            System.out.println(ans);
        }
        sc.close();
    }

    public static int helper(int pos, int mask, int[][] matrix, int n) {
        if (mask == (1 << n) - 1) return matrix[pos][0];

        if (dp[pos][mask] != -1) return dp[pos][mask];

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {  // If node i has not been visited
                int newCost = matrix[pos][i] + helper(i, mask | (1 << i), matrix, n);
                minCost = Math.min(minCost, newCost);
            }
        }

        return dp[pos][mask] = minCost;
    }
}