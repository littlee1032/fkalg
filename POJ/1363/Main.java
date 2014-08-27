import java.util.Scanner;

public class Main {
    private static final int MAX = 1010;
    private static final int[] target = new int[MAX];
    private static final int[] stack = new int[MAX];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n > 0) {
            while (true) {
                for (int i = 0; i < n; i++) {
                    target[i] = scan.nextInt();
                    if (target[0] == 0) {
                        break;
                    }
                }
                if (target[0] == 0) break;
                else {
                    System.out.println(judge(n));
                }
            }
            System.out.println();
            n = scan.nextInt();
        }
    }

    private static String judge(int n) {
        int targetIdx = 0;
        int round = 1;
        int top = -1;
        while (targetIdx < n) {
            if (top >= 0 && stack[top] == target[targetIdx]) {
                top--;
                targetIdx++;
            } else if (top < 0 && target[targetIdx] == round) {
                targetIdx++;
                round++;
                continue;
            } else {
                stack[++top] = round;
                round++;
                if (top >= n) break;
            }
        }
        return top == -1 ? "Yes" : "No";
    }
}
