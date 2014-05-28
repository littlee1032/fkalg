import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[] dp;
    private static int n;

    private static void printDp() {
        int start = 0;
        for (int i = 0; i < n; i++) {
            int[] newArray = new int[n];
            System.arraycopy(dp, start, newArray, i, n-i);
            System.out.println(Arrays.toString(newArray));
            start += n - i;
        }
    }

    private static int getIdx(int x, int y) {
        return x * n - (x-1)*x/2 + (y-x);
    }

    private static int getDp(int x, int y) {
        return dp[getIdx(x, y)];
    }

    private static void setDp(int x, int y, int v) {
        dp[getIdx(x, y)] = v;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        scan.nextLine();
        String str = scan.nextLine();
        char[] c = str.toCharArray();

        int MAX = 1000000;

        dp = new int[n * (n+1) / 2];
        Arrays.fill(dp, MAX);
        for (int i = 0; i < n; i++) setDp(i, i, 0);
        for (int len = 2; len <= n; len++) {
            for (int s = 0; s + len - 1 < n; s++) {
                //printDp();
                setDp(s, s+len-1, Math.min(getDp(s, s+len-1), 1+getDp(s+1, s+len-1)));
                setDp(s, s+len-1, Math.min(getDp(s, s+len-1), 1+getDp(s, s+len-2)));
                if (c[s] == c[s+len-1]) {
                    if (s+1 <= s + len - 2) {
                        setDp(s, s+len-1, Math.min(getDp(s, s+len-1), getDp(s+1, s+len-2)));
                    } else {
                        setDp(s, s+len-1, 0);
                    }
                }
            }
        }
        System.out.println(getDp(0, n-1));
    }
}
