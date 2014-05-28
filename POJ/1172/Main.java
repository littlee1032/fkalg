import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static List<Integer> unavoid = new ArrayList<Integer>();
    private static List<Integer> splitable = new ArrayList<Integer>();
    private static List<List<Integer>> arrows = new ArrayList<List<Integer>>();
    private static int n = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int in = scan.nextInt();
        while (in != -1) {
            List<Integer> adjs = new ArrayList<Integer>();
            while (in != -2) {
                adjs.add(in);
                in = scan.nextInt();
            }
            arrows.add(adjs);
            n++;
            in = scan.nextInt();
        }
        arrows.add(new ArrayList<Integer>()); //last node

        for (int i = 1; i < n; i++) {
            check(i);
        }

        System.out.print(unavoid.size() + " ");
        System.out.println(join(unavoid, " "));
        System.out.print(splitable.size() + " ");
        System.out.println(join(splitable, " "));
    }

    private static void check(int current) {
        boolean[] reachable = new boolean[n+1];
        dfs(0, current, reachable);
        if (!reachable[n] && current != n) {
            unavoid.add(current);
            if (splitable(reachable)) {
                splitable.add(current);
            }
        }
    }

    private static void dfs(int node, int current, boolean[] reachable) {
        reachable[node] = true;
        for (Integer adj : arrows.get(node)) {
            if (adj == current) continue;
            if (reachable[adj]) continue;
            dfs(adj, current, reachable);
        }
    }

    private static boolean dfs2(int node, boolean[] vis, boolean[] reachable) {
        vis[node] = true;
        for (Integer adj : arrows.get(node)) {
            if (vis[adj]) continue;
            if (reachable[adj]) return false;
            if (!dfs2(adj, vis, reachable)) return false;
        }
        return true;
    }

    private static boolean splitable(boolean[] reachable) {
        boolean[] vis = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            if (!reachable[i] && !dfs2(i, vis, reachable)) return false;
        }
        return true;
    }

    private static String join(List<Integer> list, String join) {
        String ret = "";
        for (int i = 0; i < list.size(); i++) {
            ret += list.get(i);
            if (i < list.size() -1) {
                ret += join;
            }
        }
        return ret;
    }
}
