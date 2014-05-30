import java.util.Arrays;

public class Excavations2 {
    public long count(int[] kind, int[] found, int K) {
        int n = 51;
        int[] num = new int[n];
        for (int i = 0; i < kind.length; i++) {
            num[kind[i]]++;
        }
        int[] foundNums = new int[found.length];
        for (int i = 0; i < found.length; i++) {
            foundNums[i] = num[found[i]];
        }
        long[][] c = new long[n][n];
        c[0][0] = 1;
        for (int i = 0; i < n; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int m = found.length;
        long[][] dp = new long[m + 1][K + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][0] = 1;
        for (int i = 0; i <= m - 1; i++) {
            for (int j = 1; j <= foundNums[i]; j++) {
                for (int k = j; k <= K; k++) {
                    dp[i + 1][k] += dp[i][k - j] * c[foundNums[i]][j];
                }
            }
        }
        return dp[m][K];
    }

    public static void main(String[] args) {
        int[] kind = new int[] { 1, 2, 1, 1, 2, 3, 4, 3, 2, 2 };
        int[] found = new int[] { 4, 2 };
        int K = 3;
        System.out.println(new Excavations2().count(kind, found, K));
    }
}
