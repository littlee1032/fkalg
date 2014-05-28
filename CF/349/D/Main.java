import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Main {
    private static long[] vals;
    private static long[] multis;
    private static boolean[] vis;
    private static HashSet<Integer>[] adj;
    private static boolean overflow = false;

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        if (b == 1) return 1;
        else return gcd (b, a % b);
    }

    private static long lcm(long a, long b) {
        if (a < b) return lcm(b, a);
        if (a == 0 || b == 0) return a + b;
        return a / gcd(a, b) * b;
    }

    private static void dfs(int idx) {
        vis[idx] = true;
        multis[idx] = 1L;
        int childNo = 0;
        for (Integer i : adj[idx]) {
            if (!vis[i]) {
                adj[i].remove((Integer)idx);
                childNo++;
                dfs(i);
            }
        }

        if (childNo != 0) {
            long lcm = 0;
            for (Integer i : adj[idx]) {
                lcm = lcm(lcm, multis[i]);
            }
            long min = Long.MAX_VALUE;
            for (Integer i : adj[idx]) {
                min = Math.min(min, vals[i] / lcm * lcm);
            }

            multis[idx] = multi(childNo, lcm);
            vals[idx] = multi(childNo, min);
        }
    }

    private static long multi(long a, long b) {
        if (Long.MAX_VALUE / a < b) {
            overflow = true;
            return Long.MAX_VALUE;
        } else {
            return a * b;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        vals = new long[n];
        multis = new long[n];
        vis = new boolean[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            vals[i] = scan.nextInt();
            sum += vals[i];
        }
        adj = new HashSet[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new HashSet<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            int p = scan.nextInt() - 1;
            int c = scan.nextInt() - 1;
            adj[p].add(c);
            adj[c].add(p);
        }

        dfs(0);
        if (overflow) System.out.println(sum);
        else System.out.println(sum - vals[0]);
    }
}
