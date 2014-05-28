import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] map = new int[n + 1][n + 1];
        int[] children = new int[n + 1];
        Arrays.fill(children, -1);
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = scan.nextInt();
            }
        }
        int end = scan.nextInt();
        boolean[] fireHouse = new boolean[n + 1];
        int fireHouseNum = 0;
        while (scan.hasNextInt()) {
            fireHouse[scan.nextInt()] = true;
            fireHouseNum++;
        }

        Set<Integer> waiting = new HashSet<Integer>();
        for (int i = 1; i <= n; i++) {
            waiting.add(i);
        }
        System.out.println("Org\tDest\tTime\tPath");
        children[end] = 0;
        dists[end] = 0;
        while(!waiting.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int minNode = 0;
            for (int i = 1; i <= n; i++) {
                if (dists[i] < min && waiting.contains(i)) {
                    min = dists[i];
                    minNode = i;
                }
            }
            waiting.remove(minNode);
            if (fireHouse[minNode]) {
                fireHouseNum--;
                System.out.print(minNode + "\t" + end + "\t" + min + "\t" + minNode);
                int cur = minNode;
                while (children[cur] != 0) {
                    System.out.print("\t" + children[cur]);
                    cur = children[cur];
                }
                System.out.println();
            }
            if (fireHouseNum == 0) break;

            for (int i = 1; i <= n; i++) {
                if (map[i][minNode] >= 0 && waiting.contains(i)) {
                    if (min + map[i][minNode] < dists[i]) {
                        dists[i] = min + map[i][minNode];
                        children[i] = minNode;
                    }
                }
            }
        }
    }
}
