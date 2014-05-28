import java.util.Scanner;

public class Main {
    private static final boolean DEBUG = true;

    private static int getIdx(char c) {
        switch (c) {
        case 'A':
            return 0;
        case 'C':
            return 1;
        case 'G':
            return 2;
        case 'T':
            return 3;
        default:
            return 4;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nMax = 101;
        int[][] map = new int[5][];
        map[0] = new int[] {5, -1, -2, -1, -3};
        map[1] = new int[] {-1, 5, -3, -2, -4};
        map[2] = new int[] {-2, -3, 5, -2, -2};
        map[3] = new int[] {-1, -2, -2, 5, -1};
        map[4] = new int[] {-3, -4, -2, -1, Integer.MIN_VALUE};

        int T = scan.nextInt();
        scan.nextLine();
        int[][] dp = new int[nMax][nMax];
        while (T-- > 0) {
            String line = scan.nextLine();
            String[] tmp = line.split(" ");
            int len1 = Integer.valueOf(tmp[0]);
            String gen1 = tmp[1];
            line = scan.nextLine();
            tmp = line.split(" ");
            int len2 = Integer.valueOf(tmp[0]);
            String gen2 = tmp[1];
            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    dp[i][j] = 0;
                }
            }

            for (int i = 1; i <= len1; i++) {
                char c = gen1.charAt(i - 1);
                dp[i][0] = dp[i - 1][0] + map[getIdx(c)][getIdx('-')];
            }

            for (int j = 1; j <= len2; j++) {
                char c = gen2.charAt(j - 1);
                dp[0][j] = dp[0][j - 1] + map[getIdx('-')][getIdx(c)];
            }


            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    char c1 = gen1.charAt(i - 1);
                    char c2 = gen2.charAt(j - 1);
                    int cmp1 = dp[i - 1][j - 1] + map[getIdx(c1)][getIdx(c2)];
                    int cmp2 = dp[i - 1][j] + map[getIdx(c1)][getIdx('-')];
                    int cmp3 = dp[i][j - 1] + map[getIdx('-')][getIdx(c2)];
                    dp[i][j] = Math.max(Math.max(cmp1, cmp2), cmp3);
                }
            }

            System.out.println(dp[len1][len2]);
        }
    }
}
