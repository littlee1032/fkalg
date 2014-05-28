import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 13;
    public static final int[] xs = new int[100];
    public static final int[] ys = new int[100];

    public static final int[] parents = new int[13];

    public static final boolean isInLine(int s1, int e1, int s) {
        return xs[s] >= Math.min(xs[s1], xs[e1])
            && xs[s] <= Math.max(xs[s1], xs[e1])
            && ys[s] >= Math.min(ys[s1], ys[e1])
            && ys[s] <= Math.max(ys[s1], ys[e1]);
    }

    public static final boolean isInterSect(int s1, int e1, int s2, int e2) {
        int c1 = (xs[s1] - xs[e1]) * (ys[s1] - ys[s2]) - (ys[s1] - ys[e1]) * (xs[s1] - xs[s2]);
        int c2 = (xs[s1] - xs[e1]) * (ys[s1] - ys[e2]) - (ys[s1] - ys[e1]) * (xs[s1] - xs[e2]);
        int c3 = (xs[s2] - xs[e2]) * (ys[s2] - ys[s1]) - (ys[s2] - ys[e2]) * (xs[s2] - xs[s1]);
        int c4 = (xs[s2] - xs[e2]) * (ys[s2] - ys[e1]) - (ys[s2] - ys[e2]) * (xs[s2] - xs[e1]);

        if (c1 * c2 < 0 && c3 * c4 < 0) {
            return true;
        }
        if (c1 == 0 && isInLine(s1, e1, s2)) return true;
        if (c2 == 0 && isInLine(s1, e1, e2)) return true;
        if (c3 == 0 && isInLine(s2, e2, s1)) return true;
        if (c4 == 0 && isInLine(s2, e2, e1)) return true;
        return false;
    }

    public static int getParent(int i) {
        if (parents[i] != i) {
            parents[i] = getParent(parents[i]);
        }
        return parents[i];
    }

    public static void initParents(int n) {
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                int s1 = (i - 1) * 2;
                int s2 = (j - 1) * 2;
                if (isInterSect(s1, s1+1, s2, s2+1)) {
                    parents[getParent(j)] = getParent(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            for (int i = 1; i <= n; i++) {
                int s = (i - 1) * 2;
                xs[s] = scan.nextInt();
                ys[s] = scan.nextInt();
                xs[s + 1] = scan.nextInt();
                ys[s + 1] = scan.nextInt();
            }
            int straw1 = scan.nextInt();
            int straw2 = scan.nextInt();
            while (straw1 + straw2 > 0) {
                initParents(n);
                if (getParent(straw1) == getParent(straw2)) {
                    System.out.println("CONNECTED");
                } else {
                    System.out.println("NOT CONNECTED");
                }
                straw1 = scan.nextInt();
                straw2 = scan.nextInt();
            }
            n = scan.nextInt();
        }
    }
}
