import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int ones = 0;
        int twos = 0;
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            if (ones < 0) {
                ok = false;
                break;
            }
            int bill = scan.nextInt();
            if (bill == 25) {
                ones++;
            } else if (bill == 50) {
                ones--;
                twos++;
            } else if (bill == 100) {
                if (twos > 0) {
                    twos--;
                    ones--;
                } else {
                    ones -= 3;
                }
            }
        }
        if (ones < 0) ok = false;
        if (!ok) System.out.println("NO");
        else System.out.println("YES");
    }
}
