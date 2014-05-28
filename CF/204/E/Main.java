import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static long VERYLARGE = 2000000000L;

    private static void mul(long[][] a, long[][] b) {
        long[][] c = new long[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            Arrays.fill(c[i], VERYLARGE);
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] = Math.min(c[i][j], a[i][k] + b[k][j]);
                }
            }
        }

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                a[i][j] = c[i][j];
    }

    private static void plus(long[][] a, long[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] < VERYLARGE && b[i][j] < VERYLARGE)
                    a[i][j] += b[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] as = new int[n];
        int[] bs = new int[n];

        for (int i = 0; i < n; i++) {
            as[i] = scan.nextInt();
        }

        for (int i = 0; i < n; i++) {
            bs[i] = scan.nextInt();
        }

        long[][] aa = new long[n + 1][n + 1];
        long[][] dp = new long[n + 1][n + 2];

        for (int x = 0; x <= n; x++) {
            for (int i = 0; i <= n; i++) Arrays.fill(dp[i], VERYLARGE);
            dp[0][x] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n; j++) {
                    if (j < n) dp[i+1][j+1] = Math.min(dp[i+1][j+1], as[i] + dp[i][j]);
                    if (j > 0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], bs[i] + dp[i][j]);
                }
            }
            /*
            if (x == 0) {
                for (int i = 0; i <= n; i++) {
                    System.out.println(Arrays.toString(dp[i]));
                }
            }
            */
            for (int y = 0; y <= n; y++) aa[x][y] = dp[n][y];
        }
        /*
        for (int i = 0; i <= n; i++) {
            System.out.println(Arrays.toString(aa[i]));
        }
        System.out.println();
        */

        long b[][] = new long[n + 1][n + 1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (i == j) b[i][j] = 0;
                else b[i][j] = VERYLARGE;
            }
        }

        while (m > 0) {
            if ((m & 1) != 0) {
                mul(b, aa);
            }
            mul(aa, aa);
            m = m >> 1;
            /*
            for (int i = 0; i <=n; i++) {
                System.out.println(Arrays.toString(aa[i]));
            }
            System.out.println();
            */
        }

        System.out.println(b[0][0]);
    }
}
