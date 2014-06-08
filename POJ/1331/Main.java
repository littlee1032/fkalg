import java.util.Scanner;

public class Main {
    private static final int[][] table = new int[15][7];

    private static void init() {
        for (int i = 2; i <= 16; i++) {
            table[i-2][0] = 1;
            for (int j = 1; j <= 6; j++) {
                table[i-2][j] = table[i-2][j-1] * i;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        init();
        int t = scan.nextInt();
        while (t-- > 0) {
            int m = scan.nextInt();
            int n = scan.nextInt();
            int r = scan.nextInt();

            System.out.println(solve(m, n, r));
        }
    }

    private static int solve(int m, int n, int r) {
        for (int b = 2; b <= 16; b++) {
            long mm = tranverse(m, b);
            long nn = tranverse(n, b);
            long rr = tranverse(r, b);

            if (mm > 0 && nn > 0 && rr > 0 && mm * nn == rr) return b;
        }
        return 0;
    }

    private static long tranverse(int m , int b) {
        long ret = 0;
        int idx = 0;
        while (m > 0) {
            int r = m % 10;
            if (r >= b) return -1L;
            ret += table[b-2][idx] * 1L * r;
            m /= 10;
            idx++;
        }
        return ret;
    }
}
