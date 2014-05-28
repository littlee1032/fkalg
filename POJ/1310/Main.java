import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int lightNo;
    private static final int NMAX = 6;
    private static int[][] lightInfo = new int[NMAX][4];
    private static double[] lightMiles = new double[NMAX];
    private static boolean[] valid = new boolean[32];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        lightNo = scan.nextInt();
        int caseIdx = 0;
        while(lightNo > 0) {
            for (int i = 0; i < lightNo; i++) {
                lightMiles[i] = scan.nextDouble();
                for (int j = 1; j <= 3; j++) {
                    lightInfo[i][j] = scan.nextInt();
                    lightInfo[i][0] += lightInfo[i][j];
                }
            }
            int lastSuccess = -1;
            for (int i = 30; i <= 60; i++) {
                valid[i - 30] = test(i);
            }
            print(valid, ++caseIdx);
            Arrays.fill(valid,false);
            lightNo = scan.nextInt();
        }
    }

    private static boolean test(int speed) {
        for (int i = 0; i < lightNo; i++) {
            int circle = lightInfo[i][0];
            double sec = lightMiles[i] / speed * 60 * 60;
            double mod = sec - (int)(sec / circle) * circle;

            if (mod > lightInfo[i][1] + lightInfo[i][2] && mod < circle) return false;
        }
        return true;
    }

    private static void print(boolean[] valid, int caseNo) {
        System.out.print("Case " + caseNo + ":");
        boolean found = false;
        boolean isFirst = true;
        int lowIdx = -1;
        int highIdx = -1;
        for (int i = 0; i < 32; i++) {
            if (valid[i]) {
                if (lowIdx == -1) {
                    lowIdx = i;
                    highIdx = i;
                } else {
                    highIdx = i;
                }
                found = true;
            } else {
                if (highIdx != -1) {
                    if (!isFirst) {
                        System.out.print(",");
                    } else {
                        isFirst = false;
                    }
                    if (highIdx == lowIdx) System.out.print(" " + (highIdx+30));
                    else System.out.print(" " + (lowIdx+30) + "-" + (highIdx+30));
                    lowIdx = - 1;
                    highIdx = -1;
                }
            }
        }
        if (!found) {
            System.out.print(" No acceptable speeds.");
        }
        System.out.println();
    }
}
