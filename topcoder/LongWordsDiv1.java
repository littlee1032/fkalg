
public class LongWordsDiv1 {
    private static final int MOD = 1000000007;
    private static final int NMAX = 5001;
    private static final long[] dp = new long[NMAX];
  
    public int count(int n) {
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = 1; j < i - 1; j++) {
                dp[i] += (dp[j] * dp[i - 1 - j]) % MOD;
                dp[i] %= MOD;
            }
        }
        long ret = dp[n];
        for (int i = 2; i <= n; i++) ret = (ret * i) % MOD;
        return (int)ret;
    }
}
