import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static final boolean DEBUG = true;
    public static final int NMAX = 10;
    public static final int[][] dist = new int[NMAX][1 << NMAX];
    public static final boolean[][] rooms = new boolean[NMAX][NMAX];
    public static final boolean[][] switches = new boolean[NMAX][NMAX];
    public static final Deque<int[]> queue = new LinkedList<int[]>();
    public static final Deque<String> msgs = new LinkedList<String>();
    public static final int[][][] pre = new int[NMAX][1 << NMAX][2];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int d = scan.nextInt();
        int s = scan.nextInt();
        int caseIdx = 0;
        while (r + d + s > 0) {
            caseIdx++;
            for (int i = 0; i < r; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                Arrays.fill(rooms[i], false);
                Arrays.fill(switches[i], false);
            }
            for (int i = 0; i < d; i++) {
                int r1 = scan.nextInt();
                int r2 = scan.nextInt();
                rooms[r1 - 1][r2 - 1] = true;
                rooms[r2 - 1][r1 - 1] = true;
            }
            for (int i = 0; i < s; i++) {
                int s1 = scan.nextInt();
                int s2 = scan.nextInt();
                switches[s1 - 1][s2 - 1] = true;
            }

            System.out.println("Villa #" + caseIdx);
            queue.clear();
            dist[0][1] = 0;
            queue.offer(new int[]{0, 1});
            boolean flag = false;
            msgs.clear();
            while (!queue.isEmpty()) {
                int[] head = queue.poll();
                int cr = head[0];
                int cs = head[1];
                int mDist = dist[cr][cs];
                if (cr == r - 1 && cs == (1 << (r - 1))) {
                    // found
                    System.out.println("The problem can be solved in " + mDist + " steps:");
                    while (cr != 0 || cs != 1) {
                        int[] mpre = pre[cr][cs];
                        if (mpre[0] == 0) {
                            // switch light
                            int light = mpre[1];
                            msgs.push("- Switch " + (((cs & (1 << light)) != 0) ? "on" : "off") + " light in room " + (light + 1) + ".");
                            cs ^= (1 << light);
                        } else {
                            // move to room
                            msgs.push("- Move to room " + (cr + 1) + ".");
                            cr = mpre[1];
                        }
                    }
                    flag = true;
                    break;
                } else {
                    // bfs
                    for (int i = 0; i < r; i++) {
                        if (i != cr && rooms[cr][i] && ((cs & (1 << i)) != 0) && mDist + 1 < dist[i][cs]) {
                            dist[i][cs] = mDist + 1;
                            pre[i][cs] = new int[]{1, cr};
                            queue.offer(new int[]{i, cs});
                        }
                    }
                    for (int i = 0; i < r; i++) {
                        if (i != cr && switches[cr][i]) {
                            int ms = cs ^ (1 << i);
                            if (mDist + 1 < dist[cr][ms]) {
                                dist[cr][ms] = mDist + 1;
                                pre[cr][ms] = new int[]{0, i};
                                queue.offer(new int[]{cr, ms});
                            }
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println("The problem cannot be solved.");
            } else {
                while (!msgs.isEmpty()) {
                    System.out.println(msgs.pop());
                }
            }
            System.out.println();
            r = scan.nextInt();
            d = scan.nextInt();
            s = scan.nextInt();
        }
    }
}
