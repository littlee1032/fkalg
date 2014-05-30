
public class EnclosingTriangleColorful {
    public int getNumber(int m, int[] x, int[] y) {
        int[][][] boarders = new int[4][m][2];
        for (int i = 1; i <= m - 1; i++) {
            boarders[0][i] = new int[]{0, i};
            boarders[1][i] = new int[]{m, i};
            boarders[2][i] = new int[]{i, m};
            boarders[3][i] = new int[]{i, 0};
        }
        
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            for (int im = 1; im < m; im++) {
                for (int j = i + 1; j < 4; j++) {
                    for (int jm = 1; jm < m; jm++) {
                        for (int k = j + 1; k < 4; k++) {
                            for (int km = 1; km < m; km++) {
                                if (test(x, y, boarders[i][im], boarders[j][jm], boarders[k][km])) {
                                    ret++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    private boolean test1(int x, int y, int[] p1, int[] p2, int[] p3) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        
        int dx1 = x - p1[0];
        int dy1 = y - p1[1];
        
        int dx2 = p3[0] - p1[0];
        int dy2 = p3[1] - p1[1];
        
        return false;
    }
    
    private boolean test(int[] x, int[] y, int[] is, int[] is2, int[] is3) {
//        int dx
        for (int i = 0; i < x.length; i++) {
//            int xx = is[0] - 
        }
        return false;
    }
}
