import java.util.Scanner;

public class Main {
    private static char get(int num) {
        switch(num) {
        case 0: return '_';
        case 27: return '.';
        default: return (char)('a' + num - 1);
        }
    }

    private static int get(char c) {
        switch(c) {
        case '_': return 0;
        case '.': return 27;
        default: return c - 'a' + 1;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            char[] ciphertext = scan.nextLine().trim().toCharArray();
            int len = ciphertext.length;
            char[] plaintext = new char[len];

            for (int i = 0; i < len; i++) {
                int ciphercode = get(ciphertext[i]);
                int plaincode = (ciphercode + i) % 28;
                plaintext[(n * i) % len] = get(plaincode);
            }
            System.out.println(String.valueOf(plaintext));
            n = scan.nextInt();
        }
    }
}
