import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlbertoTheAviator {
    public class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            if (b[1] != a[1])
                return b[1] - a[1];
            else
                return a[0] - a[1];
        }
    }

    public int MaximumFlights(int F, int[] duration, int[] refuel) {
        int n = duration.length;
        int[][] dp = new int[F + 1][n + 1];
        List<int[]> tuples = new ArrayList<int[]>();
        for (int i = 0; i < n; i++)
            tuples.add(new int[] { duration[i], refuel[i] });
        Collections.sort(tuples, new MyComparator());

        for (int i = 1; i <= n; i++) {
            int[] tuple = tuples.get(i - 1);
            int dura = tuple[0];
            int refu = tuple[1];
            for (int j = 0; j <= F; j++) {
                dp[j][i] = Math.max(dp[j][i], dp[j][i - 1]);
                if (j >= dura) {
                    dp[j - dura + refu][i] = Math.max(dp[j - dura + refu][i], dp[j][i - 1] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= F; i++) {
            max = Math.max(max, dp[i][n]);
        }
        return max;
    }
}
