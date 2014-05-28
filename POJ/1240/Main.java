import java.util.Scanner;

public class Main {
    private static final int NMAX = 30;
    private static long[][] c = new long[NMAX][NMAX];
    private static char[] cmp1;
    private static char[] cmp2;
    private static int n;

    private static void computeC() {
        for (int i = 0; i < NMAX; i++) {
            c[i][0] = 1;
        }

        for (int i = 1; i < NMAX; i++) {
            for (int j = 1; j < NMAX; j++) {
                c[i][j] = c[i-1][j-1] + c[i-1][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        computeC();

        while (scan.hasNext()) {
            String[] cmps = scan.nextLine().split(" ");
            n = Integer.valueOf(cmps[0]);
            if (n == 0) break;
            cmp1 = cmps[1].toCharArray();
            cmp2 = cmps[2].toCharArray();
            int len = cmp1.length;
            long num = dfs(0, len-1, 0, len-1);
            System.out.println(num);
        }
    }

    private static long dfs(int rs, int re, int ls, int le) {
        if (rs == re) return 1;
        long res = 1;
        int r = rs + 1;
        int l = ls;
        int son = 0;
        while (r <= re) {
            while (l < le) {
                if (cmp1[r] == cmp2[l]) {
                    son++;
                    break;
                }
                l++;
            }
            res *= dfs(r, r + l - ls, ls, l);
            r += l - ls + 1;
            l++;
            ls = l;
        }
        return res * c[n][son];
    }
}
