import java.util.Scanner;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        scan.nextLine();
        while (T-- > 0) {
            String line = scan.nextLine();
            String[] tmp = line.split(" ");
            int month = Integer.valueOf(tmp[1]);
            int day = Integer.valueOf(tmp[2]);
            if ((month == 9 && day == 30) || (month == 11 && day == 30)) {
                System.out.println("YES");
            } else if ((month + day) % 2 == 1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
