import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static int[][] ords;
    private static int[] dp;
    private static int[] dpCount;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};
    private static boolean[] vis;

    public static class Node {
        public int idx;
        public List<Node> children = new LinkedList<Node>();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n == 0) {
            System.out.println(0);
            return;
        }
        ords = new int[n][3];
        dp = new int[n];
        dpCount = new int[n];
        vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                ords[i][j] = scan.nextInt();
            }
            dp[i] = ords[i][2];
            dpCount[i] = 1;
        }

        Node root = new Node();
        root.idx = 0;
        vis[0] = true;
        makeTree(root);
        dfs(root);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            if (dpCount[i] >= 2)
                max = Math.max(max, dp[i]);
        System.out.println(max);
    }

    private static void dfs(Node p) {
        if (p.children.isEmpty()) return;
        for (Node child : p.children) {
            dfs(child);
            if (dp[child.idx] > 0) {
                dpCount[p.idx] += dpCount[child.idx];
                dp[p.idx] += dp[child.idx];
            }
        }
    }

    private static void makeTree(Node p) {
        int idx = p.idx;
        int x = ords[idx][0];
        int y = ords[idx][1];
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            for (int j = 0; j < ords.length; j++) {
                if (vis[j]) continue;
                else {
                    if (ords[j][0] == xx && ords[j][1] == yy) {
                        vis[j] = true;
                        Node child = new Node();
                        child.idx = j;
                        p.children.add(child);
                        makeTree(child);
                    }
                }
            }
        }
    }
}
