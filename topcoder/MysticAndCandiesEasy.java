import java.util.Arrays;

public class MysticAndCandiesEasy {
    public int minBoxes(int c, int x, int[] high) {
        if (c == x) return high.length;
        
        int left = c - x;
        Arrays.sort(high);
        for (int i = 0; i < high.length; i++) {
            left -= high[i];
            if (left < 0) return high.length - i;
        }
        
        return high.length;
    }
}
