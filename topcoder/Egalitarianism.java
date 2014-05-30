import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Egalitarianism {
    public int maxLen(boolean[][] rels, int start) {
        int n = rels.length;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        Set<Integer> checked = new HashSet<Integer>();
        checked.add(start);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            Integer[] holder = queue.toArray(new Integer[0]);
            queue.clear();
            for (Integer i : holder) {
                for (int idx = 0; idx < n; idx++) {
                    if (rels[i][idx] && !checked.contains(idx)) {
                        checked.add(idx);
                        dis[idx] = step;
                        queue.offer(idx);
                    }
                }
            }
            
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dis[i]);
        }
        return max;
    }

    public int maxDifference(String[] isFriend, int d) {
        int n = isFriend.length;
        boolean[][] rels = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isFriend[i].charAt(j) == 'Y')
                    rels[i][j] = true;
                else
                    rels[i][j] = false;
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, maxLen(rels, i));
            if (max == Integer.MAX_VALUE) return -1;
        }
        return d * max;
    }

    public static void main(String[] args) {
        String[] strs = new String[] {"NN",
        "NN"};
        int d = 1;
        System.out.println(new Egalitarianism().maxDifference(strs, d));
    }
}
