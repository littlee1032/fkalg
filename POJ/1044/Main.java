import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nMax = 21;
        int[] top = new int[nMax];
        int[] inc = new int[nMax];
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int caseNum = 0;
        while (n != 0) {
            caseNum++;
            for (int i = 1; i <= n; i++) {
                top[i] = scan.nextInt();
                int start = scan.nextInt();
                int end = scan.nextInt();
                inc[i] = end - start;
                while (top[i] - inc[i] >= start) {
                    top[i] -= inc[i];
                }
            }
            while (true) {
                int min = Integer.MAX_VALUE;
                int minIdx = 0;
                int minSame = 0;
                for (int i = 1; i <= n; i++) {
                    if (top[i] < min) {
                        minIdx = i;
                        min = top[i];
                    } else if (top[i] == min) {
                        minSame++;
                    }
                }
                if (min >= 10000) {
                    System.out.println("Case #" + caseNum + ":");
                    System.out.println("Unknown bugs detected.");
                    System.out.println();
                    break;
                } else if (minSame == n - 1) {
                    // found
                    System.out.println("Case #" + caseNum + ":");
                    System.out.println("The actual year is " + min + ".");
                    System.out.println();
                    break;
                } else {
                    top[minIdx] += inc[minIdx];
                }
            }
            n = scan.nextInt();
        }
    }
}
