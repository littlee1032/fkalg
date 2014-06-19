import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    private static final BigInteger[] ps = new BigInteger[51];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ps[0] = BigInteger.ONE;
        for (int i = 1; i < 51; i++) {
            ps[i] = BigInteger.valueOf(i).multiply(ps[i - 1]);
        }
        String line = scan.nextLine();
        boolean first = true;
        while (!"-1".equals(line)) {
            line = line.replaceAll("[()]", "");
            String[] holder = line.split(",");
            int n = Integer.valueOf(holder[0]);
            BigInteger res = BigInteger.ZERO;
            int[] prim = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                prim[i] = Integer.valueOf(holder[i]);
            }
            for (int i = 1; i <= n; i++) {
                int less = 0;
                for (int j = i + 1; j <= n; j++) {
                    if (prim[j] < prim[i]) less++;
                }
                prim[i] = less;
            }
            for (int i = 1; i <= n; i++) {
                res = res.add(BigInteger.valueOf(prim[i]).multiply(ps[n-i]));
            }
            if (!first) System.out.print(",");
            first = false;
            System.out.print(res.add(BigInteger.ONE));
            line = scan.nextLine();
        }
        System.out.println();
    }
}
