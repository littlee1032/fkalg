import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AlienAndGame {
    private static class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) return a[0] - b[0]; // start
            else return a[1] - b[1]; // row
        }
    }
    
    private static Comparator<int[]> cmp = new MyComparator();

    public int getNumber(String[] board) {
        int row = board.length;
        int col = board[0].length();
        
        Map<Integer, List<int[]>> containers = new HashMap<Integer, List<int[]>>();
        for (int i = 1; i <= 50; i++) containers.put(i, new LinkedList<int[]>());

        for (int i = 0; i < row; i++) {
            int start = 0;
            char pre = board[i].charAt(0);
            for (int j = 0; j < col; j++) {
                char cur = board[i].charAt(j);
                if (pre != cur) {
                    putIntoContainers(containers, j-start, start, i);
                    start = j;
                    pre = cur;
                }
            }
            putIntoContainers(containers, col-start, start, i);
        }

        for (int size = 50; size > 1; size--) {
            List<int[]> cands = containers.get(size);
            if (cands.size() < size) continue;           
            Collections.sort(cands, cmp);
            int ps = -1;
            int pr = -1;
            int rowNum = 0;
            for (int[] cand : cands) {
                int ms = cand[0];
                int mr = cand[1];
                if (ms != ps || mr != pr+1) {
                    ps = ms;
                    rowNum = 1;
                } else {
                    rowNum++;
                    if (rowNum == size) return size * size;
                }
                pr = mr;
            }
        }
        return 1;
    }
    
    private static void putIntoContainers(Map<Integer, List<int[]>> containers, int length, int start, int row) {
        for (int i = 2; i <= length; i++) {
            List<int[]> cands = containers.get(i);
            for (int j = start; j + i <= start + length; j++)
                cands.add(new int[]{j, row});
        }
    }
    
}
