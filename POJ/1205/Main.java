import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int NMAX = 1000;
    private static long[][] dp = new long[NMAX][3];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < NMAX; i++) Arrays.fill(dp[i], -1);
        dp[1][0] = 0;
        dp[1][1] = 1L;
        dp[1][2] = 1L;

        while (scan.hasNext()) {
            int n = scan.nextInt();
            System.out.println(dfs(n, 0) + dfs(n, 1));
        }
    }

    private static long dfs(int n, int dir) {
        if (dp[n][dir] != -1) return dp[n][dir];
        else {
            dp[n][dir] = dfs(n-1, 0) + dfs(n-1, 1);
            if (dir > 0) dp[n][dir] += dfs(n-1, 2);
            return dp[n][dir];
        }
    }
}
