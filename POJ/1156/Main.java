import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int v = scan.nextInt();
        int u = scan.nextInt();
        int c = scan.nextInt();

        int[][] map = new int[u][v];
        for (int i = 0; i < u; i++) {
            for (int j = 0; j < v; j++) {
                map[i][j] = scan.nextInt();
            }
        }

        LinkedList<Integer> q1 = new LinkedList<Integer>(); //min
        LinkedList<Integer> q2 = new LinkedList<Integer>(); //max

        int ans = 0;
        int[] min = new int[u];
        int[] max = new int[u];
        for (int y = 0; y < v; y++) {
            for (int x = 0; x < u; x++) {
                min[x] = map[x][y];
                max[x] = map[x][y];
            }
            for (int w = 0; w < 100 && y + w < v; w++) {
                int ay = y + w;
                for (int r = 0; r < u; r++) {
                    min[r] = Math.min(min[r], map[r][ay]);
                    max[r] = Math.max(max[r], map[r][ay]);
                }
                q1.clear();
                q2.clear();
                for (int r = 0, f = 0; r < u && (u-f)*(w+1) > c; r++) {
                    while (!q1.isEmpty() && min[q1.getLast()] > min[r]) q1.removeLast();
                    q1.add(r);
                    while (!q2.isEmpty() && max[q2.getLast()] < max[r]) q2.removeLast();
                    q2.add(r);

                    while (!q1.isEmpty() && !q2.isEmpty() &&
                           max[q2.getFirst()] - min[q1.getFirst()] > c) {
                        f++;
                        while (!q1.isEmpty() && q1.getFirst() < f) q1.removeFirst();
                        while (!q2.isEmpty() && q2.getFirst() < f) q2.removeFirst();
                    }
                    if (r >= f) {
                        ans = Math.max(ans, (r-f+1) * (w+1));
                    } else {
                        r = f -1;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
