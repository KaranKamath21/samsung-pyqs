import java.util.Scanner;

public class Oil_Mines {

    static int n_comp, n_mines, ans;
    static int[] mines = new int[20];
    static boolean[] vis = new boolean[20];

    public static void helper(int i, int sum, int companies, int mn, int mx) {
        if (vis[i]) {
            if (companies == n_comp) {
                int newMin = Math.min(sum, mn);
                int newMax = Math.max(sum, mx);
                ans = Math.min(ans, newMax - newMin);
            }
            return;
        }

        int j = (i + 1) % n_mines;
        vis[i] = true;

        // Add this mine to the same company.
        helper(j, sum + mines[i], companies, mn, mx);
        
        int newMin = Math.min(sum, mn);
        int newMax = Math.max(sum, mx);

        // Add this mine to a new company.
        helper(j, mines[i], companies + 1, newMin, newMax);
        
        vis[i] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while (t-- > 0) {
            n_comp = sc.nextInt();
            n_mines = sc.nextInt();

            for (int i = 0; i < n_mines; i++) {
                mines[i] = sc.nextInt();
                vis[i] = false;
            }

            ans = Integer.MAX_VALUE;
            for (int i = 0; i < n_mines; i++) {
                helper(i, 0, 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            if (n_comp > n_mines) ans = -1;
            System.out.println(ans);
        }
        
        sc.close();
    }
}