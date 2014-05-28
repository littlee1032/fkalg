import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            sum += a;
            max = Math.max(max, a);
        }
        long round = Math.max((sum + n - 1) / n, max);
        while (round * n - round < sum) round++;
        System.out.println(round);
    }
}
