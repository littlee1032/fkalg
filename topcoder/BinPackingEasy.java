public class BinPackingEasy {
    private static long[] masks;
    
    static {
        masks = new long[50];
        for (int i = 0; i < 50; i++) {
            masks[i] = 1L << i;
        }
    }
    
    public int minBins(int[] item) {
        int n = item.length;
        boolean[] vis = new boolean[n];
        
        int ret = 0;
        while (n > 0) {
            int binNum = tryOneBin(item, vis);
            n -= binNum;
            ret++;
        }
        
        return ret;
    }
    
    private int tryOneBin(int[] item, boolean[] vis) {
        long[] dp = new long[301];
        
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                int w = item[i];
                for (int j = 0; j < 300; j++) {
                    if (dp[j] != 0 && j + w <= 300 &&
                            dp[j + w] == 0) {
                       dp[j + w] = dp[j] | masks[i];
                    }
                }
                if (dp[0] == 0 && dp[w] == 0) dp[w] = masks[i];
            }
        }
        
        for (int i = 300; i >= 0; i--) {
            if (dp[i] > 0) {
                int ret = 0;
                for (int j = 0; j < vis.length; j++) {
                    if ((dp[i] & masks[j]) > 0) {
                        ret++;
                        vis[j] = true;
                    }
                }
                return ret;
            }
        }
        return 0;
    }
}
