import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 100;
    public static final int[] count = new int[NMAX * 3 * 4];
    public static final double[] sum = new double[NMAX * 3 * 4];
    public static final double[] xs = new double[NMAX * 2];
    public static final BoarderComparator cmp = new BoarderComparator();

    public static class Boarder {
        double x1;
        double x2;
        double h;
        int s;

        Boarder(double x1, double x2, double h, int s) {
            this.x1 = x1;
            this.x2 = x2;
            this.h = h;
            this.s = s;
        }
    }

    public static class BoarderComparator implements Comparator<Boarder> {
        public int compare(Boarder b1, Boarder b2) {
            if (b1.h < b2.h) return -1;
            else if (b1.h == b2.h) return 0;
            else return 1;
        }
    }

    public static void updateSum(int left, int right, int root) {
        if (count[root] > 0) sum[root] = xs[right] - xs[left];
        else if (left == right) sum[root] = 0;
        else sum[root] = sum[root * 2 + 1] + sum[root * 2 + 2];
    }

    public static void update(int left, int right, int l, int r, int s, int root) {
        if (DEBUG) {
            System.out.println(left + ", " + right + ", " + l + ", " + r + ", " + root);
        }
        if (l <= left && r >= right) {
            count[root] += s;
            updateSum(left, right, root);
        } else {
            int mid = (left + right) / 2;
            if (l < mid) update(left, mid, l, r, s, root * 2 + 1);
            if (r > mid) update(mid, right, l, r, s, root * 2 + 2);
            updateSum(left, right, root);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int testCounter = 0;
        List<Boarder> bs = new ArrayList<Boarder>();
        while (n != 0) {
            testCounter++;
            bs.clear();
            Arrays.fill(count, 0);
            Arrays.fill(sum, 0.0d);
            Arrays.fill(xs, 0.0d);
            int xsIdx = 0;
            for (int i = 0; i < n; i++) {
                double x1 = scan.nextDouble();
                xs[xsIdx++] = x1;
                double y1 = scan.nextDouble();
                double x2 = scan.nextDouble();
                xs[xsIdx++] = x2;
                double y2 = scan.nextDouble();
                Boarder b1 = new Boarder(x1, x2, y2, -1);
                bs.add(b1);
                Boarder b2 = new Boarder(x1, x2, y1, 1);
                bs.add(b2);
            }

            //discrete xs
            Arrays.sort(xs, 0, 2 * n);
            if (DEBUG) {
                for (int i = 0; i < 2 * n; i++) {
                    System.out.print(xs[i] + " ");
                }
                System.out.println();
            }
            int k = 1;
            for (int i = 1; i < 2 * n; i++) {
                if (xs[i] != xs[i - 1]) {
                    xs[k++] = xs[i];
                }
            }

            Collections.sort(bs, cmp);
            double ret = 0.0;
            for (int i = 0; i < bs.size() - 1; i++) {
                Boarder b = bs.get(i);
                int l = Arrays.binarySearch(xs, 0, k, b.x1);
                int r = Arrays.binarySearch(xs, 0, k, b.x2);
                update(0, k - 1, l, r, b.s, 0);
                if (DEBUG) {
                for (int j = 0; j < n * 4; j++) {
                    System.out.print(sum[j] + " ");
                }
                System.out.println();
                }
                ret += sum[0] * (bs.get(i+1).h - b.h);
            }
            System.out.println("Test case #" + testCounter);
            System.out.printf("Total explored area: %.2f\n", ret);
            System.out.println();
            n = scan.nextInt();
        }
    }
}
