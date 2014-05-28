import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean boyAhead = false;
        int girlPos = 0;

        String line = scan.nextLine().trim();
        char prev = line.charAt(0);
        int sec = 0;
        if ('M' == prev) boyAhead = true;
        else girlPos = 1;
        for (int i = 1; i < line.length(); i++) {
            char cur = line.charAt(i);
            if ('M' == cur) {
                boyAhead = true;
            } else {
                if (boyAhead) {
                    sec = Math.max(sec + 1, i - girlPos);
                }
                girlPos++;
            }
            prev = cur;
        }

        System.out.println(sec);
    }
}
