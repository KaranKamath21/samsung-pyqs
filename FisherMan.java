import java.util.Scanner;

public class FisherMan {
    static int[] gate = new int[3];
    static int[] fm = new int[3];
    static int people, n, mini = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        // Input for gates
        for (int i = 0; i < 3; i++) {
            gate[i] = sc.nextInt();
        }

        // Input for fishermen counts at each gate
        for (int i = 0; i < 3; i++) {
            fm[i] = sc.nextInt();
            people += fm[i];
        }

        // Iterate over positions for each gate
        for (int i = 1; i <= n - (fm[0] + fm[1] + fm[2]) + 1; i++) {
            for (int j = i + fm[0]; j <= n - (fm[1] + fm[2]) + 1; j++) {
                for (int k = j + fm[1]; k <= n - fm[2] + 1; k++) {
                    int ans = 0;

                    // Calculate distance cost for fishermen at gate[0]
                    for (int p = i; p < i + fm[0]; p++) {
                        ans += Math.abs(p - gate[0]) + 1;
                    }

                    // Calculate distance cost for fishermen at gate[1]
                    for (int p = j; p < j + fm[1]; p++) {
                        ans += Math.abs(p - gate[1]) + 1;
                    }

                    // Calculate distance cost for fishermen at gate[2]
                    for (int p = k; p < k + fm[2]; p++) {
                        ans += Math.abs(p - gate[2]) + 1;
                    }

                    // Update the minimum answer
                    mini = Math.min(ans, mini);
                }
            }
        }

        System.out.println(mini);
        sc.close();
    }
}