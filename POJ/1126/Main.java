import java.util.Scanner;

public class Main {
    public static final boolean DEBUG = true;
    public static final int NMAX = 256;
    public static final int[] stack = new int[NMAX];

    public static boolean isNative(char c) {
        return (c >= 'p' && c <= 'z');
    }

    public static boolean isPrefix(char c) {
        return c == 'N';
    }

    public static boolean needTwo(char c) {
        return c == 'C' || c == 'D' || c == 'E' || c == 'I';
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String l = scan.nextLine().trim();
            int pos = 0;
            stack[pos] = 1;
            boolean yes = true;
            for (int i = 0; i < l.length(); i++) {
                char c = l.charAt(i);
                if (isNative(c)) {
                    stack[pos]--;
                    if (stack[pos] < 0) {
                        yes = false;
                        break;
                    }
                } else if (isPrefix(c)) {
                    stack[++pos] = 1;
                } else if (needTwo(c)) {
                    stack[++pos] = 2;
                } else {
                    yes = false;
                    break;
                }

                while (pos >= 1 && stack[pos] == 0) {
                    stack[--pos]--;
                }
            }

            if (yes && pos == 0 && stack[pos] == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
