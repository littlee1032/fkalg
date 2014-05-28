import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    private final static boolean DEBUG = false;
    private final static String PRF = "wanted_";

    private static void print(int[][] mapping, Map<String, Integer> nameMapping) {
        for (Entry<String, Integer> node : nameMapping.entrySet()) {
            System.out.printf("%3d: %6s \n", node.getValue(), node.getKey());
        }
        System.out.println();
        //        for (int i = 0; i < nameMapping.size(); i++) {
        //            for (int j = 0; j < nameMapping.size(); j++) {
        //                System.out.printf("%10d", mapping[i][j]);
        //            }
        //            System.out.println();
        //        }
    }

    private static int maxFlow(boolean[][] map, int[][] capacity, int s, int t, int nodeNum) {
        int[][] flow = new int[nodeNum][nodeNum];
        int[] parent = new int[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            Arrays.fill(flow[i], 0);
        }

        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(s);
        while (true) {
            Arrays.fill(parent, -1);
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int i = 0; i < nodeNum; i++) {
                    if (node == i) continue;
                    if (!map[node][i]) continue;
                    if (capacity[node][i] - flow[node][i] <= 0) continue;
                    if (parent[i] != -1) continue;
                    parent[i] = node;
                    if (i == t) break;
                    q.offer(i);
                }
                if (DEBUG) System.out.println(q);
            }

            if (parent[t] == -1) break;
            int thisDiff = Integer.MAX_VALUE;
            for (int i = t; i != s; i = parent[i]) {
                thisDiff = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], thisDiff);
            }
            for (int i = t; i != s; i = parent[i]) {
                flow[parent[i]][i] += thisDiff;
                flow[i][parent[i]] -= thisDiff;
                if (DEBUG) {
                    System.out.println(i + " <---- " + parent[i] + "  " + thisDiff);
                }
            }
            if (DEBUG) System.out.println("---------------------------");
            q.offer(s);
        }

        int maxFlow = 0;
        for (int i = 0; i < nodeNum; i++) {
            maxFlow += flow[i][t];
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int cMax = 90000;
        int nMax = 802;
        Map<String, Integer> receptacles = new LinkedHashMap<String, Integer>();
        scan.nextLine();
        while (n-- > 0) {
            String rec = scan.nextLine().trim();
            int count = 0;
            if (receptacles.containsKey(rec)) {
                count = receptacles.get(rec);
            }
            count++;
            receptacles.put(rec, count);
            rec = PRF + rec;
            if (!receptacles.containsKey(rec)) receptacles.put(rec, 0);
        }
        int m = scan.nextInt();
        int wanted = m;
        scan.nextLine();
        while (m-- > 0) {
            String rec = PRF + scan.nextLine().trim().split(" ")[1];
            int count = 0;
            if (receptacles.containsKey(rec)) {
                count = receptacles.get(rec);
            }
            count++;
            receptacles.put(rec, count);
            rec = rec.substring(PRF.length());
            if (!receptacles.containsKey(rec)) receptacles.put(rec, 0);
        }

        // s is 0, t is 1
        Map<String, Integer> nodeMapping = new HashMap<String, Integer>();
        nodeMapping.put("s", 0);
        nodeMapping.put("t", 1);
        int count = 2;
        for (String node : receptacles.keySet()) {
            nodeMapping.put(node, count++);
        }
        boolean[][] mapping = new boolean[nMax][nMax];
        for (int i = 0; i < nMax; i++) {
            Arrays.fill(mapping[i], false);
        }
        int[][] capacity = new int[nMax][nMax];
        for (int i = 0; i < nMax; i++) {
            Arrays.fill(capacity[i], 0);
        }
        int k = scan.nextInt();
        scan.nextLine();
        while (k-- > 0) {
            String[] tmp = scan.nextLine().trim().split(" ");
            String sinkStr = tmp[0];
            String sourceStr = tmp[1];
            if (!nodeMapping.containsKey(sinkStr)) {
                nodeMapping.put(sinkStr, count++);
                receptacles.put(sinkStr, 0);
            }
            if (!nodeMapping.containsKey(sourceStr)) {
                nodeMapping.put(sourceStr, count++);
                receptacles.put(sourceStr,  0);
            }
            int source = nodeMapping.get(sourceStr);
            int sink = nodeMapping.get(sinkStr);
            mapping[source][sink] = true;
            capacity[source][sink] = cMax;
            mapping[sink][source] = true;
            capacity[sink][source] = 0;
        }

        int nodeNum = nodeMapping.size() + 2;
        for (String node : receptacles.keySet()) {
            if (node.startsWith(PRF)) {
                int source = nodeMapping.get(node);
                int sink = nodeMapping.get(node.substring(PRF.length()));
                mapping[source][sink] = true;
                capacity[source][sink] = cMax;
                mapping[sink][source] = true;
                capacity[sink][source] = cMax;

                // t
                mapping[source][1] = true;
                capacity[source][1] = receptacles.get(node);
                mapping[1][source] = true;
                capacity[1][source] = 0;
            } else {
                int sink = nodeMapping.get(node);
                // s
                mapping[0][sink] = true;
                capacity[0][sink] = receptacles.get(node);
                mapping[sink][0] = true;
                capacity[sink][0] = 0;
            }
        }

        if (DEBUG) {
            System.out.println("mapping:");
            print(capacity, nodeMapping);
        }
        int maxflow = maxFlow(mapping, capacity, 0, 1, nodeNum);
        System.out.println(wanted - maxflow);
    }
}
