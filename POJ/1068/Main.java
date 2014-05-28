import java.util.Scanner;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = false;

    private static void print(boolean[] isRightP, int n) {
        for (int i = 1; i <= 2 * n; i++) {
            char a = isRightP[i] ? ')' : '(';
            System.out.print(a + " ");
        }

        System.out.println();
    }

    private static void printW(boolean[] isRightP, int n) {
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 1; i <= 2 * n; i++) {
            if (!isRightP[i]) {
                stack.push(i);
            } else {
                int l = stack.pop();
                System.out.print((i - l + 1) / 2 + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int nMax = 41;
        boolean[] isRightP = new boolean[nMax];
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t > 0) {
            t--;
            Arrays.fill(isRightP, false);
            int n = scan.nextInt();
            int idx = 1;
            int lastNum = 0;
            for (int i = 0; i < n; i++) {
                int leftNum = scan.nextInt();
                idx += leftNum - lastNum;
                lastNum = leftNum;
                isRightP[idx] = true;
                idx++;
            }
            if (DEBUG) print(isRightP, n);
            printW(isRightP, n);
        }
    }
}
