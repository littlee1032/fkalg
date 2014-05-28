import java.util.Scanner;

public class Main {
    private static boolean[] vis = new boolean[26];
    private static int max = Integer.MIN_VALUE;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, 1, 0, -1};
    private static char[][] map;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int r = scan.nextInt();
        int c = scan.nextInt();
        map = new char[r][c];

        scan.nextLine();
        for (int i = 0; i < r; i++) {
            map[i] = scan.nextLine().toCharArray();
        }

        rec(0, 0, 1, r, c);

        System.out.println(max);
    }

    private static void rec(int x, int y, int step, int xlimit, int ylimit) {
        max = Math.max(step, max);
        vis[map[x][y] - 'A'] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= xlimit || ny >= ylimit) continue;
            char c = map[nx][ny];
            if (!vis[c - 'A']) rec(nx, ny, step+1, xlimit, ylimit);
        }
        vis[map[x][y] - 'A'] = false;
    }
}
