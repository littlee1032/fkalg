import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int c;
    private static int n;
    private static int m;
    private static final int NMAX = 1001;
    private static final double[][] dp = new double[2][NMAX];
    private static final double EPS = 0.0000001d;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        c = scan.nextInt();
        while (c != 0) {
            n = scan.nextInt();
            m = scan.nextInt();
            double result = solve();
            System.out.println(String.format("%.3f", result));
            c = scan.nextInt();
        }
    }

    private static double solve() {
        if (m > c || m > n || (m == 0 && n == 0) || (m + n) % 2 == 1) return 0.0;
        for (int i = 0; i < 2; i++) Arrays.fill(dp[i], 0.0);
        dp[0][0] = 1.0;
        double pre = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i && j <= c; j++) {
                dp[i % 2][j] = 0.0;
                if ((i + j) % 2 == 1) continue;
                else {
                    if (j > 0)
                        dp[i % 2][j] += dp[(i-1) % 2][j - 1] * (c - j + 1.0) / c;
                    if (j + 1 <= i)
                        dp[i % 2][j] += dp[(i-1) % 2][j + 1] * (j + 1.0) / c;
                    if (j == m) {
                        if (Math.abs(pre - dp[i % 2][j]) < EPS) return pre;
                        else pre = dp[i % 2][j];
                    }
                }
            }
        }

        return dp[n % 2][m];
    }
}
