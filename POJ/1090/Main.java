import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    private static final boolean DEBUG = false;
    private static final BigInteger TWO = new BigInteger("2");

    private static BigInteger getIdx(boolean[] steps, int idx, BigInteger diff) {
        if (DEBUG) System.out.println(idx + ", " + diff);
        if (idx == 0) {
            if (!steps[0]) return BigInteger.ZERO;
            else return BigInteger.ONE;
        }

        if (steps[idx]) {
            return diff.subtract(BigInteger.ONE).subtract(getIdx(steps, idx - 1, diff.divide(TWO)));
        } else {
            return getIdx(steps, idx - 1, diff.divide(TWO));
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (0 == scan.nextInt()) {
                array[i] = false;
            } else {
                array[i] = true;
            }
        }

        BigInteger diff = TWO.pow(n);
        System.out.println(getIdx(array, n - 1, diff));
    }
}
