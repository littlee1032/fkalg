import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class Main {
    private static final int NMAX = 51;
    private static final char[][] board = new char[NMAX][NMAX];
    private static final char EMPTY = ' ';

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int testIdx = 0;
        while (n > 0) {
            scan.nextLine();
            init();
            for (int i = 0; i < n; i++) {
                String line = scan.nextLine();
                String[] pInfo = line.split(" ");
                char c = pInfo[0].charAt(0);
                int x = Integer.valueOf(pInfo[1]);
                int y = Integer.valueOf(pInfo[2]);
                board[x][y] = c;
            }
            List<String> rects = solve();
            Collections.sort(rects);
            System.out.print("Point set " + (++testIdx) + ":");
            if (rects.isEmpty()) {
                System.out.println(" No rectangles");
            } else {
                System.out.println();
                for (int i = 0; i < rects.size(); i++) {
                    System.out.print(" " + rects.get(i));
                    if ((i + 1) % 10 == 0 && (i + 1) != rects.size()) {
                        System.out.println();
                    }
                }
                System.out.println();
            }
            n = scan.nextInt();
        }
    }

    private static List<String> solve() {
        List<String> ret = new LinkedList<String>();
        for (int i = NMAX - 1; i > 0; i--) {
            for (int s = 0; s < NMAX; s++) {
                if (board[s][i] != EMPTY) {
                    for (int e = s + 1; e < NMAX; e++) {
                        if (board[e][i] != EMPTY) {
                            for (int j = i - 1; j >= 0; j--) {
                                if (board[s][j] != EMPTY && board[e][j] != EMPTY) {
                                    ret.add("" + board[s][i] + board[e][i] + board[e][j] + board[s][j]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    private static void init() {
        for (int i = 0; i < NMAX; i++) Arrays.fill(board[i], EMPTY);
    }
}
