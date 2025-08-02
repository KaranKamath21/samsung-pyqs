import java.util.*;

public class Detect_cycle_in_directed_Graph {

    // DFS function to detect cycles and store them in TreeMap
    public static void DFS(List<List<Integer>> adj, int[] vis, int[] pathvis, TreeMap<Integer, ArrayList<Integer>> map,
            int node, List<Integer> path) {
        vis[node] = 1;
        pathvis[node] = 1;
        path.add(node);

        for (int i = 0; i < adj.get(node).size(); i++) {
            int adjnode = adj.get(node).get(i);
            if (vis[adjnode] == 0) {
                DFS(adj, vis, pathvis, map, adjnode, path);
            } else if (pathvis[adjnode] == 1) {
                List<Integer> list = new ArrayList<>();
                int index = path.indexOf(adjnode);
                int sum = 0;
                for (int j = index; j < path.size(); j++) {
                    list.add(path.get(j) + 1);
                    sum += path.get(j);
                }
                Collections.sort(list);
                map.put(sum, new ArrayList<>(list));
            }
        }

        pathvis[node] = 0;
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt() - 1;
            edges[i][1] = sc.nextInt() - 1;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
        }

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();

        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] vis = new int[n];
            int[] pathvis = new int[n];
            DFS(adj, vis, pathvis, map, i, path);

        }

        ArrayList<Integer> list = map.get(map.firstKey());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        
        sc.close();
    }
}