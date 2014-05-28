import java.util.Scanner;
import java.util.Arrays;

public class MyOldNaiveMain {
    private static void mark(int startX, int startY, int[][] chip, boolean vertical, int value) {
        int xdiff = 0;
        int ydiff = 0;
        if (vertical) {
            xdiff = 2;
            ydiff = 3;
        } else {
            xdiff = 3;
            ydiff = 2;
        }
        for (int x = startX; x < startX + xdiff; x++) {
            for (int y = startY; y < startY + ydiff; y++) {
                chip[x][y] = value;
            }
        }
    }

    private static void print(int[][] chip) {
        for (int i = 1; i < chip.length; i++) {
            for (int j = 1; j < chip[i].length; j++) {
                int value = chip[i][j];
                if (value >=0) {
                    System.out.print(" " + value + " ");
                } else {
                    System.out.print(value + " ");
                }
            }
            System.out.println();
        }
    }


    // 0 is not occupied
    // 1 is occupied
    // -1 is bad
    public static int maxNum(int startX, int startY, int maxX, int maxY, int[][] chip) {
        //System.out.println("startX: " + startX + " startY:" + startY);
        //print(chip);
        if (startX >= maxX) {
            if (startY < maxY) {
                return maxNum(1, startY + 1, maxX, maxY, chip);
            } else {
                return 0;
            }
        }
        int t = 0;
        int max = 0;
        // vertical
        if (startX - 1 + 3 <= maxX && startY - 1 + 2 <= maxY) {
            boolean good = true;
            for (int x = startX; x < startX + 3; x++) {
                for (int y = startY; y < startY + 2; y++) {
                    good &= (chip[x][y] == 0);
                }
            }
            if (good) {
                mark(startX, startY, chip, false, 1);
                t = 1 + maxNum(startX + 3, startY, maxX, maxY, chip);
                mark(startX, startY, chip, false, 0);
                if (t > max) {
                    max = t;
                }
            }
        }
        // horizontal
        if (startX - 1 + 2 <= maxX && startY - 1 + 3 <= maxY) {
            boolean good = true;
            for (int x = startX; x < startX + 2; x++) {
                for (int y = startY; y < startY + 3; y++) {
                    good &= (chip[x][y] == 0);
                }
            }
            if (good) {
                mark(startX, startY, chip, true, 1);
                t = 1 + maxNum(startX + 2, startY, maxX, maxY, chip);
                mark(startX, startY, chip, true, 0);
            }
            if (t > max) {
                max = t;
            }
        }
        // move forward
        t = maxNum(startX + 1, startY, maxX, maxY, chip);
        if (t > max) {
            max = t;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int D = scan.nextInt();
        while (D > 0) {
            int maxX = scan.nextInt();
            int maxY = scan.nextInt();
            int[][] chip = new int[maxX + 1][maxY + 1];
            for (int i = 0; i < maxX + 1; i++) {
                Arrays.fill(chip[i], 0);
            }
            int badNum = scan.nextInt();
            while (badNum > 0) {
                int badX = scan.nextInt();
                int badY = scan.nextInt();
                chip[badX][badY] = -1;
                badNum--;
            }
            System.out.println(maxNum(1, 1, maxX, maxY, chip));
            D--;
        }
    }
}
