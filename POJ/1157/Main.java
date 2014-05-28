import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int MM = -100000;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int f = scan.nextInt();
        int v = scan.nextInt();

        int[][] values = new int[f+1][v+1];
        for (int i = 1; i <= f; i++) {
            for (int j = 1; j <= v; j++) {
                values[i][j] = scan.nextInt();
            }
        }

        int[][] dp = new int[2][v+1];
        Arrays.fill(dp[0], MM);
        Arrays.fill(dp[1], MM);

        int idx = 0;

        int max = MM;
        for (int j = 1; j <= v; j++) {
            max = Math.max(max, values[1][j]);
            dp[idx][j] = max;
        }
        for (int i = 2; i <= f; i++) {
            int needToLeave = f - i;
            int preIdx = idx;
            idx = 1 - idx;
            for (int j = i; j + needToLeave <= v; j++) {
                max = dp[preIdx][j-1] + values[i][j];
                dp[idx][j] = Math.max(max, dp[idx][j-1]);
            }
        }
        System.out.println(dp[idx][v]);
    }
}
