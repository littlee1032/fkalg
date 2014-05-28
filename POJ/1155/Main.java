import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] costs = new int[n+1];
        costs[1] = 0;
        for (int i = 1; i <= n-m; i++) {
            int base = costs[i];
            int k = scan.nextInt();
            for (int j = 0; j < k; j++) {
                int idx = scan.nextInt();
                costs[idx] = base + scan.nextInt();
            }
        }

        System.out.println(Arrays.toString(Arrays.copyOfRange(costs, n-m+1, n+1)));

        int[] diffs = new int[m];
        int min = 0;
        int max = 0;
        for (int i = 0; i < m; i++) {
            diffs[i] = scan.nextInt() - costs[n-m+1+i];
            if (diffs[i] < 0) min += diffs[i];
            else if (diffs[i] > 0) max += diffs[i];
        }

        int[] ret = new int[max - min + 1];
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(ret));
            for (int j = 0; j < max - min + 1; j++) {
                if (ret[j] != 0) {
                    ret[j+diffs[i]] = Math.max(ret[j+diffs[i]], ret[j]+1);
                }
            }
            if (ret[-min+diffs[i]] == 0) ret[-min+diffs[i]] = 1;
        }
        int r = 0;
        for (int i = -min; i < max - min + 1; i++) {
            r = Math.max(ret[i], r);
        }
        System.out.println(r);
    }
}
