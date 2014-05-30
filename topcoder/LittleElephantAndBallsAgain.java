
public class LittleElephantAndBallsAgain {
    public int getNumber(String s) {
        int len = s.length();
        if (len == 0) return 0;
        
        int r = 0;
        int g = 0;
        int b = 0;
        
        char c = s.charAt(0);
        if (c == 'R') r++;
        else if (c == 'G') g++;
        else b++;
        
        int max = 1;
        for (int i = 1; i < len; i++) {
            c = s.charAt(i);
            if (c == 'R') {
                if (r == 0) {
                    g = 0;
                    b = 0;
                }
                r++;
                max = Math.max(max, r);
            } else if (c == 'G') {
                if (g == 0) {
                    r = 0;
                    b = 0;
                }
                g++;
                max = Math.max(max, g);
            } else {
                if (b == 0) {
                    r = 0;
                    g = 0;
                }
                b++;
                max = Math.max(max, b);
            }
        }
        
        return len - max;
    }
}
