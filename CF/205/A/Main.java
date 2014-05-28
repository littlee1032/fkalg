import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int sum = 0;
        int upper = 0;
        boolean xor = false;
        for (int i = 0; i < n; i++) {
            int up = scan.nextInt();
            int bottom = scan.nextInt();
            sum += up;
            upper += up;
            sum += bottom;
            if (up % 2 != bottom % 2) xor = true;
        }

        if (sum % 2 == 1) {
            System.out.println(-1);
        } else {
            if (upper % 2 == 1) {
                if (xor)
                    System.out.println(1);
                else
                    System.out.println(-1);
            } else {
                System.out.println(0);
            }
        }
    }
}
