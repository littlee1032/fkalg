import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class GameOnABoard {
    private static final boolean DEBUG = false;
    
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};
    private static final Comparator<int[]> comparator = new MyComparator();

    private static class MyComparator implements Comparator<int[]> {
        public int compare(int[] arg0, int[] arg1) {
            return arg0[2] - arg1[2];
        }
        
    }
    
    private int getL(int x1, int y1, boolean[][] board) {
        Queue<int[]> pq = new PriorityQueue<int[]>(board.length * board[0].length, comparator);
        boolean[][] checked = new boolean[board.length][board[0].length]; 
        int v = board[x1][y1] ? 1 : 0;
        int[] first = new int[]{x1, y1, v};
        pq.offer(first);
        int max = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int x = tmp[0];
            int y = tmp[1];
            v = tmp[2];
            if (!checked[x][y]) {
                checked[x][y] = true;
                max = Math.max(max, v);
                for (int i = 0; i < 4; i++) {
                    int x2 = x + dx[i];
                    int y2 = y + dy[i];
                    if (x2 >= 0 && x2 < board.length && y2 >= 0 && y2 < board[0].length && !checked[x2][y2]) {
                        int newV = v;
                        if (board[x2][y2]) newV++;
                        pq.offer(new int[]{x2, y2, newV});
                    }
                }
            }
            
        }
        return max;
    }

    public int optimalChoice(String[] cost) {
        int rowNum = cost.length;
        int colNum = cost[0].length();
        boolean[][] board = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            String c = cost[i];
            for (int j = 0; j < colNum; j++) {
                if (c.charAt(j) == '0') {
                    board[i][j] = false;
                } else {
                    board[i][j] = true;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int x1 = 0; x1 < rowNum; x1++) {
            for (int y1 = 0; y1 < colNum; y1++) {
                int max = getL(x1, y1, board);
                if (DEBUG) System.out.println("x1: " + x1 + " y1: " + y1);
                if (DEBUG) System.out.println("max: " + max);
                min = Math.min(min, max);
                if (DEBUG) System.out.println("min: " + min);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        String[] cost = new String[] { "01", "10" };
        GameOnABoard goa = new GameOnABoard();
        System.out.println(goa.optimalChoice(cost));
    }
}
