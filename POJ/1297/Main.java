import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static int m;
    private static int n;
    private static final int LIST_MAX = 110;
    private static final int PRO_MAX = 100010;
    private static final int[] list = new int[LIST_MAX];
    private static final double[] product = new double[PRO_MAX];
    private static final double[] dp = new double[PRO_MAX];
    private static final double[] ndp = new double[PRO_MAX];
    private static final double INF = 1000000000d;
    private static final Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        m = scan.nextInt();
        n = scan.nextInt();

        while (m + n > 0) {
            for (int i = 0; i < m; i++) list[i] = scan.nextInt();
            for (int i = 0; i < n; i++) {
                int p = scan.nextInt();
                double pp = scan.nextDouble();
                if (!map.containsKey(p)) {
                    map.put(p, new LinkedList<Integer>());
                }
                map.get(p).add(0, i);
                product[i] = pp;
            }

            Arrays.fill(ndp, INF);
            Arrays.fill(dp, 0);
            for (int k = 1; k <= m; k++) {
                int want = list[m - k];
                double prePrice = INF;
                int end = n - 1;
                for (int start : map.get(want)) {
                    for (int j = end - 1; j > start; j--) {
                        ndp[j] = Math.min(ndp[j + 1], prePrice + dp[j+1]);
                    }
                    prePrice = Math.min(prePrice, product[start]);
                    end = start + 1;
                }
                Arrays.fill(ndp, 0, end, Math.min(ndp[end], prePrice + dp[end]));
                System.arraycopy(ndp, 0, dp, 0, n);
                ndp[n-k] = INF;
                dp[n] = INF;
            }

            double min = INF;
            for (int i = 0; i < n; i++) min = Math.min(min, dp[i]);
            if (min == INF) System.out.println("Impossible");
            else System.out.println(String.format("%.2f", min));

            m = scan.nextInt();
            n = scan.nextInt();
            map.clear();
        }
    }

    private static void print(double[] arr, int len) {
        for (int i = 0; i < len; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}
