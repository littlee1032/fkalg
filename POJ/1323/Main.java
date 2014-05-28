import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int caseIdx = 0;
        while (m + n > 0) {
            int[] my = new int[m];
            for (int i = 0; i < m; i++) {
                my[i] = scan.nextInt();
            }
            Arrays.sort(my);

            int c = 0;
            int idx = m - 1;
            int ret = 0;
            for (int i = m * n; i > 0; i--) {
                if (idx < 0) break;
                if (my[idx] == i) {
                    if (c == 0) ret++;
                    else c--;
                    idx--;
                } else {
                    c++;
                }
            }
            System.out.println("Case " + (++caseIdx) + ": " + ret);
            n = scan.nextInt();
            m = scan.nextInt();
        }
    }
}
