import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = false;
    public static final int NMAX = 32;
    public static final boolean[][] map = new boolean[NMAX][NMAX];
    public static final int[] dx = new int[]{1, 0, -1, 0};
    public static final int[] dy = new int[]{0, 1, 0, -1};

    public static void mark(char c, int x, int y) {
        if (DEBUG) System.out.println(c + " (" + x + ", " + y + ")");
        switch(c) {
        case 'E':
            map[x][y - 1] = true;
            return;
        case 'W':
            map[x - 1][y] = true;
            return;
        case 'N':
            map[x][y] = true;
            return;
        default:
            map[x - 1][y - 1] = true;
            return;
        }
    }

    public static void printMap(int n) {
        for (int j = n; j >= 0; j--) {
            for (int i = 0; i <= n; i++) {
                System.out.print(map[i][j] ? 'X' : '.');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 1;
        while (n-- > 0) {
            for (int i = 0; i < 32; i++) Arrays.fill(map[i], false);
            int x = scan.nextInt();
            int y = scan.nextInt();
            scan.nextLine();
            String path = scan.nextLine().trim();
            for (int i = 0; i < path.length() - 1; i++) {
                char c = path.charAt(i);
                int idx = 0;
                switch(c) {
                case 'E':
                    idx = 0;
                    break;
                case 'W':
                    idx = 2;
                    break;
                case 'N':
                    idx = 1;
                    break;
                default:
                    idx = 3;
                    break;
                }
                mark(c, x, y);
                if (DEBUG) printMap(7);
                x += dx[idx];
                y += dy[idx];
            }
            System.out.println("Bitmap #" + count++);
            printMap(31);
            System.out.println();
        }
    }
}
