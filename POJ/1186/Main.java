import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map.Entry;

public class Main {
    private static Map<Integer, Integer> h = new HashMap<Integer, Integer>();
    private static int[] ks;
    private static int[] ps;
    private static int m;
    private static long ans = 0L;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        m = scan.nextInt();

        ks = new int[n];
        ps = new int[n];

        for (int i = 0; i < n; i++) {
            ks[i] = scan.nextInt();
            ps[i] = scan.nextInt();
        }

        fill(0, 0, n / 2);
        fill2(0, n / 2 + 1, n-1);
        System.out.println(ans);
    }

    private static void fill(int total, int start, int end) {
        if (start > end) {
            int count = h.containsKey(total) ? h.get(total) : 0;
            count++;
            h.put(total, count);
        } else {
            for (int i = 1; i <= m; i++) {
                int add = ks[start];
                for (int j = 1; j <= ps[start]; j++) add *= i;
                fill(total + add, start + 1, end);
            }
        }
    }

    private static void fill2(int total, int start, int end) {
        if (start > end) {
            if (h.containsKey(-total)) ans += h.get(-total);
        } else {
            for (int i = 1; i <= m; i++) {
                int add = ks[start];
                for (int j = 1; j <= ps[start]; j++) add *= i;
                fill2(total + add, start + 1, end);
            }
        }
    }
}
