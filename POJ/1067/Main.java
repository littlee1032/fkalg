import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            int diff = b - a;
            int t = (int)Math.floor(diff * (Math.sqrt(5.0) + 1) / 2 );
            if (t == a) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
    }
}
