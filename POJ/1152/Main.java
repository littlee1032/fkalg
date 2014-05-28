import java.util.Scanner;

public class Main {
    private static int num(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        else if (c >= 'A' && c <= 'Z') return 10 + c - 'A';
        else return 36 + c - 'a';
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String line = scan.nextLine().trim();
            if ("0".equals(line)) {
                System.out.println("2");
                continue;
            }
            int len = line.length();
            long sum = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                int num = num(line.charAt(i));
                sum += num;
                max = Math.max(max, num);
            }
            boolean found = false;
            int n = max + 1;
            while (n < 63) {
                if (sum % (n - 1) == 0) break;
                n++;
            }
            if (n < 63) System.out.println(n);
            else System.out.println("such number is impossible!");
        }
    }
}
