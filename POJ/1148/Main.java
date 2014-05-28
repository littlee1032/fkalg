import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        boolean[] xSign = new boolean[n];
        boolean[] ySign = new boolean[n];

        for (int i = 0; i < n; i++) {
            xs[i] = scan.nextInt();
            ys[i] = scan.nextInt();
        }

        Arrays.sort(xs);
        Arrays.sort(ys);
        int xChange = 0;
        int yChange = 0;

        for (int i = 0; i < n; i++) {
            int p = scan.nextInt();
            switch(p) {
            case 1:
                xSign[i] = true;
                ySign[i] = true;
                break;
            case 2:
                xSign[i] = false;
                ySign[i] = true;
                break;
            case 3:
                xSign[i] = false;
                ySign[i] = false;
                break;
            default:
                xSign[i] = true;
                ySign[i] = false;
            }
            if (i != 0) {
                if (xSign[i - 1] != xSign[i]) xChange++;
                if (ySign[i - 1] != ySign[i]) yChange++;
            }
        }
        int multi = 1;
        if (!xSign[0]) multi = -1;
        int s = n - 1 - xChange;
        int lm = multi;
        for (int i = s; i < n; i++) {
            xs[i] *= lm;
            lm *= -1;
        }
        lm = multi * -1;
        for (int i = s - 1; i >= 0; i--) {
            xs[i] *= lm;
            lm *= -1;
        }

        s = n - 1 - yChange;
        multi = 1;
        if (!ySign[0]) multi = -1;
        lm = multi;
        for (int i = s; i < n; i++) {
            ys[i] *= lm;
            lm *= -1;
        }
        lm = multi * -1;
        for (int i = s - 1; i >= 0; i--) {
            ys[i] *= lm;
            lm *= -1;
        }
        int xStart = n - 1 - xChange;
        int yStart = n - 1 - yChange;
        int xEnd = xStart;
        int yEnd = yStart;

        if (xs[xStart] > 0) System.out.print("+");
        System.out.print(xs[xStart] + " ");
        if (ys[yStart] > 0) System.out.print("+");
        System.out.println(ys[yStart]);

        for (int i = 1; i < n; i++) {
            int idx = 0;
            if (xSign[i - 1] != xSign[i]) idx = ++xEnd;
            else idx = --xStart;

            if (xs[idx] > 0) System.out.print("+");
            System.out.print(xs[idx] + " ");

            if (ySign[i - 1] != ySign[i]) idx = ++yEnd;
            else idx = --yStart;

            if (ys[idx] > 0) System.out.print("+");
            System.out.println(ys[idx]);
        }
    }
}
