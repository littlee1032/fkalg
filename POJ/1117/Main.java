import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Main {
    private static final int[] tens = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000,
                                                10000000, 100000000, 1000000000, 1000000001};

    private static boolean isOK(int cand, int target) {
        int len = 0;
        int tmp = cand;
        while (tmp > 0) {
            len++;
            tmp /= 10;
        }

        for (int i = 0; i < len; i++) {
            int newCand = cand / tens[i + 1] * tens[i] + cand % tens[i];
            if (newCand + cand == target) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int count = 0;

        List<Integer> cands = new LinkedList<Integer>();
        for (int i = N / 2; i <= N; i++) {
            int lastNum = i % 10;
            int mod = 11;
            int num = i / 10;
            int target = N;
            int diff = lastNum;
            int zeros = 1;
            while (num > 0) {
                if (i == 155) {
                    System.out.println(lastNum + ", " + mod + ", " + num + ", " + target + ", " + diff + ", " + zeros);
                }
                if ((target - diff) % mod == 0) {
                    count++;
                    cands.add(i);
                    System.out.println(i);
                    break;
                }
                diff += lastNum * zeros;
                lastNum = num % 10;
                zeros *= 10;
                diff += lastNum * zeros;
                num /= 10;
                mod *= 10;
            }
        }

        System.out.println(count);
        for (Integer cand : cands) {
            String aCandStr = String.valueOf(N - cand);
            String candStr = String.valueOf(cand);
            while (aCandStr.length() != candStr.length() - 1) {
                aCandStr = "0" + aCandStr;
            }
            System.out.println(candStr + " + "  + aCandStr + " = " + N);
        }
    }
}
