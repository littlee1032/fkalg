import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;

public class Main {
    private static class MC implements Comparator<int[]> {
        @Override public int compare(int[] c1, int[] c2) {
            return c1[2] - c2[2];
        }
    }

    private static int findParent(int n, int[] parents) {
        if (parents[n] != n) {
            parents[n] = findParent(parents[n], parents);
        }
        return parents[n];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Comparator<int[]> cmp = new MC();
        while (scan.hasNext()) {
            int n = scan.nextInt();
            List<int[]> nodes = new LinkedList<int[]>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int dist = scan.nextInt();
                    if (i != j) nodes.add(new int[]{i, j, dist});
                }
            }
            Collections.sort(nodes, cmp);
            int[] parents = new int[n];
            for (int i = 0; i < n; i++) parents[i] = i;
            long sum = 0;
            for (int[] node : nodes) {
                int e1 = node[0];
                int p1 = findParent(e1, parents);
                int e2 = node[1];
                int p2 = findParent(e2, parents);
                int len = node[2];
                if (p1 != p2) {
                    sum += len;
                    parents[p2] = p1;
                }
            }
            System.out.println(sum);
        }
    }
}
