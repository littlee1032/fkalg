import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final double ep = 0.0001;
    private static final double INF = 100000000;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n1 = scan.nextInt();
        while (n1 >= 0) {
            int n2 = scan.nextInt();
            int n3 = scan.nextInt();
            int n4 = scan.nextInt();
            int target = scan.nextInt();

            int[] cs = new int[]{n1, n2, n3, n4};

            boolean found = false;
            do {
                found = solve(cs, target);
            } while (!found && nextPermutation(cs));

            System.out.print(n1 + " " + n2 + " " + n3 + " " + n4 + " " + target + " ");
            if (found) System.out.println("OK!");
            else System.out.println("NO!");

            n1 = scan.nextInt();
        }
    }

    private static boolean solve(int[] cs, int target) {
        int[] ops = new int[3];
        for (int i = 0; i < 4; i++) {
            ops[0] = i;
            for (int j = 0; j < 4; j++) {
                ops[1] = j;
                for (int k = 0; k < 4; k++) {
                    ops[2] = k;
                    if (solve(ops, cs, target)) return true;
                }
            }
        }
        return false;
    }

    private static double calc(int op, double a, double b) {
        if (a == INF || b == INF) return INF;
        switch(op) {
        case 0: return a + b;
        case 1: return a - b;
        case 2: return a * b;
        default:
            if (b != 0) return a / b;
            else return INF;
        }
    }

    private static boolean equal(double a, double b) {
        return Math.abs(a - b) < ep;
    }

    private static boolean solve(int[] ops, int[] cs, int target) {
        // a (b (c d))
        if (equal(calc(ops[0], cs[0], calc(ops[1], cs[1], calc(ops[2], cs[2], cs[3]))), target)) return true;
        // a ((b c) d)
        if (equal(calc(ops[0], cs[0], calc(ops[2], calc(ops[1], cs[1], cs[2]), cs[3])), target)) return true;
        // (a (b c)) d
        if (equal(calc(ops[2], calc(ops[0], cs[0], calc(ops[1], cs[1], cs[2])), cs[3]), target)) return true;
        // ((a b) c) d
        if (equal(calc(ops[2], calc(ops[1], calc(ops[0], cs[0], cs[1]), cs[2]), cs[3]), target)) return true;
        // (a b) (c d)
        if (equal(calc(ops[1], calc(ops[0], cs[0], cs[1]), calc(ops[2], cs[2], cs[3])), target)) return true;

        return false;
    }

    private static boolean nextPermutation(int[] cs) {
        int size = cs.length;
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cs[k - 1] < cs[k]) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        int cmpValue = cs[k - 1];
        for (; l > k; l--) {
            if (cs[l] > cmpValue) break;
        }
        cs[k - 1] = cs[l];
        cs[l] = cmpValue;
        for (int i = k, j = size - 1; i < j; i++, j--) {
            int tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
        }
        return true;
    }
}
