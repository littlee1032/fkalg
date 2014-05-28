import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int NMAX = 100001;
    private static int[] bills = new int[NMAX];
    private static int count;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            count = 0;
            int target = scan.nextInt();
            int billNo = scan.nextInt();

            for (int i = 0; i < billNo; i++) {
                int num = scan.nextInt();
                int val = scan.nextInt();

                int base = 1;
                while (num >= base) {
                    bills[count++] = val * base;
                    num -= base;
                    base *= 2;
                }

                if (num > 0) bills[count++] = val * num;
            }

            System.out.println(solve(target));
        }
    }

    private static int solve(int target) {
        if (target == 0 || count == 0) return 0;

        int g = 0;
        for (int i = 0; i < count; i++) {
            g = gcd(bills[i], g);
        }

        target = target / g;
        for (int i = 0; i < count; i++) {
            bills[i] /= g;
        }

        boolean[] ok = new boolean[target + 1];
        ok[0] = true;
        int max = 0;
        for (int i = 0; i < count; i++) {
            int val = bills[i];
            for (int j = target; j >= val; j--) {
                if (ok[j - val]) {
                    ok[j] = true;
                    max = Math.max(max, j);
                }
            }
        }
        return max * g;
    }

    private static int gcd(int a, int b) {
        int c = 0;
        while (b != 0) {
            c = b;
            b = a % b;
            a = c;
        }
        return a;
    }
}
