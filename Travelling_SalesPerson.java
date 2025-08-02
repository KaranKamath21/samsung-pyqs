// This is a typical DP with Bit Masking problem.

import java.util.*;

public class Travelling_SalesPerson {
    static int N;
    static int[][] dist;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            N = sc.nextInt();
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    dist[i][j] = sc.nextInt();
            }

            dp = new int[N][1 << N];
            for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

            int minCost = tsp(0, 1);
            System.out.println(minCost);
        }

        sc.close();
    }

    public static int tsp(int pos, int mask) {
        // Base case: If all cities are visited, return cost to go back to starting city
        if (mask == (1 << N) - 1) return dist[pos][0];

        // Check if we have already computed this state
        if (dp[pos][mask] != -1) return dp[pos][mask];

        int minCost = Integer.MAX_VALUE;

        // Try visiting each unvisited city and calculate the minimum path
        for (int city = 0; city < N; city++) {
            if ((mask & (1 << city)) == 0) { // City not yet visited
                int newCost = dist[pos][city] + tsp(city, mask | (1 << city));
                minCost = Math.min(minCost, newCost);
            }
        }

        return dp[pos][mask] = minCost;
    }
}