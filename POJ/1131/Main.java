import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 100;
    public static final char[] ans = new char[NMAX];

    private static void printNum(char[] num, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(num[i]);
        }
        System.out.println();
    }

    public static int dv8(char[] num, int len) {
        if (DEBUG) {
            System.out.print("BEFORE: ");
            printNum(num, len);
        }
        int carry = 0;
        for (int i = 0; i < len; i++) {
            int c = num[i] - '0';
            c += 10 * carry;
            num[i] = (char)((c / 8) + '0');
            carry = c % 8;
        }
        while (carry != 0) {
            num[len++] = (char)(carry * 10 / 8 + '0');
            carry = carry * 10 % 8;
        }
        if (DEBUG) {
            System.out.print("AFTER: ");
            printNum(num, len);
        }
        return len;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String oct = scan.nextLine().trim();
            Arrays.fill(ans, '0');
            char[] num = oct.toCharArray();
            int len = 1;
            for (int i = num.length - 1; i >= 2; i--) {
                ans[0] = (char)(num[i] - '0' + ans[0]);
                len = dv8(ans, len);
            }
            System.out.print(oct + " [8] = ");
            int n = NMAX;
            for (int i = NMAX - 1; i >= 0; i--) {
                if ('0' == ans[i]) {
                    n--;
                } else
                    break;
            }
            for (int i = 0; i < n; i++) {
                if (i == 1) {
                    System.out.print('.');
                }
                System.out.print(ans[i]);
            }
            System.out.println(" [10]");
        }
    }
}
