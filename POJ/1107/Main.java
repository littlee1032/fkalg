import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int getIdx(int oIdx, int len, int rotate) {
        rotate = rotate % len;
        return (oIdx - rotate + len) % len;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k1 = scan.nextInt();
        int k2 = scan.nextInt();
        int k3 = scan.nextInt();

        while (k1 + k2 + k3 > 0) {
            int k1num = 0;
            int k2num = 0;
            int k3num = 0;
            scan.nextLine();
            String code = scan.nextLine();

            char[] k1array = new char[code.length()];
            char[] k2array = new char[code.length()];
            char[] k3array = new char[code.length()];
            for (int i = 0; i < code.length(); i++) {
                char c = code.charAt(i);
                if (c >= 'a' && c <= 'i') {
                    k1array[k1num++] = c;
                } else if (c >= 'j' && c <= 'r') {
                    k2array[k2num++] = c;
                } else {
                    k3array[k3num++] = c;
                }
            }
            int k1Idx = 0;
            int k2Idx = 0;
            int k3Idx = 0;
            for (int j = 0; j < code.length(); j++) {
                char c = code.charAt(j);
                if (c >= 'a' && c <= 'i') {
                    for (;k1Idx < k1num; k1Idx++) {
                        if (c == k1array[k1Idx]) {
                            break;
                        }
                    }
                    System.out.print(k1array[getIdx(k1Idx, k1num, k1)]);
                    k1Idx++;
                } else if (c >= 'j' && c <= 'r') {
                    for (; k2Idx < k2num; k2Idx++) {
                        if (c == k2array[k2Idx]) {
                            break;
                        }
                    }
                    System.out.print(k2array[getIdx(k2Idx, k2num, k2)]);
                    k2Idx++;
                } else {
                    for (; k3Idx < k3num; k3Idx++) {
                        if (c == k3array[k3Idx]) {
                            break;
                        }
                    }
                    System.out.print(k3array[getIdx(k3Idx, k3num, k3)]);
                    k3Idx++;
                }
            }
            System.out.println();

            k1 = scan.nextInt();
            k2 = scan.nextInt();
            k3 = scan.nextInt();
        }
    }
}
