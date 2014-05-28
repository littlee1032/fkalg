import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] ars = scan.nextLine().split(" ");
        while (!"0".equals(ars[0]) && !"END".equals(ars[1])) {
            long target = Long.valueOf(ars[0]);
            int n = ars[1].length();
            long[] base = new long[n];
            for (int i = 0; i < n; i++) {
                base[i] = ars[1].charAt(i) - 'A' + 1;
            }
            Arrays.sort(base);
            long[][] map = new long[n][5];
            for (int i = 0; i < n; i++) {
                long start = 1L;
                for (int j = 0; j < 5; j++) {
                    map[i][j] = start * base[i];
                    start *= base[i];
                }
            }
            boolean[] vis = new boolean[n];
            int[] breadcumbs = new int[5];
            boolean ret = solve(target, map, vis, 0, breadcumbs);
            if (!ret) System.out.println("no solution");
            ars = scan.nextLine().split(" ");
        }
    }

    private static boolean solve(long target, long[][] map, boolean[] vis, int step, int[] breadcumbs) {
        if (target == 0) {
            for (int i = 0; i < 5; i++) System.out.print((char)('A' + breadcumbs[i] -1));
            System.out.println();
            return true;
        } else if (step >= 5) return false;
        else {
            for (int i = vis.length - 1; i >= 0; i--) {
                if (vis[i]) continue;
                long diff = map[i][step];
                breadcumbs[step] = (int)map[i][0];
                long newTarget = target;
                if (step % 2 == 0) {
                    newTarget = target - map[i][step];
                } else {
                    newTarget = target + map[i][step];
                }
                vis[i] = true;
                boolean res = solve(newTarget, map, vis, step+1, breadcumbs);
                vis[i] = false;
                if (res) return true;
            }
            return false;
        }
    }
}
