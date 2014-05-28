import java.util.Scanner;

public class Main {
    private static final int NMAX = 25;
    private static final long[] threes = new long[NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        threes[0] = 1;
        for (int i = 1; i < NMAX; i++) {
            threes[i] = 3 * threes[i-1];
        }
        int n = scan.nextInt();
        while (n > -1) {
            System.out.println(solve(n));
            n = scan.nextInt();
        }
    }

    private static long solve(int n) {
        if (n == 0) return 0;

        long ret = 0;
        for (int i = 0; i < n; i++) {
            ret += threes[gcd(n, i)];
        }

        if (n % 2 == 0) {
            ret += n / 2 * threes[(n - 2) / 2 + 2];
            ret += n / 2 * threes[n / 2];
        } else {
            ret += n * threes[(n - 1) / 2 + 1];
        }

        return ret / 2 / n;
    }

    private static int gcd(int a, int b) {
        int c = 0;
        while (b > 0) {
            c = b;
            b = a % b;
            a = c;
        }
        return a;
    }
}
