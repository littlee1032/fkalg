import java.util.Scanner;

public class Main {
    private static final String OK = "%d coconuts, %d people and 1 monkey";
    private static final String NOK = "%d coconuts, no solution";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n >= 0) {
            boolean found = false;
            for (int i = (int)Math.sqrt(n) + 1; i > 1; i--) {
                if (solve(n, i)) {
                    found = true;
                    System.out.println(String.format(OK, n, i));
                    break;
                }
            }
            if (!found) System.out.println(String.format(NOK, n));
            n = scan.nextInt();
        }
    }

    private static boolean solve(int n, int test) {
        int left = n;
        for (int i = 0; i < test; i++) {
            if ((left - 1) % test != 0) return false;
            left = (left - 1) / test * (test - 1);
        }
        if (left % test != 0) return false;
        else return true;
    }
}
