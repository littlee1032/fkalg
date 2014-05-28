import java.util.Scanner;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = true;

    private static final int NMAX = 700;
    private static final int[][] ps = new int[NMAX][2];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            for (int i = 0; i < n; i++) {
                ps[i][0] = scan.nextInt();
                ps[i][1] = scan.nextInt();
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int local = 0;
                    for (int k = j + 1; k < n; k++) {
                        if ((ps[j][1] - ps[i][1]) * (ps[k][0] - ps[j][0]) == (ps[k][1] - ps[j][1]) * (ps[j][0] - ps[i][0])) {
                            local++;
                        }
                    }
                    max = Math.max(max,local);
                }
            }
            System.out.println(max + 2);
            n = scan.nextInt();
        }
    }
}
