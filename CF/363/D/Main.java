import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        long budget = scan.nextInt();
        scan.nextLine();
        long[] pm = new long[n];
        String[] tmp = scan.nextLine().split(" ");
        long total = 0L;
        for (int i = 0; i < n; i++) {
            pm[i] = Long.valueOf(tmp[i]);
            total += pm[i];
        }
        Arrays.sort(pm);
        tmp = scan.nextLine().split(" ");
        long[] bc = new long[m];
        for (int i = 0; i < m; i++) {
            bc[i] = Long.valueOf(tmp[i]);
        }
        Arrays.sort(bc);
        long[] bcTotal = new long[m];
        bcTotal[0] = bc[0];
        for (int i = 1; i < m; i++) {
            bcTotal[i] = bcTotal[i - 1] + bc[i];
        }

        long maxTotal = total + budget;
        int idx = Arrays.binarySearch(bcTotal);
        if (idx < 0) {
            idx = -idx - 2;
        } else if (idx == m) {
            idx = m - 1;
        }
    }
}
