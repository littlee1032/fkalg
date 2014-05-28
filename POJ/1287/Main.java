import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {
    private static final int NMAX = 51;
    private static final int INF = 1000;
    private static final int[][] conns = new int[NMAX][NMAX];
    private static final boolean[] vis = new boolean[NMAX];

    private static class MComparator implements Comparator<int[]> {
        @Override public int compare(int[] a, int[] b) {
            return a[2] - b[2];
        }
    }

    private static final MComparator CMP = new MComparator();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int p = scan.nextInt();

        while (p > 0) {
            Arrays.fill(vis, false);
            for (int i = 0; i < NMAX; i++) Arrays.fill(conns[i], INF);

            int r = scan.nextInt();
            for (int i = 0; i < r; i++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                int w = scan.nextInt();
                conns[s][e] = Math.min(conns[s][e], w);
                conns[e][s] = Math.min(conns[e][s], w);
            }
            System.out.println(solve());

            p = scan.nextInt();
        }
    }

    private static int solve() {
        int ret = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(10, CMP);
        for (int i = 2; i < NMAX; i++) {
            if (conns[1][i] < INF) {
                pq.offer(new int[]{1, i, conns[1][i]});
            }
        }
        vis[1] = true;

        while (!pq.isEmpty()) {
            int[] cand = pq.poll();
            if (!vis[cand[1]]) {
                int e = cand[1];
                vis[e] = true;
                for (int i = 1; i < NMAX; i++) {
                    if (conns[e][i] < INF && !vis[i]) {
                        pq.offer(new int[]{e, i, conns[e][i]});
                    }
                }
                ret += cand[2];
            }
        }

        return ret;
    }
}
