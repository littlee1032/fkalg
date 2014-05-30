
public class DeerInZooDivTwo {
    public int[] getminmax(int N, int K) {
        int min = Integer.MAX_VALUE;
        int max = N - (K / 2 + K % 2);
        if (K >= N) {
            min = 0;
        } else {
            min = N - K;
        }
        int[] ret = new int[2];
        ret[0] = min;
        ret[1] = max;
        return ret;
    }

}
