import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class GearsDiv1 {
    public int getmin(String color, String[] graph) {
        int ret = Integer.MAX_VALUE;
        int n = color.length();
        char[] carr = color.toCharArray();
        int[] before = new int[n];
        int cnt1 = 0;
        int cnt2 = 0;
        for (int r = 0; r < 2; r++) {
            for (int g = 0; g < 2; g++) {
                for (int b = 0; b < 2; b++) {
                    if (r + g + b == 0 || r + g + b == 3) continue;
                    cnt1 = 0;
                    cnt2 = 0;
                    char c1 = ' ';
                    char c2 = ' ';
                    if (r == g) {
                        c1 = 'R';
                        c2 = 'G';
                    }
                    if (g == b) {
                        c1 = 'G';
                        c2 = 'B';
                    }
                    if (b == r) {
                        c1 = 'B';
                        c2 = 'R';
                    }
                    Arrays.fill(before, 0);
                    for (int i = 0; i < n; i++) {
                        char c = carr[i];
                        if (c == c1) {
                            before[i] = cnt1;
                            cnt1++;
                        } else if (c == c2) {
                            before[i] = cnt2;
                            cnt2++;
                        }
                    }
                    int s = cnt1 * 2 + cnt2 * 2;
                    int t = s + 1;
                    int num = t + 1;
                    int[][] capacity = new int[num][num];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if ('N' == graph[i].charAt(j)) continue;
                            if (carr[i] == c1 && carr[j] == c2) {
                                capacity[before[i] + cnt1][before[j] + 2 * cnt1]++;
                            }
                        }
                    }
                    for (int i = 0; i < cnt1; i++) {
                        capacity[i][i + cnt1] = 1;
                        capacity[s][i] = 1;
                    }
                    for (int i = 0; i < cnt2; i++) {
                        capacity[i + 2 * cnt1][i + 2 * cnt1 + cnt2] = 1;
                        capacity[i + 2 * cnt1 + cnt2][t] = 1;
                    }
                    int f = flow(capacity, s, t, num);
                    ret = Math.min(ret, f);
                }
            }
        }
        return ret;
    }

    public int flow(int[][] c, int s, int t, int n) {
        int ret = 0;
        int[] p = new int[n];
        int num = c.length;
        int[][] flow = new int[num][num];
        Queue<Integer> queue = new LinkedList<Integer>();
        while (true) {
            Arrays.fill(p, -1);
            queue.offer(s);
            while (!queue.isEmpty()) {
                int start = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (c[start][i] - flow[start][i] > 0 && i != start && p[i] == -1) {
                        queue.offer(i);
                        p[i] = start;
                    }
                }
            }
            if (p[t] == -1) break;
            ret++;  
            int node = t;
            while (node != s) {
                flow[p[node]][node]++;
                flow[node][p[node]]--;
                node = p[node];
            }
        }
        return ret;
    }
    
    public static void main(String[] args) {
        GearsDiv1 f = new GearsDiv1();
        String[] strs = new String[]{"RGB", "RGBR"};
        String[][] graphs = new String[][]{
                {"NYY","YNY","YYN"}, {"NNNN","NNNN","NNNN","NNNN"}
        };
        for (int i = 0; i < strs.length; i++)
            System.out.println(f.getmin(strs[i], graphs[i]));
    }
}
