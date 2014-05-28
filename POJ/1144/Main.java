import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 100;
    public static final boolean[][] map = new boolean[NMAX][NMAX];
    public static final boolean[] vis = new boolean[NMAX];
    public static final int[] parent = new int[NMAX];
    public static final int[] low = new int[NMAX];
    public static final int[] lab = new int[NMAX];
    public static final boolean[] isArc = new boolean[NMAX];
    public static int count = 0;

    public static void printMap(int size) {
        System.out.print("\t");
        for (int i = 0; i < size; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < size; j++) {
                System.out.print((map[i][j] ? 1 : 0) + "\t");
            }
            System.out.println();
        }
    }

    public static void printArray(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    public static void dfs(int node, int size) {
        if (DEBUG) System.out.println("DFS node " + node + " at count " + count);
        vis[node] = true;
        low[node] = count;
        lab[node] = count;
        count++;
        for (int i = 0; i < size; i++) {
            if (map[node][i]) {
                if (!vis[i]) {
                    parent[i] = node;
                    dfs(i, size);
                    low[node] = Math.min(low[node], low[i]);
                } else if (i != parent[node]) {
                    low[node] = Math.min(low[node], lab[i]);
                }
            }
            if (DEBUG) System.out.println("node " + node + " low is " + low[node]);
        }
    }

    public static int countArcNode(int size) {
        Arrays.fill(parent, -1);
        Arrays.fill(low, Integer.MAX_VALUE);
        Arrays.fill(vis, false);
        Arrays.fill(lab, 0);
        Arrays.fill(isArc, false);
        count = 0;
        parent[0] = 0;

        dfs(0, size);
        if (DEBUG) {
            System.out.println("Parents: ");
            printArray(parent, size);
        }
        int cNum = 0;
        for (int i = 0; i < size; i++) {
            if (parent[i] == 0) {
                cNum++;
            }
        }
        int ret = 0;
        if (cNum > 2) isArc[0] = true;
        else isArc[0] = false;
        for (int i = 1; i < size; i++) {
            int p = parent[i];
            if (p != 0 && low[i] >= lab[p]) {
                if (DEBUG) System.out.println("Node " + (p + 1) + " become arc becasue " + (i + 1));
                isArc[p] = true;
            }
        }
        for (int i = 0; i < size; i++) {
            if (isArc[i]) {
                if (DEBUG) System.out.println("Node " + (i + 1) + " is arc");
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            int node = scan.nextInt();
            int count = 0;
            for (int i = 0; i < NMAX; i++) {
                Arrays.fill(map[i], false);
            }
            while (node > 0) {
                node--;
                String rest = scan.nextLine().trim();
                for (String str : rest.split(" ")) {
                    int nodeNum = Integer.valueOf(str) - 1;
                    map[node][nodeNum] = true;
                    map[nodeNum][node] = true;
                }
                node = scan.nextInt();
            }
            if (DEBUG) printMap(n);
            /*
            for (int i = 0; i < n; i++) {
                if (isCritical(i, n)) count++;
            }
            System.out.println(count);
            */
            System.out.println(countArcNode(n));
            n = scan.nextInt();
        }
    }
}
