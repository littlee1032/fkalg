
public class ShoutterDiv2 {
    public int count(int[] s, int[] t) {
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = i + 1; j < s.length; j++) {
                int min = Math.min(s[i], s[j]);
                int max = Math.max(t[i], t[j]);
                if (max - min <= (t[i] - s[i] + t[j] - s[j])) {
                    count++;
                }
            }
        }
        return count;
    }
}
