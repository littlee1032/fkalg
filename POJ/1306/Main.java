import java.util.Scanner;

public class Main {
    private static final int NMAX = 101;
    private static final int[][] c = new int[NMAX][NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        c[0][0] = 1;
        for (int i = 1; i < NMAX; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }

        int n = scan.nextInt();
        int m = scan.nextInt();
        while (m + n > 0) {
            System.out.println(String.format("%d things taken %d at a time is %d exactly.", n, m, c[n][m]));
            n = scan.nextInt();
            m = scan.nextInt();
        }
    }
}
