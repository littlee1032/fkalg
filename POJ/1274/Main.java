import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int[][] c;
    private static int[] p;
    private static int[] f;
    private static int num;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            num = n + m + 2;
            c = new int[num][num];
            p = new int[num];
            f = new int[num];

            for (int i = 1; i <= n; i++) c[0][i] = 1;
            for (int i = n + 1; i <= n + m; i++) c[i][num - 1] = 1;

            for (int i = 0; i < n; i++) {
                int stalls = scan.nextInt();
                for (int j = 0; j < stalls; j++) {
                    int stallNum = scan.nextInt() + n;
                    c[i+1][stallNum] = 1;
                }
            }

            System.out.println(max(0, num - 1));
        }
    }

    private static int max(int s, int e) {
        int ret = 0;
        int cMax = dfs(s, e);
        while (cMax > 0) {
            ret += cMax;
            residual(cMax, s, e);
            cMax = dfs(s, e);
        }
        return ret;
    }

    private static int dfs2(int node, int e) {
        if (node == e) return f[e];
        else {
            for (int i = 0; i < num; i++) {
                if (c[node][i] > 0 && p[i] == -1) {
                    p[i] = node;
                    f[i] = Math.min(f[node], c[node][i]);
                    int next = dfs2(i, e);
                    if (next > 0) return next;
                }
            }
            return 0;
        }
    }

    private static int dfs(int s, int e) {
        Arrays.fill(p, -1);
        p[s] = -2;
        Arrays.fill(f, Integer.MAX_VALUE);
        return dfs2(s, e);
    }

    private static void residual(int cMax, int s, int e) {
        for (int i = e; i > s; i = p[i]) {
            int pre = p[i];
            c[pre][i] -= cMax;
            c[i][pre] += cMax;
        }
    }
}
