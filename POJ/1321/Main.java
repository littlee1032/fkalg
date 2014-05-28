import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final boolean[][] board = new boolean[8][8];
    private static int n;
    private static int k;
    private static int[][] dp = new int[2][1 << 8];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        k = scan.nextInt();
        while (n + k > 0) {
            init();
            scan.nextLine();
            for (int i = 0; i < n; i++) {
                char[] cs = scan.nextLine().toCharArray();
                for (int j = 0; j < n; j++) {
                    if ('#' == cs[j]) {
                        board[i][j] = true;
                    }
                }
            }
            solve();

            n = scan.nextInt();
            k = scan.nextInt();
        }
    }

    private static void init() {
        for (int i = 0; i < 8; i++) Arrays.fill(board[i], false);
        for (int i = 0; i < 2; i++) Arrays.fill(dp[i], 0);
        dp[0][0] = 1;
    }

    private static void solve() {
        int idx1 = 1;
        int idx2 = 0;
        for (int i = 0; i < n; i++) {
            idx1 ^= 1;
            idx2 ^= 1;
            for (int s = (1 << n) - 1; s >= 0; s--) {
                dp[idx2][s] = dp[idx1][s];
                for (int j = n - 1; j >= 0; j--) {
                    if (board[i][j] && (s & (1 << j)) == 0) {
                        dp[idx2][s | (1 << j)] += dp[idx1][s];
                    }
                }
            }
        }

        int ans = 0;
        for (int s = 0; s < (1 << n); s++) {
            int count = 0;
            int ss = s;
            while (ss > 0) {
                ss &= ss - 1;
                count++;
            }
            if (count == k) {
                ans += dp[idx2][s];
            }
        }

        System.out.println(ans);
    }
}
