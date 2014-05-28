import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static int[] sumT;
    private static int[] sumF;
    private static int[] dp;
    private static int s;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        s = scan.nextInt();

        sumT = new int[n+2];
        sumF = new int[n+2];
        for (int i = 1; i <= n; i++) {
            sumT[i] = scan.nextInt();
            sumF[i] = scan.nextInt();
        }

        for (int i = n; i >= 1; i--) {
            sumT[i] += sumT[i+1];
            sumF[i] += sumF[i+1];
        }

        List<Integer> queue = new LinkedList<Integer>();
        queue.add(n+1);
        dp = new int[n+2];
        for (int i = n; i >= 1; i--) {
            while(queue.size() >= 2) {
                int first = queue.get(0);
                int second = queue.get(1);
                if (getRatio(first, second) <= sumF[i]) {
                    queue.remove(0);
                } else {
                    break;
                }
            }
            dp[i] = getDp(i, queue.get(0));
            while (queue.size() > 2) {
                int qSize = queue.size();
                int last = queue.get(qSize - 1);
                int secLast = queue.get(qSize - 2);
                if (getRatio(i, secLast) <= getRatio(secLast, last)) {
                    queue.remove(qSize-1);
                } else {
                    break;
                }
            }
            queue.add(i);
        }
        System.out.println(dp[1]);
    }

    private static int getDp(int i, int j) {
        return dp[j] + (s + sumT[i] - sumT[j])*sumF[i];
    }

    private static int getRatio(int i, int j) {
        return (dp[i] - dp[j]) / (sumT[i] -sumT[j]);
    }
}
