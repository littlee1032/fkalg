
public class GooseTattarrattatDiv2 {
    public int getmin(String S) {
        int len = S.length();
        int[] num = new int[26];
        for (int i = 0; i < len; i++) {
            num[S.charAt(i) - 'a']++;
        }
        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, num[i]);
        }
        return len - max;
    }
}
