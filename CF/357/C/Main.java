import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] beat = new int[n+1];
        TreeSet<Integer> tree = new TreeSet<Integer>();
        for (int i = 1; i <= n; i++) {
            tree.add(i);
        }

        for (int i = 1; i <= m; i++) {
            int l = scan.nextInt() - 1;
            int r = scan.nextInt();
            int w = scan.nextInt();

            while (tree.higher(l) != null && tree.higher(l) <= r) {
                Integer counterpart = tree.higher(l);
                if (counterpart != w) {
                    beat[counterpart] = w;
                    tree.remove(counterpart);
                }
                l = counterpart;
            }
        }

        System.out.print(beat[1]);
        for (int i = 2; i <= n; i++) {
            System.out.print(" " + beat[i]);
        }
    }
}
