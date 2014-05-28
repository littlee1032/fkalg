import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n == 0) {
            System.out.println("O-|-OOOO");
        } else {
            while (n > 0) {
                int num = n % 10;
                n /= 10;

                if (num >= 5) {
                    System.out.print("-O");
                    num -= 5;
                } else {
                    System.out.print("O-");
                }
                System.out.print("|");
                for (int i = 0; i < num; i++) {
                    System.out.print("O");
                }
                System.out.print("-");
                for (int i = 0; i < 4 - num; i++) {
                    System.out.print("O");
                }
                System.out.println();
            }
        }
    }
}
