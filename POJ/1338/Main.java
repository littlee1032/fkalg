import java.util.Scanner;

public class Main {
    private static final long[] dp = new long[1501];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        dp[0] = 1;
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        for (int i = 1; i <= 1500; i++) {
            long min = Math.min(dp[idx2] * 2 , Math.min(dp[idx3] * 3, dp[idx5] * 5));
            dp[i] = min;
            if (min == dp[idx2] * 2) idx2++;
            if (min == dp[idx3] * 3) idx3++;
            if (min == dp[idx5] * 5) idx5++;
        }

        int n = scan.nextInt();
        while (n > 0) {
            System.out.println(dp[n - 1]);
            n = scan.nextInt();
        }
    }
}
