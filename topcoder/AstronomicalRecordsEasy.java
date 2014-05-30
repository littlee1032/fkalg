
public class AstronomicalRecordsEasy {
    private int gcd(int a, int b) {
        if (b == 1) return 1;
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
    
    private int cal(int[] as, int aIdx, int[] bs, int bIdx) {
        int a = as[aIdx];
        int b = bs[bIdx];
        int lcm = lcm(a, b);
        int am = lcm / a;
        int bm = lcm / b;
        
        int aCur = 0;
        int bCur = 0;
        int ret = 0;

        while (aCur < as.length && bCur < bs.length) {
            int aCmp = as[aCur] * am;
            int bCmp = bs[bCur] * bm;
            ret++;
            if (aCmp == bCmp) {
                aCur++;
                bCur++;
            } else if (aCmp < bCmp) {
                aCur++;
            } else {
                bCur++;
            }
        }
        
        if (aCur < as.length) ret += as.length - aCur;
        else if (bCur < bs.length) ret += bs.length - bCur;
        
        return ret;
    }
    
    public int minimalPlanets(int[] as, int[] bs) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < as.length; i++) {
            for (int j = 0; j < bs.length; j++) {
                min = Math.min(min, cal(as, i, bs, j));
            }
        }
        return min;
    }
}
