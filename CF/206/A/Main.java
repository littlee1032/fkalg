import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int d = scan.nextInt();

        if (k > 1 && d == 0) System.out.println("No solution");
        else {
            System.out.print(d);
            for (int i = 0; i < k - 1; i++) System.out.print("0");
            System.out.println();
        }
    }
}
