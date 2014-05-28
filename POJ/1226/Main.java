import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int m = scan.nextInt();
            scan.nextLine();
            List<String> strings = new ArrayList<String>();
            for (int j = 0; j < m; j++) {
                strings.add(scan.nextLine());
            }
            solve(strings);
        }
    }

    private static void solve(List<String> strings) {
        String sMinLen = strings.get(0);
        for (int i = 1; i < strings.size(); i++) {
            String cmp = strings.get(i);
            if (cmp.length() < sMinLen.length()) {
                sMinLen = cmp;
            }
        }

        StringBuilder sb = new StringBuilder(sMinLen);
        StringBuilder rsb = new StringBuilder(sMinLen).reverse();
        int minLen = sMinLen.length();
        for (int len = minLen; len > 0; len--) {
            for (int i = 0; i + len <= minLen; i++) {
                String cmp1 = sb.substring(i, i + len);
                String cmp2 = rsb.substring(minLen - i - len, minLen - i);
                boolean ok = true;
                for (int j = 0; j < strings.size(); j++) {
                    String s = strings.get(j);
                    if (!s.contains(cmp1) && !s.contains(cmp2)) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    System.out.println(len);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}
