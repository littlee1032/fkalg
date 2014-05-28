import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static final Set<Integer> vs = new HashSet<Integer>();
    private static final String OK_FORMAT = "Case %d is a tree.";
    private static final String NOK_FORMAT = "Case %d is not a tree.";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int s = scan.nextInt();
        int e = scan.nextInt();
        int edges = 0;

        int testIdx = 0;
        while (s + e >= 0) {
            if (s + e ==  0) {
                testIdx++;
                if (edges == 0) {
                    System.out.println(String.format(OK_FORMAT, testIdx));
                } else {
                    if (vs.size() == edges + 1) {
                        System.out.println(String.format(OK_FORMAT, testIdx));
                    } else {
                        System.out.println(String.format(NOK_FORMAT, testIdx));
                    }
                }
                vs.clear();
                edges = 0;
            } else {
                edges++;
                vs.add(s);
                vs.add(e);
            }
            s = scan.nextInt();
            e = scan.nextInt();
        }
    }
}
