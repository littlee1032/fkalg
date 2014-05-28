import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n-- > 0) {
            int c = scan.nextInt();
            int[] dp = new int[c+1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            int[] num = new int[c+1];
            int[] prices = new int[c+1];
            int[] sum = new int[c+1];
            for (int i = 1; i <= c; i++) {
                num[i] = scan.nextInt();
                prices[i] = scan.nextInt();
                sum[i] = sum[i-1] + num[i];
            }
            // dp
            for (int i = 1; i <= c; i++) {
                for (int j = 0; j < i; j++) {
                    dp[i] = Math.min(dp[i], dp[j] + (sum[i] - sum[j] + 10) * prices[i]);
                }
            }
            System.out.println(dp[c]);
        }
    }
}
