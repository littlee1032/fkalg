import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 101;
    public static final int[][] dp = new int[NMAX][NMAX];
    public static final int[][] pre = new int[NMAX][NMAX];

    public static void print(String str, int start, int end) {
        int preIdx = pre[start][end];
        if (DEBUG) System.out.println(start + ", " + end + ", " + preIdx);
        if (start >= end) return;
        if (preIdx == -1) {
            System.out.print(str.charAt(start));
            print(str, start + 1, end - 1);
            System.out.print(str.charAt(end - 1));
        } else if (start == end - 1) {
            if ('(' == str.charAt(start) || ')' == str.charAt(start)) {
                System.out.print("()");
            } else {
                System.out.print("[]");
            }
        } else {
            print(str, start, preIdx);
            print(str, preIdx, end);
        }
    }

    public static void printMatrix(int len, int[][] matrix) {
        System.out.print("\t");
        for (int i = 0; i < len; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < len; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < len; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();
        int len = line.length();
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], 0);
            dp[i][i + 1] = 2;
            pre[i][i + 1] = i + 1;
        }
        for (int l = 2; l <= len; l++) {
            for (int s = 0; s <= len - l; s++) {
                dp[s][s + l] = Integer.MAX_VALUE;
                if (('(' == line.charAt(s) && ')' == line.charAt(s + l - 1)) ||
                    ('[' == line.charAt(s) && ']' == line.charAt(s + l - 1))) {
                    if (dp[s][s + l] > dp[s + 1][s + l - 1] + 2) {
                        dp[s][s + l] = dp[s + 1][s + l - 1] + 2;
                        pre[s][s + l] = -1;
                    }
                }
                int min = dp[s][s + l];
                for (int k = 1; k < l; k++) {
                    int m1 = dp[s][s + k];
                    int m2 = dp[s + k][s + l];
                    if (m1 + m2 <= min) {
                        min = m1 + m2;
                        pre[s][s + l] = s + k;
                    }
                }
                dp[s][s + l] = min;
            }
        }
        if (DEBUG) {
            printMatrix(len + 1, dp);
            printMatrix(len + 1, pre);
        }
        print(line, 0, len);
        System.out.println();
    }
}
