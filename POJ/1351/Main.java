import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int n;
    private static final int[] masks = new int[] {1, 1 << 1, 1 << 2, 1 << 3};
    private static final long[][] meetOneDp = new long[2][1 << 4];
    private static final long[][][] tryDp = new long[2][4][1 << 4];
    private static final int[] corrects = new int[] {15, 13, 11};
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        while (n > 0) {
            solve();
            n = scan.nextInt();
        }
    }

    private static void solve() {
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            Arrays.fill(tryDp[idx][i], 0);
            tryDp[idx][i][masks[i]] = 1;
        }
        Arrays.fill(meetOneDp[idx], 0);
        for (int i = 1; i < n; i++) {
            int nIdx = 1 - idx;
            Arrays.fill(meetOneDp[nIdx], 0);
            for (int j = 0; j < 4; j++) Arrays.fill(tryDp[nIdx][j], 0);
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < (1 << 4); k++) {
                    for (int l = 0; l < 4; l++) {
                        if (Math.abs(j - l) == 3) {
                            meetOneDp[nIdx][k | masks[l]] += tryDp[idx][j][k];
                        } else {
                            tryDp[nIdx][l][k | masks[l]] += tryDp[idx][j][k];
                        }
                    }
                }
            }

            for (int j = 0; j < (1 << 4); j++) {
                for (int k = 0; k < 4; k++) {
                    meetOneDp[nIdx][j | masks[k]] += meetOneDp[idx][j];
                }
            }

            idx = nIdx;
        }

        long count = 0;
        for (int i = 0; i < corrects.length; i++) {
            count += meetOneDp[idx][corrects[i]];
        }

        System.out.println(n + ": " + count);
    }
}
