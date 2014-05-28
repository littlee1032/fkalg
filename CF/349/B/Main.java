import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int v = scan.nextInt();
        int[] ls = new int[9];
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < ls.length; i++) {
            ls[i] = scan.nextInt();
            if (min >= ls[i]) {
                min = ls[i];
                minIdx = i;
            }
        }

        if (min > v) {
            System.out.println(-1);
            return;
        }

        int n = v / min;
        int r = v % min;
        while (r != 0 && n > 0) {
            boolean bigger = false;
            for (int i = ls.length - 1; i > minIdx; i--) {
                if (ls[i] <= r + ls[minIdx]) {
                    System.out.print(i + 1);
                    r -= ls[i] - ls[minIdx];
                    bigger = true;
                    break;
                }
            }
            if (!bigger) break;
            else {
                n--;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(minIdx + 1);
        }
        System.out.println();
    }
}
