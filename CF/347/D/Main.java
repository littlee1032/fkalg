import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] c1 = scan.nextLine().trim().toCharArray();
        char[] c2 = scan.nextLine().trim().toCharArray();
        char[] virus = scan.nextLine().trim().toCharArray();
        int n1 = c1.length;
        int n2 = c2.length;
        int m = virus.length;
        int[][][] dp = new int[n1 + 1][n2 + 1][m + 1];
        int[][][] step = new int[n1 + 1][n2 + 1][m + 1];

        // preprocessor
        int[] next = new int[m + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int pre = 0;
        while (pos < m) {
            if (virus[pos - 1] == virus[pre]) {
                pre++;
                next[pos] = pre;
                pos++;
            } else if (pre > 0) {
                pre = next[pre];
            } else {
                next[pos] = 0;
                pos++;
            }
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                for (int k = 0; k < m; k++) {
                    if (c1[i - 1] == c2[j - 1]) {
                        int kk = k;
                        while (kk >= 0 && c1[i - 1] != virus[kk]) {
                            kk = next[kk];
                        }
                        if (dp[i][j][kk + 1] < 1 + dp[i - 1][j - 1][k]) {
                            dp[i][j][kk + 1] = 1 + dp[i - 1][j - 1][k];
                            step[i][j][kk + 1] = 100 + k;
                        }
                    }

                    if (dp[i][j][k] < dp[i - 1][j][k]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                        step[i][j][k] = -1;
                    }
                    if (dp[i][j][k] < dp[i][j - 1][k]) {
                        dp[i][j][k] = dp[i][j - 1][k];
                        step[i][j][k] = 1;
                    }
                }
            }
        }

        int result = 0;
        int maxK = 0;
        for (int i = 0; i < m; i++) {
            if (result < dp[n1][n2][i]) {
                result = dp[n1][n2][i];
                maxK = i;
            }
        }
        //System.out.println(result + ", " + maxK);
        if (result == 0) {
            System.out.println(result);
        } else {
            char[] cc = new char[result];
            int ccIdx = result - 1;
            int n1Idx = n1;
            int n2Idx = n2;
            int mIdx = maxK;
            while (ccIdx >= 0) {
                switch (step[n1Idx][n2Idx][mIdx]) {
                case -1:
                    n1Idx--;
                    break;
                case -2:
                    n1Idx--;
                    n2Idx--;
                    break;
                case 1:
                    n2Idx--;
                    break;
                case 0:
                    cc[ccIdx--] = c1[n1Idx - 1];
                    n1Idx--;
                    n2Idx--;
                    mIdx--;
                    break;
                default:
                    cc[ccIdx--] = c1[n1Idx - 1];
                    mIdx = step[n1Idx][n2Idx][mIdx] - 100;
                    n1Idx--;
                    n2Idx--;
                    break;
                }
            }
            System.out.println(cc);
        }
    }
}
