import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            int total = scan.nextInt();
            if (total % 2 == 1) {
                for (int j = 0; j < total; j++) scan.nextInt();
                System.out.println("YES");
            } else {
                int m = 0;
                for (int j = 0; j < total; j++) {
                    if (scan.nextInt() == 1) {
                        if (j % 2 == 0) m++;
                        else m--;
                    }
                }
                if (m == 0 || m == 1 || m == -1) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }
}
