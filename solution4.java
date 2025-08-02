class solution4 {
   public static boolean canFinish(int n, int[][] prerequisites) {
      boolean[] vis = new boolean[prerequisites.length];
      int[] indegree = new int[n];

      for(int[] p : prerequisites) indegree[p[1]]++;

      boolean flag = true;

      while (flag){
         flag = false;
         for(int i = 0; i < prerequisites.length; i++){
            if(!vis[i] && indegree[prerequisites[i][0]] == 0){
                  vis[i] = true;
                  flag = true;
                  indegree[prerequisites[i][1]]--;
            }
         }
      }

      for(int i : indegree)
         if(i != 0) return false;

      return true;
   }

   public static void main(String[] args) {
      int[][] prerequisites = {{1, 0}, {0, 1}};
      System.out.println(canFinish(2, prerequisites));
   }
}