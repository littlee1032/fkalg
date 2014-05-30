import java.util.Arrays;

public class BuildingRoutes {
    private int[][] used;
    private int[][] map;
    private int[][] dist;
    private static final int MAX = 10000000;

    public int build(String[] dist, int t) {
        int n = dist.length;
        used = new int[n][n];
        map = new int[n][n];
        this.dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(this.dist[i], MAX);
        }

        for (int i = 0; i < n; i++) {
            char[] c = dist[i].toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = c[j] - '0';
                this.dist[i][j] = map[i][j];
            }
        }

        return solve(t);
    }

    private int solve(int limit) {
        int n = used.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i != j && j != k && k != j) {
                        dist[i][k] = Math.min(dist[i][k], dist[i][j] + dist[j][k]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                using(i, j);
            }
        }

        int ret = 0;
        for (int i = 0; i < used.length; i++) {
            for (int j = 0; j < used.length; j++) {
                if (i == j)
                    continue;
                if (used[i][j] >= limit)
                    ret += map[i][j];
            }
        }

        return ret;
    }

    private void using(int s, int e) {
        if (dist[s][e] == map[s][e])
            used[s][e]++;
        for (int i = 0; i < used.length; i++) {
            if (s == i || i == e)
                continue;
            if (dist[s][e] == dist[s][i] + dist[i][e]) {
                using(s, i);
                using(i, e);
            }
        }
    }
}
