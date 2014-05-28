import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int l = scan.nextInt();
        int r = scan.nextInt();
        int ql = scan.nextInt();
        int rl = scan.nextInt();

        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            ws[i] = scan.nextInt();
        }

        long[] lws = new long[n + 1];
        long[] rws = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            lws[i] = lws[i-1] + 1L * ws[i-1] * l;
        }
        for (int i = n; i > 0; i--) {
            rws[i-1] = rws[i] + 1L * ws[i-1] * r;
        }

        //System.out.println(Arrays.toString(lws));
        //System.out.println(Arrays.toString(rws));
        Long tot = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // try left hands
            long t = lws[i + 1] + rws[i + 1];
            int leftHands = i + 1;
            int rightHands = n - 1 - i;
            if (leftHands > rightHands) {
                if (rightHands > 0)
                    t += 1L * ql * (leftHands - rightHands - 1);
                else
                    t += 1L * ql * (leftHands - 1);
            } else if (leftHands < rightHands) {
                if (leftHands > 0)
                    t += 1L * rl * (rightHands - leftHands - 1);
                else
                    t += 1L * rl * (rightHands - 1);
            }
            //System.out.println(leftHands + ", " + rightHands);
            //System.out.println(t);
            tot = Math.min(tot, t);
            t = lws[i] + rws[i];
            leftHands = i;
            rightHands = n - i;
            if (leftHands > rightHands) {
                if (rightHands > 0)
                    t += 1L * ql * (leftHands - rightHands - 1);
                else
                    t += 1L * ql * (leftHands - 1);
            } else if (leftHands < rightHands) {
                if (leftHands > 0)
                    t += 1L * rl * (rightHands - leftHands - 1);
                else
                    t += 1L * rl * (rightHands - 1);
            }
            //System.out.println(leftHands + ", " + rightHands);
            //System.out.println(t);
            tot = Math.min(tot, t);
        }

        System.out.println(tot);
    }
}
