import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class Cipher {
    private static String encode(int[] cipher, int reNum, String text) {
        int encodeLen = cipher.length;
        char[] encodeStr = new char[encodeLen];
        Arrays.fill(encodeStr, ' ');
        Set<Integer> round = new HashSet<Integer>();
        Set<Integer> done = new HashSet<Integer>();
        for (int i = 0; i < encodeLen; i++) {
            if (!done.contains(i)) {
                int idx = i;
                while (!round.contains(idx)) {
                    round.add(idx);
                    idx = cipher[idx];
                }
                int circleLen = round.size();
                int remain = reNum % circleLen;
                for (Integer orig : round) {
                    int target = orig;
                    for (int j = 0; j < remain; j++) {
                        target = cipher[target];
                    }
                    char c = orig >= text.length() ? ' ' : text.charAt(orig);
                    encodeStr[target] = c;
                }
                done.addAll(round);
                round.clear();
            }
        }
        return new String(encodeStr);
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n != 0) {
            int[] cipher = new int[n];
            for (int i = 0; i < n; i++) {
                cipher[i] = scan.nextInt() - 1;
            }
            int reNum = scan.nextInt();
            while (reNum != 0) {
                /*
                StringBuilder sb = new StringBuilder();
                int c = System.in.read();
                while (c != 10) {
                    System.out.print(c);
                    sb.append(Character.toChars(c)[0]);
                    c = System.in.read();
                }
                String text = sb.toString();
                */
                String text = scan.nextLine().trim();
                System.out.println(encode(cipher, reNum, text));
                reNum = scan.nextInt();
            }
            System.out.println();
            n = scan.nextInt();
        }
    }
}
