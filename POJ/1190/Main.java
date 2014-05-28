import java.util.Scanner;

public class Main {
    private static int min = Integer.MAX_VALUE;
    private static int[] mins = new int[21];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        for (int i = 1; i < 21; i++) mins[i] = i * i * i + mins[i-1];
        // compute max r
        int maxR = (int)Math.sqrt((n - mins[m-1]) / m);
        int minR = m;
        int minH = m;
        int maxH = n / (minR * minR);

        for (int i = maxR; i >= minR; i--) {
            for (int j = maxH; j >= minH; j--) {
                int volLeft = n - i*i*j;
                if (volLeft < 0) continue;
                int qNow = i * i + 2 * i * j;
                dfs(volLeft, qNow, m - 1, i, j);
            }
        }

        System.out.println(min);
    }

    private static void dfs(int volLeft, int qNow, int mLeft, int preR, int preH) {
        if (mLeft < 0) return;
        if (mins[mLeft] > volLeft) return;
        if (volLeft / preR * 2 + qNow >= min) return;
        if (volLeft == 0) {
            min = Math.min(min, qNow);
            return;
        }

        for (int cr = preR - 1; cr >= mLeft; cr--) {
            for (int ch = preH - 1; ch >= mLeft; ch--) {
                int cVolLeft = volLeft - cr * cr * ch;
                if (cVolLeft < 0) continue;
                int cQNow = qNow + 2 * cr * ch;
                if (cQNow >= min) continue;
                dfs(cVolLeft, cQNow, mLeft - 1, cr, ch);
            }
        }
    }
}
