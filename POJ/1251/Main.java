import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    private static class Road {
        public int start;
        public int end;
        public int cost;
        public int idx;
    }

    private static class MyComparator implements Comparator<Road> {
        @Override public int compare(Road o1, Road o2) {
            return o1.cost - o2.cost;
        }
    }

    private static final int ROAD_MAX = 80;
    private static boolean[] vis;
    private static boolean[] roadVis = new boolean[ROAD_MAX];
    private static Map<Integer, List<Road>> adjs = new HashMap<Integer, List<Road>>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            adjs.clear();
            for (int i = 0; i < n; i++) adjs.put(i, new LinkedList<Road>());
            vis = new boolean[n];
            Arrays.fill(roadVis, false);
            scan.nextLine();
            PriorityQueue<Road> pq = new PriorityQueue<Road>(75, new MyComparator());
            Road min = new Road();
            min.cost = Integer.MAX_VALUE;
            int roadIdx = 0;
            for (int i = 0; i < n - 1; i++) {
                String[] ars = scan.nextLine().split(" ");
                int me = ars[0].charAt(0) - 'A';
                int num = Integer.valueOf(ars[1]);
                for (int j = 0; j < num; j++) {
                    int you = ars[j * 2 + 2].charAt(0) - 'A';
                    int cost = Integer.valueOf(ars[j * 2 + 3]);
                    Road r = new Road();
                    r.start = me;
                    r.end = you;
                    r.cost = cost;
                    r.idx = roadIdx++;
                    adjs.get(me).add(r);
                    adjs.get(you).add(r);
                    if (r.cost < min.cost) min = r;
                }
            }
            pq.offer(min);
            roadVis[min.idx] = true;
            int ans = 0;
            while (!pq.isEmpty()) {
                Road r = pq.poll();
                if (!vis[r.start] || !vis[r.end]) {
                    vis[r.start] = true;
                    vis[r.end] = true;
                    ans += r.cost;
                    for (Road ar : adjs.get(r.start)) {
                        if (!roadVis[ar.idx] && (!vis[ar.start] || !vis[ar.end])) {
                            roadVis[ar.idx] = true;
                            pq.offer(ar);
                        }
                    }
                    for (Road ar : adjs.get(r.end)) {
                        if (!roadVis[ar.idx] && (!vis[ar.start] || !vis[ar.end])) {
                            roadVis[ar.idx] = true;
                            pq.offer(ar);
                        }
                    }
                }
            }
            System.out.println(ans);
            n = scan.nextInt();
        }
    }
}
