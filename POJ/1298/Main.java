import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        while ("START".equals(line)) {
            line = scan.nextLine();
            char[] arr = line.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = convert(arr[i]);
            }
            System.out.println(String.valueOf(arr));
            line = scan.nextLine();
            line = scan.nextLine();
        }
    }

    private static char convert(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char)('A' + ((int)(c - 'A') + 21) % 26);
        } else {
            return c;
        }
    }
}
