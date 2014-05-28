import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    private static void calcDiag(int r, int c, char[][]map, int[][]diff, int[][] states) {
        boolean isFirst = ((r + c) % 2 == 0);
        int n = map.length;
        int undef = 3 * n;
        int right = undef;
        int bottom = undef;

        if (c < n - 1) right = diff[r][c+1];
        if (r < n - 1) bottom = diff[r+1][c];

        int bias = 0;
        if (map[r][c] == 'a') bias = 1;
        else if (map[r][c] == 'b') bias = -1;

        if (isFirst) {
            int choose = right;
            if (choose == undef || (bottom != undef && bottom < choose)) choose = bottom;
            diff[r][c] = choose + bias;
        } else {
            int choose = right;
            if (choose == undef || (bottom != undef && bottom > choose)) choose = bottom;
            diff[r][c] = choose + bias;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();

        char[][] map = new char[n][n];
        int[][] diff = new int[n][n];
        int[][] states = new int[n][n];
        List<int[]> nStates = new ArrayList<int[]>();

        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            map[i] = line.toCharArray();
        }

        Queue<int[]> spiders = new LinkedList<int[]>();
        spiders.offer(new int[]{0, 0});
        nStates.add(new int[26]);

        int stateGen = 1;
        int[][] spans = new int[][]{{1, 0}, {0, 1}};
        while (!spiders.isEmpty()) {
            int[] spider = spiders.poll();
            int row = spider[0];
            int col = spider[1];
            int mState = states[row][col];
            int[] mnStates = nStates.get(mState);

            for (int i = 0; i < 2; i++) {
                int rDiff = spans[i][0];
                int cDiff = spans[i][1];

                int nRow = row + rDiff;
                int nCol = col + cDiff;

                if (nRow < n && nCol < n) {
                    char nextChar = map[nRow][nCol];
                    if (mnStates[nextChar - 'a'] == 0) {
                        states[nRow][nCol] = stateGen;
                        mnStates[nextChar - 'a'] = stateGen;
                        stateGen++;
                        nStates.add(new int[26]);
                    } else {
                        states[nRow][nCol] = mnStates[nextChar - 'a'];
                        
                    }
                    spiders.add(new int[]{nRow, nCol});
                }
            }
        }

        char c = map[n-1][n-1];
        if (c == 'a') diff[n-1][n-1] = 1;
        else if (c == 'b') diff[n-1][n-1] = -1;

        for (int row = n - 2; row >= 0; row--) {
            calcDiag(row, n-1, map, diff, states);
        }

        for (int col = n - 2; col >= 0; col--) {
            calcDiag(0, col, map, diff, states);
        }

        /*
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(diff[i]));
        }
        */
        if (diff[0][0] > 0) System.out.println("FIRST");
        else if (diff[0][0] < 0) System.out.println("SECOND");
        else System.out.println("DRAW");
    }
}
