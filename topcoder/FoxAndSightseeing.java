public class FoxAndSightseeing {
    public int getMin(int[] position) {
        int n = position.length;
        int[] dis = new int[n - 1];
        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            dis[i] = Math.abs(position[i] - position[i + 1]);
            total += dis[i];
        }

        int ret = total;
        for (int i = 1; i < n - 1; i++) {
            ret = Math.min(ret, total - dis[i - 1] - dis[i] + Math.abs(position[i - 1] - position[i + 1]));
        }
        
        return ret;
    }
}
