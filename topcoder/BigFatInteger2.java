import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BigFatInteger2 {
    private static final String OK = "divisible";
    private static final String NOK = "not divisible";

    private List<long[]> getFactors(int a) {
        List<long[]> ret = new ArrayList<long[]>();
        int limit = (int) Math.ceil(Math.sqrt(a));
        for (int i = 2; i <= limit; i++) {
            long[] cur = new long[2];
            while (a % i == 0) {
                cur[0] = i;
                cur[1]++;
                a /= i;
            }
            if (cur[0] != 0) {
                ret.add(cur);
            }
            if (a == 1)
                break;
        }
        if (a != 1) {
            ret.add(new long[] { a, 1 });
        }
        return ret;
    }

    private long gcd(long a, long b) {
        if (a % b == 0)
            return b;
        if (a < b)
            return gcd(b, a);
        return gcd(b, a % b);
    }

    private void print(List<long[]> array) {
        for (long[] cur : array) {
            System.out.print("(");
            System.out.print(Arrays.toString(cur));
            System.out.print(")");
        }
        System.out.println();
    }

    public String isDivisible(int a, int b, int c, int d) {
        if (c == 1)
            return OK;
        List<long[]> aFac = getFactors(a);
         print(aFac);
        List<long[]> cFac = getFactors(c);
         print(cFac);

        long gcd = gcd(b, d);
        b /= gcd;
        d /= gcd;

//        int aIdx = 0;
//        int cIdx = 0;

        int found = 0;
        for (long[] cfactor : cFac) {
            for (long[] afactor : aFac) {
                if (cfactor[0] == afactor[0]) {
                    if (cfactor[1]*d <= afactor[1]*b)
                        found++;
                }
            }
        }

        if (found == cFac.size())
            return OK;
        else
            return NOK;

        /*
         * for (cIdx = 0; cIdx < cFac.size();) { long[] cur = cFac.get(cIdx); if
         * (aIdx == aFac.size()) return NOK; while (aIdx < aFac.size()) { long[]
         * aCur = aFac.get(aIdx); if (aCur[0] < cur[0]) { aIdx++; continue; }
         * else if (aCur[0] > cur[0]) { return NOK; } else { if (aCur[1] * b <
         * cur[1] * d) { return NOK; } else { aIdx++; cIdx++; break; } } } }
         * return cIdx == cFac.size() ? OK : NOK;
         */
    }

     public static void main(String[] args) {
      int[] test = new int[]{1000000000, 1000000000, 536870912, 310344827};
     // int[] test = new int[]{999999937, 1000000000, 999999929, 1};
     // int[] test = new int[]{2, 1, 6, 1};
     // int[] test = new int[]{1000000000, 1000000000, 499999999, 1};
//     int[] test = new int[]{735134400, 1000000000, 38, 1};
     System.out.println(new BigFatInteger2().isDivisible(test[0], test[1],
     test[2], test[3]));
     }
}
