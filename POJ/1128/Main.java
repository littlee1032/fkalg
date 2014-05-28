import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static final boolean DEBUG = true;
    public static final int[][] lts = new int[26][2];
    public static final int[][] brs = new int[26][2];
    public static final boolean[][] map = new boolean[26][26];
    public static final int[] ins = new int[26];
    public static final char[] output = new char[26];
    public static final boolean[] checked = new boolean[26];

    public static void init() {
        for (int i = 0; i < 26; i++) {
            lts[i][0] = Integer.MAX_VALUE;
            lts[i][1] = Integer.MAX_VALUE;
            brs[i][0] = Integer.MIN_VALUE;
            brs[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < 26; i++) {
            Arrays.fill(map[i], false);
        }
        Arrays.fill(ins, 0);
        Arrays.fill(output, '.');
        Arrays.fill(checked, false);
    }

    public static void link(int s, int e) {
        if (s != e && !map[s][e]) {
            map[s][e] = true;
            ins[e]++;
        }
    }

    public static void print(int n, int pos) {
        if (pos > n) {
            for (int i = 0; i < pos; i++) {
                System.out.print(output[i]);
            }
            System.out.println();
        } else {
            for (int i = 0; i <= n; i++) {
                if (ins[i] == 0 && !checked[i]) {
                    checked[i] = true;
                    output[pos] = (char)('A' + i);
                    for (int j = 0; j <= n; j++) {
                        if (map[i][j]) ins[j]--;
                    }
                    print(n, pos+1);
                    // backtrace
                    for (int j = 0; j <= n; j++) {
                        if (map[i][j]) ins[j]++;
                    }
                    output[pos] = '.';
                    checked[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            init();
            int h = scan.nextInt();
            int w = scan.nextInt();
            char[][] store = new char[h][w];
            scan.nextLine();
            int maxIdx = 0;
            for (int i = 0; i < h; i++) {
                String l = scan.nextLine().trim();
                for (int j = 0; j < l.length(); j++) {
                    char c = l.charAt(j);
                    store[i][j] = c;
                    if ('.' != c) {
                        int idx = c - 'A';
                        maxIdx = Math.max(maxIdx, idx);
                        lts[idx][0] = Math.min(lts[idx][0], i);
                        lts[idx][1] = Math.min(lts[idx][1], j);
                        brs[idx][0] = Math.max(brs[idx][0], i);
                        brs[idx][1] = Math.max(brs[idx][1], j);
                    }
                }
            }
            for (int i = 0; i <= maxIdx; i++) {
                int minY = lts[i][1];
                int maxY = brs[i][1];
                int minX = lts[i][0];
                int maxX = brs[i][0];
                for (int j = minX; j <= maxX; j++) {
                    link(i, store[j][minY] - 'A');
                    link(i, store[j][maxY] - 'A');
                }
                for (int j = minY; j <= maxY; j++) {
                    link(i, store[minX][j] - 'A');
                    link(i, store[maxX][j] - 'A');
                }
            }
            print(maxIdx, 0);
        }
    }
}
