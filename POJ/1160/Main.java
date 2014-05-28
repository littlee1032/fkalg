import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int v = scan.nextInt();
        int p = scan.nextInt();
        int MAX = 10000000;

        int[][] dp = new int[v+1][p+1];
        int[] poses = new int[v+1];
        int[][] locates = new int[v+1][v+1];

        for (int i = 1; i <= v; i++) {
            poses[i] = scan.nextInt();
        }

        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                locates[i][j] = locates[i][j-1] + poses[j] - poses[(i+j)/2];
            }
        }

        for (int i = 0; i <= v; i++) Arrays.fill(dp[i], MAX);
        for (int i = 2; i <= v; i++) dp[i][1] = locates[1][i];
        for (int j = 2; j <= p; j++) dp[j][j] = 0;

        for (int i = 2; i <= v; i++) {
            for (int j = 2; j < i && j <= p; j++) {
                for (int k = j - 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j-1] + locates[k+1][i]);
                }
            }
        }

        System.out.println(dp[v][p]);
    }
}
