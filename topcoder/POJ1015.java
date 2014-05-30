import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class POJ1015 {
    private static void select(int[] ps, int[] ds, int n, int m) {
        // pi - di + 20
        int[][] dp = new int[201][8400];
        int[][] route = new int[201][8400];
        
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += ps[i] - ds[i] + 21;
        }
        
        for (int j = 0; j <= sum; j++) {
            for (int k = 1; k <= n; k++) {
                int diff = ps[k] - ds[k] + 21;
                if (ps[k] + ds[k] + 1 > dp[1][diff]) {
                    dp[1][diff] = ps[k] + ds[k] + 1;
                    route[1][diff] = k;
                }
            }
        }
        
//        for (int j = 0; j <= sum; j++) {
//            System.out.print(dp[1][j] + " ");
//        }
//        System.out.println();
            
        for (int i = 2; i <= m; i++) {
            for (int j = 0; j <= sum; j++) {
                for (int k = 1; k <= n; k++) {
                    // loop to find the largest k
                    int diff = ps[k] - ds[k] + 21;
//                    try {
                    if (j >= diff && dp[i - 1][j - diff] > 0 && dp[i - 1][j - diff] + ps[k] + ds[k] > dp[i][j]) {
                        boolean used = false;
                        int ii = i - 1;
                        int jj = j - diff;
                        while (ii > 0 && jj > 0) {
                            if (route[ii][jj] == k) {
                                used = true;
                                break;
                            }
                            jj -= ps[route[ii][jj]] - ds[route[ii][jj]] + 21;
                            ii--;
                        }
                        if (!used) {
                            dp[i][j] = dp[i - 1][j - diff] + ps[k] + ds[k];
                            route[i][j] = k;
                        }
                    }
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("i: " + i + " j: " + j + " k: " + k);
//                        throw e;
//                    }
                }
            }
        }
        
//        for (int j = 1; j <= sum; j++)
//            System.out.print(j + " ");
//        System.out.println();
        
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= sum; j++) {
//                System.out.print(dp[i][j] + " ");
//                if (j >= 10)
//                    System.out.print(" ");
//            }
//            System.out.println();
//        }
//        
//        System.out.println();
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= sum; j++) {
//                System.out.print(route[i][j] + " ");
//                if (j >= 10)
//                    System.out.print(" ");
//            }
//            System.out.println();
//        }
        
        List<Integer> list = new LinkedList<Integer>();
        int zero = m * 21;
        int dt = 0;
        int k = 0;
        while (dt <= zero) {
            int left = 0;
            int right = 0;
            if (dp[m][zero - dt] != 0)
                left = dp[m][zero - dt];
            if (dp[m][zero + dt] != 0)
                right = dp[m][zero + dt];
            
            if (right != 0 && right >= left) {
                k = zero + dt;
                break;
            }
            if (left != 0 && left > right) {
                k = zero - dt;
                break;
            }
            dt++;
        }
        
//        System.out.println("k: " + k + " zero: " + zero + " dt: " + dt);
//        System.out.println("dp: " + dp[m][k]);
//        for (int j = 0; j <= sum; j++) {
//            System.out.print(dp[m][j] + " ");
//        }
//        System.out.println();
        
        int ii = m;
        int jj = k;
        int pa = 0;
        int da = 0;
        while (ii > 0 && jj >= 0) {
            int index = route[ii][jj];
            pa += ps[index];
            da += ds[index];
            list.add(index);
            ii--;
            jj -= ps[index] - ds[index] + 21;
        }
        
        Collections.sort(list);
        
        System.out.println("Best jury has value " + pa + " for prosecution and value " + da + " for defence:");
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext())
            System.out.print(" " + iter.next());
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
//        Scanner scan = new Scanner(new FileInputStream("/Users/lee/workspace/eclipse/POJ/resource/1015/1015.test"));
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] ps = new int[201];
        int[] ds = new int[201];
        
        int round = 0;
        while (n != 0 && m != 0) {
            round++;
            for (int i = 1; i <= n; i++) {
                ps[i] = scan.nextInt();
                ds[i] = scan.nextInt();
            }
            
            System.out.println("Jury #" + round);
            
            select(ps, ds, n, m);
            
            System.out.println();
            System.out.println();
//            System.out.println("m: " + m + " n: " + n);
            n = scan.nextInt();
            m = scan.nextInt();
        }
        
        scan.close();
    }

}
