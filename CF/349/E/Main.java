import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int q = scan.nextInt();
        long[] arr = new long[n];
        scan.nextLine();
        String line = scan.nextLine();
        String[] tmp = line.split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.valueOf(tmp[i]);
        }

        int[][] intersects = new int[m][m];
        long[] add = new long[m];
        long[] sums = new long[m];
        boolean[] isBigger = new boolean[m];
        int bar = (int)Math.sqrt(n);
        int[][] sets = new int[m][];
        for (int i = 0; i < m; i++) {
            line = scan.nextLine();
            tmp = line.split(" ");
            int k = Integer.valueOf(tmp[0]);
            sets[i] = new int[k];
            for (int j = 0; j < k; j++) {
                sets[i][j] = Integer.valueOf(tmp[j + 1]);
            }
            Arrays.sort(sets[i]);
            if (k >= bar) {
                isBigger[i] = true;
                long sum = 0;
                for (int j = 0; j < k; j++) {
                    sum += arr[sets[i][j] - 1];
                }
                sums[i] = sum;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (!isBigger[j] && !isBigger[i]) continue;
                if (i == j) continue;
                for (int k = 0; k < sets[i].length; k++) {
                    if (Arrays.binarySearch(sets[j], sets[i][k]) >= 0) {
                        intersects[i][j]++;
                    }
                }
            }
        }

        for (int i = 0; i < q; i++) {
            /*
            System.out.println("arr: " + Arrays.toString(arr));
            System.out.println("sums: " + Arrays.toString(sums));
            System.out.println("adds: " + Arrays.toString(add));
            */
            line = scan.nextLine();
            tmp = line.split(" ");
            int setNo = Integer.valueOf(tmp[1]) - 1;
            if ("?".equals(tmp[0])) {
                if (isBigger[setNo]) {
                    System.out.println(add[setNo] * sets[setNo].length + sums[setNo]);
                } else {
                    long sum = 0;
                    for (int k = 0; k < sets[setNo].length; k++) {
                        sum += arr[sets[setNo][k] - 1];
                    }
                    for (int j = 0; j < m; j++) {
                        sum += intersects[setNo][j] * add[j];
                    }
                    System.out.println(sum);
                }
            } else {
                int incr = Integer.valueOf(tmp[2]);
                if (isBigger[setNo]) {
                    add[setNo] += incr;
                    for (int j = 0; j < m; j++) {
                        sums[j] += (long)incr * intersects[j][setNo];
                    }
                } else {
                    for (int k = 0; k < sets[setNo].length; k++) {
                        arr[sets[setNo][k] - 1] += incr;
                    }
                    for (int j = 0; j < m; j++) {
                        sums[j] += intersects[j][setNo] * (long)incr;
                    }
                }
            }
        }
    }
}
