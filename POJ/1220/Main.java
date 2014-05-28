import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int getNum(char c) {
        if (c >= '0' && c <= '9') return c - '0';
        else if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        else if (c >= 'a' && c <= 'z') return c - 'a' + 36;
        else return -1;
    }

    public static char getChar(int n) {
        if (n >= 0 && n <= 9) return (char)('0' + n);
        else if (n >= 10 && n < 36) return (char)('A' + n - 10);
        else if (n >= 36 && n < 62) return (char)('a' + n - 36);
        else return '*';
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            String[] margs = line.split(" ");
            solve(margs);
        }
    }

    private static void solve(String[] args) {
        int sbase = Integer.valueOf(args[0]);
        int tbase = Integer.valueOf(args[1]);
        char[] target = args[2].toCharArray();
        int startIdx = 0;
        List<Character> res = new ArrayList<Character>();
        while (startIdx < target.length) {
            int carry = 0;
            for (int i = startIdx; i < target.length; i++) {
                int num = getNum(target[i]) + carry * sbase;;
                target[i] = getChar(num / tbase);
                carry = num % tbase;
            }
            res.add(getChar(carry));
            startIdx = 0;
            for (int i = 0; i < target.length; i++) {
                if (getNum(target[i]) == 0) startIdx = i + 1;
                else break;
            }
        }
        System.out.println(sbase + " " + args[2]);
        System.out.print(tbase + " ");
        for (int i = res.size() - 1; i >= 0; i--) {
            System.out.print(res.get(i));
        }
        System.out.println();
        System.out.println();
    }
}
