import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = true;
    public static final int[] mod2 = {2, 4, 8, 6};
    public static final int[] mod3 = {1, 3, 9, 7};
    public static final int[] mod7 = {1, 7, 9, 3};
    public static final int[] mod9 = {1, 9, 1, 9};

    public static int get5(int n) {
        if (n == 0) return 0;
        else return n / 5 + get5(n/5);
    }

    public static int get2(int n) {
        if (n == 0) return 0;
        else return n / 2 + get2(n/2);
    }

    public static int getOther(int n , int num) {
        if (n == 0) return 0;
        if (n < num) return 0;
        else return getOther(n / 2, num) + g(n, num);
    }

    public static int g(int n, int num) {
        if (n == 0) return 0;
        else return n / 10 + (n % 10 >= num ? 1 : 0) + g(n / 5, num);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int N = scan.nextInt();
            int M = scan.nextInt();

            if (N == 0) {
                System.out.println(1);
                continue;
            }

            int num2 = get2(N) - get2(N - M);
            int num5 = get5(N) - get5(N - M);

            if (num2 < num5) {
                System.out.println(5);
                continue;
            }

            int last = num2 == num5 ? 1 : mod2[(num2 - num5 - 1) % 4];
            int diff = getOther(N,3) - getOther(N - M, 3);
            last *= mod3[diff % 4];
            last %= 10;
            diff = getOther(N, 7) - getOther(N - M, 7);
            last *= mod7[diff % 4];
            last %= 10;
            diff = getOther(N, 9) - getOther(N - M, 9);
            last *= mod9[diff % 4];
            last %= 10;
            System.out.println(last);
        }
    }
}
