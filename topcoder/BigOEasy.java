import java.util.Arrays;

public class BigOEasy {
    private static final String BOUND = "Bounded";
    private static final String UNBOUND = "Unbounded";
    private static boolean[] vis;
    private static int[] counts;
    private static int n;
    private static boolean[][] g;

    public String isBounded(String[] graph) {
        n = graph.length;
        vis = new boolean[n];
        g = new boolean[n][n];
        counts = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ('Y' == graph[i].charAt(j))
                    g[i][j] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(vis, false);
            Arrays.fill(counts, 0);
            dfs(i);
            if (counts[i] > 1)
                return UNBOUND;
        }

        return BOUND;
    }

    private void dfs(int node) {
        vis[node] = true;
        for (int i = 0; i < n; i++) {
            if (g[node][i]) {
                if (vis[i])
                    counts[i]++;
                else
                    dfs(i);
            }
        }
    }
}
