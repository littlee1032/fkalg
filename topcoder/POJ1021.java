import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class POJ1021 {
    public static class XYPair {
        public int x;
        public int y;
        
        public XYPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(XYPair other) {
            if (this == other)
                return true;
            else
                return (x == other.x) && (y == other.y);
        }
        
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    
    public static class Cluster {
        public XYPair leftTop;
        public XYPair rightBottom;
        
        public String toString() {
            return leftTop + "====>" + rightBottom;
        }
    }
    
    public static List<Cluster> getClusters (int[][] a, int m, int n) {
        List<Cluster> ret = new ArrayList<Cluster>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != 1)
                    continue;
                else {
                    Cluster cluster = new Cluster();
                    cluster.leftTop = new XYPair(i, j);
                    cluster.rightBottom = new XYPair(i, j);
                    updateCluster(a, i, j, cluster, m, n);
                    if (!cluster.leftTop.equals(cluster.rightBottom)) {
                        ret.add(cluster);
                    }
                }
            }
        }
        
        return ret;
    }
    
    public static void updateCluster(int[][] a, int x , int y, Cluster cluster, int m, int n) {
        if (a[x][y] != 1)
            return;
        else {
            a[x][y] = 2;
            if (x < cluster.leftTop.x) {
                cluster.leftTop.x = x;
            }
            if (x > cluster.rightBottom.x) {
                cluster.rightBottom.x = x;
            }
            if (y < cluster.rightBottom.y) {
                cluster.rightBottom.y = y;
            }
            if (y > cluster.leftTop.y) {
                cluster.leftTop.y = y;
            }
        }
        if (x - 1 >= 0)
            updateCluster(a, x - 1, y, cluster, m, n);
        if (x + 1 < m)
            updateCluster(a, x + 1, y, cluster, m, n);
        if (y - 1 >= 0)
            updateCluster(a, x, y - 1, cluster, m, n);
        if (y + 1 < n)
            updateCluster(a, x, y + 1, cluster, m, n);
    }
     
    public static boolean clusterEq(int[][] a, XYPair aLT, XYPair aRB, int [][] b, XYPair bLT, XYPair bRB) {
        int axlen = aRB.x - aLT.x;
        int aylen = aLT.y - aRB.y;
        int bxlen = bRB.x - bLT.x;
        int bylen = bLT.y - bRB.y;
        
        if (axlen + aylen != bxlen + bylen || axlen * aylen != bxlen * bylen)
            return false;
        
        XYPair bRT = new XYPair(bRB.x, bLT.y);
        XYPair bLB = new XYPair(bLT.x, bRB.y);

        if (axlen == bxlen) {
            if (matrixEq(a, aLT, aRB, b, bLT, bRB, 1, 1, false))
                return true;
            if (matrixEq(a, aLT, aRB, b, bRT, bLB, -1, 1, false))
                return true;
            if (matrixEq(a, aLT, aRB, b, bRB, bLT, -1, -1, false))
                return true;
            if (matrixEq(a, aLT, aRB, b, bLB, bRT, 1, -1, false))
                return true;
        }
        
        if (axlen == bylen) {
            if (matrixEq(a, aLT, aRB, b, bLT, bRB, 1, 1, true))
                return true;
            if (matrixEq(a, aLT, aRB, b, bRT, bLB, 1, -1, true))
                return true;
            if (matrixEq(a, aLT, aRB, b, bRB, bLT, -1, -1, true))
                return true;
            if (matrixEq(a, aLT, aRB, b, bLB, bRT, -1, 1, true))
                return true;
        }
        
        return false;
    }

    private static boolean matrixEq(int[][] a, XYPair aStart, XYPair aEnd, int[][] b,
            XYPair bStart, XYPair bEnd, int xDirect, int yDirect,
            boolean xyTransform) {
        for (int xDiff = 0; aStart.x + xDiff <= aEnd.x; xDiff++) {
            for (int yDiff = 0; aStart.y - yDiff >= aEnd.y; yDiff++) {
                if (xyTransform) {
                    if (a[aStart.x + xDiff][aStart.y - yDiff] != b[bStart.x
                            + yDiff * yDirect][bStart.y - xDiff * xDirect])
                        return false;
                } else {
                    if (a[aStart.x + xDiff][aStart.y - yDiff] != b[bStart.x
                            + xDiff * xDirect][bStart.y - yDiff * yDirect])
                        return false;
                }
            }
        }

        return true;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] boardA = new int[100][100];
        int[][] boardB = new int[100][100];
        int testNum = scan.nextInt();
        for (int i = 0; i < testNum; i++) {
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++)
                    boardA[x][y] = 0;
            }
            
            int m = scan.nextInt();
            int n = scan.nextInt();
            int pieceNum = scan.nextInt();
            for (int counter = 0; counter < pieceNum; counter++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                boardA[x][y] = 1;
            }
            
//            System.out.println("====== A ========");
//            for (int yy = n - 1; yy >= 0; yy--) {
//                for (int xx = 0; xx < m; xx++) {
//                    if (boardA[xx][yy] == 1) {
//                        System.out.print("X" + " ");
//                    } else {
//                        System.out.print(". ");
//                    }
//                }
//                System.out.println();
//            }
            
            List<Cluster> aClusters = getClusters(boardA, m, n);
            
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++)
                    boardB[x][y] = 0;
            }
            
            for (int counter = 0; counter < pieceNum; counter++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                boardB[x][y] = 1;
            }
            
//            System.out.println("======== B ===========");
//            for (int yy = n - 1; yy >= 0; yy--) {
//                for (int xx = 0; xx < m; xx++) {
//                    if (boardB[xx][yy] == 1) {
//                        System.out.print("X" + " ");
//                    } else {
//                        System.out.print(". ");
//                    }
//                }
//                System.out.println();
//            }
            
            List<Cluster> bClusters = getClusters(boardB, m, n);
            
            if (aClusters.size() != bClusters.size())
                System.out.println("NO");
            else {
                boolean bypass = false;
                for (Cluster cluster : aClusters) {
                    boolean found = false;
                    Iterator<Cluster> iter = bClusters.iterator();
                    while (iter.hasNext()) {
                        Cluster other = iter.next();
                        if (clusterEq(boardA, cluster.leftTop, cluster.rightBottom, boardB, other.leftTop, other.rightBottom)) {
                            found = true;
                            iter.remove();
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("NO");
                        bypass = true;
                        break;
                    }
                }
                
                if (!bypass) {
                    if (bClusters.size() != 0)
                        System.out.println("NO");
                    else
                        System.out.println("YES");
                }
            }
        }
    }

}
