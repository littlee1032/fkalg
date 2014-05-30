public class VocaloidsAndSongs {
    private static final int MOD = 1000000007;
    private static int[][][][] dp;
    private static boolean[][][][] vis;

    public int count(int s, int gumi, int ia, int mayu) {
        dp = new int[s + 1][gumi + 1][ia + 1][mayu + 1];
        vis = new boolean[s + 1][gumi + 1][ia + 1][mayu + 1];

        dp[1][1][0][0] = 1;
        vis[1][1][0][0] = true;
        dp[1][1][1][0] = 1;
        vis[1][1][1][0] = true;
        dp[1][1][1][1] = 1;
        vis[1][1][1][1] = true;
        dp[1][0][1][0] = 1;
        vis[1][0][1][0] = true;
        dp[1][0][1][1] = 1;
        vis[1][0][1][1] = true;
        dp[1][0][0][1] = 1;
        vis[1][0][0][1] = true;
        dp[1][1][0][1] = 1;
        vis[1][1][0][1] = true;

        for (int i = 0; i <= gumi; i++) {
            for (int j = 0; j <= ia; j++) {
                for (int k = 0; k <= mayu; k++) {
                    dp[0][i][j][k] = 0;
                    vis[0][i][j][k] = true;
                }
            }
        }

        return dfs(s, gumi, ia, mayu);
    }

    private int dfs(int s, int g, int i, int m) {
        if (!vis[s][g][i][m]) {
            if (g > 0) {
                dp[s][g][i][m] += dfs(s - 1, g - 1, i, m);
                dp[s][g][i][m] %= MOD;
            }
            if (i > 0) {
                dp[s][g][i][m] += dfs(s - 1, g, i - 1, m);
                dp[s][g][i][m] %= MOD;
            }
            if (m > 0) {
                dp[s][g][i][m] += dfs(s - 1, g, i, m - 1);
                dp[s][g][i][m] %= MOD;
            }
            if (g > 0 && i > 0) {
                dp[s][g][i][m] += dfs(s - 1, g - 1, i - 1, m);
                dp[s][g][i][m] %= MOD;
            }
            if (g > 0 && m > 0) {
                dp[s][g][i][m] += dfs(s - 1, g - 1, i, m - 1);
                dp[s][g][i][m] %= MOD;
            }
            if (i > 0 && m > 0) {
                dp[s][g][i][m] += dfs(s - 1, g, i - 1, m - 1);
                dp[s][g][i][m] %= MOD;
            }
            if (g > 0 && i > 0 && m > 0) {
                dp[s][g][i][m] += dfs(s - 1, g - 1, i - 1, m - 1);
                dp[s][g][i][m] %= MOD;
            }
            vis[s][g][i][m] = true;
        }
        return dp[s][g][i][m];
    }
}
