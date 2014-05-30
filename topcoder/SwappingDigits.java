
public class SwappingDigits {
    public String minNumber(String num) {
        if (num.length() == 1) return num;
        String min = num;
        int len = num.length();
        for (int i = 0; i < len; i++) {
            char c1 = num.charAt(i);
            for (int j = i; j < len; j++) {
                char c2 = num.charAt(j);
                if (i == 0 && c2 == '0') continue;
                if (c2 >= c1) continue;
                String newString = num.substring(0, i) + c2 + num.substring(i + 1, j) + c1 + num.substring(j + 1);
                if (newString.compareTo(min) < 0) min = newString;
            }
        }
        return min;
    }
}
