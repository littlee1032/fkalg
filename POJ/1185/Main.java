import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int[] bias = new int[10];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int size = 1;
        for (int i = 1; i <= m; i++) size *= 3;
        bias[m-1] = 1;
        for (int i = m-2; i >= 0; i--) bias[i] = 3 * bias[i+1];
        int[] dp = new int[size];

        boolean[][] map = new boolean[n][m];
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < m; j++) {
                if ('H' == str.charAt(j)) map[i][j] = true;
            }
        }

        //0  1  2
        //   x
        //      x
        //System.out.println(Arrays.toString(bias));
        int[] ndp = new int[size];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean needcopy = false;
                for (int k = 0; k < size; k++) {
                    if (getState(k, j) != 0) {
                        ndp[k - bias[j]] = Math.max(ndp[k-bias[j]], dp[k]);
                        needcopy = true;
                        continue;
                    }
                    if (j >= 1 && getState(k, j-1) == 2) continue;
                    if (j >= 2 && getState(k, j-2) == 2) continue;
                    if (map[i][j]) continue;
                    ndp[k + 2*bias[j]] = Math.max(ndp[k+2*bias[j]], dp[k] + 1);
                    needcopy = true;
                }
                if (needcopy) System.arraycopy(ndp, 0, dp, 0, size);
            }
        }

        int max = 0;
        for (int i = 0; i < size; i++) max = Math.max(max, dp[i]);
        System.out.println(max);
    }

    private static int getState(int state, int col) {
        return (state / bias[col]) % 3;
    }
}
