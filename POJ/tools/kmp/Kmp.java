public class Kmp {
    public static int index(String src, String pattern) {
        int[] fail = new int[pattern.length()];
        for (int i = pattern.length(); i > 0; i--) {
            for (int j = i - 1; j > 0; j--) {
                String substr = pattern.substring(j, i);
                if (pattern.startsWith(substr)) fail[i - 1] = i - j;
            }
        }

        for (int i = 0, j = 0; i < src.length() && j < pattern.length();) {
            if (src.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    return i - j;
                }
            } else {
                j = fail[j];
                if (j == 0) i++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String test1 = "abcba";
        String test2 = "ab";
        System.out.println(Kmp.index(test1, test2));
        String test3 = "cdadbab";
        System.out.println(Kmp.index(test3, test2));
    }
}
