import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int[] children = new int[m+1];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            children[i+1] = scan.nextInt();
            sum += children[i+1];
        }
        int x = scan.nextInt();
        int y = scan.nextInt();
        int beginners = 0;
        int intermediates = sum;
        for (int i = 2; i <= m; i++) {
            beginners += children[i-1];
            intermediates -= children[i-1];
            if (beginners >= x && beginners <= y
                && intermediates >= x && intermediates <=y) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}
