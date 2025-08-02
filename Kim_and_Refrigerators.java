import java.io.*;
import java.util.*;

class Kim_and_Refrigerators {
    static int ans, n;
    static Point[] cust = new Point[21]; // Maximum 20 customers + 1 for office/home
    static int[][] dp = new int[(1 << 20)][21];

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int distance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    static int func(int mask, int pos) {
        // If all customers have been visited
        if (mask == (1 << n) - 1) {
            return distance(cust[pos], cust[n + 1]); // distance from last customer to home
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int mini = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            // Check if the ith customer is already visited
            if ((mask & (1 << i)) != 0) continue;

            // distancetance from current position to the next customer
            int x = distance(cust[i + 1], cust[pos]) + func(mask | (1 << i), i + 1);
            mini = Math.min(mini, x);
        }

        return dp[mask][pos] = mini;
    }

    public static void main(String[] args) throws IOException {

        int tt = 10;
        int id = 0;

        while (tt-- > 0) {
            id++;
            n = intscanner();
            
            int ox = intscanner();
            int oy = intscanner();
            int hx = intscanner();
            int hy = intscanner();
            
            cust[0] = new Point(ox, oy);  // Office
            cust[n + 1] = new Point(hx, hy);  // Home

            for (int i = 1; i <= n; i++) {
                int cx = intscanner();
                int cy = intscanner();
                cust[i] = new Point(cx, cy);
            }

            // Initialize the dp array with -1
            for (int i = 0; i < (1 << (n + 1)); i++) Arrays.fill(dp[i], -1);

            pw.println("# " + id + " " + func(0, 0));
        }

        pw.close();
    }


    static FastScanner fs = new FastScanner();

    public static long longscanner() {
        return fs.nextLong();
    }
        
    public static int intscanner() {
        return fs.nextInt();
    }

    public static String stringscanner() {
        return fs.nextLine();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {}
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        // Method to read a single character
        char nextChar() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {}
            }
            return next().charAt(0);
        }

        // Method to read an entire line as a string
        String nextLine() {
            String line = "";
            try {
                line = br.readLine();
            } catch (IOException e) {}
            return line;
        }
    }

    static PrintWriter pw = new PrintWriter(System.out);

}