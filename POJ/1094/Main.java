import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static final boolean DEBUG = false;

    private static void print(int[][] map, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int topsort(int[][] map, int[][] copy, int[] result, int n) {
        int edgeNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = map[i][j];
                if (map[i][j] == 1) edgeNum++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 1) {
                    for (int k = 0; k < n; k++) {
                        if (copy[j][k] == 1) {
                            copy[i][k] = 1;
                            edgeNum++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] == 1 && copy[j][i] == 1) {
                    return 1;
                }
            }
        }

        int resultIdx = 0;
        boolean[] checked = new boolean[n];
        int checkNum = 0;
        while (checkNum < n) {
            if (DEBUG) print(copy, n);
            int zeroInDegreeIdx = -1;
            int zeroInDegreeNum = 0;
            for (int i = 0; i < n; i++) {
                if (checked[i]) continue;
                int inDegree = 0;

                for (int j = 0; j < n; j++) {
                    if (copy[j][i] == 1) {
                        inDegree++;
                    }
                }

                if (inDegree == 0) {
                    zeroInDegreeIdx = i;
                    zeroInDegreeNum++;
                }
            }

            if (zeroInDegreeNum > 1) {
                return -1;
            } else if (zeroInDegreeNum == 0) {
                return 1;
            } else {
                if (DEBUG) System.out.println("resultIdx: " + resultIdx);
                result[resultIdx] = zeroInDegreeIdx;
                resultIdx++;
                checked[zeroInDegreeIdx] = true;
                checkNum++;
                for (int j = 0; j < n; j++) {
                    if (copy[zeroInDegreeIdx][j] == 1) {
                        copy[zeroInDegreeIdx][j] = 0;
                        edgeNum--;
                    }
                }
                if (resultIdx == n) {
                    return 0;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int nMax = 26;
        int[][] map = new int[nMax][nMax];
        int[][] copy = new int[nMax][nMax];
        int[] result = new int[nMax];
        boolean[] checked = new boolean[nMax];
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        while (n + m > 0) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(map[i], 0);
                Arrays.fill(copy[i], 0);
            }
            scan.nextLine();
            boolean gogo = false;
            for (int i = 0; i < m; i++) {
                String line = scan.nextLine();
                if (gogo) continue;
                int left = line.charAt(0) - 'A';
                int right = line.charAt(2) - 'A';
                map[left][right] = 1;
                int ret = topsort(map, copy, result, n);
                if (ret == 0) {
                    System.out.print("Sorted sequence determined after " + (i + 1) + " relations: ");
                    for (int j = 0; j < n; j++) {
                        System.out.print((char)(result[j] + 'A'));
                    }
                    System.out.println(".");
                    gogo = true;
                } else if (ret == 1) {
                    System.out.println("Inconsistency found after " + (i + 1) + " relations.");
                    gogo = true;
                }
            }
            if (!gogo) {
                System.out.println("Sorted sequence cannot be determined.");
            }

            n = scan.nextInt();
            m = scan.nextInt();
        }
    }
}
