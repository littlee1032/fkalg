import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class GooseTattarrattatDiv1 {
    public int getmin(String s) {
        int[] count = new int[26];
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            count[array[i] - 'a']++;
        }

        boolean[][] map = new boolean[26][26];
        boolean[] needMapping = new boolean[26];
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            if (array[i] != array[j]) {
                int ii = array[i] - 'a';
                int jj = array[j] - 'a';
                map[ii][jj] = true;
                map[jj][ii] = true;
                needMapping[ii] = true;
                needMapping[jj] = true;
            }
        }
        
        boolean flag = true;
        int ret = 0;
        while (flag) {
            flag = false;
            int maxIdx = -1;
            int max = 0;
            for (int i = 0; i < 26; i++) {
                if (needMapping[i] && count[i] > max) {
                    maxIdx = i;
                    max = count[i];
                    flag = true;
                }
            }
            if (flag) {
                Queue<Integer> queue = new LinkedList<Integer>();
                Set<Integer> visited = new HashSet<Integer>();
                needMapping[maxIdx] = false;
                visited.add(maxIdx);
                for (int i = 0; i < 26; i++) {
                    if (map[maxIdx][i]) {
                        queue.offer(i);
                    }
                }
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    if (!visited.contains(node)) {
                        needMapping[node] = false;
                        ret += count[node];
                        visited.add(node);
                        for (int i = 0; i < 26; i++) {
                            if (map[node][i]) queue.offer(i);
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    public static void main(String[] args) {
        GooseTattarrattatDiv1 f = new GooseTattarrattatDiv1();
        String[] strs = new String[] {"geese", "tattarrattat", "xyyzzzxxx", "xrepayuyubctwtykrauccnquqfuqvccuaakylwlcjuyhyammag", "abaabb"};
        for (int i = 0; i < 5; i++) {
            System.out.println(f.getmin(strs[i]));
        }
    }
}
