
public class ErasingCharacters {
    public String simulate(String s) {
        boolean found = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                s = s.substring(0, i) + s.substring(i + 2);
                found = true;
                break;
            }
        }
        if (found) return simulate(s);
        else return s;
    }
}
