import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
    private static final int NMAX = 21;
    private static int vNum;
    private static final char[] cs = new char[NMAX];
    private static final int[] inDegree = new int[NMAX];
    private static final boolean[][] graph = new boolean[NMAX][NMAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            Arrays.fill(inDegree, 0);
            for (int i = 0; i < NMAX; i++) Arrays.fill(graph[i], false);

            String line = scan.nextLine();
            int idx = 0;
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            char[] vertices = line.toCharArray();
            Arrays.sort(vertices);
            for (char c : vertices) {
                if (' ' != c) {
                    cs[idx] = c;
                    map.put(c, idx++);
                }
            }
            vNum = idx;

            line = scan.nextLine();
            char[] constraints = line.toCharArray();
            for (int i = 0; i + 2 < constraints.length; i += 4) {
                int idx1 = map.get(constraints[i]);
                int idx2 = map.get(constraints[i+2]);
                graph[idx1][idx2] = true;
                inDegree[idx2]++;
            }

            StringBuilder sb = new StringBuilder();
            dfs(0, sb);
            System.out.println();
        }
    }

    private static void dfs(int offset, StringBuilder sb) {
        if (offset == vNum) {
            System.out.println(sb);
        } else {
            for (int i = 0; i < vNum; i++) {
                if (inDegree[i] == 0) {
                    inDegree[i]--;
                    sb.append(cs[i]);
                    for (int j = 0; j < vNum; j++) {
                        if (graph[i][j]) inDegree[j]--;
                    }
                    dfs(offset + 1, sb);
                    sb.setLength(offset);
                    for (int j = 0; j < vNum; j++) {
                        if (graph[i][j]) inDegree[j]++;
                    }
                    inDegree[i]++;
                }
            }
        }
    }
}
