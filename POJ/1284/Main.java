import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int NMAX = 65536;
    private static final int[] phi = new int[NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(phi, 0);
        phi[1] = 1;

        for (int i = 2; i < NMAX; i++) {
            if (phi[i] == 0) {
                for (int j = i; j < NMAX; j += i) {
                    if (phi[j] == 0) phi[j] = j;
                    phi[j] = phi[j] * (i - 1) / i;
                }
            }
        }

        while (scan.hasNext()) {
            System.out.println(phi[scan.nextInt() - 1]);
        }
    }
}
