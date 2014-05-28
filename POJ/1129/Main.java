import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final boolean[][] map = new boolean[26][26];
    public static final int[] colors = new int[26];

    private static void printColor(int n) {
        System.out.print("Color:\t");
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.print("\t");
        for (int i = 0; i < n; i++) {
            System.out.print(colors[i] + "\t");
        }
        System.out.println();
    }

    private static void printMap(int n) {
        System.out.print("Map:\t");
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] ? 1 : 0);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            scan.nextLine();
            Arrays.fill(colors, 1);
            for (int i = 0; i < 26; i++) {
                Arrays.fill(map[i], false);
            }
            for (int i = 0; i < n; i++) {
                String l = scan.nextLine().trim();
                String[] tmp = l.split(":");
                int idx = tmp[0].charAt(0) - 'A';
                if (tmp.length == 2) {
                    for (int j = 0; j < tmp[1].length(); j++) {
                        map[idx][tmp[1].charAt(j) - 'A'] = true;
                        map[tmp[1].charAt(j) - 'A'][idx] = true;
                    }
                }
            }
            if (DEBUG) printMap(n);
            for (int k = 1; k < 4; k++) {
                for (int i = 0; i < n; i++) {
                    if (colors[i] == k) {
                        for (int j = i + 1; j < n; j++) {
                            if (map[i][j] && colors[j] == k) colors[j]++;
                        }
                    }
                }
                if (DEBUG) printColor(n);
            }
            int num = 0;
            for (int i = 0; i < n; i++) {
                num = Math.max(num, colors[i]);
            }
            System.out.print(num + " ");
            if (num == 1) System.out.println("channel needed.");
            else System.out.println("channels needed.");
            n = scan.nextInt();
        }
    }
}
