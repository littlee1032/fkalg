import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int t = scan.nextInt();
            min = Math.min(min, t);
            max = Math.max(max, t);
        }

        int ret = (min * 2 < max) ? max : 2 * min;

        for (int i = 0; i < m; i++) {
            if (scan.nextInt() <= ret) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ret);
    }
}
