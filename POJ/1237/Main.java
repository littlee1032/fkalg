import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int NMAX = 26;
    private static int[][] map = new int[NMAX][NMAX];
    private static int[] degree = new int[NMAX];
    private static final int MAX = 1000000;

    private static void init() {
        for(int i = 0; i < NMAX; i++)
            Arrays.fill(map[i], MAX);
        Arrays.fill(degree, 0);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String street = scan.nextLine();
        init();
        int sum = 0;
        while (true) {
            int len = street.length();
            sum += len;
            int e1 = street.charAt(0) - 'a';
            int e2 = street.charAt(len-1) - 'a';
            map[e1][e2] = len;
            map[e2][e1] = len;
            degree[e1]++;
            degree[e2]++;

            street = scan.nextLine();
            if ("deadend".equals(street)) {
                // calc
                int odd = 0;
                int[] odds = new int[2];
                for (int i = 0; i < NMAX; i++) {
                    if (degree[i] % 2 == 1) odds[odd++] = i;
                }
                if (odd == 0) {
                    System.out.println(sum);
                } else {
                    // odd == 2
                    // floyd
                    for (int k = 0; k < NMAX; k++) {
                        for (int i = 0; i < NMAX; i++) {
                            for (int j = 0; j < NMAX; j++) {
                                map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                            }
                        }
                    }

                    System.out.println(sum + map[odds[0]][odds[1]]);
                }

                init();
                sum = 0;
                if (scan.hasNext()) {
                    street = scan.nextLine();
                } else {
                    break;
                }
            }
        }
    }
}
