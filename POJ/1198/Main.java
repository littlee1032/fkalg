import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Main {
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};
    private static int[] dx2 = new int[]{-2, 0, 2, 0};
    private static int[] dy2 = new int[]{0, -2, 0, 2};
    private static int[][] board = new int[4][2];

    static class Board {
        public int[] board = new int[8];
        public final int hashCode;

        public Board(int[] b) {
            System.arraycopy(b, 0, board, 0, 8);
            for (int i = 0; i < 8; i += 2) {
                for (int j = i + 2; j < 8; j += 2) {
                    if (board[i] > board[j] || (board[i] == board[j] && board[i+1] > board[j+1])) {
                        // change
                        int tmp1 = board[i];
                        int tmp2 = board[i+1];
                        board[i] = board[j];
                        board[i+1] = board[j+1];
                        board[j] = tmp1;
                        board[j+1] = tmp2;
                    }
                }
            }
            int h = 0;
            for (int i = 0; i < 8; i++) {
                h += board[i];
                h *= 10;
            }
            hashCode = h;
        }

        @Override public String toString() {
            return String.valueOf(hashCode);
        }

        @Override public int hashCode() {
            return hashCode;
        }

        @Override public boolean equals(Object o) {
            if (!(o instanceof Board)) return false;
            Board b = (Board)o;
            return hashCode == b.hashCode;
        }

        public boolean isEmpty(int x, int y) {
            for (int i = 0; i < 8; i += 2) {
                if (board[i] < x) continue;
                if (board[i] > x) return true;
                if (board[i+1] < y) continue;
                if (board[i+1] == y) return false;
                if (board[i+1] > y) return true;
            }
            return true;
        }

        public boolean isValid(int x, int y) {
            return x > 0 && x <= 8 && y > 0 && y <= 8;
        }

        public Set<Board> nextBoards() {
            Set<Board> ret = new HashSet<Board>();
            for (int i = 0; i < 8; i += 2) {
                int x = board[i];
                int y = board[i+1];

                for (int j = 0; j < 4; j++) {
                    int candX = x + dx[j];
                    int candY = y + dy[j];
                    boolean ok = false;
                    if (isValid(candX, candY) && isEmpty(candX, candY)) {
                        ok = true;
                    }
                    if (!ok && isValid(candX, candY) && !isEmpty(candX, candY)) {
                        candX = x + dx2[j];
                        candY = y + dy2[j];
                        if (isValid(candX, candY) && isEmpty(candX, candY)) {
                            ok = true;
                        }
                    }
                    if (ok) {
                        board[i] = candX;
                        board[i+1] = candY;
                        ret.add(new Board(board));
                        board[i] = x;
                        board[i+1] = y;
                    }
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Set<Board> forward = new HashSet<Board>();
        Set<Board> backward = new HashSet<Board>();

        int[] s = new int[8];
        int[] e = new int[8];
        for (int i = 0; i < 8; i++) s[i] = scan.nextInt();
        for (int i = 0; i < 8; i++) e[i] = scan.nextInt();

        forward.add(new Board(s));
        backward.add(new Board(e));

        int fStep = 0;
        int bStep = 0;

        while (true) {
            for (Board b : forward) {
                if (backward.contains(b)) {
                    System.out.println("YES");
                    return;
                }
            }
            fStep++;
            Set<Board> last = new HashSet<Board>(forward);
            forward.clear();
            for (Board b : last) {
                forward.addAll(b.nextBoards());
            }

            if (fStep + bStep > 8) break;

            for (Board b : backward) {
                if (forward.contains(b)) {
                    System.out.println("YES");
                    return;
                }
            }
            bStep++;
            last = new HashSet<Board>(backward);
            backward.clear();
            for (Board b : last) {
                backward.addAll(b.nextBoards());
            }
        }
        System.out.println("NO");
    }
}
