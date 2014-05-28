import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    private static boolean dfs(int point, boolean[][] map, int num, int[] result, boolean[] checked) {
        for (int i = 1; i <= num; i++) {
            if (map[point][i] && !checked[i]) {
                checked[i] = true;
                if (result[i] == 0 || dfs(result[i], map, num, result, checked)) {
                    result[i] = point;
                    return true;
                }
            }
        }
        return false;
    }

    private static int hungry(boolean[][] map, int num1, int num2, int[] result, boolean[] checked) {
        Arrays.fill(result, 0);
        int max = 0;
        for (int i = 1; i <= num1; i++) {
            Arrays.fill(checked, false);
            if (dfs(i, map, num2, result, checked)) {
                max++;
            }
        }

        return max;
    }

    static class MyComparator implements Comparator<String> {
        @Override public int compare(String s1, String s2) {
            int idx = s1.indexOf(":");
            String cmp1 = s1.substring(0, idx);
            idx = s2.indexOf(":");
            String cmp2 = s2.substring(0, idx);
            return cmp1.compareTo(cmp2);
        }
    }

    public static void main(String[] args) {
        int nMax = 21;
        String[] names = new String[nMax];
        String[] ids = new String[nMax];
        Arrays.fill(names, "");
        boolean[][] map = new boolean[nMax][nMax];
        for (int i = 0; i < nMax; i++) {
            Arrays.fill(map[i], true);
        }
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        String nextLine = scan.nextLine();
        String[] tmpIds = nextLine.split(" ");
        System.arraycopy(tmpIds, 0, ids, 1, n);
        nextLine = scan.nextLine();
        String[] logs = nextLine.split(" ");
        Set<String> currentNames = new HashSet<String>();
        Set<String> namesShowed = new HashSet<String>();
        int idx = 1;
        while (!"Q".equals(logs[0])) {
            if ("E".equals(logs[0])) {
                String name = logs[1];
                currentNames.add(name);
                if (!namesShowed.contains(name)) {
                    namesShowed.add(name);
                    names[idx++] = name;
                }
            } else if ("M".equals(logs[0])) {
                String id = logs[1];
                int idIdx = 0;
                for (int i = 1; i <= n; i++) {
                    if (id.equals(ids[i])) {
                        idIdx = i;
                        break;
                    }
                }
                for (int i = 1; i <= n; i++) {
                    if (!currentNames.contains(names[i])) {
                        map[idIdx][i] = false;
                    }
                }
            } else {
                // L
                currentNames.remove(logs[1]);
            }
            nextLine = scan.nextLine();
            logs = nextLine.split(" ");
        }
        int[] result = new int[n + 1];
        int[] ret = new int[n + 1];
        Arrays.fill(result, 0);
        for (int i = 1; i <= n; i++) {
            int matchCount = 0;
            int nameIdx = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j]) {
                    matchCount++;
                    nameIdx = j;
                }
            }
            if (matchCount == 1) {
                ret[nameIdx] = i;
                map[i][nameIdx] = false;
                for (int kk = 1; kk <= n; kk++) {
                    map[kk][nameIdx] = false;
                }
            }
        }
        boolean[] checked = new boolean[n + 1];
        int maxCount = hungry(map, n, n, result, checked);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j]) {
                    map[i][j] = false;
                    int max = hungry(map, n, n, result, checked);
                    map[i][j] = true;
                    if (max < maxCount) {
                        ret[j] = i;
                        break;
                    }
                }
            }
        }
        List<String> rets = new LinkedList<String>();
        for (int i = 1; i <= n; i++) {
            String name = names[i];
            String id = ret[i] == 0 ? "???" : ids[ret[i]];
            rets.add(name + ":" + id);
        }
        Collections.sort(rets, new MyComparator());
        for (String s : rets) {
            System.out.println(s);
        }
    }
}
