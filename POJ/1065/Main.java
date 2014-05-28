import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;

    private static void sort(int[] l, int[] w, int begin, int end, int pivot) {
        if (DEBUG) {
            System.out.println(begin + " " + end + " " + pivot);
            print(l, w, begin, end);
        }
        if (begin >= end) return;
        int lPivot = l[pivot];
        int wPivot = w[pivot];
        int pos = begin - 1;
        for (int i = begin; i <= end; i++) {
            if ((i != pivot) && (l[i] < lPivot || (l[i] == lPivot && w[i] <= wPivot))) {
                pos++;
                int tmp = l[pos];
                l[pos] = l[i];
                l[i] = tmp;
                tmp = w[pos];
                w[pos] = w[i];
                w[i] = tmp;
            }
        }
        pos++;
        int tmp = l[pos];
        l[pos] = lPivot;
        l[pivot] = tmp;
        tmp = w[pos];
        w[pos] = wPivot;
        w[pivot] = tmp;
        sort(l, w, begin, pos - 1, pos - 1);
        sort(l, w, pos + 1, end, end);
    }

    private static int getSetupTime(int[] l, int[] w, int n) {
        if (n == 0) return 0;
        else {
            int ret = 0;
            boolean[] used = new boolean[n + 1];
            Arrays.fill(used, false);
            int left = n;
            while (left > 0) {
                int ml = 0, mw = 0;
                for (int i = 1; i <= n; i++) {
                    if (!used[i] && l[i] >= ml && w[i] >= mw) {
                        used[i] = true;
                        left--;
                        ml = l[i];
                        mw = w[i];
                    }
                }
                if (DEBUG) System.out.println(left);
                ret++;
            }
            return ret;
        }
    }

    private static void print(int[] l, int[] w, int b, int e) {
        for (int i = b; i <= e; i++) {
            System.out.println("(" + l[i] + ", " + w[i] + ")");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nMax = 5001;
        int[] l = new int[nMax];
        int[] w = new int[nMax];
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scan.nextInt();
            for (int j = 1; j <= n; j++) {
                l[j] = scan.nextInt();
                w[j] = scan.nextInt();
            }
            sort(l, w, 1, n, n);
            if (DEBUG) {
                System.out.println("FINAL");
                print(l, w, 1, n);
            }
            System.out.println(getSetupTime(l, w, n));
        }
    }
}
