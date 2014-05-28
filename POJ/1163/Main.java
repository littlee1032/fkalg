import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] dp = new int[2][n];
        int preIdx = 0;

        for (int i = 0; i < n; i++) {
            int[] cur = dp[1-preIdx];
            int[] pre = dp[preIdx];
            for (int j = 0; j <= i; j++) {
                int nn = scan.nextInt();
                if (j >= 1) cur[j] = Math.max(cur[j], pre[j-1]+nn);
                if (j < n-1) cur[j] = Math.max(cur[j], pre[j]+nn);
            }
            preIdx = 1 - preIdx;
        }

        int max = 0;
        for (int i = 0; i < n; i++) max = Math.max(max, dp[preIdx][i]);
        System.out.println(max);
    }
}
