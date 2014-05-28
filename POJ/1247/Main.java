import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            int[] ns = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                ns[i] = scan.nextInt();
                sum += ns[i];
            }
            if (sum % 2 == 1) {
                System.out.println("No equal partitioning.");
            } else {
                sum /= 2;
                for (int i = 0; i < n; i++) {
                    if (sum > 0) sum -= ns[i];
                    else if (sum < 0) {
                        System.out.println("No equal partitioning.");
                        break;
                    } else {
                        System.out.println(String.format("Sam stops at position %d and Ella stops at position %d.", i, i + 1));
                        break;
                    }
                }
            }
            n = scan.nextInt();
        }
    }
}
