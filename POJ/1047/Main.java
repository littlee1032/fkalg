import java.util.Scanner;

public class Main {
    private static String multi(String s, int n) {
        int carry = 0;
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            int a = s.charAt(i) - '0';
            sb.append((a * n + carry) % 10);
            carry = (a * n + carry) / 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String number = scan.nextLine();
            boolean cyclic = true;
            int len = number.length();

            for (int i = 1; i <= len; i++) {
                String newNum = multi(number, i);
                newNum += newNum;
                if (!newNum.contains(number)) {
                    cyclic = false;
                    break;
                }
            }

            if (cyclic) {
                System.out.println(number + " is cyclic");
            } else {
                System.out.println(number + " is not cyclic");
            }
        }
    }
}
