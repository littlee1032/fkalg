import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Main {
    private static final boolean DEBUG = false;

    private static boolean bfs(boolean[][] rels, int idx, int[] groups, int groupId, boolean isFirst) {
        groups[idx] = groupId;
        int id = isFirst ? groupId + 1 : groupId - 1;
        for (int i = 0; i < rels.length; i++) {
            if (rels[idx][i] && groups[i] != -1 && groups[i] != id) {
                return false;
            }
        }
        for (int i = 0; i < rels.length; i++) {
            if (rels[idx][i] && groups[i] == -1) {
                if (!bfs(rels, i, groups, id, !isFirst)) return false;
            }
        }
        return true;
    }

    private static int[] dp(int[][] bags, int[] groups) {
        int bagNum = bags.length;
        int n = groups.length;
        int bias = 125;
        boolean[][] dp = new boolean[bagNum][2 * bias + 1];
        for (int i = 0; i < bagNum; i++) {
            Arrays.fill(dp[i], false);
        }

        dp[0][bags[0][0] - bags[0][1] + bias] = true;
        dp[0][bags[0][1] - bags[0][0] + bias] = true;
        int pdiff = bags[0][0] - bags[0][1];
        if (pdiff < 0) {
            pdiff *= -1;
        }
        int diff = 0;
        for (int i = 1; i < bagNum; i++) {
            diff += pdiff;
            for (int j = bias - diff; j <= bias + diff; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j + bags[i][0] - bags[i][1]] = true;
                    dp[i][j - bags[i][0] + bags[i][1]] = true;
                }
            }
            pdiff = bags[i][0] - bags[i][1];
            if (pdiff < 0) pdiff *= -1;
        }

        int minIdx = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 2 * bias + 1; i++) {
            if (dp[bagNum - 1][i]) {
                if (Math.abs(i - bias) < min) {
                    min = Math.abs(i - bias);
                    minIdx = i;
                }
            }
        }

        if (DEBUG) System.out.println("min: " + min);
        if (DEBUG) System.out.println("minIdx: " + minIdx);

        int finalGroupId = 2 * bagNum;
        int[] ret = new int[]{0, 0};
        Set<Integer> team1 = new HashSet<Integer>();
        Set<Integer> team2 = new HashSet<Integer>();
        for (int i = bagNum - 1; i > 0; i--) {
            int groupId = i * 2;
            if (dp[i - 1][minIdx - bags[i][0] + bags[i][1]]) {
                minIdx -= bags[i][0] - bags[i][1];
                team1.add(groupId);
                team2.add(groupId + 1);
            } else {
                minIdx -= bags[i][1] - bags[i][0];
                team1.add(groupId + 1);
                team2.add(groupId);
            }
        }
        if (minIdx == bags[0][0] - bags[0][1] + bias) {
            team1.add(0);
            team2.add(1);
        } else {
            team1.add(1);
            team2.add(0);
        }

        for (int i = 0; i < n; i++) {
            if (team1.contains(groups[i])) {
                groups[i] = finalGroupId;
                ret[0]++;
            } else {
                groups[i] = finalGroupId + 1;
                ret[1]++;
            }
        }
        if (DEBUG) {
            for (int i = 0; i < n; i++) {
                System.out.print(groups[i] + " ");
            }
            System.out.println();
        }
        return ret;
    }

    private static void printRels(boolean[][] rels, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(rels[i][j] ? 1 : 0);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        boolean[][] rels = new boolean[n][n];
        for (int i = 1; i <= n; i++) {
            int myFriend = scan.nextInt();
            while (myFriend != 0) {
                rels[i - 1][myFriend - 1] = true;
                myFriend = scan.nextInt();
            }
        }

        if (DEBUG) {
            System.out.println("Before:");
            printRels(rels, n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    if (rels[i][j] && rels[j][i]) {
                        rels[i][j] = false;
                        rels[j][i] = false;
                    } else {
                        rels[i][j] = true;
                        rels[j][i] = true;
                    }
                } else {
                    rels[i][j] = false;
                }
            }
        }

        if (DEBUG) {
            System.out.println("After: ");
            printRels(rels, n);
        }

        int[] groups = new int[n];
        Arrays.fill(groups, -1);

        int groupId = 0;
        for (int i = 0; i < n; i++) {
            if (groups[i] == -1) {
                if (!bfs(rels, i, groups, groupId, true)) {
                    System.out.println("No solution");
                    return;
                }
                groupId += 2;
            }
        }

        int bagNum = groupId / 2;
        int[][] bags = new int[bagNum][2];
        for (int i = 0; i < bagNum; i++) {
            Arrays.fill(bags[i], 0);
        }
        for (int i = 0; i < n; i++) {
            groupId = groups[i];
            int p = groupId / 2;
            int q = groupId % 2;
            bags[p][q]++;
        }

        int[] nums = dp(bags, groups);
        for (int i = 0; i < 2; i++) {
            int num = nums[i];
            System.out.print(num + " ");
            for (int j = 0; j < n; j++) {
                if (groups[j] == bagNum * 2 + i) {
                    System.out.print(j + 1);
                    num--;
                    if (num != 0) System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
