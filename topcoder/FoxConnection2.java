import java.util.Arrays;

public class FoxConnection2 {
    private static int MOD = 1000000007;
    private static int[][] dp;
    private static int need = 0;
    private static boolean[][] map;
    private static int ans = 0;

    private static void dfs(int cur, int from) {
        int[] ret = new int[need + 1];
        int[] newRet = new int[need + 1];
        ret[1] = 1;
        for (int i = 1; i < map.length; i++) {
            if (map[cur][i] && (from != i)) {
                dfs(i, cur);
                Arrays.fill(newRet, 0);
                for (int j = 0; j <= need; j++) {
                    for (int k = 0; k <= j; k++) {
                        newRet[j] += ret[j - k] * dp[i][k];
                        newRet[j] %= MOD;
                    }
                }
                System.arraycopy(newRet, 0, ret, 0, need + 1);
            }
        }
        ret[0] = 1;
        System.arraycopy(ret, 0, dp[cur], 0, need + 1);
        ans += dp[cur][need];
        ans %= MOD;
    }

    public int ways(int[] a, int[] b, int k) {
        int len = a.length;
        int maxNode = a.length + 1;
        need = k;
        int size = maxNode + 1;
        map = new boolean[size][size];
        dp = new int[size][need + 1];
        for (int i = 0; i < size; i++)
            dp[i][1] = 1;

        for (int i = 0; i < len; i++) {
            map[a[i]][b[i]] = true;
            map[b[i]][a[i]] = true;
        }

        dfs(1, -1);
        return ans;
    }
}
