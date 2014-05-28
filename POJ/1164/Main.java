import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    private static int WEST = 1;
    private static int NORTH = 2;
    private static int EAST = 4;
    private static int SOUTH = 8;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, -1, 0, 1};
    private static int[] walls = new int[]{WEST, NORTH, EAST, SOUTH};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[][] castle = new int[m][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                castle[i][j] = scan.nextInt();
            }
        }

        boolean[][] vis = new boolean[m][n];
        int room = 0;
        int max = 0;

        Queue<int[]> q = new LinkedList<int[]>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j]) {
                    vis[i][j] = true;
                    q.offer(new int[]{i,j});
                    int roomSize = 0;
                    while (!q.isEmpty()) {
                        int[] ord = q.poll();
                        roomSize++;
                        int x = ord[0];
                        int y = ord[1];
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx >= 0 && nx < m &&
                                ny >= 0 && ny < n) {
                                if (!vis[nx][ny] && (castle[x][y] & walls[k]) == 0) {
                                    vis[nx][ny] = true;
                                    q.offer(new int[]{nx, ny});
                                }
                            }
                        }
                    }
                    room++;
                    max = Math.max(max, roomSize);
                }
            }
        }

        System.out.println(room);
        System.out.println(max);
    }
}
