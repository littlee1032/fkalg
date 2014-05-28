import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] as = new int[n];

        for (int i = 0; i < n; i++) {
            as[i] = scan.nextInt();
        }

        scan.nextLine();
        String line = scan.nextLine().trim();

        int[] ms = new int[n];
        for (int i = 0; i < ms.length; i++) {
            ms[i] = line.charAt(i) - '0';
        }

        int[] maxam = new int[n];
        for (int i = 1; i < n; i++) {
            maxam[i] = maxam[i - 1] + as[i - 1];
        }

        int[] dp = new int[n];
        if (ms[0] == 1) {
            dp[0] = as[0];
        }

        for (int i = 1; i < n; i++) {
            if (ms[i] == 0) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.max(dp[i - 1] + as[i], maxam[i]);
            }
        }

        System.out.println(dp[n - 1]);
    }
}
