import java.util.Scanner;

public class Main {
    private static final boolean DEBUG = true;
    private static final int NMAX = 7;
    private static final int[] order = new int[NMAX];
    private static final boolean[] terminals = new boolean[1 << NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int counter = 0;
        while (n > 0) {
            counter++;
            scan.nextLine();
            String line = scan.nextLine().trim();
            int nIdx = 0;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c >= '1' && c <= '7') {
                    order[nIdx++] = c - '0';
                }
            }
            line = scan.nextLine().trim();
            for (int i = 0; i < (1 << n); i++) {
                char c = line.charAt(i);
                terminals[i] = (c == '0') ? false : true;
            }
            System.out.println("S-Tree #" + counter + ":");
            int m = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < m; i++) {
                int diff = 1 << (n - 1);
                int idx = 0;
                line = scan.nextLine().trim();
                for (int j = 0; j < n; j++) {
                    char c = line.charAt(order[j] - 1);
                    idx += (c == '0') ? 0 : diff;
                    diff /= 2;
                }
                System.out.print(terminals[idx] ? 1 : 0);
            }
            System.out.println();
            System.out.println();
            n = scan.nextInt();
        }
    }
}
