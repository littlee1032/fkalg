import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Set<String> filter = new HashSet<String>();
        while (scan.hasNext()) {
            filter.clear();
            int n = scan.nextInt();
            int nc = scan.nextInt();
            scan.nextLine();
            String str = scan.nextLine();
            int cnt = 0;
            for (int i = 0; i + n <= str.length(); i++) {
                String subStr = str.substring(i, i+n);
                if (!filter.contains(subStr)) {
                    cnt++;
                    filter.add(subStr);
                }
            }
            System.out.println(cnt);
        }
    }
}
