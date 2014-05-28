import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int calculateFish(int t, int fi, int di) {
        if (t == 0) {
            return 0;
        } else {
            if (di == 0) {
                return fi * t;
            }
            int limit = fi / di;
            if (t > limit + 1) {
                t = limit + 1;
            }
            return fi * t - di * (t - 1) * t / 2;
        }
    }

    private static void solve(int n, int hNum, int[] fi, int[] di, int[] ti, int[] tii, int[] df, int[][] track) {
        for (int i = n; i >= 1; i--) {
            for (int h = hNum - tii[i]; h >= 1; h--) {
                int max = 0;
                int maxTimeSpent = 0;
                for (int j = 0; j <= h; j++) {
                    int currentValue = calculateFish(j, fi[i], di[i]);
                    int nextJ = h - j - ti[i];
                    if (nextJ > 0) {
                        currentValue += df[nextJ];
                    }
                    if (currentValue >= max) {
                        max = currentValue;
                        maxTimeSpent = j;
                    }
                }
                df[h] = max;
                track[i][h] = maxTimeSpent;
            }
        }
        int maxTotal = df[hNum];
        int hIdx = hNum;
        for (int i = 1; i <=n; i++) {
            if (i != 1) {
                System.out.print(", ");
            }
            if (hIdx < 1) {
                System.out.print(0);
            } else {
                System.out.print(track[i][hIdx] * 5);
                hIdx -= track[i][hIdx] + ti[i];
            }
        }
        System.out.println();
        System.out.println("Number of fish expected: " + maxTotal);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int NMAX = 25;
        int HMAX = 16;
        int MAX = 192;
        int[] df = new int[MAX + 1];
        int[][] track = new int[NMAX + 1][MAX + 1];
        int[] fi = new int[NMAX + 1];
        int[] di = new int[NMAX + 1];
        int[] ti = new int[NMAX + 1];
        int[] tii = new int[NMAX + 2];
        Arrays.fill(fi, 0);
        Arrays.fill(di, 0);
        Arrays.fill(ti, 0);
        Arrays.fill(tii, 0);
        for (int i = 0; i < NMAX + 1; i++) {
            Arrays.fill(track[i], 0);
        }
        int n = scan.nextInt();
        while (n != 0) {
            int h = scan.nextInt();
            int hNum = h * 60 / 5;
            Arrays.fill(df, 0);
            for (int i = 1; i <= n; i++) {
                fi[i] = scan.nextInt();
            }
            for (int i = 1; i <= n; i++) {
                di[i] = scan.nextInt();
            }
            for (int i = 1; i < n; i++) {
                ti[i] = scan.nextInt();
                tii[i + 1] = tii[i] + ti[i];
            }
            solve(n, hNum, fi, di, ti, tii, df, track);
            n = scan.nextInt();
        }
    }
}
