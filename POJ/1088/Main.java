import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final int[] dx = new int[]{1, 0, -1, 0};
    private static final int[] dy = new int[]{0, 1, 0, -1};

    private static int findMax(int[][] heights, int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                genDp(heights, dp, i, j);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private static void genDp(int[][] heights, int[][] dp, int x, int y) {
        if (dp[x][y] != -1) return;
        int height = 1;
        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            if (xx < 0 || xx >= dp.length || yy < 0 || yy >= dp[0].length) continue;
            if (heights[xx][yy] < heights[x][y]) {
                genDp(heights, dp, xx, yy);
                height = Math.max(height, dp[xx][yy] + 1);
            }
        }
        dp[x][y] = height;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int R = scan.nextInt();
        int C = scan.nextInt();

        int[][] dp = new int[R][C];
        int[][] heights = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                heights[i][j] = scan.nextInt();
            }
        }

        System.out.println(findMax(heights, dp));
    }

}
