import java.util.Scanner;
import java.util.Comparator;

public class Main {
    private static int cmp(char c1, char c2) {
        int offset1 = c1 >= 'a' ? c1 - 'a' : c1 - 'A';
        int offset2 = c2 >= 'a' ? c2 - 'a' : c2 - 'A';
        if (offset1 == offset2) return c1 - c2;
        else return offset1 - offset2;
    }

    private static boolean nextPerm(char[] cs) {
        int size = cs.length;
        int k = size - 1;
        for (; k >= 1; k--) {
            if (cmp(cs[k-1], cs[k]) < 0) break;
        }
        if (k == 0) return false;
        int l = size - 1;
        char cmp = cs[k - 1];
        for (; l > k; l--) {
            if (cmp(cmp, cs[l]) < 0) break;
        }
        cs[k - 1] = cs[l];
        cs[l] = cmp;
        for (int i = k, j = size - 1; i < j; i++, j--) {
            cmp = cs[i];
            cs[i] = cs[j];
            cs[j] = cmp;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            char[] cs = scan.nextLine().toCharArray();
            for (int j = 0; j < cs.length; j++) {
                for (int k = j; k < cs.length; k++) {
                    if (cmp(cs[j], cs[k]) > 0) {
                        char tmp = cs[j];
                        cs[j] = cs[k];
                        cs[k] = tmp;
                    }
                }
            }
            do {
                for (int j = 0; j < cs.length; j++) System.out.print(cs[j]);
                System.out.println();
            } while(nextPerm(cs));
        }
    }
}
