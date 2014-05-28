import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();
        while (!"#".equals(line)) {
            char[] cs = line.toCharArray();
            int idx = -1;
            for (int i = cs.length - 1; i >= 1; i--) {
                if (cs[i] > cs[i - 1]) {
                    idx = i - 1;
                    break;
                }
            }
            if (idx == -1) {
                System.out.println("No Successor");
            } else {
                // find the closest bigger
                int swapIdx = idx + 1;
                for (int i = cs.length - 1; i > idx; i--) {
                    if (cs[i] > cs[idx] && cs[i] < cs[swapIdx]) {
                        swapIdx = i;
                    }
                }
                char tmp = cs[idx];
                cs[idx] = cs[swapIdx];
                cs[swapIdx] = tmp;
                Arrays.sort(cs, idx + 1, cs.length);
                for (int i = 0; i < cs.length; i++) {
                    System.out.print(cs[i]);
                }
                System.out.println();
            }
            line = scan.nextLine().trim();
        }
    }
}
