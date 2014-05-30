public class AlienAndPassword {
    public int getNumber(String s) {
        int len = s.length();
        int ret = 0;
        for (int i = 0; i < len;) {
            char current = s.charAt(i);
            for (int j = i+1; j < len; j++) {                
                if (current != s.charAt(j)) break;
                i++;
            }
            i++;
            ret++;
        }
        return ret;
    }
}
