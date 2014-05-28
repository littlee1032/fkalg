import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final long[] number = new long[20];
    private static final long[] sum = new long[20];

    private static void print(int idx) {
        long total = -1; //zero is idx 0
        for (int i = 0; i < 1000; i++) {
            if (number[i] == -1) {
                long r = 0;
                for (int j = 0; j <= i - 1; j++) {
                    r += number[j] * number[i - 1 - j];
                }
                number[i] = r;
            }
            total += number[i];
            sum[i] = total;
            if (total >= idx) {
                print(idx - total + number[i], i);
                System.out.println();
                break;
            }
        }
    }

    private static void print(long idx, int nodeNum) {
        if (idx == 1 && nodeNum == 0) {
            return;
        }
        if (nodeNum == 1) {
            System.out.print("X");
        } else {
            long total = 0;
            int left = 0;
            for (; left <= nodeNum - 1; left++) {
                total += number[left] * number[nodeNum - 1 - left];
                if (total >= idx) break;
            }
            long num = idx - total + number[left] * number[nodeNum - 1 - left];
            long q = (num - 1) / number[nodeNum - 1 - left] + 1;
            long r = num % number[nodeNum - 1 - left];
            if (r == 0) r = number[nodeNum - 1 - left];
            if (left > 0) {
                System.out.print("(");
                print(q, left);
                System.out.print(")");
            }
            System.out.print("X");
            if (nodeNum - 1 - left > 0) {
                System.out.print("(");
                print(r, nodeNum - 1 - left);
                System.out.print(")");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Arrays.fill(number, -1);
        number[0] = 1;
        number[1] = 1;
        number[2] = 2;
        Arrays.fill(sum, 0);
        sum[0] = 1;
        sum[1] = 2;
        sum[2] = 4;
        int idx = scan.nextInt();
        while (idx > 0) {
            print(idx);
            idx = scan.nextInt();
        }
    }
}
