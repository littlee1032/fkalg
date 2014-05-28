import java.util.Scanner;

public class Main {
    private static int[][][][] dp;
    private static boolean[][][][] vis;
    private static final int MOD = 11380;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int l1 = scan.nextInt();
        int l2 = scan.nextInt();
        int l3 = scan.nextInt();
        int d = scan.nextInt();

        dp = new int[l1+1][l2+1][l3+1][d+1];
        vis = new boolean[l1+1][l2+1][l3+1][d+1];

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                for (int k = 0; k <= l3; k++) {
                    dp[i][j][k][0] = 0;
                    vis[i][j][k][0] = true;
                }
            }
        }

        for (int i = 0; i <= d; i++) {
            dp[0][0][0][i] = 1;
            vis[0][0][0][i] = true;
        }

        if (d == 0) {
            System.out.println(dp[l1][l2][l3][d]);
        } else {
            int ans = dfs(l1, l2, l3, d) - dfs(l1, l2, l3, d-1);
            while (ans < 0) ans += MOD;
            System.out.println(ans % MOD);
        }
    }

    private static int dfs(int l, int m, int n, int d) {
        if (vis[l][m][n][d]) return dp[l][m][n][d];
        int add = 0;
        for (int nn = 0; nn < n; nn++) {
            add += (dfs(0, 0, nn, d-1) * dfs(l, m, n - nn - 1, d)) % MOD;
            add %= MOD;
        }
        for (int mm = 0; mm < m; mm++) {
            for (int nn = 0; nn <= n; nn++) {
                add += (dfs(0, mm, nn, d-1) * dfs(l, m - mm - 1, n - nn, d)) % MOD;
                add %= MOD;
            }
        }
        for (int ll = 0; ll < l; ll++) {
            for (int mm = 0; mm <= m; mm++) {
                for (int nn = 0; nn <= n; nn++) {
                    add += (dfs(ll, mm, nn, d-1) * dfs(l - ll - 1, m - mm, n - nn, d)) % MOD;
                    add %= MOD;
                }
            }
        }
        dp[l][m][n][d] = add;
        vis[l][m][n][d] = true;
        return add;
    }
}
