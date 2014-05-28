import java.util.Arrays;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static int[] p;
    private static long[][] f;
    private static long[] flow;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int edge = scan.nextInt();
            int n = scan.nextInt();
            p = new int[n+1];
            f = new long[n+1][n+1];
            flow = new long[n+1];

            for (int i = 0; i < edge; i++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                int c = scan.nextInt();

                f[s][e] += c;
            }

            System.out.println(max(1, n));
        }
    }

    private static long max(int s, int e) {
        int max = 0;
        long fMax = bfs(s, e);
        while (fMax > 0) {
            residual(fMax, s, e);
            max += fMax;
            fMax = bfs(s, e);
        }
        return max;
    }

    private static long bfs(int s, int e) {
        Arrays.fill(flow, 0);
        flow[s] = Long.MAX_VALUE;
        Arrays.fill(p, -1);
        p[s] = -2;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(s);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 2; i <= e; i++) {
                if (f[node][i] > 0 && p[i] == -1) {
                    flow[i] = Math.min(flow[node], f[node][i]);
                    p[i] = node;
                    if (i == e) break;
                    else q.offer(i);
                }
            }
        }
        return flow[e];
    }

    private static void residual(long fMax, int s, int e) {
        for (int i = e; i > s; i = p[i]) {
            int pre = p[i];
            f[pre][i] -= fMax;
            f[i][pre] += fMax;
        }
    }
}
