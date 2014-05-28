import java.util.Scanner;

public class Main {
    private static final int NMAX = 10001;
    private static final boolean[] filter = new boolean[NMAX];
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) filter[2*i] = true;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) filter[11 * i + 2 * j] = true;
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) filter[101 * i + 11 * j + 2*k] = true;
            }
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    for (int l = 0; l <= 9; l++) {
                        int idx = 1001*i + 101*j + 11*k + 2*l;
                        if (idx < NMAX) filter[idx] = true;
                    }
                }
            }
        }

        /*
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    for (int l = 0; l <= 9; l++) {
                        for (int m = 0; m <= 9; m++) {
                            int idx = 10001*i + 1001*j + 101*k + 11*l + 2*m;
                            if (idx < NMAX) filter[idx] = true;
                        }
                    }
                }
            }
        }
        */

        for (int i = 1; i < NMAX; i++) {
            if (!filter[i]) System.out.println(i);
        }
    }
}
