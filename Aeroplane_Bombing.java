import java.util.*;

public class Aeroplane_Bombing {
    static int ans;

    private static void maxCoins(int[][] arr, int i, int j, int saferow, boolean bombed, int coins) {
        if (i < 0 || j < 0 || j >= 5) {
            ans = Math.max(ans, coins);
            return;
        }

        if (arr[i][j] == 1 || arr[i][j] == 0) {
            int temp = coins;
            temp += arr[i][j];
            if (bombed) saferow--;
            maxCoins(arr, i - 1, j, saferow, bombed, temp);
            maxCoins(arr, i - 1, j + 1, saferow, bombed, temp);
            maxCoins(arr, i - 1, j - 1, saferow, bombed, temp);
        } else {
            if (bombed == true && saferow <= 0) {
                ans = Math.max(ans, coins);
                return;
            } else if (bombed == true && saferow > 0) {
                saferow--;
                maxCoins(arr, i - 1, j, saferow, bombed, coins);
                maxCoins(arr, i - 1, j + 1, saferow, bombed, coins);
                maxCoins(arr, i - 1, j - 1, saferow, bombed, coins);
            } else {
                bombed = true;
                maxCoins(arr, i - 1, j, 4, bombed, coins);
                maxCoins(arr, i - 1, j + 1, 4, bombed, coins);
                maxCoins(arr, i - 1, j - 1, 4, bombed, coins);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int k = 0;

        while (t-- > 0) {
            int n = sc.nextInt();
            ans = Integer.MIN_VALUE;
            int[][] arr = new int[n][5];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 5; j++)
                    arr[i][j] = sc.nextInt();
            }

            maxCoins(arr, n - 1, 3, 0, false, 0);
            maxCoins(arr, n - 1, 1, 0, false, 0);
            maxCoins(arr, n - 1, 2, 0, false, 0);

            k++;
            System.out.println("#" + k + " " + ans);
        }

        sc.close();

    }
}