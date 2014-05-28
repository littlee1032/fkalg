import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int nMax = 401;
        int[] counts = new int[nMax];
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while (T-- > 0) {
            Arrays.fill(counts, 0);
            int n = scan.nextInt();
            for (int i = 0; i < n; i++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                if (s > e) {
                    int tmp = s;
                    s = e;
                    e = tmp;
                }
                if (s % 2 == 0) s--;
                if (e % 2 == 1) e++;
                for (int j = s; j <= e; j++) counts[j]++;
            }
            int max = 0;
            for (int i = 1; i < nMax; i++) {
                if (max < counts[i]) max = counts[i];
            }
            System.out.println(10 * max);
        }
    }
}
