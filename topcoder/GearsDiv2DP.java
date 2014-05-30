import java.util.Arrays;

public class GearsDiv2DP {
    public int getIdx(int n, int idx) {
        return (idx + n) % n;
    }

    public void print(char[] arr, int s, int e) {
        for (int i = s; i < e; i++) {
            System.out.print(i % 10);
        }
        System.out.println();
        for (int i = s; i < e; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    
    public void printDp(int[][] dp) {
        System.out.print("\t");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    System.out.print("X");
                } else {
                    System.out.print(dp[i][j]);
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getmin(String Directions) {
        char[] arr = Directions.toCharArray();
        char[] holder = new char[arr.length * 2];
        System.arraycopy(arr, 0, holder, 0, arr.length);
        System.arraycopy(arr, 0, holder, arr.length, arr.length);
//        print(holder, 0, holder.length);
        int n = holder.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], arr.length);
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = i; j >= 0; j--)
                dp[i][j] = 0;
        }
        for (int i = 0; i < dp.length - 1; i++) {
            if (holder[i] != holder[i + 1])
                dp[i][i + 1] = 0;
        }
        int[][] trace = new int[n][n];
        for (int len = 2; len <= arr.length; len++) {
            for (int i = 0; i < holder.length - len && i < arr.length; i++) {
                for (int k = i; k <= i + len - 1; k++) {
                    int orig = dp[i][i + len - 1];
                    if (k < i + len - 1 && holder[k] != holder[k + 1]) {
                        dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], dp[i][k] + dp[k + 1][i + len - 1]);
                    } else {
                        if (k == 0) {
                            dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], 1 + dp[1][i + len - 1]);
                        } else if (k == i + len - 1) {
                            dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], dp[i][i + len - 2] + 1);
                        } else {
                            dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], 1 + dp[i][k - 1] + dp[k + 1][i + len - 1]);
                        }
                    }
                    if (orig != dp[i][i + len - 1]) {
                        trace[i][i + len - 1] = k;
                    }
                }
            }
        }
//        printDp(dp);
//        System.out.println();
//        printDp(trace);
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            ret = Math.min(ret, dp[i][i + arr.length - 1] + (holder[i] != holder[i + arr.length - 1]? 0 : 1));
        }
        return ret;
    }

    public static void main(String[] args) {
        String str = "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";
        System.out.println(str.length());
        System.out.println(new GearsDiv2DP().getmin(str));
    }
}
