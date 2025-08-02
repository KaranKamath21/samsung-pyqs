import java.util.*;

public class Warmholes {
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Wormhole {
        Point entry, exit;
        int cost;
        Wormhole(int x1, int y1, int x2, int y2, int cost) {
            this.entry = new Point(x1, y1);
            this.exit = new Point(x2, y2);
            this.cost = cost;
        }
    }

    static class State {
        Point position;
        int cost;
        State(Point position, int cost) {
            this.position = position;
            this.cost = cost;
        }
    }

    static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static int findMinCost(Point source, Point destination, Wormhole[] wormholes) {
        int n = wormholes.length;
        int ans = manhattanDistance(source, destination);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        pq.add(new State(source, 0));
        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int currentCost = current.cost;
            Point currentPos = current.position;
            // Cost from current state.
            ans = Math.min(ans, currentCost + manhattanDistance(currentPos, destination));

            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;

                visited[i] = true;
                Point entry = wormholes[i].entry;
                Point exit = wormholes[i].exit;
                int wormholeCost = wormholes[i].cost;
                pq.add(new State(exit, currentCost + manhattanDistance(currentPos, entry) + wormholeCost));
                pq.add(new State(entry, currentCost + manhattanDistance(currentPos, exit) + wormholeCost));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            Point source = new Point(sc.nextInt(), sc.nextInt());
            Point destination = new Point(sc.nextInt(), sc.nextInt());

            Wormhole[] wormholes = new Wormhole[n];
            for (int i = 0; i < n; i++) {
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();
                int cost = sc.nextInt();
                wormholes[i] = new Wormhole(x1, y1, x2, y2, cost);
            }

            int minCost = findMinCost(source, destination, wormholes);
            System.out.println(minCost);
        }
        sc.close();
    }
}