import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 101;
    public static final int[][] map = new int[NMAX][NMAX];
    public static final int[] times = new int[NMAX];
    public static final boolean[] checked = new boolean[NMAX];

    public static void print(int[] arr, int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void fill(int start, int n) {
        times[start] = 0;
        int candidate = start;
        while (candidate != 0) {
            checked[candidate] = true;
            int next = 0;
            int currentMin = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (!checked[i] && map[candidate][i] > -1) {
                    if (times[i] > times[candidate] + map[candidate][i]) {
                        times[i] = times[candidate] + map[candidate][i];
                    }
                }
                if (!checked[i] && times[i] < currentMin) {
                    currentMin = times[i];
                    next = i;
                }
            }
            candidate = next;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            for (int i = 1; i <= n; i++) {
                Arrays.fill(map[i], -1);
                int num = scan.nextInt();
                while (num-- > 0) {
                    int other = scan.nextInt();
                    map[i][other] = scan.nextInt();
                }
            }

            int min = 0;
            int minTime = Integer.MAX_VALUE;
            boolean disjoint = true;
            for (int i = 1; i <= n; i++) {
                Arrays.fill(times, Integer.MAX_VALUE);
                Arrays.fill(checked, false);
                fill(i, n);
                if (DEBUG) {
                    System.out.println(i + ": ");
                    print(times, n);
                }
                int max = -1;
                for (int j = 1; j <= n; j++) {
                    max = Math.max(max, times[j]);
                }
                if (max < minTime) {
                    min = i;
                    minTime = max;
                    disjoint = false;
                }

            }
            if (disjoint) {
                System.out.println("disjoint");
            } else {
                System.out.println(min + " " + minTime);
            }
            n = scan.nextInt();
        }
    }
}
