import java.util.Set;
import java.util.HashSet;

public class GameInDarknessDiv2 {
    public static final String AWIN = "Alice wins";
    public static final String BWIN = "Bob wins";

    public static final int[] dx = new int[] {1, 0, -1, 0};
    public static final int[] dy = new int[] {0, 1, 0, -1};

    public static final Set<String> fault = new HashSet<String>();

    public String check(String[] field, String[] moves) {
        if (moves.length == 0) return BWIN;
        int yNum = field.length;
        int xNum = field[0].length();
        boolean[][] map = new boolean[xNum][yNum];
        int Ax = 0;
        int Ay = 0;
        int Bx = 0;
        int By = 0;
        for (int i = 0; i < yNum; i++) {
            String r = field[i];
            for (int j = 0; j < xNum; j++) {
                char c = r.charAt(j);
                if ('#' == c) continue;
                map[j][i] = true;                
                if ('A' == c) {
                    Ax = j;
                    Ay = i;
                } else if ('B' == c) {
                    Bx = j;
                    By = i;
                }
            }
        }
        String move = moves[0];
        for (int i = 1; i < moves.length; i++) {
            move += moves[i];
        }
        
        if (canEscape(map, Ax, Ay, Bx, By, move, 0)) {
            return BWIN;
        } else {
            return AWIN;
        }
    }

    private boolean canEscape(boolean[][] map, int ax, int ay, int bx, int by, String move, int i) {
//        System.out.println("Alice at: (" + ax + "," + ay + ") and Bob at (" + bx + "," + by + ") at step " + i);
        if (ax == bx && ay == by) return false;
        if (i >= move.length()) return true;
        char c = move.charAt(i);
        int anx = ax;
        int any = ay;
        switch (c) {
        case 'U':
            any--;
            break;
        case 'D':
            any++;
            break;
        case 'R':
            anx++;
            break;
        default:
            anx--;
            break;
        }
        if (anx == bx && any == by) return false;
        for (int idx = 0; idx < 4; idx++) {
            int bnx = bx + dx[idx];
            int bny = by + dy[idx];
//            System.out.println("Alice new pos: (" + anx + "," + any + ") Bob new pos: (" + bnx + "," + bny + ")");
            if (bnx >= 0 && bnx < map.length && bny >= 0 && bny < map[0].length && map[bnx][bny]) {
                String key = anx + "," + any + "," + bnx + "," + bny + "," + (i + 1) + "," + idx;
//                System.out.println("Key: " + key + " " + fault.contains(key));
                if (fault.contains(key)) return false;
                if (canEscape(map, anx, any, bnx, bny, move, i + 1)) return true;
                else fault.add(key);
            }
        }
//        System.out.println("Alice at: (" + ax + "," + ay + ") and Bob at (" + bx + "," + by + ") fail at step " + i);
        return false;
    }
    
    public static void main(String[] args) {
        String[] field = new String[]{"A.###",
        ".B..."};
        String[] moves = new String[]{"RDRRRLLLLUDUDRLURDLUD"};
        System.out.println(new GameInDarknessDiv2().check(field, moves));
    }
}
