import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Main {
    private static int[][] offers = new int[5][3];
    private static int[][] specials = new int[100][12];
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    private static int[] dp;
    private static int[] masks;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int b = scan.nextInt();
        if (b == 0) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < b; i++) {
            for (int j = 0; j < 3; j++) {
                offers[i][j] = scan.nextInt();
            }
            map.put(offers[i][0], i);
        }

        //for (int i = 0; i < b; i++) System.out.println(Arrays.toString(offers[i]));

        int s = scan.nextInt();
        for (int i = 0; i < s; i++) {
            int num = scan.nextInt();
            specials[i][0] = num;
            for (int j = 0; j < num; j++) {
                specials[i][2*j+1] = scan.nextInt();
                specials[i][2*j+2] = scan.nextInt();
            }
            specials[i][2*num+1] = scan.nextInt();
        }

        //for (int i = 0; i < s; i++) System.out.println(Arrays.toString(specials[i]));

        masks = new int[b];
        masks[0] = 1;
        for (int i = 0; i < b - 1; i++) {
            masks[i+1] = masks[i] * (offers[i][1] + 1);
        }

        //System.out.println("masks: " + Arrays.toString(masks));

        dp = new int[masks[b-1] * (offers[b-1][1] + 1)];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] cur = new int[b];
        for (int i = 1; i < dp.length; i++) {
            //System.out.println(i + " : " + Arrays.toString(dp));
            for (int j = 0; j < b; j++) {
                cur[j] = (i / masks[j]) % (offers[j][1] + 1);
            }
            //System.out.println("cur: " + Arrays.toString(cur));
            int v = 0;
            for (int j = 0; j < b; j++) {
                v += cur[j] * offers[j][2];
            }
            dp[i] = Math.min(dp[i], v);
            for (int j = 0; j < s; j++) {
                int num = specials[j][0];
                boolean apply = true;
                int pre = i;
                for (int k = 0; k < num; k++) {
                    int pc = specials[j][2*k+1];
                    int numNeed = specials[j][2*k+2];
                    int idx = map.get(pc);
                    if (cur[idx] < numNeed) {
                        apply = false;
                        break;
                    } else {
                        pre -= numNeed * masks[idx];
                    }
                }
                if (apply) {
                    dp[i] = Math.min(dp[i], dp[pre] + specials[j][2*num+1]);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}
