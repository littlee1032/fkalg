import java.util.Scanner;

public class Main {
    private static final boolean DEBUG = false;

    private static void printCables(long[] cables) {
        for (long cable : cables) {
            System.out.print(cable + ", ");
        }
        System.out.println();
    }

    private static long getMax(long[] cables, int reqNum, long min, long max) {
        if (DEBUG) {
            System.out.println(reqNum + ", " + min + ", " + max);
            printCables(cables);
        }
        long middle = (min + max) / 2;
        if (middle == 0) return 0;
        long num = 0;
        for (int i = 0; i < cables.length; i++) {
            num += (cables[i] / middle);
        }
        if (min == middle) {
            if (num >= reqNum) return min;
            else return 0;
        }
        if (num >= reqNum) {
            return getMax(cables, reqNum, middle, max);
        } else {
            return getMax(cables, reqNum, min, middle);
        }
    }

    private static long parse(String s) {
        long ret = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '.') {
                ret = ret * 10 + (c - '0');
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int K = scan.nextInt();
        long[] cables = new long[N];
        long total = 0;
        long cableMax = 0;
        scan.nextLine();

        for (int i = 0; i < N; i++) {
            long cable = parse(scan.nextLine());
            total += cable;
            if (cableMax < cable) cableMax = cable;
            cables[i] = cable;
        }

        int max = (int)getMax(cables, K, 0, cableMax + 1);
        if (DEBUG) System.out.println("Max: " + max);
        double ret = max * 0.01d;
        System.out.printf("%.2f", ret);
        System.out.println();
    }
}
