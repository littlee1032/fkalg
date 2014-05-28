import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int NMAX = 4;
    private static final int[][] board = new int[NMAX][NMAX];
    private static final int W = -1;
    private static final int E = 0;
    private static int max = 0;
    private static int l = 0;

    private static void init() {
        for (int i = 0; i < NMAX; i++) Arrays.fill(board[i], 0);
        max = 0;
    }

    private static void dfs(int count) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (board[i][j] == E) {
                    int flag = i * 10 + j + 1;
                    mark(i, j, E, flag);
                    max = Math.max(max, count+1);
                    dfs(count+1);
                    mark(i, j, flag, E);
                }
            }
        }
    }

    private static void mark(int x, int y, int expectFlag, int flag) {
        for (int yy = y; yy >= 0; yy--) {
            if (board[x][yy] == W) break;
            else if (board[x][yy] == expectFlag) {
                board[x][yy] = flag;
            }
        }
        for (int yy = y + 1; yy < l; yy++) {
            if (board[x][yy] == W) break;
            else if (board[x][yy] == expectFlag) {
                board[x][yy] = flag;
            }
        }
        for (int xx = x - 1; xx >= 0; xx--) {
            if (board[xx][y] == W) break;
            else if (board[xx][y] == expectFlag) board[xx][y] = flag;
        }
        for (int xx = x + 1; xx < l; xx++) {
            if (board[xx][y] == W) break;
            else if (board[xx][y] == expectFlag) board[xx][y] = flag;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        l = scan.nextInt();
        while (l > 0) {
            init();
            scan.nextLine();
            for (int i = 0; i < l; i++) {
                String line = scan.nextLine();
                int x = 0;
                for (char c : line.toCharArray()) {
                    if (c == '.') board[x++][l-i-1] = E;
                    else board[x++][l-i-1] = W;
                }
            }
            dfs(0);
            System.out.println(max);
            l = scan.nextInt();
        }
    }
}
