import java.util.HashSet;
import java.util.Set;


public class LittleElephantAndIntervalsDiv2 {
    public int getNumber(int m, int[] l, int[] r) {
        Set<Integer> dominates = new HashSet<Integer>();
        int round = l.length;
        for (int i = 1; i <= m ; i++) {
            for (int j = round - 1; j >= 0; j--) {
                if (l[j] <= i && r[j] >= i) {
                    dominates.add(j);
                    break;
                }
            }
        }
        
        int ret = 1;
        int size = dominates.size();
        while (size-- > 0) {
            ret *= 2;
        }
        
        return ret;
    }
}
