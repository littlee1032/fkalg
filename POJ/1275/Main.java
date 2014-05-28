import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int[] r = new int[24];
    private static final int[] s = new int[25];
    private static final int INF = 1000000;

    private static final int[][] map = new int[25][25];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testNo = scan.nextInt();
        while (testNo-- > 0) {
            Arrays.fill(s, 0);
            for (int i = 0; i < 25; i++) Arrays.fill(map[i], -INF);
            for (int i = 0; i < 24; i++) {
                r[i] = scan.nextInt();
            }
            int candNo = scan.nextInt();
            for (int i = 0; i < candNo; i++) {

            }
        }
    }
}
