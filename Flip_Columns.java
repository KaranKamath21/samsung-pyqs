import java.util.*;

public class Flip_Columns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int[][] mat = new int[n][m];
        Map<String, Integer> rowPatterns = new HashMap<>();
        int maxRowsWithAllOnes = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            StringBuilder rowStr = new StringBuilder();
            for (int j = 0; j < m; j++) {
                mat[i][j] = sc.nextInt();
                rowStr.append(mat[i][j]);
            }

            String rowPattern = rowStr.toString();
            rowPatterns.put(rowPattern, rowPatterns.getOrDefault(rowPattern, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : rowPatterns.entrySet()) {
            String rowPattern = entry.getKey();
            int rowCount = entry.getValue();
            int zeroCount = 0;

            // Count the number of '0's in the row patternw
            for (int j = 0; j < rowPattern.length(); j++) {
                if (rowPattern.charAt(j) == '0') {
                    zeroCount++;
                }
            }

            // Check if the row can be toggled to all '1's within the given constraints
            if (zeroCount <= k && (k - zeroCount) % 2 == 0) {
                maxRowsWithAllOnes = Math.max(maxRowsWithAllOnes, rowCount);
            }
        }

        System.out.println(maxRowsWithAllOnes);
        sc.close();
    }
}