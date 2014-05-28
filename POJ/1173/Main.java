import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int m = scan.nextInt();

        int[][] bc = new int[n+1][k+1];
        int[][] gc = new int[n+1][k+1];

        for (int i = 0; i <= Math.min(n, k); i++) {
            bc[i][i] = 1;
            gc[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j * m < i) continue;
                int bcRet = 0;
                int gcRet = 0;
                for (int l = 1; l <= Math.min(m, i); l++) {
                    bcRet += gc[i-l][j-1];
                    gcRet += bc[i-l][j-1];
                }
                bc[i][j] = bcRet;
                gc[i][j] = gcRet;
            }
        }

        int total = bc[n][k];
        System.out.println(total);

        int s = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < s; i++) {
            System.out.println(rank(scan.nextLine().toCharArray(), bc, gc, m));
        }
    }

    private static int rank(char[] array, int[][] bc, int[][] gc, int max) {
        //        System.out.println("Num: " + Arrays.toString(array));
        if (array.length == 1) return 0;
        char pre = array[0];
        int[] barNums = new int[array.length];
        int barNum = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != pre) {
                barNum++;
            }
            barNums[i] = barNum;
            pre = array[i];
        }
        //        System.out.println("bars: " + Arrays.toString(barNums));
        int totalBar = barNums[array.length-1];
        int[] candidate = new int[array.length];
        int zeros = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) continue;
            if ('0' == array[i]) zeros++;
            else {
                candidate[i] = Math.max(0, max-zeros);
                zeros = 0;
            }
        }
        //        System.out.println("candidates: " + Arrays.toString(candidate));
        int ret = 0;
        for (int i = 1; i < array.length; i++) {
            if ('1' == array[i] && candidate[i] > 0) {
                if ('1' == array[i-1]) {
                    //                    System.out.println(i + " " + (array.length-i) + " " + (totalBar-barNums[i]));
                    ret += gc[array.length-i][totalBar - barNums[i]];
                } else {
                    // 01
                    for (int j = 0; j < candidate[i] && i+j < array.length; j++) {
                        ret += bc[array.length-i-j-1][totalBar - barNums[i]+1];
                        //                        System.out.println(i + " " + j + " " + (array.length-i-j-1) + " " + (totalBar-barNums[i]+1));
                    }
                }
            }
        }
        return ret;
    }
}
