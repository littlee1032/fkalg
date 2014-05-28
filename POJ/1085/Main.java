import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final boolean DEBUG = false;

    private static final int mask = 0x7fffe;

    private static final int[] triangles = new int[9];
    private static final int[] boarders = new int[]{12, 13, 23, 24, 25, 35, 36, 45, 56, 47, 48, 58, 59, 69, 70, 78, 89, 100};

    private static int getBoarder(int start, int end) {
        int search = start * 10 + end;
        return getBoarder(search);
    }

    private static int getBoarder(int boarder) {
        for (int i = 0; i < 18; i++) {
            if (boarders[i] == boarder) {
                return i + 1;
            }
        }
        return -1;
    }

    private static int mark(int pos, int initial) {
        return initial & (1 << pos) & mask;
    }

    private static int newTriangle(int snapshot, int boarder) {
        int ret = 0;
        for (int i = 0; i < triangles.length; i++) {
            if ((snapshot & triangles[i]) != triangles[i] &&
                (((snapshot | (1 << boarder)) & triangles[i]) == triangles[i])) {
                ret++;
            }
        }
        return ret;
    }

    private static int solve(int initial, int[] left) {
        int leftNum = left.length;
        int size = 1;
        while (leftNum-- > 0) size *= 2;
        int[] dp = new int[size];
        boolean[] checked = new boolean[size];
        Arrays.fill(dp, 0);
        Arrays.fill(checked, false);
        checked[size - 1] = true;

        solve(initial, left, dp, checked, 0);
        if (DEBUG) print(dp);
        return dp[0];
    }

    private static void print(int[] dp) {
        for (int i = 0; i < dp.length; i++) {
            if (i % 30 == 0) System.out.println();
            System.out.print(dp[i]);
            System.out.print(",");
        }
        System.out.println();
    }

    private static void solve(int snapshot, int[] left, int[] dp, boolean[] checked, int idx) {
        if (checked[idx]) return;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < left.length; i++) {
            int boarderIdx = getBoarder(left[i]);
            int m = 1 << boarderIdx;
            int count = 0;
            if ((snapshot & m) == 0) {
                int newSnapshot = snapshot | m;
                int newIdx = idx + (1 << i);
                solve(newSnapshot, left, dp, checked, newIdx);
                int newTriangle = newTriangle(snapshot, boarderIdx);
                if (newTriangle == 0) {
                    count = -1 * dp[newIdx];
                } else {
                    count = newTriangle + dp[newIdx];
                }
                if (DEBUG) {
                    System.out.printf("%H %H %d %d %d\n", snapshot, m, boarderIdx, i, idx);
                }
                max = Math.max(max, count);
            }
        }
        dp[idx] = max;
        checked[idx] = true;
    }

    public static void main(String[] args) {
        triangles[0] = (1 << 1) | (1 << 2) | (1 << 3) & mask;
        triangles[1] = (1 << 4) | (1 << 5) | (1 << 8) & mask;
        triangles[2] = (1 << 3) | (1 << 5) | (1 << 6) & mask;
        triangles[3] = (1 << 6) | (1 << 7) | (1 << 9) & mask;
        triangles[4] = (1 << 10) | (1 << 11) | (1 << 16) & mask;
        triangles[5] = (1 << 8) | (1 << 11) | (1 << 12) & mask;
        triangles[6] = (1 << 12) | (1 << 13) | (1 << 17) & mask;
        triangles[7] = (1 << 9) | (1 << 13) | (1 << 14) & mask;
        triangles[8] = (1 << 14) | (1 << 15) | (1 << 18) & mask;

        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            System.out.print("Game " + caseNum + ": ");
            int n = scan.nextInt();
            int initial = 0;
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < boarders.length; i++) {
                list.add(boarders[i]);
            }
            int player = 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                if (a > b) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }
                int search = 10 * a + b;
                int boarderIdx = getBoarder(search);
                int triangleNum = newTriangle(initial, boarderIdx);
                if (triangleNum > 0) {
                    count += player * triangleNum;
                } else {
                    player *= -1;
                }
                initial |= (1 << boarderIdx);
                list.remove(Integer.valueOf(search));
            }
            int[] left = new int[list.size()];
            for (int i = 0; i < left.length; i++) {
                left[i] = list.get(i);
            }
            if (DEBUG) {
                System.out.print("LEFT: ");
                for (int i = 0; i < left.length; i++) {
                    System.out.print(left[i] + ", ");
                }
                System.out.println();
            }
            count += player * solve(initial, left);
            if (count > 0) {
                System.out.println("A wins.");
            } else {
                System.out.println("B wins.");
            }
        }
    }
}
