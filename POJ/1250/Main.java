import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean[] vis = new boolean[26];
        boolean[] bye = new boolean[26];
        String str = scan.nextLine();
        while ('0' != str.charAt(0)) {
            String[] ars = str.split(" ");
            int n = Integer.valueOf(ars[0]);
            String customers = ars[1];
            Arrays.fill(bye, false);
            Arrays.fill(vis, false);
            int left = 0;
            for (int i = 0; i < customers.length(); i++) {
                char c = customers.charAt(i);
                if (!vis[c - 'A']) {
                    if (n > 0) {
                        vis[c - 'A'] = true;
                        bye[c - 'A'] = false;
                        n--;
                    } else {
                        if (!bye[c - 'A']) {
                            left++;
                            bye[c - 'A'] = true;
                        } else {
                            bye[c - 'A'] = false;
                        }
                    }
                } else {
                    vis[c - 'A'] = false;
                    n++;
                }
            }
            if (left == 0) {
                System.out.println("All customers tanned successfully.");
            } else {
                System.out.println(left + " customer(s) walked away.");
            }
            str = scan.nextLine();
        }
    }
}
