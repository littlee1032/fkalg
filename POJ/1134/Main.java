import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static final int[][] arrays = new int[3][3];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("Test #" + i);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    arrays[j][k] = scan.nextInt();
                }
                Arrays.sort(arrays[j]);
            }
            int max = 0;
            for (int j = 0; j < 3; j++) {
                int m = arrays[j][2] * 8 + arrays[j][1] * 6 + arrays[j][0] * 5;
                for (int k = 0; k < 3; k++) {
                    if (k == j) continue;
                    m += arrays[k][2] * 7 + arrays[k][1] * 7 + arrays[k][0] * 5;
                }
                max = Math.max(max, m);
            }
            System.out.println(max);
            System.out.println();
        }
    }
}
