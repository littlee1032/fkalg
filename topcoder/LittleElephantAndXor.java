import java.util.HashMap;
import java.util.Map;

public class LittleElephantAndXor {
    private static int[] ts = new int[31];

    private Map<String, Long> cache = new HashMap<String, Long>();

    static {
        for (int i = 0; i < 31; i++) {
            ts[i] = 1 << i;
        }
    }

    private int getLen(int n) {
        for (int i = 30; i >= 0; i--) {
            if (ts[i] <= n)
                return i;
        }
        return 0;
    }

    public long getNumber(int a, int b, int c) {
        String key = a + ", " + b + ", " + c;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (a == 0 && b == 0)
            return 1L;
        if (c == 1) {
            if (a == 1 && b == 0)
                return 2L;
            if (a == 0 && b == 1)
                return 2L;
            if (a == 1 && b == 1)
                return 4L;
        }
        if (c == 0) {
            if (a == 1 && b == 0)
                return 1L;
            if (a == 0 && b == 1)
                return 1L;
            if (a == 1 && b == 1)
                return 2L;
        }

        int aLen = getLen(a);
        int bLen = getLen(b);
        int cLen = getLen(c);

        int maxLen = Math.max(aLen, Math.max(bLen, cLen));
        int af = (maxLen > aLen) ? 0 : 1;
        int bf = (maxLen > bLen) ? 0 : 1;
        int cf = (maxLen > cLen) ? 0 : 1;

        if (cf == 0) {
            if (af != bf) {
                if (af == 1) {
                    long ret = getNumber(ts[maxLen] - 1, b, c);
                    cache.put(key, ret);
                    return ret;
                } else {
                    long ret = getNumber(a, ts[maxLen] - 1, c);
                    cache.put(key, ret);
                    return ret;
                }
            } else {
                /* af = bf = 1 */
                long ret = getNumber(a - ts[maxLen], b - ts[maxLen], c) + getNumber(ts[maxLen] - 1, ts[maxLen] - 1, c);
                cache.put(key, ret);
                return ret;
            }
        } else {
            if (af == 0 && bf == 0) {
                long ret = (a + 1L) * (b + 1L);
                cache.put(key, ret);
                return ret;
            } else if (af == 1 && bf == 1) {
                long ret = (a - ts[maxLen] + 1L) * (b - ts[maxLen] + 1L)
                        + getNumber(ts[maxLen] - 1, b - ts[maxLen], c - ts[maxLen])
                        + getNumber(a - ts[maxLen], ts[maxLen] - 1, c - ts[maxLen]) + (ts[maxLen] * 1L) * (ts[maxLen]);
                cache.put(key, ret);
                return ret;
            } else if (af == 1 && bf == 0) {
                long ret = ts[maxLen] * (b + 1L) + getNumber(a - ts[maxLen], b, c - ts[maxLen]);
                cache.put(key, ret);
                return ret;
            } else /* if (af == 0 && bf == 1) */{
                long ret = (a + 1L) * ts[maxLen] + getNumber(a, b - ts[maxLen], c - ts[maxLen]);
                cache.put(key, ret);
                return ret;
            }
        }
    }
}
