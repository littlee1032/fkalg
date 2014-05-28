import java.util.Scanner;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    private static final boolean DEBUG = false;

    private static void print(int[][] board, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    System.out.print('X');
                } else if (board[i][j] == Integer.MAX_VALUE) {
                    System.out.print('+');
                } else {
                    System.out.print(board[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        int nMax = 100;
        int[][] board = new int[nMax][nMax];
        Deque<String> stack = new LinkedList<String>();
        while (t > 0) {
            t--;
            int m = scan.nextInt();
            int n = scan.nextInt();
            if (DEBUG) System.out.println("m: " + m + " n: " + n);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int num = scan.nextInt();
                    if (num == 0) board[i][j] = 0;
                    else board[i][j] = -1;
                }
            }
            scan.nextLine();
            String line = scan.nextLine();
            while (!line.startsWith("0 0")) {
                stack.push(line);
                line = scan.nextLine();
            }

            int steps = stack.size();
            if (DEBUG) System.out.println("Steps: " + stack);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != -1) {
                        board[i][j] = steps;
                    }
                }
            }

            int checked = Integer.MAX_VALUE;

            while (!stack.isEmpty()) {
                int step = stack.size();
                line = stack.pop();
                String[] tmp = line.split(" ");
                int min = Integer.valueOf(tmp[0]);
                int max = Integer.valueOf(tmp[1]);
                char d = tmp[2].charAt(0);
                for (int i = 0; i < m; i++) {
                    if (DEBUG) System.out.println(line);
                    for (int j = 0; j < n; j++) {
                        if (DEBUG) {
                            System.out.println("(" + i + ", " + j + ")");
                        }
                        if (board[i][j] != step && board[i][j] != checked) continue;
                        int jMin = j;
                        int jMax = j;
                        int iMin = i;
                        int iMax = i;
                        switch (d) {
                        case 'R':
                            jMin = j >= max ? j - max : 0;
                            jMax = j - min;
                            for (int jj = j; jj >= jMin; jj--) {
                                if (board[i][jj] == -1) {
                                    jMin = jj + 1;
                                    break;
                                }
                            }
                            break;
                        case 'L':
                            jMin = j + min;
                            jMax = j + max > n ? n - 1 : j + max;
                            for (int jj = j; jj <= jMax; jj++) {
                                if (board[i][jj] == -1) {
                                    jMax = jj - 1;
                                    break;
                                }
                            }
                            break;
                        case 'U':
                            iMin = i + min;
                            iMax = i + max > m ? m - 1 : i + max;
                            for (int ii = i; ii <= iMax; ii++) {
                                if (board[ii][j] == -1) {
                                    iMax = ii - 1;
                                    break;
                                }
                            }
                            break;
                        case 'D':
                            iMin = i >= max ? i - max : 0;
                            iMax = i - min;
                            for (int ii = i; ii >= iMin; ii--) {
                                if (board[ii][j] == - 1) {
                                    iMin = ii + 1;
                                    break;
                                }
                            }
                            break;
                        }
                        if (DEBUG) System.out.println(iMin + " " + iMax + " " + jMin + " " + jMax);
                        for (int ii = iMin; ii <= iMax; ii++) {
                            for (int jj = jMin; jj <= jMax; jj++) {
                                if (board[ii][jj] != -1)
                                    board[ii][jj] = checked;
                            }
                        }
                    }
                    if (DEBUG) print(board, m, n);
                }
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (board[i][j] == checked) {
                            board[i][j] = step - 1;
                        }
                    }
                }
            }
            int ret = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) ret++;
                }
            }
            System.out.println(ret);
        }
    }
}
