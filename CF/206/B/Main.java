import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int c1 = scan.nextInt();
        int c2 = scan.nextInt();
        int c3 = scan.nextInt();
        int c4 = scan.nextInt();

        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] bus = new int[n];
        for (int i = 0; i < n; i++) {
            bus[i] = scan.nextInt();
        }
        int[] trolley = new int[m];
        for (int i = 0; i < m; i++) {
            trolley[i] = scan.nextInt();
        }

        int busTotal = 0;
        for (int i = 0; i < n; i++) {
            int tot = c1 * bus[i];
            tot = Math.min(tot, c2);
            busTotal += tot;
        }
        busTotal = Math.min(busTotal, c3);

        int trolleyTotal = 0;
        for (int i = 0; i < m; i++) {
            int tot = c1 * trolley[i];
            tot = Math.min(tot, c2);
            trolleyTotal += tot;
        }
        trolleyTotal = Math.min(trolleyTotal, c3);

        System.out.println(Math.min(busTotal + trolleyTotal, c4));
    }
}
