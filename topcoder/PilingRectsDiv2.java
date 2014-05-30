
public class PilingRectsDiv2 {
    public int getmax(int[] xs, int[] ys, int limit) {
        int max = 0;
        for (int i = 1; i <= 200; i++) {
            for (int j = 1; j <= 200; j++) {
                if (i * j >= limit) {
                    int res = 0;
                    for (int k = 0; k < xs.length; k++) {
                        if ((xs[k] >= i && ys[k] >= j) || (xs[k] >= j && ys[k] >= i)) res++;
                    }
                    max = Math.max(max, res);
                }
            }
        }
        if (max == 0) return -1;
        else return max;
    }
}
