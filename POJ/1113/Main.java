import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final boolean DEBUG = false;
    private static final double eps = 1e-9;

    private static int getIdx(int a, int n) {
        return (a + n) % n;
    }

    private static class MyComparator implements Comparator<int[]> {
        public int compare(int[] xy1, int[] xy2) {
            if (xy1[0] == 0 && xy1[1] == 0) return -1;
            if (xy2[0] == 0 && xy2[1] == 0) return 1;

            if (xy1[0] == 0 && xy2[0] == 0) return xy1[1] - xy2[1];
            if (xy1[1] == 0 && xy2[1] == 0) return xy1[0] - xy2[0];

            if (xy1[0] * xy2[1] == xy2[0] * xy1[1]) return xy1[0] - xy2[0];

            double len1 = Math.sqrt(Math.pow(xy1[0], 2) + Math.pow(xy1[1], 2));
            double len2 = Math.sqrt(Math.pow(xy2[0], 2) + Math.pow(xy2[1], 2));

            double product1 = xy1[0];
            double product2 = xy2[0];

            if (product2 * len1 - product1 * len2 < 0) return -1;
            else if (product2 * len1 - product1 * len2 > 0) return 1;
            else return 0;
        }
    }

    /*
    private static void sort(int[][] ps, int start, int end) {
        if (start >= end) return;
        int[] pivot = ps[end - 1];
        MyComparator cmp = new MyComparator();
        int storeIdx = start;
        for (int i = start; i < end - 1; i++) {
            int[] a = ps[i];
            if (cmp.compare(pivot, a) < 0) {
                if (i != storeIdx) {
                    int[] tmp = ps[storeIdx];
                    ps[storeIdx] = a;
                    ps[i] = tmp;
                }
                storeIdx++;
            }
        }
        ps[storeIdx] = pivot;
        sort(ps, 0, storeIdx);
        sort(ps, storeIdx + 1, end);
    }
    */

    private static void swap(int[][] arr, int srcIdx, int destIdx, int length) {
        for (int i = 0; i < length; i++) {
            int[] tmp = arr[srcIdx + i];
            arr[srcIdx + i] = arr[destIdx + i];
            arr[destIdx + i] = tmp;
        }
    }

    private static void rotate(int[][] arr, int offset) {
        int n = arr.length;
        int idx = 0;
        if (DEBUG) System.out.println("offerset: " + offset);
        while (idx + offset < n) {
            if (DEBUG) System.out.println("idx: " + idx + " offset: " + offset);
            for (; idx + offset + offset <= n; idx += offset) {
                swap(arr, idx, idx + offset, offset);
            }
            if (idx + offset < n) {
                int left = n - (idx + offset);
                int oldOffset = offset;
                if (left < offset) {
                    oldOffset = offset;
                    offset = left;
                }
                if (idx + oldOffset + offset <= n) {
                    swap(arr, idx, idx + oldOffset, offset);
                }
                idx = idx + offset;
                offset = oldOffset - offset;
            }
        }
    }

    private static void print(int[][] ps, int n) {
        int pageNum = 15;
        for (int i = 0; i < n; i++) {
            System.out.print("[" + ps[i][0] + "," + ps[i][1] + "] ");
            if (i % pageNum == pageNum -1) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private static boolean cc(int[] xy1, int[] xy2, int[] xy3) {
        return (xy1[0] - xy3[0]) * (xy1[1] - xy2[1]) > (xy1[0] - xy2[0]) * (xy1[1] - xy3[1]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int L = scan.nextInt();
        int[][] ps = new int[n][2];
        int minIdx = 0;
        for (int i = 0; i < n; i++) {
            ps[i][0] = scan.nextInt();
            ps[i][1] = scan.nextInt();

            if (ps[i][1] < ps[minIdx][1] ||
                (ps[minIdx][1] == ps[i][1] && ps[i][0] < ps[minIdx][0])) {
                minIdx = i;
            }
        }
        int diffx = ps[minIdx][0];
        int diffy = ps[minIdx][1];
        for (int i = 0; i < n; i++) {
            ps[i][0] -= diffx;
            ps[i][1] -= diffy;
        }
        if (DEBUG) System.out.println("minIdx: " + minIdx);
        if (DEBUG) print(ps, n);
        //        rotate(ps, minIdx + 1);
        Arrays.sort(ps, new MyComparator());
        if (DEBUG) print(ps, n);
        int[][] hull = new int[n][2];
        int m = 1;
        hull[0] = ps[0];
        hull[1] = ps[1];
        for (int i = 2; i < n ; i++) {
            while (cc(hull[m - 1], hull[m], ps[i])) {
                if (m > 1) {
                    m--;
                } else {
                    if (i == 0) break;
                    else i++;
                }
            }
            m++;
            hull[m] = ps[i];
        }

        if (DEBUG) {
            System.out.println("HULL:");
            print(hull, m);
        }

        double ret = 0d;
        for (int i = 0; i <= m; i++) {
            int post = i + 1;
            if (post > m) post = 0;
            ret += Math.sqrt(Math.pow(hull[i][0] - hull[post][0], 2) + Math.pow(hull[i][1] - hull[post][1], 2));
        }
        ret += 2 * Math.PI * L;
        System.out.println((int)Math.floor(ret + 0.5));
    }
}
