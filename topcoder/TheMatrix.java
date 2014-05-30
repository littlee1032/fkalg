import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TheMatrix {
    public int MaxArea(String[] board) {
        int row = board.length;
        int col = board[0].length();
        Map<Integer, List<int[]>> rows = new HashMap<Integer, List<int[]>>();
        for (int i = 0; i < row; i++) {
            char[] arr = board[i].toCharArray();
            int start = 0;
            for (int j = 0; j < col; j++) {
                if (j + 1 < col && arr[j] != arr[j + 1])
                    continue;
                else {
                    if (!rows.containsKey(i)) {
                        rows.put(i, new LinkedList<int[]>());
                    }
                    int[] e = new int[] { arr[start] - '0', start, j + 1 };
                    start = j + 1;
                    rows.get(i).add(e);
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < row; i++) {
            for (int[] e : rows.get(i)) {
                ret = Math.max(ret, dfs(e[1], e[2], e[0], i, i, row, rows));
            }
        }
        return ret;
    }

    private int dfs(int start, int end, int symbol, int startRow, int preRow, int rowNum, Map<Integer, List<int[]>> rows) {
        int currentMax = (end - start) * (preRow - startRow + 1);
        if (preRow + 1 >= rowNum) {
            return currentMax;
        }
        for (int[] e : rows.get(preRow + 1)) {
            if (e[2] <= start)
                continue;
            if (e[1] >= end)
                break;
            int cs = Math.max(start, e[1]);
            int ce = Math.min(end, e[2]);
            int symbol1 = (cs - start + symbol) % 2;
            int symbol2 = (cs - e[1] + e[0]) % 2;
            if (symbol1 == symbol2)
                continue;
            currentMax = Math.max(currentMax, dfs(cs, ce, symbol2, startRow, preRow + 1, rowNum, rows));
        }
        return currentMax;
    }
}
