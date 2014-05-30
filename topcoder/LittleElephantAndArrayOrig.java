import java.util.Arrays;


public class LittleElephantAndArrayOrig {
    private static long MOD = 1000000007L;
    
    public boolean nextPermutation(int[] cs) {
        int size = cs.length;
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cs[k - 1] < cs[k]) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        int cmpValue = cs[k - 1];
        for (; l > k; l--) {
            if (cs[l] < cmpValue) break;
        }
        cs[k - 1] = cs[l];
        cs[l] = cmpValue;
        for (int i = k, j = size - 1; i < j; i++, j--) {
            int tmp = cs[i];
            cs[i] = cs[j];
            cs[j] = tmp;
        }
        return true;
    }
    
    public int getNumber(long a, int n) {
        long[] nums = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            nums[i] = a + i;
        }
        
        int dn = 0;
        long maxN = nums[n];
        while (maxN > 0) {
            dn++;
            maxN /= 10;
        }
        int lessDigitCount = 0;
        for (int i = 0; i <= n; i++) {
            int dnn = 0;
            long nn = nums[i];
            while (nn > 0) {
                dnn++;
                nn /= 10;
            }
            if (dnn < dn) lessDigitCount++;
        }
        if (lessDigitCount != 0) return getNumber(a, lessDigitCount - 1);
        
        int[] masks = new int[dn];
        for (int i = 0; i < dn; i++) {
            masks[i] = 1 << i;
        }
        
        int[][] c = new int[dn + 1][dn + 1];
        for (int i = 0; i <= dn; i++) {
            c[i][0] = 1;
            c[i][i] = 1;
        }
        for (int i = 1; i <= dn; i++) {
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        
//        for (int i = 0; i <= dn; i++) {
//            for (int j = 0; j <= i; j++) {
//                System.out.print(c[i][j] + ",");
//            }
//            System.out.println();
//        }
//        System.out.println();
        
        int[][] perms = new int[dn][dn];
        for (int i = 0; i < dn; i++) {
            int zeros = dn - i;
            for (int j = 0; j < dn; j++) {
                if (zeros-- > 0)
                    perms[i][j] = 0;
                else
                    perms[i][j] = 1;
            }
        }
        int[] mBits = new int[dn];
        int[] yBits = new int[dn];

        int maxMask = (2 << dn) - 1;
        long[] total = new long[dn];
        total[0] = 1;
        
        long[][] dp = new long[n + 1][2 << dn];
        dp[0][0] = 1;
        if (dn > 1) {
            Arrays.fill(dp[n], 1);
        }
        Arrays.fill(dp[n], 1);

        for (int i = n - 1; i >= 0; i--) {
//            System.out.println("total: ");
//            for (int iii = 0; iii < dn; iii++) {
//                System.out.print(total[iii] + ",");
//            }
//            System.out.println();
            for (int j = 0; j < dn; j++) {
                System.arraycopy(perms[j], 0, mBits, 0, dn);                
                do {
                    System.arraycopy(perms[j], 0, yBits, 0, dn);
                    do {
                        int mMask = genMask(mBits, masks);
                        int yMask = genMask(yBits, masks);
                        long mNum = gen(nums[i], mMask, masks, dn);
                        long yNum = gen(nums[i + 1], yMask, masks, dn);
//                        System.out.println(i+","+j+","+mNum+","+yNum);
                        if (mNum <= yNum) {
                            dp[i][mMask] += dp[i + 1][yMask];
                            dp[i][mMask] %= MOD;
                            total[j] += dp[i + 1][yMask];
                            total[j] %= MOD;
                        }
                    } while (nextPermutation(yBits));
                } while (nextPermutation(mBits)); 
            }
        }
        
//        for (int i = 0; i <= n; i++) {
//            System.out.print(nums[i] + "\t");
//            for (int j = 0; j <= maxMask; j++) {
//                System.out.print(dp[i][j] + "\t");
//            }
//            System.out.println();
//        }
        int ret = 0;
        for (int i = 0; i < dn; i++) {
            ret += total[i];
            ret %= MOD;
        }
        return ret;
    }
    
    public long gen(long num, int mask, int[] masks, int n) {
        long ret = 0;
        long mul = 1;
        for (int i = 0; i < n; i++) {
            if ((mask & masks[i]) == 0) {
                ret += (num % 10) * mul;
                mul *= 10;
            }
            num /= 10;
        }
        return ret;
    }
    
    public int genMask(int[] bits, int[] masks) {
        int ret = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                ret |= masks[bits.length - 1 - i];
            }
        }
        return ret;
    }
    
    public static void main(String[] args) {
        long[] as = new long[] {1, 10, 4747774, 6878542150015L};
        int[] ns = new int[] {9, 2, 1, 74};
        LittleElephantAndArrayOrig test = new LittleElephantAndArrayOrig();
        for (int i = 0; i < 4; i++) {
            System.out.println(test.getNumber(as[i], ns[i]));
        }
    }
}
