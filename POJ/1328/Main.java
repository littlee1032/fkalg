import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static class MCom implements Comparator<double[]> {
        @Override public int compare(double[] a, double[] b) {
            if (a[0] == b[0]) {
                if (a[1] < b[1]) return -1;
                else if (a[1] == b[1]) return 0;
                else return 1;
            } else {
                if (a[0] < b[0]) return -1;
                else if (a[0] == b[0]) return 0;
                else return 1;
            }
        }
    }

    private static final Comparator<double[]> CMP = new MCom();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int caseIdx = 0;
        List<double[]> bars = new LinkedList<double[]>();

        while (m != 0 || n != 0) {
            bars.clear();
            boolean na = false;
            for (int i = 0; i < n; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                if (y > m) na = true;
                if (!na) {
                    double sqrt = Math.sqrt(m * m - y * y);
                    bars.add(new double[]{x - sqrt, x + sqrt});
                }
            }
            if (na) {
                System.out.println(String.format("Case %d: %d", ++caseIdx, -1));
            } else {
                Collections.sort(bars, CMP);
                int ret = 0;
                double s = -10000000000.0d;
                double e = 10000000000.0d;
                for (double[] bar : bars) {
                    if ((bar[0] <= s && s <= bar[1]) || (s <= bar[0] && bar[0] <= e)) {
                        s = Math.max(s, bar[0]);
                        e = Math.min(e, bar[1]);
                    } else {
                        ret++;
                        s = bar[0];
                        e = bar[1];
                    }
                }
                ret++;
                System.out.println(String.format("Case %d: %d", ++caseIdx, ret));
            }

            n = scan.nextInt();
            m = scan.nextInt();
        }
    }
}
