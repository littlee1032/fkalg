import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> strs = new ArrayList<String>();
        String str = scan.nextLine();
        char c = str.charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != c) {
                strs.add(sb.toString());
                sb.setLength(0);
                c = ch;
            }
            sb.append(ch);
        }
        if (sb.length() > 0) strs.add(sb.toString());

        int size = strs.size();
        for (int i = 0; i < size; i++) {
            String s = strs.get(i);
            if (s.length() >= 3) {
                strs.set(i, s.substring(0, 2));
            }
        }

        for (int i = 1; i < size; i++) {
            String pre = strs.get(i - 1);
            String cur = strs.get(i);
            if (pre.length() == 2 && cur.length() == 2) {
                strs.set(i, cur.substring(0, 1));
            }
        }

        for (String s : strs) {
            System.out.print(s);
        }
        System.out.println();
    }
}
