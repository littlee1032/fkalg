import java.util.Scanner;

public class Main {
    private static final int NMAX = 31;
    private static long[][] dp = new long[NMAX][NMAX];

    private static long get(int g, int l) {
        if (g == 0 && l == 0) return 0;
        if (dp[g][l] == 0) {
            dp[g][l] = get(g-1, l-1) + 1 + get(g-1, Math.min(g-1, l));
        }
        return dp[g][l];
    }

    private static void init() {
        for (int i = 1; i < NMAX; i++) {
            dp[i][0] = i;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        init();
        int g = scan.nextInt();
        int l = scan.nextInt();

        int caseIdx = 1;
        while (g + l > 0) {
            System.out.println(String.format("Case %d: %d", caseIdx++, get(g, l)));
            g = scan.nextInt();
            l = scan.nextInt();
        }
    }
}
