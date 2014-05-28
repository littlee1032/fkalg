import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static int[] parents;
    private static int[] ranks;
    private static int n;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        parents = new int[n+1];
        ranks = new int[n+1];
        for (int i = 0; i < n+1; i++) parents[i] = i;
        int k = scan.nextInt();

        int lieNum = 0;
        for (int i = 0; i < k; i++) {
            int rel = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            if (x > n || y > n || !isTrue(rel, x, y)) {
                lieNum++;
                //                System.out.println("Wrong: " + rel + " " + x + " " + y);
            }
        }

        System.out.println(lieNum);
    }

    private static boolean isTrue(int rel, int x, int y) {
        int pa = getParent(x);
        int py = getParent(y);
        if (pa == py) {
            if (rel == 1) return ranks[x] == ranks[y];
            else return (ranks[x] - ranks[y] + 1) % 3 == 0;
        }
        // merge
        parents[py] = x;
        if (rel == 1) ranks[py] = (ranks[x] - ranks[y] + 4) % 3;
        else ranks[py] = (ranks[x] - ranks[y] + 5) % 3;
        return true;
    }

    private static int getParent(int idx) {
        int p = parents[idx];
        if (p != idx) {
            parents[idx] = getParent(p);
            ranks[idx] = (ranks[p] + ranks[idx]) % 3;
        }
        return parents[idx];
    }
}
