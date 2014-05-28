import java.util.Scanner;

public class Main {
    private static final int NMAX = 10000;
    private static final int[] dp = new int[NMAX];

    private static int dp(int n) {
        if (n < NMAX && n > 0 && dp[n] > 0) return dp[n];
        else {
            int ret = 0;
            if (n % 2 == 1) ret = 1 + dp(3 * n + 1);
            else ret = dp(n / 2) + 1;
            if (n < NMAX && n > 0) dp[n] = ret;
            return ret;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        dp[1] = 1;
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            int s = Math.min(a, b);
            int e = Math.max(a, b);
            int max = 0;
            for (int i =  s; i <= e; i++) {
                max = Math.max(max, dp(i));
            }
            System.out.println(a + " " + b + " " + max);
        }
    }
}
