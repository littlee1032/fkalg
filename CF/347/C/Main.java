import java.util.Scanner;

public class Main {
    private static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (a % b == 0) return b;
        if (a % 2 == 0 && b % 2 != 0) return gcd(a / 2, b);
        if (a % 2 != 0 && b % 2 == 0) return gcd(a, b / 2);
        if (a / 2 == 0 && b / 2 == 0) return 2 * gcd(a / 2, b / 2);
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int a = scan.nextInt();
        int g = a;
        int max = a;
        for (int i = 1; i < n; i++) {
            a = scan.nextInt();
            g = gcd(g, a);
            max = Math.max(max, a);
        }
        int round = max / g - n;
        if (round % 2 == 1) System.out.println("Alice");
        else System.out.println("Bob");
    }
}
