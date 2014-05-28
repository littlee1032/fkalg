import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int zs = 0;
        int fs = 0;

        for (int i = 0; i < n; i++) {
            if (scan.nextInt() == 0) zs++;
            else fs++;
        }

        if (zs == 0) {
            System.out.println(-1);
        } else {
            if (fs * 5 < 45) System.out.println(0);
            else {
                int m = fs / 9;
                for (int i = 0; i < m * 9; i++) {
                    System.out.print(5);
                }
                for (int i = 0; i < zs; i++) {
                    System.out.print(0);
                }
                System.out.println();
            }
        }
    }
}
