
public class WolfDelaymaster {
    private static final String INVALID = "INVALID";
    private static final String VALID = "VALID";
    private static final char[] words = new char[]{'w', 'o', 'l', 'f'};

    public String check(String str) {
        if ("".equals(str)) return VALID;
        int occ = consume('w', 0, str);
        if (occ == 0) return INVALID;
        int s = occ;
        for (int i = 1; i < words.length; i++) {
            int mOcc = consume(words[i], s, str);
            if (mOcc != occ) return INVALID;
            s += mOcc;
        }
        return check(str.substring(s));
    }
    
    public int consume(char w, int s, String str) {
        int ret = 0;
        for (int i = s; i < str.length(); i++) {
            if (str.charAt(i) == w) ret++;
            else break;
        }
        return ret;
    }
}
