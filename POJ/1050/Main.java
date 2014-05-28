import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int nMax = 101;
        int[][] s = new int[nMax][nMax];
        for (int i = 0; i < nMax; i++) {
            Arrays.fill(s[i], 0);
        }

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i][j - 1] + scan.nextInt();
            }
        }

        int max = Integer.MIN_VALUE;
        for (int c = 1; c <= n; c++) {
            for (int j = 1; j + c - 1 <= n; j++) {
                int tMin = 0;
                int tMax = Integer.MIN_VALUE;
                int maxIdx = -1;
                int minIdx = 0;
                int tmp = 0;
                for (int i = 1; i <=n; i++) {
                    tmp += s[i][j + c - 1] - s[i][j - 1];
                    if (tmp > tMax) {
                        tMax = tmp;
                        maxIdx = i;
                    }
                }
                tmp = 0;
                for (int i = 1; i < maxIdx; i++) {
                    tmp += s[i][j + c - 1] - s[i][j - 1];
                    if (tmp < tMin) {
                        tMin = tmp;
                        minIdx = i;
                    }
                }
                if (max < tMax - tMin) {
                    max = tMax - tMin;
                }
            }
        }
        System.out.println(max);
    }
}
