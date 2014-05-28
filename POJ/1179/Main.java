import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static int MAX = 20000000;
    private static int MIN = -20000000;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] ops = new int[n];
        int[] nums = new int[n];
        scan.nextLine();
        String[] tmp = scan.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            ops[i] = "t".equals(tmp[2*i]) ? 0 : 1;
            nums[i] = Integer.valueOf(tmp[2*i+1]);
        }
        /*
        System.out.println("ops: " + Arrays.toString(ops));
        System.out.println("nums: " + Arrays.toString(nums));
        */
        int[][] maxdp = new int[n][n];
        int[][] mindp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(maxdp[i], MIN);
            Arrays.fill(mindp[i], MAX);

            maxdp[i][i] = nums[i];
            mindp[i][i] = nums[i];
        }

        for (int skip = 1; skip <= n - 1; skip++) {
            for (int s = 0; s < n; s++) {
                int e = (s + skip) % n;
                //System.out.println("s: " + s + " e: " + e);
                for (int skip2 = 0; skip2 < skip; skip2++) {
                    int opIdx = (s+skip2+1) % n;
                    int midIdx = (s+skip2) % n;
                    int[] lefts = new int[]{mindp[s][midIdx], maxdp[s][midIdx]};
                    int[] rights = new int[]{mindp[(midIdx+1) % n][e], maxdp[(midIdx+1) % n][e]};
                    int origMax = maxdp[s][e];
                    int origMin = mindp[s][e];
                    //System.out.println("Orig: " + origMax + " " + origMin);
                    if (ops[opIdx] == 0) {
                        // +
                        maxdp[s][e] = Math.max(origMax, lefts[1] + rights[1]);
                        mindp[s][e] = Math.min(origMin, lefts[0] + rights[0]);
                    } else {
                        // *
                        int curMax = MIN;
                        int curMin = MAX;
                        for (int idx1 = 0; idx1 < 2; idx1++)
                            for (int idx2 = 0; idx2 < 2; idx2++) {
                                curMax = Math.max(curMax, lefts[idx1] * rights[idx2]);
                                curMin = Math.min(curMin, lefts[idx1] * rights[idx2]);
                            }
                        maxdp[s][e] = Math.max(maxdp[s][e], curMax);
                        mindp[s][e] = Math.min(mindp[s][e], curMin);
                    }
                    //System.out.println("Res: " + maxdp[s][e] + " " + mindp[s][e]);
                }
            }
        }
        int curMax = MIN;
        List<Integer> cands = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            int nIdx = (i+1) % n;
            int mMax = maxdp[nIdx][i];
            //System.out.println((i+1) + ": " + maxdp[nIdx][i]);
            if (mMax > curMax) {
                curMax = mMax;
                cands.clear();
                cands.add(nIdx+1);
            } else if (mMax == curMax) {
                cands.add(nIdx+1);
            }
        }
        Collections.sort(cands);
        System.out.println(curMax);
        System.out.print(cands.get(0));
        for (int i = 1; i < cands.size(); i++) System.out.print(" " + cands.get(i));
        System.out.println();
    }
}
