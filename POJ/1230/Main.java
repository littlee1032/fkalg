import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    static class Wall implements Comparable<Wall> {
        @Override public int compareTo(Wall o) {
            return o.right - right;
        }

        public int left;
        public int right;

        public Wall(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static Wall[] walls;
    private static final int NMAX = 101;
    private static int[] columns = new int[NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int w = scan.nextInt();
            int k = scan.nextInt();
            walls = new Wall[w];
            Arrays.fill(columns, 0);

            for (int j = 0; j < w; j++) {
                int left = scan.nextInt();
                scan.nextInt();
                int right = scan.nextInt();
                scan.nextInt();
                int min = Math.min(left, right);
                int max = Math.max(left, right);
                walls[j] = new Wall(min, max);
                for (int l = min; l <= max; l++) {
                    columns[l]++;
                }
            }
            Arrays.sort(walls);

            int ans = 0;
            for (int j = 0; j < NMAX; j++) {
                if (columns[j] > k) {
                    int delNum = columns[j] - k;
                    ans += delNum;
                    for (int l = 0; l < w && delNum > 0; l++) {
                        Wall wall = walls[l];
                        if (wall.left <= j && wall.right >= j) {
                            delNum--;
                            for (int ll = wall.left; ll <= wall.right; ll++) {
                                columns[ll]--;
                            }
                            wall.right = -1;
                        }
                    }
                }
            }

            System.out.println(ans);
        }
    }
}
