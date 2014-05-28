import java.util.Scanner;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 501;
    public static final int[][] map = new int[NMAX][NMAX];
    public static final int[][] time = new int[NMAX][NMAX];
    public static final Comparator<int[]> comparator = new PQComparator();
    public static final PriorityQueue<int[]> pq = new PriorityQueue<int[]>(NMAX, comparator);

    public static class PQComparator implements Comparator<int[]> {
        public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
        }
    }

    public static void printTime(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.print(time[i][1] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int count = 1;
        while (n + m > 0) {
            System.out.println("System #" + (count++));
            pq.clear();
            for (int i = 1; i <= n; i++) {
                time[i][0] = i;
                time[i][1] = Integer.MAX_VALUE;
                Arrays.fill(map[i], -1);
                pq.offer(time[i]);
            }
            for (int i = 1; i <= m; i++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                int t = scan.nextInt();
                map[s][e] = t;
                map[e][s] = t;
            }
            time[1][1] = 0;
            if (DEBUG) printTime(n);
            while (!pq.isEmpty()) {
                int[] cMin = pq.poll();
                int s = cMin[0];
                int t = cMin[1];
                if (DEBUG) System.out.println("s: " + s + " t: " + t);
                for (int i = 1; i <= n; i++) {
                    if (map[s][i] > 0 && t + map[s][i] < time[i][1]) {
                        pq.remove(time[i]);
                        time[i][1] = t + map[s][i];
                        pq.offer(time[i]);
                    }
                }
            }
            if (DEBUG) printTime(n);
            double maxTime = -1.0d;
            int[] ids = new int[] {-1, -1};
            for (int i = 1; i <= n; i++) {
                if (time[i][1] > maxTime) {
                    maxTime = time[i][1];
                    ids[0] = i;
                    ids[1] = -1;
                }
                for (int j = i + 1; j <= n; j++) {
                    if (map[i][j] > -1) {
                        int min = Math.min(time[i][1], time[j][1]);
                        int max = Math.max(time[i][1], time[j][1]);
                        if (min + map[i][j] > max) {
                            double cTime = (min + map[i][j] + max) * 1.0d / 2;
                            if (cTime > maxTime) {
                                maxTime = cTime;
                                ids[0] = i;
                                ids[1] = j;
                            }
                        }
                    }
                }
            }
            if (ids[1] == -1) {
                System.out.println("The last domino falls after " + maxTime + " seconds, at key domino " + ids[0] + ".");
            } else {
                System.out.println("The last domino falls after " + maxTime + " seconds, between key dominoes " + ids[0] + " and " + ids[1] + ".");
            }
            System.out.println();
            n = scan.nextInt();
            m = scan.nextInt();
        }
    }
}
