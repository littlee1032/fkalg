import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static int[] far;
    public static boolean[] vis;
    public static boolean[] isHotel;
    public static int[] froms;
    public static int maxK;

    private static void dfs(int from, int level, int[] result) {
        /*
        System.out.println("vis: " + Arrays.toString(vis));
        System.out.println("far: " + Arrays.toString(far));
        System.out.println("isHotel: " + Arrays.toString(isHotel));
        System.out.println("froms: " + Arrays.toString(froms));
        System.out.println("from: " + from);
        System.out.println(maxK);
        System.out.println(result[0] + ", " + result[1]);
        System.out.println();
        */
        vis[from] = true;
        int to = froms[from];
        if (to >= 0 && !vis[to]) {
            dfs(to, level + 1, result);
        }
        if (to >= 0) {
            far[from] = far[to] + 1;
        } else if (isHotel[from]) {
            far[from] = 1;
        }
        int k = level + far[from];
        if (k >= maxK) {
            maxK = k;
            result[1] = from;
        }
        //        System.out.println("After: " + maxK + ", " + result[1]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        vis = new boolean[n];
        far = new int[n];
        Arrays.fill(far, Integer.MIN_VALUE);
        froms = new int[n];
        Arrays.fill(froms, -1);
        isHotel = new boolean[n];
        maxK = 0;

        for (int i = 0; i < n; i++) {
            if (scan.nextInt() != 0) {
                isHotel[i] = true;
                far[i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int from = scan.nextInt() - 1;
            if (from != -1) {
                if (froms[from] == -1) {
                    froms[from] = i;
                } else {
                    froms[from] = -2;
                }
            }
        }

        int[] result = new int[]{0, -1};
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(i, 0, result);
        }
        if (result[1] != -1) {
            System.out.println(maxK);
            int obj = result[1];
            for (int i = 0; i < maxK; i++) {
                System.out.print(obj + 1);
                obj = froms[obj];
                if (i != maxK - 1) System.out.print(" ");
                else System.out.println();
            }
        } else {
            System.out.println(1);
            for (int i = 0; i < n; i++) {
                if (isHotel[i]) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }
}
