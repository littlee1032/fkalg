import java.util.Scanner;

public class Main {
    private static long[][] arr;
    private static int n;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int code = scan.nextInt();

        while (code != 3) {
            switch(code) {
            case 0:
                n = scan.nextInt();
                arr = new long[n+1][n+1];
                break;
            case 1:
                update(scan.nextInt()+1, scan.nextInt()+1, scan.nextInt());
                break;
            case 2:
                long ans = query(scan.nextInt()+1, scan.nextInt()+1, scan.nextInt()+1, scan.nextInt()+1);
                System.out.println(ans);
                break;
            default:
            }
            code = scan.nextInt();
        }
    }

    private static int lowbit(int num) {
        return num & (-num);
    }

    private static void update(int x, int y, int val) {
        for (int i = x; i <= n; i += lowbit(i)) {
            for (int j = y; j <= n; j += lowbit(j)) {
                arr[i][j] += val;
            }
        }
    }

    private static long query(int x, int y) {
        long ret = 0L;
        for (int i = x; i >= 1; i -= lowbit(i)) {
            for (int j = y; j >= 1; j -= lowbit(j)) {
                ret += arr[i][j];
            }
        }
        return ret;
    }

    private static long query(int left, int bottom, int right, int top) {
        return query(right, top) - query(left-1, top) - query(right, bottom-1)
            + query(left-1, bottom-1);
    }
}
