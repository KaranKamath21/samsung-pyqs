import java.util.*;

public class Rock_Climbing {
    static boolean valid(int x, int y, int[][] a) {
        return (y < a[0].length && y >= 0 && a[x][y] > 0);
    }

    public static void solve(int[][] dp, int n, int[][] mat, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] rem = q.remove();
            x = rem[0]; y = rem[1];

            if (valid(x, y + 1, mat) && dp[x][y + 1] > dp[x][y]) {
                dp[x][y + 1] = dp[x][y];
                q.add(new int[] { x, y + 1 });
            }

            if (valid(x, y - 1, mat) && dp[x][y - 1] > dp[x][y]) {
                dp[x][y - 1] = dp[x][y];
                q.add(new int[] { x, y - 1 });
            }

            for (int i = 0; i < n; i++) {
                if (i != x && mat[i][y] > 0) {
                    int dis = Math.max(Math.abs(i - x), dp[x][y]);
                    if (dis < dp[i][y]) {
                        dp[i][y] = dis;
                        q.add(new int[] { i, y });
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n - 1);
        }

        dp[n - 1][0] = 0;
        solve(dp, n, mat, n - 1, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 3) {
                    System.out.println(dp[i][j]);
                }
            }
        }

        sc.close();
    }
}