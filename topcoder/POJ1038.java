import java.util.Arrays;
import java.util.Scanner;

public class POJ1038 {
    private static final int[] threes = new int[]{1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049};

    private static int getProfile(int[] profile) {
        int ret = 0;
        for (int i = 0; i < profile.length; i++) {
            ret += profile[i] * threes[i];
        }
        return ret;
    }

    private static void backProfile(int p, int[] profile) {
        for (int i = 0; i < profile.length; i++) {
            profile[i] = p % 3;
            p /= 3;
        }
    }
    
    private static int fillThreeOrder(int orig, int pos, int value) {
        int diff = threes[pos];
        int num = orig / diff;
        int left = num % 3;
        if (left + value > 2) {
            value = 2 - left;
        } else if (left + value < 0) {
            value = 0;
        }
        return orig + value * diff;
    }

    private static boolean posIsMarked(int num, int pos) {
        return (num / threes[pos]) % 3 != 0;
    }

    private static void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] arrs = new int[4][60000];
        boolean[][] chip = new boolean[151][11];
        int[] profile = new int[10];
        int D = scan.nextInt();
        while (D > 0) {
            int maxX = scan.nextInt();
            int maxY = scan.nextInt();
            for (int i = 0; i < maxX + 1; i++) {
                Arrays.fill(chip[i], true);
            }
            int badNum = scan.nextInt();
            while (badNum > 0) {
                int badX = scan.nextInt();
                int badY = scan.nextInt();
                chip[badX][badY] = false;
                badNum--;
            }

            for (int i = 0; i < 4; i++) {
                Arrays.fill(arrs[i], 0);
            }
            Arrays.fill(profile, 0);
            int profileNum = threes[maxY];

            // compute
            int result = 0;
            int cur = 0;
            for (int i = 2; i <= maxX; i++) {
                int bias = 0;
                for (int j = 1; j <= maxY; j++) {
                    cur++;
                    int prev1 = (cur - 1) % 4;
                    int current = cur % 4;
                    if (!chip[i][j]) {
                        System.arraycopy(arrs[prev1], 0, arrs[current], 0, profileNum);
                    } else {
                        for (int p = 0; p < profileNum; p++) {
                            backProfile(p, profile);
                            int max = arrs[prev1][p];
                            if (profile[maxY - j] != 0) {
                                profile[maxY - j]--;
                                max = arrs[prev1][getProfile(profile)];
                                profile[maxY - j]++;
                            }
                            // 2 * 3
                            if (i >= 2 && j >= 3) {
                                boolean f = true;
                                for (int ii = i; ii > i - 2; ii--) {
                                    for (int jj = j; f && jj > j - 3; jj--) {
                                        if (!chip[ii][jj] || profile[maxY - jj] != 0) {
                                            f = false;
                                            break;
                                        }
                                    }
                                }
                                // could try
                                if (f) {
                                    int prev3 = (cur - 3) % 4;
                                    for (int jj = j - 2; jj <= j; jj++) {
                                        profile[maxY - jj] = 1;
                                    }
                                    int cmp = arrs[prev3][getProfile(profile)] + 1;
                                    if (cmp > max) {
                                        max = cmp;
                                    }
                                    for (int jj = j - 2; jj <= j; jj++) {
                                        profile[maxY - jj] = 0;
                                    }
                                }
                            }
                            // 3 * 2
                            if (i >= 3 && j >= 2) {
                                boolean f = true;
                                for (int ii = i; ii > i - 3; ii--) {
                                    for (int jj = j; f && jj > j - 2; jj--) {
                                        if (!chip[ii][jj] || profile[maxY - jj] != 0) {
                                            f = false;
                                            break;
                                        }
                                    }
                                }
                                // could try
                                if (f) {
                                    int prev2 = (cur - 2) % 4;
                                    for (int jj = j - 1; jj <= j; jj++) {
                                        profile[maxY - jj] = 2;
                                    }
                                    int cmp = arrs[prev2][getProfile(profile)] + 1;
                                    if (cmp > max) {
                                        max = cmp;
                                    }
                                }
                            }
                            arrs[current][p] = max;
                        }
                    }
                    result = arrs[current][0];
                }
            }
            System.out.println(result);
            D--;
        }
    }
}
