import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String OK = "YES";
    private static final String NOK = "NO";
    private static final int L = 0;
    private static final int U = 1;
    private static final int R = 2;
    private static final int D = 3;

    private static class Rect {
        private final int[] ords;

        public Rect(int[] ords) {
            this.ords = ords;
        }

        public boolean isOverlap(Rect o) {
            boolean isOverlap = true;
            for (int i = 0; i < 4; i++) {
                if (ords[i] != o.ords[i]) {
                    isOverlap = false;
                    break;
                }
            }
            return isOverlap;
        }

        @Override public String toString() {
            return String.format("L:%d, U:%d, R:%d, D:%d", ords[L], ords[U], ords[R], ords[D]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int k = scan.nextInt();
            int p = scan.nextInt();

            Rect[] rects = new Rect[k];
            for (int kk = 0; kk < k; kk++) {
                int left = Integer.MAX_VALUE;
                int right = 1;
                int upper = Integer.MAX_VALUE;
                int down = 1;
                for (int j = 0; j < p; j++) {
                    int x = scan.nextInt();
                    int y = scan.nextInt();
                    left = Math.min(left, x-1);
                    right = Math.max(right, x);
                    upper = Math.min(upper, y-1);
                    down = Math.max(down, y);
                }
                rects[kk] = new Rect(new int[]{left, upper, right, down});
            }

            for (int l = 0; l < k; l++) {
                Rect rect1 = rects[l];
                for (int m = 0; m < k; m++) {
                    if (l == m) continue;
                    Rect rect2 = rects[m];
                    if (rect1.ords[L] > rect2.ords[L] && rect1.ords[L] < rect2.ords[R])
                        rect1.ords[L] = rect2.ords[L];
                    if (rect1.ords[R] > rect2.ords[L] && rect1.ords[R] < rect2.ords[R])
                        rect1.ords[R] = rect2.ords[R];
                    if (rect1.ords[U] > rect2.ords[U] && rect1.ords[U] < rect2.ords[D])
                        rect1.ords[U] = rect2.ords[U];
                    if (rect1.ords[D] > rect2.ords[U] && rect1.ords[D] < rect2.ords[D])
                        rect1.ords[D] = rect2.ords[D];
                }
            }

            boolean ok = true;
            for (int l = 0; l < k && ok; l++) {
                Rect rect1 = rects[l];
                for (int m = l + 1; m < k; m++) {
                    Rect rect2 = rects[m];
                    //System.out.println("Comparing: " + rect1 + " to " + rect2);
                    if (rect1.isOverlap(rect2)) {
                        ok = false;
                        break;
                    }
                }
            }

            System.out.println(ok ? OK : NOK);
        }
    }
}
