import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final boolean[][] map = new boolean[210][210];
    private static final int[] match = new int[210];
    private static final boolean[] vis = new boolean[210];

    private static void init() {
        for (int i = 0; i < 210; i++) Arrays.fill(map[i], false);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            int m = scan.nextInt();
            int k = scan.nextInt();

            init();

            for (int i = 0; i < k; i++) {
                int jobNo = scan.nextInt();
                int mode1 = scan.nextInt();
                int mode2 = scan.nextInt();
                if (mode1 != 0 && mode2 != 0)
                    map[mode1][n + mode2] = true;
            }

            System.out.println(hungary(n + m));
            n = scan.nextInt();
        }
    }

    private static int hungary(int nMax) {
        int ret = 0;
        Arrays.fill(match, -1);
        for (int i = 1; i < nMax; i++) {
            Arrays.fill(vis, false);
            if (augement(nMax, i)) ret++;
        }
        return ret;
    }

    private static boolean augement(int nMax, int n) {
        for (int i = 1; i < nMax; i++) {
            if (!vis[i] && map[n][i]) {
                vis[i] = true;
                if (match[i] == -1 || augement(nMax, match[i])) {
                    match[i] = n;
                    return true;
                }
            }
        }
        return false;
    }
}
