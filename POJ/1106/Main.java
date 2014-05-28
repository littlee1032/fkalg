import java.util.Scanner;
import java.util.Comparator;

public class Main {
    private static final boolean DEBUG = true;
    private static final int[][] xys = new int[150][2];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        double r = scan.nextDouble();

        while (r > 0) {
            double r2 = r * r;
            int n = scan.nextInt();
            int num = 0;
            for (int i = 0; i < n; i++) {
                int xx = scan.nextInt() - x;
                int yy = scan.nextInt() - y;
                if (xx * xx + yy * yy <= r2) {
                    xys[num][0] = xx;
                    xys[num][1] = yy;
                    num++;
                }
            }
            int max = 0;
            for (int i = 0; i < num; i++) {
                int a = -1 * xys[i][1];
                int b = xys[i][0];
                int upperMax = 0;
                int belowMax = 0;
                for (int j = 0; j < num; j++) {
                    if (a * xys[j][0] + b * xys[j][1] >= 0) upperMax++;
                    if (a * xys[j][0] + b * xys[j][1] <= 0) belowMax++;
                }
                max = Math.max(max, Math.max(upperMax, belowMax));
            }
            System.out.println(max);
            x = scan.nextInt();
            y = scan.nextInt();
            r = scan.nextDouble();
        }
    }
}
