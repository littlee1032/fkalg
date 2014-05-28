import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] ns = new int[2 * n][2];
        int[] counter = new int[90];
        for (int i = 0; i < 2 * n; i++) {
            int num = scan.nextInt();
            ns[i][0] = num;
            counter[num - 10]++;
        }

        int total = 0;
        int dup = 0;

        for (int i = 0; i < 90; i++) {
            if (counter[i] > 0) {
                if (counter[i] >= 2) {
                    dup++;
                } else {
                    total++;
                }
            }
        }

        total += 2 * dup;

        int half = (total + 1) / 2;
        int tot = (total - half) * half;
        System.out.println(tot);

        int diff = n - half;
        half = n;
        for (int i = 0; i < 2 * n; i++) {
            int num = ns[i][0];
            if (counter[num - 10] >= 2) {
                ns[i][1] = 1;
                counter[num - 10] = -2;
                half--;
            } else if (counter[num - 10] == -2) {
                ns[i][1] = 2;
                counter[num - 10] = -3;
            } else if (counter[num - 10] == -3 && diff > 0) {
                ns[i][1] = 1;
                half--;
                diff--;
            }
        }

        for (int i = 0; i < 2 * n; i++) {
            int num = ns[i][0];
            if (ns[i][1] == 0) {
                if (half > 0) {
                    ns[i][1] = 1;
                    half--;
                } else {
                    ns[i][1] = 2;
                }
            }
        }

        for (int i = 0; i < 2 * n; i++) {
            System.out.print(ns[i][1]);
            if (i < 2 * n - 1) System.out.print(" ");
            else System.out.println();
        }
    }
}
