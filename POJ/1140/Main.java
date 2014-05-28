import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 2000;
    public static final int[] result = new int[NMAX];

    public static void divide(int numer, int denom) {
        int n = numer * 10;
        for (int i = 0; i < NMAX; i++) {
            result[i] = n / denom;
            n %= denom;
            n *= 10;
        }
    }

    public static int solve() {
        for (int i = 0; i < NMAX / 2; i++) {
            boolean terminate = true;
            for (int j = i + 1; j < NMAX; j++) {
                if (result[j] != 0) {
                    terminate = false;
                    break;
                }
            }
            if (terminate) {
                for (int j = i + 1; j < NMAX; j++) {
                    result[j] = -1;
                }
                return 0;
            }
        }
        for (int i = 0; i < NMAX / 2; i++) {
            for (int k = 1; k < NMAX / 2; k++) {
                boolean repeat = true;
                for (int j = i; j < i + k; j++) {
                    int cmpIdx = j + k;
                    while (cmpIdx < NMAX) {
                        if (result[j] != result[cmpIdx]) {
                            repeat = false;
                            break;
                        }
                        cmpIdx += k;
                    }
                    if (!repeat) break;
                }
                if (repeat) {
                    for (int j = i + k; j < NMAX; j++) {
                        result[j] = -1;
                    }
                    return k;
                }
            }
        }
        return -1;
    }

    public static void print() {
        System.out.print(".");
        int count = 1;
        for (int i = 0; i < NMAX; i++) {
            if (result[i] == -1) break;
            System.out.print(result[i]);
            count++;
            if (count == 50) {
                System.out.println();
                count = 0;
            }
        }
        if (count != 0) System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int d = scan.nextInt();
        while (n + d != 0) {
            divide(n, d);
            if (DEBUG) print();
            int len = solve();
            print();
            if (len == 0) {
                System.out.println("This expansion terminates.");
            } else {
                System.out.println("The last " + len + " digits repeat forever.");
            }
            n = scan.nextInt();
            d = scan.nextInt();
        }
    }
}
