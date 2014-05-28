import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static int getParent(int i, int[] parent) {
        int p = i;
        while (p != parent[p]) {
            p = parent[p];
        }

        while (parent[i] != p) {
            parent[i] = p;
            i = parent[i];
        }

        return p;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine().trim();

        int n = line.length();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            char c = line.charAt(i);
            int n1 = i;
            int n2 = (i + 1) % n;
            if ('0' == c) {
                parent[n2] = getParent(n1, parent);
            } else {
                parent[n1] = getParent(n2, parent);
            }
        }
        

        Set<Integer> groups = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            groups.add(parent[i]);
        }

        System.out.println(groups.size());
    }
}
