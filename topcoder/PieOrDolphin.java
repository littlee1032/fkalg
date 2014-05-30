import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PieOrDolphin {
    private static final int[][] map = new int[50][50];
    private static final int[] toys = new int[50];
    
    public int[] Distribute(int[] choice1, int[] choice2) {
        int n = choice1.length;
        int[] ret = new int[n];
        Map<Integer, List<Integer>> dup = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            int p1 = choice1[i];
            int p2 = choice2[i];
            if (map[p1][p2] == 0) {
                map[p1][p2] = i;
                map[p2][p1] = -i;
            } else {
                int key = Math.min(p1, p2) * 100 + Math.max(p1, p2);
                if (!dup.containsKey(key)) dup.put(key, new LinkedList<Integer>());
                dup.get(key).add(Math.abs(map[p1][p2]));
                dup.get(key).add(i);
                map[p1][p2] = 0;
                map[p2][p1] = 0;      
            }
        }
        dfs(ret, 0);
//        for (Entry<Integer, List<Integer>> entry : dup.entrySet()) {
//            int cur = 1;
//            for (Integer i : entry.getValue()) {
//                ret[i] = cur;
//                cur = 3 - cur;
//            }
//        }
        return ret;
    }
    
    public static void main(String[] args) {
        int[] choice1 = new int[]{21,4,14,0,31,46,1,34,2,3,27,19,47,46,17,11,41,12,31,0,34,18,8,14,23,40,0,18,48,35,42,24,25,32,25,44,17,6,44,34,12,39,43,39,26,
                34,10,6,13,2,40,15,16,32,32,29,1,23,8,10,49,22,10,15,40,20,0,30,1,43,33,42,28,39,28,4,38,11,5,1,47,12,0,22,20,33,33,34,18,8,23,6};
        int[] choice2 = new int[]{25,5,39,20,44,47,11,49,42,17,25,15,23,11,32,17,24,4,11,47,27,41,40,0,49,27,5,28,6,11,18,0,17,1,0,32,45,28,17,5,13,40,40,25,33,
                7,8,32,12,0,39,30,8,39,23,9,8,34,34,37,5,1,24,23,0,29,11,42,29,40,24,18,37,1,21,0,31,47,23,33,45,48,31,11,40,45,24,22,19,26,37,39};
        PieOrDolphin test = new PieOrDolphin();
        int[] ret = test.Distribute(choice1, choice2);
        System.out.println(Arrays.toString(ret));
        int[] expect = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 1, 2, 1, 1, 1, 2, 2, 2,
                1, 2, 2, 2, 1, 2, 2, 1, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 2,
                2, 2, 2, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 2,
                2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1 };
        System.out.println(calc(choice1, choice2, ret));
        System.out.println(calc(choice1, choice2, expect));
    }
    
    public static int calc(int[] choice1, int[] choice2, int[] ret) {
        int[] toys = new int[50];
        for (int i = 0; i < choice1.length; i++) {
            if (ret[i] == 0) continue;
            if (ret[i] == 1) {
                toys[choice1[i]]++;
                toys[choice2[i]]--;
            } else {
                toys[choice1[i]]--;
                toys[choice2[i]]++;
            }
        }
        int sum = 0;
        System.out.println("TY:" + Arrays.toString(toys));
        for (int i = 0; i < toys.length; i++) sum += Math.abs(toys[i]);
        return sum;
    }
    
    private void dfs(int[] ret, int node) {
        for (int i = 0; i < 50; i++) {
            if (map[node][i] != 0) {
                // 0 no toy
                // 1 dolphin
                // 2 pie
                if (toys[node] == 0) {
                    toys[node] = 1;
                } else {
                    toys[node] = 3 - toys[node];
                }
                toys[i] = 3 - toys[node];
                if (map[node][i] > 0) ret[map[node][i]] = toys[node];
                else ret[-map[node][i]] = toys[i];
                map[node][i] = 0;
                
                dfs(ret, i);
            }
        }
    }
}
