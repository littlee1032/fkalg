import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;

public class Main {
    private static Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
    private static Set<Integer> sets = new HashSet<Integer>();
    private static List<int[]> boards = new ArrayList<int[]>();

    private static class MyComparator implements Comparator<int[]> {
        @Override public int compare(int[] a, int[] b) {
            for (int i = 0; i < 5; i++) {
                if (a[i] != b[i]) return a[i] - b[i];
            }
            return 0;
        }
    }

    private static void initMap(int sum) {
        for (int i = 0; i <= 9; i++) map.put(i, new ArrayList<int[]>());
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9 && j <= sum - i; j++) {
                for (int k = 0; k <= 9 && k <= sum - i - j; k++) {
                    for (int l = 0; l <= 9 && l <= sum-i-j-k; l++) {
                        for (int m = 1; m <= 9 && m <= sum-i-j-k-l; m++) {
                            if (i+j+k+l+m != sum) continue;
                            int num = 10000 * i + 1000 * j + 100 * k + 10 * l + m;
                            if (isPrime(num)) {
                                map.get(i).add(new int[]{j, k, l, m});
                                sets.add(num);
                            }
                        }
                    }
                }
            }
        }
        /*
        for (int i = 0; i <= 9; i++) {
            List<int[]> cands = map.get(i);
            for (int[] cand : cands) {
                System.out.print(i);
                for (int n : cand) System.out.print(n);
                System.out.print(", ");
            }
            System.out.println();
        }
        */
    }

    private static boolean isPrime(int num) {
        for (int i = 2; i <= (int)Math.sqrt(num)+1; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void validate(int[][] board, int sum) {
        for (int i = 1; i <= 4; i++) {
            int num = 10000*board[i][0] + 1000*board[i][1] + 100*board[i][2] + 10*board[i][3] + board[i][4];
            if (!sets.contains(num)) return;
        }


        int num = 10000*board[0][0] + 1000*board[1][1] + 100*board[2][2] + 10*board[3][3] + board[4][4];
        if (!sets.contains(num)) return;

        num = 10000*board[4][0] + 1000*board[3][1] + 100*board[2][2] + 10*board[1][3] + board[0][4];
        if (sets.contains(num)) {
            int[] okb = new int[5];
            for (int i = 0; i < 5; i++) {
                okb[i] = 10000*board[i][0]+1000*board[i][1]+100*board[i][2]+10*board[i][3]+board[i][4];
            }
            boards.add(okb);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int sum = scan.nextInt();
        int start = scan.nextInt();

        initMap(sum);
        int[][] board = new int[5][5];
        board[0][0] = start;

        for (int i = 0; i < map.get(start).size(); i++) {
            int[] cand1 = map.get(start).get(i);
            System.arraycopy(cand1, 0, board[0], 1, 4);

            for (int j = 0; j < map.get(board[0][4]).size(); j++) {
                int[] cand2 = map.get(board[0][4]).get(j);
                if (cand2[1] % 2 == 0 || cand2[2] % 2 == 0 || cand2[3] % 2 == 0
                    || cand2[0] % 2 == 0) continue;
                for (int jj = 0; jj < 4; jj++) board[jj+1][4] = cand2[jj];

                for (int k = 0; k < map.get(board[0][0]).size(); k++) {
                    int[] cand6 = map.get(board[0][0]).get(k);
                    if (cand6[0] * cand6[1] * cand6[2] * cand6[3] == 0) continue;
                    for (int jj = 0; jj < 4; jj++) board[jj+1][0] = cand6[jj];

                    for (int n = 0; n < map.get(board[0][1]).size(); n++) {
                        int[] cand3 = map.get(board[0][1]).get(n);
                        for (int kk = 0; kk < 4; kk++) board[kk+1][1] = cand3[kk];

                        for (int l = 0; l < map.get(board[0][2]).size(); l++) {
                            int[] cand4 = map.get(board[0][2]).get(l);
                            for (int ll = 0; ll < 4; ll++) board[ll+1][2] = cand4[ll];

                            for (int m = 0; m < map.get(board[0][3]).size(); m++) {
                                int[] cand5 = map.get(board[0][3]).get(m);
                                for (int mm = 0; mm < 4; mm++) board[mm+1][3] = cand5[mm];
                                validate(board, sum);
                            }
                        }
                    }
                }
            }
        }

        if (boards.isEmpty()) System.out.println();
        else {
            Collections.sort(boards, new MyComparator());
            for (int[] ab : boards) {
                for (int a : ab) {
                    System.out.println(a);
                }
                System.out.println();
            }
        }
    }
}
