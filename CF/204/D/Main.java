import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        String line = scan.nextLine().trim();
        String[] tmp = line.split(" ");
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.valueOf(tmp[i]);
        }

        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)
                if (p[i] > p[j]) c++;
        }

        if (c == 0) System.out.println(0);
        else {
            int ret = 0;
            while (c > 1) {
                ret += 4;
                c -= 2;
            }
            if (c == 1) ret += 1;
            System.out.println(ret);
        }
    }
}
