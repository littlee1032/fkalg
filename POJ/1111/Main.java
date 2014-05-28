import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
    private static final boolean DEBUG = false;
    private static final int NMAX = 21;
    private static final boolean[][] pic = new boolean[NMAX][NMAX];
    private static final int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] parent = new int[NMAX * NMAX];

    private static void print(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void printPic(int r, int c) {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(pic[i][j] ? 1 : 0);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static int[] getBoarder(int c, int x, int y) {
        int up = (x - 1) * (2 * c + 1) + y;
        return new int[]{up, up + c, up + c + 1, up + 2 * c + 1};
    }

    private static int getParent(int idx) {
        int p = parent[idx];
        while (p != parent[p]) {
            p = getParent(p);
        }
        parent[idx] = p;
        return p;
    }

    private static void union(int idx1, int idx2) {
        parent[getParent(idx1)] = getParent(idx2);
    }

    private static int getIdx(int x, int y, int c) {
        return (x - 1) * c + (y - 1);
    }

    private static int solve(int r, int c, int x, int y) {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                parent[getIdx(i, j, c)] = getIdx(i, j, c);;
            }
        }
        if (DEBUG) print(parent, r * c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int xx = i + 1;
                int yy = j + 1;
                if (!pic[xx][yy]) continue;
                for (int k = 0; k < 8; k++) {
                    int xxx = xx + dx[k];
                    int yyy = yy + dy[k];
                    if (xxx >= 1 && xxx <= r && yyy >= 1 && yyy <= c && pic[xxx][yyy]) {
                        union(getIdx(xx, yy, c), getIdx(xxx, yyy, c));
                    }
                }
            }
        }
        int p = getParent(getIdx(x, y, c));
        if (DEBUG) {
            for (int i = 0; i < r * c; i++) getParent(i);
            print(parent, r * c);
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < r * c; i++) {
            if (getParent(i) == p) {
                int xx = i / c + 1;
                int yy = i % c + 1;
                int[] boarders = getBoarder(c, xx, yy);
                for (int j = 0; j < boarders.length; j++) {
                    if (set.contains(boarders[j])) {
                        set.remove(boarders[j]);
                    } else {
                        set.add(boarders[j]);
                    }
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int c = scan.nextInt();
        int x = scan.nextInt();
        int y = scan.nextInt();
        while (r + c + x + y > 0) {
            scan.nextLine();
            for (int i = 1; i <= r; i++) {
                String line = scan.nextLine().trim();
                for (int j = 0; j < c; j++) {
                    if ('X' == line.charAt(j)) {
                        pic[i][j + 1] = true;
                    } else {
                        pic[i][j + 1] = false;
                    }
                }
            }
            if (DEBUG) printPic(r, c);
            System.out.println(solve(r, c, x, y));

            r = scan.nextInt();
            c = scan.nextInt();
            x = scan.nextInt();
            y = scan.nextInt();
        }
    }
}
