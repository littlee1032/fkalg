
public class TypoCoderDiv2 {
    public int count(int[] rating) {
        int bar = 1200;
        int pre = 500;
        int count = 0;
        for (int i = 0; i < rating.length; i++) {
            int cur = rating[i];
            if ((pre >= bar && cur < bar) ||
                    (pre < bar && cur >= bar)) count++;
            pre = cur;
        }
        return count;
    }
}
