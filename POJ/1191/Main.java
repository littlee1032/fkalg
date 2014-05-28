import java.util.Scanner;

public class Main {
    private static final int[] map = new int[8][8];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = scan.nextInt();
                sum += map[i][j];
            }
        }

        double avg = sum * 1.0d / n;
        
    }
}
