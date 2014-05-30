
public class ConvertibleStrings {
    public void print(int[][] map, boolean[][] flag) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("   ");
            for (int j = 0; j < flag.length; j++) {
                System.out.print(flag[i][j] ? 1 + " " : 0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int minRemoved = Integer.MAX_VALUE;

    public void chose(int[][] map, boolean[][] flag, int h, int lastRemoved) {
        if (h == 10) {
            if (lastRemoved < minRemoved) minRemoved = lastRemoved;
            return;
        }
        for (int j = 1; j < 10; j++) {
            if (map[h][j] > 0) {
                boolean good = true;
                for (int i = 0; i < h; i++) {
                    if (flag[i][j]) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                   flag[h][j] = true;
                   int removing = map[h][0] - map[h][j];
                   if (lastRemoved + removing < minRemoved) {
                       chose(map, flag, h + 1, lastRemoved + removing);
                   }
                   flag[h][j] = false;
                }
            }
        }
        if (lastRemoved + map[h][0] < minRemoved) {
            chose(map, flag, h + 1, lastRemoved + map[h][0]);
        }
    }

    public int leastRemovals(String A, String B) {
        int[][] map = new int[10][10];
        int len = A.length();
        for (int i = 0; i < len; i++) {
            int f = A.charAt(i) - 'A' + 1;
            int t = B.charAt(i) - 'A' + 1;
            map[f][t]++;
            map[f][0]++;
        }
        boolean[][] chose = new boolean[10][10];
        chose(map, chose, 1, 0);
        return minRemoved;
    }
    
    public static void main(String[] args) {
        String s1 = "FHDHIFFAECCAHICCDFFBFHEHB";
        String s2 = "GGGHAGEIDHICFCFHEIDCDBBBH";
        
        ConvertibleStrings m = new ConvertibleStrings();
        System.out.println(m.leastRemovals(s1, s2));
    }
}
