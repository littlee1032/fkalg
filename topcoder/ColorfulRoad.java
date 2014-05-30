import java.util.Arrays;


public class ColorfulRoad {
    public int getMin(String road) {
        int len = road.length();
        char[] rc = road.toCharArray();
        int[] cost = new int[len];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        for (int i = 0; i < len; i++) {
            if (cost[i] == Integer.MAX_VALUE) continue;
            char cur = rc[i];
            char exp = 'R';
            if ('R' == cur) {
                exp = 'G';
            } else if ('G' == cur) {
                exp = 'B';
            }
            for (int j = i + 1; j < len; j++) {
                if (rc[j] == exp) {
                    cost[j] = Math.min(cost[j], cost[i] + (i-j) * (i-j));
                }
            }
        }
        
        if (cost[len - 1] == Integer.MAX_VALUE) return -1;
        else return cost[len - 1];
    }
}
