import java.util.Arrays;

public class Family {
    private boolean[][] map;
    private int[] index;

    public String isFamily(int[] parent1, int[] parent2) {
        int n = parent1.length;
        map = new boolean[n][n];
        index = new int[n];
        Arrays.fill(index, -1);
        for (int i = 0; i < n; i++) {
            if (parent1[i] != -1) {
                map[parent1[i]][parent2[i]] = true;
                map[parent2[i]][parent1[i]] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (index[i] == -1) {
                if (!dfs(i, 0)) {
                    return "Impossible";
                }
            }
        }

        return "Possible";
    }

    private boolean dfs(int n, int idx) {
        index[n] = idx;
        int nextIdx = 1 - idx;
        for (int i = 0; i < index.length; i++) {
            if (map[n][i]) {
                if (index[i] != -1 && index[i] != nextIdx)
                    return false;
                else {
                    if (index[i] == -1 && !dfs(i, nextIdx))
                        return false;
                }
            }
        }
        return true;
    }
}
