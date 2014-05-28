import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final int MAXN = 10;
    public static final boolean[][] map = new boolean[MAXN][MAXN];
    public static final int[][] dis = new int[MAXN][2];
    public static final boolean[] vis = new boolean[MAXN];

    public static boolean passCheck(int guard, int s, int e, int n) {
        Arrays.fill(vis, false);
        return !dfs(guard, s, e, n);
    }

    private static boolean dfs(int guard, int s, int e, int n) {
        if (DEBUG) System.out.println("dfs: " + guard + " " + s + " " + e);
        if (s == e) return true;
        vis[s] = true;
        for (int i = 0; i < n; i++) {
            if (!vis[i] && map[s][i] && (i != guard)) {
                if (dfs(guard, i, e, n)) return true;
            }
        }
        return false;
    }

    public static void printDis(int[][] dis, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(dis[i][0] + "\t");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(dis[i][1] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int et = scan.nextInt();
        while (scan.hasNextInt()) {
            int s = scan.nextInt();
            int t = scan.nextInt();
            map[t][s] = true;
        }
        for (int i = 0; i < r; i++) {
            dis[i][0] = Integer.MAX_VALUE;
        }
        dis[et][0] = 0;
        dis[et][1] = et;
        // spfa
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(et);
        while (!queue.isEmpty()) {
            int s = queue.poll();
            for (int i = 0; i < r; i++) {
                dis[i][1] = i;
                if (map[s][i] && dis[s][0] + 1 < dis[i][0]) {
                    dis[i][0] = dis[s][0] + 1;
                    queue.offer(i);
                }
            }
        }

        // sort
        for (int i = 0; i < r; i++) {
            for (int j = i + 1; j < r; j++) {
                if (dis[i][0] > dis[j][0]) {
                    int tmp = dis[i][0];
                    dis[i][0] = dis[j][0];
                    dis[j][0] = tmp;
                    tmp = dis[i][1];
                    dis[i][1] = dis[j][1];
                    dis[j][1] = tmp;
                }
            }
        }

        if (DEBUG) printDis(dis, r);

        // try every nodes,except et room
        int guard = -1;
        for (int i = 0; i < r; i++) {
            if (dis[i][1] == et) continue;
            if (passCheck(dis[i][1], et, 0, r)) {
                guard = dis[i][1];
                break;
            }
        }
        System.out.println("Put guards in room " + guard + ".");
    }
}
