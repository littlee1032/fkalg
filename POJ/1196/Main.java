import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int[][][][][] dp = new int[6][6][6][6][6];
    private static boolean[] used = new boolean[26];
    private static int[] maxr = new int[5];
    private static int[] maxc = new int[5];
    private static final String FIRST = "ABCDEFGHIJKLMNOPQRSTUVWXY";

    private static void clear() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    for (int l = 0; l < 6; l++) {
                        Arrays.fill(dp[i][j][k][l], -1);
                    }
                }
            }
        }

        Arrays.fill(used, false);
        Arrays.fill(maxr, 0);
        Arrays.fill(maxc, 0);
    }

    private static int dp_search(int r0, int r1, int r2, int r3, int r4, int begin) {
        if (r0+r1+r2+r3+r4 == 0) return 1;
        if (r3 == 0) return 1;
        if (dp[r0][r1][r2][r3][r4] >= 0) return dp[r0][r1][r2][r3][r4];
        if (used[begin]) return dp_search(r0, r1, r2, r3, r4, begin+1);
        int ret = 0;
        if (r0 > 0 && begin > maxr[0] && begin > maxc[5-r0]) ret += dp_search(r0-1, r1, r2, r3, r4, begin+1);
        if (r1 > r0 && begin > maxr[1] && begin > maxc[5-r1]) ret += dp_search(r0, r1-1, r2, r3, r4, begin+1);
        if (r2 > r1 && begin > maxr[2] && begin > maxc[5-r2]) ret += dp_search(r0, r1, r2-1, r3, r4, begin+1);
        if (r3 > r2 && begin > maxr[3] && begin > maxc[5-r3]) ret += dp_search(r0, r1, r2, r3-1, r4, begin+1);
        if (r4 > r3 && begin > maxr[4] && begin > maxc[5-r4]) ret += dp_search(r0, r1, r2, r3, r4-1, begin+1);
        dp[r0][r1][r2][r3][r4] = ret;
        return ret;
    }

    private static int left(String str) {
        clear();
        char[] arr = str.toCharArray();
        int[] args = new int[]{5, 5, 5, 5, 5};
        for (int i = 0; i < arr.length; i++) {
            int row = i / 5;
            int col = i % 5;
            args[row]--;
            int idx = arr[i] - 'A';
            if (used[idx] || maxr[row] > idx || maxc[col] > idx) return 0;

            used[idx] = true;
            maxr[row] = idx;
            maxc[col] = idx;
        }
        return dp_search(args[0], args[1], args[2], args[3], args[4], 0);
    }

    private static String findStr(int n) {
        if (n == 1) return FIRST;
        String ret = "A";
        int left = n;
        for (int i = 1; i < 25; i++) {
            for (int j = 1; j < 25; j++) {
                String cand = String.valueOf((char)('A' + j));
                int currentLeft = left(ret + cand);
                if (currentLeft >= left) {
                    ret += cand;
                    break;
                } else {
                    left -= currentLeft;
                }
            }
        }
        return ret;
    }

    private static int findNum(String str) {
        if (FIRST.equals(str)) return 1;
        int ret = 1;
        char[] arr = str.toCharArray();
        for (int i = 1; i < 25; i++) {
            char orig = arr[i];
            for (char c = 'A'; c < orig; c++) {
                ret += left(new String(arr, 0, i) + String.valueOf(c));
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String op = scan.nextLine();
        if ("W".equals(op)) {
            System.out.println(findNum(scan.nextLine()));
        } else {
            System.out.println(findStr(scan.nextInt()));
        }
    }
}
