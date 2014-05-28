import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    private static Comparator<int[]> cmp = new MyComparator();

    private static class MyComparator implements Comparator<int[]> {
        @Override public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] rects = new int[n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                rects[i][j] = scan.nextInt();
            }
        }

        int ret = 0;
        int[] slices = new int[2*n];
        for (int i = 0; i < n; i++) {
            slices[2*i] = rects[i][0];
            slices[2*i+1] = rects[i][2];
        }
        Arrays.sort(slices);
        for (int i = 0; i < 2*n - 1; i++) {
            int left = slices[i];
            int right = slices[i+1];
            int width = right - left;
            List<int[]> cands = new ArrayList<int[]>();
            for (int j = 0; j < n; j++) {
                if (rects[j][0] <= left && rects[j][2] >= right) {
                    cands.add(new int[]{rects[j][1], 1});
                    cands.add(new int[]{rects[j][3], -1});
                }
            }
            Collections.sort(cands, cmp);
            int add = 0;
            int times = 0;
            for (int j = 0; j < cands.size(); j++) {
                int preAdd = add;
                add += cands.get(j)[1];
                if (preAdd == 0 && add == 1) times++;
                else if (preAdd == 1 && add == 0) times++;
            }
            ret += width * times;
        }
        slices = new int[2*n];
        for (int i = 0; i < n; i++) {
            slices[2*i] = rects[i][1];
            slices[2*i+1] = rects[i][3];
        }
        Arrays.sort(slices);
        for (int i = 0; i < 2*n - 1; i++) {
            int left = slices[i];
            int right = slices[i+1];
            int width = right - left;
            List<int[]> cands = new ArrayList<int[]>();
            for (int j = 0; j < n; j++) {
                if (rects[j][1] <= left && rects[j][3] >= right) {
                    cands.add(new int[]{rects[j][0], 1});
                    cands.add(new int[]{rects[j][2], -1});
                }
            }
            Collections.sort(cands, cmp);
            int add = 0;
            int times = 0;
            for (int j = 0; j < cands.size(); j++) {
                int preAdd = add;
                add += cands.get(j)[1];
                if (preAdd == 0 && add == 1) times++;
                else if (preAdd == 1 && add == 0) times++;
            }
            ret += width * times;
        }
        System.out.println(ret);
    }
}
