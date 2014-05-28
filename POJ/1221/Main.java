import java.util.Scanner;

public class Main {
    private final static int NMAX = 250;
    private final static long[][] dp = new long[NMAX][NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < NMAX; i++) {
            dp[0][i] = 1L;
            dp[i][i] = 1L;
        }
        for (int i = 1; i < NMAX; i++) {
            for (int j = i / 2 + 1; j < i; j++) {
                dp[i][j] = 1L;
            }
        }

        for (int i = 2; i < NMAX; i++) {
            for (int j = i / 2; j > 0; j--) {
                dp[i][j] = dp[i - 2*j][j] + dp[i][j+1];
            }
        }
        int n = scan.nextInt();
        while (n != 0) {
            System.out.println(n + " " + dp[n][1]);
            n = scan.nextInt();
        }
    }
}
