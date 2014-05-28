import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    private static final BigInteger TS = new BigInteger("26");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while (!"*".equals(line)) {
            char c = line.charAt(0);
            if (c >= '0' && c <= '9') calcNum(line);
            else calcChar(line);
            line = scan.nextLine();
        }
    }

    private static void calcNum(String line) {
        BigInteger val = new BigInteger(line);
        StringBuilder sb = new StringBuilder();

        while (!val.equals(BigInteger.ZERO)) {
            int mod = val.mod(TS).intValue();
            sb.append((char)('a' + mod - 1));
            val = val.divide(TS);
        }

        sb = sb.reverse();
        System.out.print(sb);
        for (int i = 0; i < 22 - sb.length(); i++) System.out.print(" ");
        System.out.println(formatNum(line));
    }

    private static void calcChar(String line) {
        char[] arr = line.toCharArray();
        BigInteger val = BigInteger.ZERO;
        for (int i = 0; i < arr.length; i++) {
            val = val.multiply(TS);
            val = val.add(BigInteger.valueOf(arr[i] - 'a')).add(BigInteger.ONE);
        }

        System.out.print(line);
        for (int i = 0; i < 22 - line.length(); i++) System.out.print(" ");
        System.out.println(formatNum(val.toString()));
    }

    private static String formatNum(String num) {
        StringBuilder sb = new StringBuilder(num);
        StringBuilder nsb = new StringBuilder();
        sb = sb.reverse();
        for (int i = 0; i < sb.length(); i++) {
            nsb.append(sb.charAt(i));
            if ((i + 1) % 3 == 0 && (i+1) < sb.length()) {
                nsb.append(',');
            }
        }

        return nsb.reverse().toString();
    }
}
