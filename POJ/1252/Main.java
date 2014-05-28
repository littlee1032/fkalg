import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    private static final int[] currencies = new int[12];
    private static final int MAX_TRY = 2001;
    private static final int[] trades = new int[MAX_TRY];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < MAX_TRY; j++) trades[j] = j;
            for (int j = 0; j < 6; j++) {
                int c = scan.nextInt();
                currencies[j] = c;
                currencies[j+6] = -c;
            }
            Queue<Integer> cands = new LinkedList<Integer>();
            cands.offer(0);
            while (!cands.isEmpty()) {
                int c = cands.poll();
                for (int j = 0; j < 12; j++) {
                    int nc = currencies[j] + c;
                    if (nc > 0 && nc < MAX_TRY && trades[nc] > trades[c] + 1) {
                        trades[nc] = trades[c] + 1;
                        cands.offer(nc);
                    }
                }
            }
            int max = 0;
            int sum = 0;
            for (int j = 1; j <= 100; j++) {
                sum += trades[j];
                max = Math.max(max, trades[j]);
            }
            System.out.printf("%.2f %d\n", sum / 100.0, max);
        }
    }
}
