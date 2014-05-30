import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class LittleElephantAndPermutationDiv2 {
    public boolean nextPermutation(List<Integer> cs) {
        int size = cs.size();
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cs.get(k - 1).compareTo(cs.get(k)) < 0) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        Integer cmpValue = cs.get(k - 1);
        for (; l >= k; l--) {
            if (cs.get(l).compareTo(cmpValue) > 0) break;
        }
        cs.set(k - 1, cs.get(l));
        cs.set(l,  cmpValue);
        Collections.reverse(cs.subList(k, size));
        return true;
    }

    public long getNumber(int n, int k) {
        int[] fac = new int[11];
        fac[0] = 1;
        for (int i = 1; i < 11; i++) {
            fac[i] = fac[i - 1] * i;
        }
        
        List<Integer> z = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) z.add(i);
        
        List<Integer> cmp = new LinkedList<Integer>(z);
        long ret = 0;
        do {
            int magic = 0;
            for (int i = 0; i < n; i++) {
                magic += Math.max(z.get(i), cmp.get(i));
            }
            if (magic >= k) ret++;
        } while (nextPermutation(cmp));
        
        return ret * fac[n];
    }
}
