import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Main {
    private static final int NMAX = 50002;
    private static final Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();

    private static boolean relax(int[] d, int s, int e, int c) {
        if (d[s] + c > d[e]) {
            d[e] = d[s] + c;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] d = new int[NMAX];
        Arrays.fill(d, Integer.MIN_VALUE);

        int n = scan.nextInt();
        int maxNode = 0;
        int minNode = NMAX;
        for (int i = 0; i < n; i++) {
            int s = scan.nextInt() + 1;
            int e = scan.nextInt() + 2;
            int w = scan.nextInt();

            maxNode = Math.max(maxNode, Math.max(s, e));
            minNode = Math.min(minNode, Math.min(s, e));
            if (!map.containsKey(s)) {
                map.put(s, new LinkedList<int[]>());
            }
            int[] edge = new int[]{e, w};
            map.get(s).add(edge);
        }

        // init source node
        map.put(0, new LinkedList<int[]>());
        map.get(0).add(new int[]{minNode+1, 0});

        Queue<Integer> q = new LinkedList<Integer>();
        Set<Integer> filter = new HashSet<Integer>();
        q.offer(0);
        filter.add(0);
        d[0] = 0;
        while (!q.isEmpty()) {
            int idx = q.poll();
            //System.out.println("Considering: " + idx);
            filter.remove(idx);

            if (idx < maxNode && relax(d, idx, idx+1, 0) && !filter.contains(idx+1)) {
                q.offer(idx+1);
                filter.add(idx+1);
            }

            if (idx > minNode && relax(d, idx, idx-1, -1) && !filter.contains(idx-1)) {
                q.offer(idx-1);
                filter.add(idx-1);
            }

            if (map.containsKey(idx)) {
                for (int[] adj : map.get(idx)) {
                    int target = adj[0];
                    int w = adj[1];
                    if (relax(d, idx, target, w) && !filter.contains(target)) {
                        q.offer(target);
                        filter.add(target);
                    }
                }
            }
        }
        System.out.println(d[maxNode]);
    }
}
