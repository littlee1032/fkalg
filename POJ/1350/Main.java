import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static final int[] digits = new int[4];
    private static List<Integer> steps = new LinkedList<Integer>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n >= 0) {
            solve(n);
            n = scan.nextInt();
        }
    }

    private static int getNum(int[] arr, int s, int e, int diff) {
        int ret = 0;
        for (int i = s; i != e; i += diff) {
            if (arr[i] == -1) continue;
            ret *= 10;
            ret += arr[i];
        }
        return ret;
    }

    private static void genArray(int n) {
        for (int i = 0; i < 4; i++) {
            digits[i] = n == 0 ? -1 : n % 10;
            n = n / 10;
        }
        Arrays.sort(digits);
    }

    private static void solve(int n) {
        System.out.println("N=" + n + ":");
        if (n > 9999 || n < 1000) {
            System.out.println("No!!");
            return;
        }
        genArray(n);
        int small = getNum(digits, 0, 4, 1);
        int large = getNum(digits, 3, -1, -1);
        if (small == large) {
            System.out.println("No!!");
        } else {
            steps.clear();
            int nn = large - small;
            steps.add(large);
            steps.add(small);
            while (nn != 0 && nn != 6174) {
                genArray(nn);
                small = getNum(digits, 0, 4, 1);
                large = getNum(digits, 3, -1, -1);
                nn = large - small;
                steps.add(large);
                steps.add(small);
            }

            int stepNum = steps.size() / 2;
            for (int i = 0; i < steps.size(); i += 2) {
                large = steps.get(i);
                small = steps.get(i + 1);
                System.out.println(large + "-" + small + "=" + (large - small));
            }
            System.out.println("Ok!!" + " " + stepNum + " times");
        }
    }
}
