import java.util.Map;
import java.util.TreeMap;

public class LittleElephantAndArray {
    private static long MOD = 1000000007L;
    private static int MAXLEN = 16;

    public int getNumber(long a, int n) {
        int[] masks = new int[MAXLEN + 1];
        for (int i = 0; i <= MAXLEN; i++) {
            masks[i] = 1 << i;
        }
        Map<Long, Long> occ = new TreeMap<Long, Long>();
        Map<Long, Long> nOcc = new TreeMap<Long, Long>();
        occ.put(-1L, 1L);
        for (int i = 0; i <= n; i++) {
            nOcc.clear();
            long num = a + i;
            int dn = String.valueOf(num).length();
            for (int j = 0; j < (1 << dn) - 1; j++) {
                long nNum = 0;
                long multi = 1;
                long mN = num;
                for (int k = 0; k < dn; k++) {
                    if ((j & masks[k]) == 0) {
                        nNum += (mN % 10) * multi;
                        multi *= 10;
                    }
                    mN /= 10;
                }
                long lastOcc = 0;
                if (nOcc.containsKey(nNum)) {
                    lastOcc = nOcc.get(nNum);
                }
                nOcc.put(nNum, ++lastOcc);
            }
            long[][] sums = new long[occ.size()][2];
            int idx = 0;
            long sum = 0;
            for (Long key : occ.keySet()) {
                sum += occ.get(key);
                sum %= MOD;
                sums[idx][0] = key;
                sums[idx][1] = sum;
                idx++;
            }
            idx = 0;
            for (Long key : nOcc.keySet()) {
                while (idx < occ.size() && sums[idx][0] <= key) idx++;
                idx--;
                long value = nOcc.get(key);
                if (idx >= 0) {
                    value *= sums[idx][1];
                    value %= MOD;
                    nOcc.put(key, value);
                } else {
                    nOcc.put(key, 0L);
                    idx = 0;
                }
                
            }
            occ.clear();
            for (Long key : nOcc.keySet()) {
                if (nOcc.get(key) != 0) {
                    occ.put(key, nOcc.get(key));
                }
            }
        }

        int ret = 0;
        for (Long key : nOcc.keySet()) {
            ret += nOcc.get(key);
            ret %= MOD;
        }
        return ret;
    }

    public static void main(String[] args) {
        long[] as = new long[] { 1, 10, 4747774, 6878542150015L, 650286024865090L };
        int[] ns = new int[] { 9, 2, 1, 74, 100 };
        LittleElephantAndArray test = new LittleElephantAndArray();
        for (int i = 0; i < 5; i++) {
            System.out.println(test.getNumber(as[i], ns[i]));
        }
    }
}
