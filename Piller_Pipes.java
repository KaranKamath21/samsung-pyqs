import java.util.Scanner;

public class Piller_Pipes {
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
            boolean[] visited = new boolean[n];
            ans = 0;
            helper(arr, visited, 0, 0, n);
            System.out.println(ans);
        }
        sc.close();
    }
    
    public static void helper(int[] arr, boolean[] visited, int p1, int p2, int n) {
        if (p1 == p2) ans = Math.max(ans, p1);
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                helper(arr, visited, p1 + arr[i], p2, n);
                helper(arr, visited, p1, p2 + arr[i], n);
                visited[i] = false;
            }
        }
    }
}
